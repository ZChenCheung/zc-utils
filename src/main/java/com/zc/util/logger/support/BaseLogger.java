package com.zc.util.logger.support;

import com.zc.util.logger.Logger;

public abstract class  BaseLogger implements Logger {

    protected abstract String format(String text, Object... args);

    @Override
    public void trace(String text, Object... args) {
        trace(format(text, args));
    }

    @Override
    public void trace(Throwable e, String text, Object... args) {
        trace(e, format(text, args));
    }

    @Override
    public void debug(String text, Object... args) {
        debug(format(text, args));
    }

    @Override
    public void debug(Throwable e, String text, Object... args) {
        debug(e, format(text, args));
    }

    @Override
    public void info(String text, Object... args) {
        info(format(text, args));
    }

    @Override
    public void info(Throwable e, String text, Object... args) {
        info(e, format(text, args));
    }

    @Override
    public void warn(String text, Object... args) {
        warn(format(text, args));
    }

    @Override
    public void warn(Throwable e, String text, Object... args) {
        warn(e, format(text, args));
    }

    @Override
    public void error(String text, Object... args) {
        error(format(text, args));
    }

    @Override
    public void error(Throwable e, String text, Object... args) {
        error(e, format(text, args));
    }


}
