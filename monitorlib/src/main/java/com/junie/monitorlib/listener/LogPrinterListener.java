package com.junie.monitorlib.listener;

/**
 * Created by niejun on 2018/1/10.
 * 卡顿检测回调
 */

public interface LogPrinterListener {

    /**
     * 开始处理事务
     */
    void startLooper();


    /**
     * 结束事务处理
     */
    void endLooper();


    /**
     * 发生了卡顿
     * @param level  卡顿等级 {@link com.junie.monitorlib.config.LaggingWaringLevel}
     */
    void lagging(int level);


}
