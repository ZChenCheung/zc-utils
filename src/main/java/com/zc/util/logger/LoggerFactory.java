package com.zc.util.logger;

import com.zc.util.logger.jdk.JdkLoggerAdapter;
import com.zc.util.logger.slf4j.Slf4jLoggerAdapter;
import com.zc.util.logger.support.FailsafeLogger;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerFactory {
    private static final ConcurrentMap<String, FailsafeLogger> LOGGERS = new ConcurrentHashMap();
    private static volatile LoggerAdapter LOGGER_ADAPTER;

    private LoggerFactory() {
    }

    public static void setLoggerAdapter(LoggerAdapter loggerAdapter) {
        if (loggerAdapter != null) {
            Logger logger = loggerAdapter.getLogger(LoggerFactory.class.getName());
            logger.info("using logger: " + loggerAdapter.getClass().getName());
            LOGGER_ADAPTER = loggerAdapter;
            Iterator i$ = LOGGERS.entrySet().iterator();

            while(i$.hasNext()) {
                Map.Entry<String, FailsafeLogger> entry = (Map.Entry)i$.next();
                ((FailsafeLogger)entry.getValue()).setLogger(LOGGER_ADAPTER.getLogger((String)entry.getKey()));
            }
        }

    }

    public static Logger getLogger(Class<?> key) {
        FailsafeLogger logger = (FailsafeLogger)LOGGERS.get(key.getName());
        if (logger == null) {
            LOGGERS.putIfAbsent(key.getName(), new FailsafeLogger(LOGGER_ADAPTER.getLogger(key)));
            logger = (FailsafeLogger)LOGGERS.get(key.getName());
        }

        return logger;
    }

    public static Logger getLogger(String key) {
        FailsafeLogger logger = (FailsafeLogger)LOGGERS.get(key);
        if (logger == null) {
            LOGGERS.putIfAbsent(key, new FailsafeLogger(LOGGER_ADAPTER.getLogger(key)));
            logger = (FailsafeLogger)LOGGERS.get(key);
        }

        return logger;
    }

    public static Level getLevel() {
        return LOGGER_ADAPTER.getLevel();
    }

    public static File getFile() {
        return LOGGER_ADAPTER.getFile();
    }

    static {
        String logger = System.getProperty("zc.conf.logger");
        if ("slf4j".equals(logger)) {
            setLoggerAdapter((LoggerAdapter)(new Slf4jLoggerAdapter()));
        } else if ("log4j".equals(logger)) {
            // TODO
        } else if ("jdk".equals(logger)) {
            setLoggerAdapter((LoggerAdapter)(new JdkLoggerAdapter()));
        } else {
            try {
                setLoggerAdapter((LoggerAdapter)(new Slf4jLoggerAdapter()));
            } catch (Throwable var6) {
                // TODO
                setLoggerAdapter((LoggerAdapter)(new JdkLoggerAdapter()));
            }
        }

    }

}
