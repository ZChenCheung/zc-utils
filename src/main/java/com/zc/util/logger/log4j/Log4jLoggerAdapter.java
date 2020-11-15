package com.zc.util.logger.log4j;

import com.zc.util.logger.Level;
import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerAdapter;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;

import java.io.File;
import java.util.Enumeration;

public class Log4jLoggerAdapter implements LoggerAdapter {
    private File file;

    public Log4jLoggerAdapter() {
        try {
            org.apache.log4j.Logger rootLogger = LogManager.getRootLogger();

            if (rootLogger != null) {
                Enumeration<Appender> appenders = rootLogger.getAllAppenders();
                if (appenders != null) {
                    while(appenders.hasMoreElements()) {
                        Appender appender = (Appender)appenders.nextElement();
                        if (appender instanceof FileAppender) {
                            FileAppender fileAppender = (FileAppender)appender;
                            String filename = fileAppender.getFile();
                            this.file = new File(filename);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static org.apache.log4j.Level toLog4jLevel(Level level) {
        if (level == Level.ALL) {
            return org.apache.log4j.Level.ALL;
        } else if (level == Level.TRACE) {
            return org.apache.log4j.Level.TRACE;
        } else if (level == Level.DEBUG) {
            return org.apache.log4j.Level.DEBUG;
        } else if (level == Level.INFO) {
            return org.apache.log4j.Level.INFO;
        } else if (level == Level.WARN) {
            return org.apache.log4j.Level.WARN;
        } else {
            return level == Level.ERROR ? org.apache.log4j.Level.ERROR : org.apache.log4j.Level.OFF;
        }
    }

    private static Level fromLog4jLevel(org.apache.log4j.Level level) {
        if (level == org.apache.log4j.Level.ALL) {
            return Level.ALL;
        } else if (level == org.apache.log4j.Level.TRACE) {
            return Level.TRACE;
        } else if (level == org.apache.log4j.Level.DEBUG) {
            return Level.DEBUG;
        } else if (level == org.apache.log4j.Level.INFO) {
            return Level.INFO;
        } else if (level == org.apache.log4j.Level.WARN) {
            return Level.WARN;
        } else {
            return level == org.apache.log4j.Level.ERROR ? Level.ERROR : Level.OFF;
        }
    }

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new Log4jLogger(LogManager.getLogger(clazz));
    }

    @Override
    public Logger getLogger(String name) {
        return new Log4jLogger(LogManager.getLogger(name));
    }

    @Override
    public Level getLevel() {
        return fromLog4jLevel(LogManager.getRootLogger().getLevel());
    }

    @Override
    public void setLevel(Level level) {
        LogManager.getRootLogger().setLevel(toLog4jLevel(level));
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void setFile(File var1) {
    }

}
