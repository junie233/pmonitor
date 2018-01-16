package com.junie.monitorlib.crash;

import java.util.List;

/**
 * Created by niejun on 2018/1/15.
 */

public class CrashInfo {

    private String pid;
    private long time;
    private String applicationName;
    private List<ThreadInfo> threadInfoList;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public List<ThreadInfo> getThreadInfoList() {
        return threadInfoList;
    }

    public void setThreadInfoList(List<ThreadInfo> threadInfoList) {
        this.threadInfoList = threadInfoList;
    }

    @Override
    public String toString() {

        String threadInfo="";
        if(threadInfoList!=null) {
            threadInfo = "大小：" + threadInfoList.size() + " " + threadInfoList.toString();
        }
        return "CrashInfo{" +
                "pid='" + pid + '\'' +
                ", time=" + time +
                ", applicationName='" + applicationName + '\'' +
                ", threadInfoList=" + threadInfo +
                '}';
    }
}
