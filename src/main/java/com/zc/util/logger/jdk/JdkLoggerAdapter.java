package com.zc.util.logger.jdk;

import com.zc.util.logger.LoggerAdapter;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.logging.*;

public class JdkLoggerAdapter implements LoggerAdapter {
    private static final String GLOBAL_LOGGER_NAME = "global";
    private File file;

    public JdkLoggerAdapter() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
            if (in != null) {
                LogManager.getLogManager().readConfiguration(in);
            } else {
                System.err.println("No such logging.properties in classpath for jdk logging config!");
            }
        } catch (Throwable var9) {
            System.err.println("Failed to load logging.properties in classpath for jdk logging config, cause: " + var9.getMessage());
        }

        try {
            Handler[] handlers = Logger.getLogger("global").getHandlers();
            Handler[] arr$ = handlers;
            int len$ = handlers.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Handler handler = arr$[i$];
                if (handler instanceof FileHandler) {
                    FileHandler fileHandler = (FileHandler)handler;
                    Field field = fileHandler.getClass().getField("files");
                    File[] files = (File[])((File[])field.get(fileHandler));
                    if (files != null && files.length > 0) {
                        this.file = files[0];
                    }
                }
            }
        } catch (Throwable var10) {
            ;
        }

    }

    private static Level toJdkLevel(com.zc.util.logger.Level level) {
        if (level == com.zc.util.logger.Level.ALL) {
            return Level.ALL;
        } else if (level == com.zc.util.logger.Level.TRACE) {
            return Level.FINER;
        } else if (level == com.zc.util.logger.Level.DEBUG) {
            return Level.FINE;
        } else if (level == com.zc.util.logger.Level.INFO) {
            return Level.INFO;
        } else if (level == com.zc.util.logger.Level.WARN) {
            return Level.WARNING;
        } else {
            return level == com.zc.util.logger.Level.ERROR ? Level.SEVERE : Level.OFF;
        }
    }

    private static com.zc.util.logger.Level fromJdkLevel(Level level) {
        if (level == Level.ALL) {
            return com.zc.util.logger.Level.ALL;
        } else if (level == Level.FINER) {
            return com.zc.util.logger.Level.TRACE;
        } else if (level == Level.FINE) {
            return com.zc.util.logger.Level.DEBUG;
        } else if (level == Level.INFO) {
            return com.zc.util.logger.Level.INFO;
        } else if (level == Level.WARNING) {
            return com.zc.util.logger.Level.WARN;
        } else {
            return level == Level.SEVERE ? com.zc.util.logger.Level.ERROR : com.zc.util.logger.Level.OFF;
        }
    }

    @Override
    public com.zc.util.logger.Logger getLogger(Class<?> key) {
        return new JdkLogger(Logger.getLogger(key == null ? "" : key.getName()));
    }

    @Override
    public com.zc.util.logger.Logger getLogger(String key) {
        return new JdkLogger(Logger.getLogger(key));
    }

    @Override
    public com.zc.util.logger.Level getLevel() {
        return fromJdkLevel(Logger.getLogger("global").getLevel());
    }

    @Override
    public void setLevel(com.zc.util.logger.Level level) {
        Logger.getLogger("global").setLevel(toJdkLevel(level));
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void setFile(File file) {
    }

}
