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
    }


    private void monitorAnr() {
         fileObserver = new FileObserver("/data/anr/", FileObserver.CLOSE_WRITE) {
            @Override
            public synchronized void onEvent(int event, String path) {
                handleTrace(event,path);
            }
        };
    }

    private void handleTrace(int event, final String path) {
        if(TextUtils.isEmpty(path)) {
            return;
        }
        if(!path.contains("trace")) {
            return;
        }
        ActivityManager.ProcessErrorStateInfo errorStateInfo = ProcessUtils.getAnrProcessState(context);
        Log.d(TAG,"errorStateInfo:" + errorStateInfo.crashData.toString());
        //开启线程解析异常
        parseHandler.post(new Runnable() {
            @Override
            public void run() {
                TraceFileParser.parse(path);
            }
        });
    }


}
