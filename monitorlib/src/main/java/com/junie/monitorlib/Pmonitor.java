package com.junie.monitorlib;

import android.os.Looper;

import com.junie.monitorlib.crash.CrashHandler;
import com.junie.monitorlib.listener.LogPrinterListener;
import com.junie.monitorlib.printer.LogPrinter;

/**
 * Created by niejun on 2018/1/10.
 */

public class Pmonitor {




    public static void startMonitor(LogPrinterListener listener) {
        Looper.getMainLooper().setMessageLogging(new LogPrinter(listener));
    }

    public static void startMonitor() {
        startMonitor(new LogPrinterListener() {
            @Override
            public void startLooper() {

            }

            @Override
            public void endLooper() {

            }

            @Override
            public void lagging(int level) {

            }
        });
        CrashHandler.getInstance().init();
    }

    public static void stopMonitor() {
        Looper.getMainLooper().setMessageLogging(null);
    }


    //FIXME 前置确认Monitor 开启了
    public static void testLag() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testCrash() {
        LogPrinter logPrinter = null;
        logPrinter.println("testCrash");
    }

    public static void testAnr() {

    }


}
