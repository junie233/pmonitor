package com.junie.monitorlib.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by niejun on 2018/1/15.
 */

public class ProcessUtils {
    private  static String TAG = ProcessUtils.class.getSimpleName();


    /**
     * ANR 时返回进程信息
     * @param context 上下文
     * @return
     */
    public static ActivityManager.ProcessErrorStateInfo getAnrProcessState(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager != null) {
                List<ActivityManager.ProcessErrorStateInfo> processErrorStateInfoList = activityManager.getProcessesInErrorState();
                if (processErrorStateInfoList != null) {
                    for (ActivityManager.ProcessErrorStateInfo errorStateInfo : processErrorStateInfoList) {
                        if (errorStateInfo.condition == ActivityManager.ProcessErrorStateInfo.NOT_RESPONDING) {
                            return errorStateInfo;
                        }

                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG,"",e);
        }
        return null;
    }





}
