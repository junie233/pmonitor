package com.junie.monitorlib.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by niejun on 2018/1/16.
 */

public class LogcatUtils {

    private static String TAG = LogcatUtils.class.getSimpleName();
    private static String  log_cmd = "logcat -d -v time -t 1000 *:V";
    private static int MAX_SIZE = 2000;


    /**
     * 获取最近log日志
     * @return 日志
     */
    public static String getRecentLogcat() {
        String logStr = "";
        try {
            Process process = Runtime.getRuntime().exec(log_cmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //清理日志，如果你这里做了sout，那么你输出的内容也会被记录，就会出现问题
                logStr = line + "\n" + logStr;
                if(logStr.length() > MAX_SIZE) {
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG,"",e);
        }
        return logStr;
    }

}
