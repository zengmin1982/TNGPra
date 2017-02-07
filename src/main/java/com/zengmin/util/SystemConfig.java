package com.zengmin.util;

/**
 * 系统配置 *
 */
public abstract class SystemConfig {

    public static String TESTTHREADNUM;

    static {
        try {
            TESTTHREADNUM = SpringPropertyResourceReader.getProperty("test.thread.total");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
