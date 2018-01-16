package com.junie.monitorlib.crash;

import android.content.Context;

/**
 * Created by niejun on 2018/1/15.
 */

public class CrashHandler {
    private JavaCrashHandler javaCrashHandler;
    private NativeCrashHandler nativeCrashHandler;
    private AnrCrashHandler anrCrashHandler;
    private Context context;

    private CrashHandler(Context context) {
        this.context = context;
        javaCrashHandler = new JavaCrashHandler();
        nativeCrashHandler = new NativeCrashHandler();
        anrCrashHandler = new AnrCrashHandler();
    }

    public void startMonitor() {
        anrCrashHandler.init(context);
    }

    private static CrashHandler instance;



    public static CrashHandler getInstance(Context context){
        if (instance == null) {
            synchronized (CrashHandler.class){
                if (instance == null) {
                    instance = new CrashHandler(context) ;
                }
            }
        }
        return instance ;
    }

}
