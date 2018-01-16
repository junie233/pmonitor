package com.junie.monitorlib.crash;

/**
 * Created by niejun on 2018/1/15.
 */

public class ThreadInfo {

    private String threadName;
    private String prio;
    private String tid;
    private String condition;
    private String stackInfo;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStackInfo() {
        return stackInfo;
    }

    public void setStackInfo(String stackInfo) {
        this.stackInfo = stackInfo;
    }


    @Override
    public String toString() {
        return "ThreadInfo{" +
                "threadName='" + threadName + '\'' +
                ", prio='" + prio + '\'' +
                ", tid='" + tid + '\'' +
                ", condition='" + condition + '\'' +
                ", stackInfo='" + stackInfo + '\'' +
                '}';
    }
}
