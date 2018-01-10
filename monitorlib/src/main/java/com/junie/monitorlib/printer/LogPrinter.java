package com.junie.monitorlib.printer;

import android.util.Log;
import android.util.Printer;

import com.junie.monitorlib.config.LaggingWaringLevel;
import com.junie.monitorlib.listener.LogPrinterListener;

/**
 * Created by niejun on 2018/1/10.
 */

public class LogPrinter implements Printer{

    private static String TAG = LogPrinter.class.getSimpleName();

    private long startTime;
    private LogPrinterListener logPrinterListener;


    public LogPrinter(LogPrinterListener logPrinterListener) {
        this.logPrinterListener = logPrinterListener;
    }

    @Override
    public void println(String s) {
        if(startTime <= 0 ) {
            //开始工作
            startTime = System.currentTimeMillis();
            logPrinterListener.startLooper();
        } else {
            //结束工作
            long exeTime = System.currentTimeMillis() - startTime;
            startTime = 0;
            //处理是否发生了卡顿
            handleLag(s,exeTime);
            logPrinterListener.endLooper();
        }
    }

    /**
     * 处理事物执行时间判断
     * @param s 打印信息
     * @param exeTime 执行时间
     */
    private void handleLag(String s, long exeTime) {
        if(exeTime > LaggingWaringLevel.LaggingWaringDangrous) {
            Log.w(TAG,"Dangrous lagging : " + s);  //FIXME 开关
            logPrinterListener.lagging(LaggingWaringLevel.LaggingWaringDefault);
        } else if(exeTime > LaggingWaringLevel.LaggingWaringDefault){
            Log.w(TAG,"Lagging : " + s);
            logPrinterListener.lagging(LaggingWaringLevel.LaggingWaringDangrous);
        } else {
            Log.i(TAG,s);
        }
    }


}
