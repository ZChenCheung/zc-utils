package com.zc.util.logger.log4j;

import com.zc.util.logger.support.BaseLogger;
import com.zc.util.logger.support.FailsafeLogger;
import org.apache.log4j.Level;

public class Log4jLogger extends FailsafeLogger {

    private static final String FQCN = FailsafeLogger.class.getName();

    private final org.apache.log4j.Logger logger;

    public Log4jLogger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String msg) {
        this.logger.log(FQCN, Level.TRACE, msg, (Throwable)null);
    }

    @Override
    public void trace(Throwable e) {
        this.logger.log(FQCN, Level.TRACE, e == null ? null : e.getMessage(), e);
    }

    @Override
    public void trace(Throwable e, String text) {
        this.logger.log(FQCN, Level.TRACE, text, e);
    }

    @Override
    public void debug(String msg) {
        this.logger.log(FQCN, Level.DEBUG, msg, (Throwable)null);
    }

    @Override
    public void debug(Throwable e) {
        this.logger.log(FQCN, Level.DEBUG, e == null ? null : e.getMessage(), e);
    }

    @Override
    public void debug(Throwable e, String text) {
        this.logger.log(FQCN, Level.DEBUG, text, e);
    }

    @Override
    public void info(String msg) {
        this.logger.log(FQCN, Level.INFO, msg, (Throwable)null);
    }

    @Override
    public void info(Throwable e) {
        this.logger.log(FQCN, Level.INFO, e == null ? null : e.getMessage(), e);
    }

    @Override
    public void info(Throwable e, String text) {
        this.logger.log(FQCN, Level.INFO, text, e);
    }

    @Override
    public void warn(String msg) {
        this.logger.log(FQCN, Level.WARN, msg, (Throwable)null);
    }

    @Override
    public void warn(Throwable e) {
        this.logger.log(FQCN, Level.WARN, e == null ? null : e.getMessage(), e);
    }

    @Override
    public void warn(Throwable e, String text) {
        this.logger.log(FQCN, Level.WARN, text, e);
    }

    @Override
    public void error(String msg) {
        this.logger.log(FQCN, Level.ERROR, msg, (Throwable)null);
    }

    @Override
    public void error(Throwable e) {
        this.logger.log(FQCN, Level.ERROR, e == null ? null : e.getMessage(), e);
    }

    @Override
    public void error(Throwable e, String text) {
        this.logger.log(FQCN, Level.ERROR, text, e);
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
        return this.logger.isEnabledFor(Level.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isEnabledFor(Level.ERROR);
    }

}

