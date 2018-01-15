package com.junie.monitorlib.crash;

import android.util.Log;

/**
 * Created by niejun on 2018/1/10.
 */

public class JavaCrashHandler implements Thread.UncaughtExceptionHandler{

    private static String TAG = JavaCrashHandler.class.getSimpleName();


    private static JavaCrashHandler instance;


    public static JavaCrashHandler getInstance(){
        if (instance == null) {
            synchronized (JavaCrashHandler.class){
                if (instance == null) {
                    instance = new JavaCrashHandler() ;
                }
            }
        }
        return instance ;
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        try {
            //保存异常
            saveCrash(thread,throwable);
            //打印
            Log.e(TAG,"Thread : "+thread.getName(),throwable);
            //处理异常
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception e) {
            Log.e(TAG,"",e);
        }

    }


    /**
     * 保存异常
     * @param thread 线程
     * @param throwable exc
     */
    private void saveCrash(Thread thread,Throwable throwable) {

    }
}
