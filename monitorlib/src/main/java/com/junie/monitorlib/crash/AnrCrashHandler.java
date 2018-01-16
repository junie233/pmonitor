package com.junie.monitorlib.crash;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.junie.monitorlib.parser.TraceFileParser;
import com.junie.monitorlib.util.ProcessUtils;

/**
 * Created by niejun on 2018/1/15.
 */

public class AnrCrashHandler {

    private static String TAG = AnrCrashHandler.class.getSimpleName();

    private FileObserver fileObserver;
    private Handler parseHandler;
    private TraceFileParser traceFileParser;
    private Context context;

    public void init(Context context) {
        this.context = context;
        parseHandler = new Handler();
        traceFileParser = new TraceFileParser();
        monitorAnr();
    }


    private void monitorAnr() {
        Log.d(TAG,"开始监控");
        fileObserver = new FileObserver("/data/anr/", FileObserver.CLOSE_WRITE) {
            @Override
            public synchronized void onEvent(int event, String path) {
                Log.d(TAG,"文件发生变化:"+path);
                handleTrace(event, "/data/anr/"+path);
            }
        };
        fileObserver.startWatching();
        handleTrace(1,"/data/anr/"+"traces.txt");
    }

    private void handleTrace(int event, final String path) {
        if(TextUtils.isEmpty(path)) {
            return;
        }
        if(!path.contains("trace")) {
            return;
        }
        Log.d(TAG,"path:" + path);
        //建议保存备份在做处理
        ActivityManager.ProcessErrorStateInfo errorStateInfo = ProcessUtils.getAnrProcessState(context);
        if (errorStateInfo != null) {
            Log.d(TAG, "errorStateInfo:" + errorStateInfo.crashData.toString());
        }
        //开启线程解析异常
        parseHandler.post(new Runnable() {
            @Override
            public void run() {
                CrashInfo crashInfo = TraceFileParser.parse(context,path);
            }
        });
    }


}
