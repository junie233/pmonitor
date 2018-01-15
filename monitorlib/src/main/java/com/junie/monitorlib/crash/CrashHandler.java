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

    private void init() {
        anrCrashHandler.init();
    }


}
