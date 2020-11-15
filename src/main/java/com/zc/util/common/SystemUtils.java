package com.zc.util.common;

public class SystemUtils {

    private SystemUtils() {}

    /**
     * 获取可用JVM的可用的处理器数量
     *
     * @return
     */
    public static int availableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
