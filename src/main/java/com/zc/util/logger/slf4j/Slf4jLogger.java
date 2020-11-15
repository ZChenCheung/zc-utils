package com.zc.util.logger.slf4j;

import com.zc.util.logger.support.BaseLogger;
import com.zc.util.logger.support.FailsafeLogger;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

public class Slf4jLogger extends FailsafeLogger implements Serializable {

    private static final long serialVersionUID = 5757680857072944206L;

    private static final String FQCN = FailsafeLogger.class.getName();
    private final org.slf4j.Logger logger;
    private final LocationAwareLogger locationAwareLogger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        if (logger instanceof LocationAwareLogger) {
            this.locationAwareLogger = (LocationAwareLogger)logger;
        } else {
            this.locationAwareLogger = null;
        }

        this.logger = logger;
    }

    @Override
    public void trace(String msg) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 0, msg, (Object[])null, (Throwable)null);
        } else {
            this.logger.trace(msg);
        }
    }

    @Override
    public void trace(Throwable e) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 0, e.getMessage(), (Object[])null, e);
        } else {
            this.logger.trace(e.getMessage(), e);
        }
    }

    @Override
    public void trace(Throwable e, String text) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 0, text, (Object[])null, e);
        } else {
            this.logger.trace(text, e);
        }
    }

    @Override
    public void debug(String msg) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 10, msg, (Object[])null, (Throwable)null);
        } else {
            this.logger.debug(msg);
        }
    }

    @Override
    public void debug(Throwable e) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 10, e.getMessage(), (Object[])null, e);
        } else {
            this.logger.debug(e.getMessage(), e);
        }
    }

    @Override
    public void debug(Throwable e, String text) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 10, text, (Object[])null, e);
        } else {
            this.logger.debug(text, e);
        }
    }

    @Override
    public void info(String msg) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 20, msg, (Object[])null, (Throwable)null);
        } else {
            this.logger.info(msg);
        }
    }

    @Override
    public void info(Throwable e) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 20, e.getMessage(), (Object[])null, e);
        } else {
            this.logger.info(e.getMessage(), e);
        }
    }

    @Override
    public void info(Throwable e, String text) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 20, text, (Object[])null, e);
        } else {
            this.logger.info(text, e);
        }
    }

    @Override
    public void warn(String msg) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 30, msg, (Object[])null, (Throwable)null);
        } else {
            this.logger.warn(msg);
        }
    }

    @Override
    public void warn(Throwable e) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 30, e.getMessage(), (Object[])null, e);
        } else {
            this.logger.warn(e.getMessage(), e);
        }
    }

    @Override
    public void warn(Throwable e, String text) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 30, text, (Object[])null, e);
        } else {
            this.logger.warn(text, e);
        }
    }

    @Override
    public void error(String msg) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 40, msg, (Object[])null, (Throwable)null);
        } else {
            this.logger.error(msg);
        }
    }

    @Override
    public void error(Throwable e) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 40, e.getMessage(), (Object[])null, e);
        } else {
            this.logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void error(Throwable e, String text) {
        if (this.locationAwareLogger != null) {
            this.locationAwareLogger.log((Marker)null, FQCN, 40, text, (Object[])null, e);
        } else {
            this.logger.error(text, e);
        }
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
