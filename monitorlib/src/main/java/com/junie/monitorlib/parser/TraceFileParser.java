package com.junie.monitorlib.parser;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.junie.monitorlib.crash.CrashInfo;
import com.junie.monitorlib.crash.ThreadInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by niejun on 2018/1/15.
 */

public class TraceFileParser {
    private static String TAG = TraceFileParser.class.getSimpleName();

    private static String pattern1 = "-{5}\\spid.*?";
    private static String pattern2 = "Cmd line:.*?";
    private static String pattern3 = ".*prio=.*";

    public static CrashInfo parse(Context context, String path) {
        if(TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        if(!file.exists()) {
            return null;
        }
        CrashInfo crashInfo = new CrashInfo();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            if(!TextUtils.isEmpty(line) && line.matches(pattern1)) {
                //解析进程标示
                String[] processInfo = line.split("\\s+");
                //pid
                crashInfo.setPid(processInfo[1]);
                String time = processInfo[4] +" " +processInfo[5];
                //time
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format.parse(time);
                crashInfo.setTime(date.getTime());

                //applicationId
                line = bufferedReader.readLine();
                if(line.matches(pattern2)) {
                    String[] appId = line.split("\\s+");
                    String applicationId = appId[2];
                    if(!TextUtils.isEmpty(applicationId) && applicationId.equals(context.getApplicationContext().getPackageName())){
                         crashInfo.setApplicationName(applicationId);
                    } else {
                        Log.d(TAG,"not my App Anr");
                        return null;
                    }
                }
                //过滤无用信息
                line = bufferedReader.readLine();
                while( TextUtils.isEmpty(line) || (!TextUtils.isEmpty(line) && !line.matches(pattern3))) {
                    line = bufferedReader.readLine();
                }

                //开始解析各个线程信息
                List<ThreadInfo> threadInfoList = new ArrayList<>();
                while(line.matches(pattern3)) {
                    ThreadInfo threadInfo = new ThreadInfo();
                    String[] threads = line.split("\\s+");
                    threadInfo.setThreadName(threads[0]);
                    if(line.contains("daemon")) {
                        threadInfo.setPrio(threads[2]);
                        threadInfo.setTid(threads[3]);
                        threadInfo.setCondition(threads[4]);
                    } else {
                        threadInfo.setPrio(threads[1]);
                        threadInfo.setTid(threads[2]);
                        threadInfo.setCondition(threads[3]);
                    }
                    //解析堆栈信息
                    StringBuilder stackBuilder = new StringBuilder();
                    while(!TextUtils.isEmpty(line = bufferedReader.readLine())) {
                        if(line.matches(".*\\|.*")) {
                            //注释信息过滤掉
                            continue;
                        }
                        stackBuilder.append(line).append("\n");
                    }
                    threadInfo.setStackInfo(stackBuilder.toString());
                    threadInfoList.add(threadInfo);
                    line = bufferedReader.readLine();
                }
                crashInfo.setThreadInfoList(threadInfoList);
            }
        } catch (Exception e) {
            Log.e(TAG,"",e);
        }
        Log.d(TAG,"anr parse result : " + crashInfo.toString());
        return crashInfo;
    }






}
