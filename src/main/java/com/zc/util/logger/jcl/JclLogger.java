package com.zc.util.logger.jcl;

import com.zc.util.logger.support.BaseLogger;
import com.zc.util.logger.support.FailsafeLogger;
import org.apache.commons.logging.Log;

import java.io.Serializable;

public class JclLogger extends FailsafeLogger implements Serializable {

    private static final long serialVersionUID = -2209624932923707548L;

    private final Log logger;

    public JclLogger(Log logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String msg) {
        this.logger.trace(msg);
    }

    @Override
    public void trace(Throwable e) {
        this.logger.trace(e);
    }

    @Override
    public void trace(Throwable e, String text) {
        this.logger.trace(text, e);
    }

    @Override
    public void debug(String msg) {
        this.logger.debug(msg);
    }

    @Override
    public void debug(Throwable e) {
        this.logger.debug(e);
    }

    @Override
    public void debug(Throwable e, String text) {
        this.logger.debug(text, e);
    }

    @Override
    public void info(String msg) {
        this.logger.info(msg);
    }

    @Override
    public void info(Throwable e) {
        this.logger.info(e);
    }

    @Override
    public void info(Throwable e, String text) {
        this.logger.info(text, e);
    }

    @Override
    public void warn(String msg) {
        this.logger.warn(msg);
    }

    @Override
    public void warn(Throwable e) {
        this.logger.warn(e);
    }

    @Override
    public void warn(Throwable e, String text) {
        this.logger.warn(text, e);
    }

    @Override
    public void error(String msg) {
        this.logger.error(msg);
    }

    @Override
    public void error(Throwable e) {
        this.logger.error(e);
    }

    @Override
    public void error(Throwable e, String text) {
        this.logger.error(text, e);
    }

    @Override
    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

}
