package com.zc.util.logger;

public interface Logger {
    void trace(String text);

    void trace(String text, Object... args);

    void trace(Throwable e);

    void trace(Throwable e, String text);

    void trace(Throwable e, String text, Object... args);

    void debug(String text);

    void debug(String text, Object... args);

    void debug(Throwable e);

    void debug(Throwable e, String text);

    void debug(Throwable e, String text, Object... args);

    void info(String text);

    void info(String text, Object... args);

    void info(Throwable e);

    void info(Throwable e, String text);

    void info(Throwable e, String text, Object... args);

    void warn(String text);

    void warn(String text, Object... args);

    void warn(Throwable e);

    void warn(Throwable e, String text);

    void warn(Throwable e, String text, Object... args);

    void error(String text);

    void error(String text, Object... args);

    void error(Throwable e);

    void error(Throwable e, String text);

    void error(Throwable e, String text, Object... args);

    boolean isTraceEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();
}
