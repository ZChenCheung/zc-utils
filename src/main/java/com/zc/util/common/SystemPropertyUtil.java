package com.zc.util.common;

import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerFactory;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class SystemPropertyUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(SystemPropertyUtil.class);

    private SystemPropertyUtil() {}

    public static double getDouble(String key) {
        return StringUtils.doubleValue(get(key));
    }

    public static double getDouble(String key, double defaultValue) {
        return StringUtils.doubleValue(get(key), defaultValue);
    }

    public static long getLong(String key) {
        return StringUtils.longValue(get(key));
    }

    public static long getLong(String key, long defaultValue) {
        return StringUtils.longValue(get(key), defaultValue);
    }

    public static float getFloat(String key) {
        return StringUtils.floatValue(get(key));
    }

    public static float getFloat(String key, float defaultValue) {
        return StringUtils.floatValue(get(key), defaultValue);
    }

    public static short getShort(String key) {
        return StringUtils.shortValue(get(key));
    }

    public static short getShort(String key, short defaultValue) {
        return StringUtils.shortValue(get(key), defaultValue);
    }

    public static int getInt(String key) {
        return StringUtils.intValue(get(key));
    }

    public static int getInt(String key, int defaultValue) {
        return StringUtils.intValue(get(key), defaultValue);
    }

    public static boolean getBoolean(String key) {
        return StringUtils.booleanValue(get(key));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return StringUtils.booleanValue(get(key), defaultValue);
    }

    public static boolean containsKey(String key) {
        return get(key) != null;
    }

    public static String get(String key) {
        return get(key, null);
    }

    public static String get(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException("key");
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty.");
        }

        String value = null;
        try {
            if (System.getSecurityManager() == null) {
                value = System.getProperty(key);
            } else {
                value = AccessController.doPrivileged((PrivilegedAction<String>) () -> System.getProperty(key));
            }
        } catch (SecurityException e) {
            LOGGER.warn("Unable to retrieve a system property '{}'; default values will be used.", key, e);
        }

        return value == null ? defaultValue : value;
    }

}
