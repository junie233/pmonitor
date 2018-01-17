package com.junie.monitorlib.crash;

/**
 * Created by niejun on 2018/1/15.
 */

public class NativeCrashHandler {

    static {
        System.loadLibrary("HelloBreakPad");
    }


    public static native void nativeCrashHandlerInit();





}
