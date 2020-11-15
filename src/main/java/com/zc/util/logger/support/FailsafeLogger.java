package com.zc.util.logger.support;

import com.zc.util.common.StringParser;
import com.zc.util.logger.Logger;
import com.zc.util.network.NetUtils;

public class FailsafeLogger extends BaseLogger {

    private Logger logger;

    public FailsafeLogger() {}

    public FailsafeLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private String appendContextMessage(String msg) {
        return " [ZC-LOGGER] " + msg + ", current host: " + NetUtils.getLocalHost();
    }

    @Override
    protected String format(String text, Object... args) {
        return StringParser.parse(text, args);
    }

    @Override
    public void trace(String msg) {
        try {
            this.logger.trace(this.appendContextMessage(msg));
        } catch (Throwable var) {
        }
    }

    @Override
    public void trace(Throwable e) {
        try {
            this.logger.trace(e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void trace(Throwable e, String msg) {
        try {
            this.logger.trace(this.appendContextMessage(msg), e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void debug(String msg) {
        try {
            this.logger.debug(this.appendContextMessage(msg));
        } catch (Throwable var) {
        }
    }

    @Override
    public void debug(Throwable e) {
        try {
            this.logger.debug(e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void debug(Throwable e, String msg) {
        try {
            this.logger.debug(this.appendContextMessage(msg), e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void info(String msg) {
        try {
            this.logger.info(this.appendContextMessage(msg));
        } catch (Throwable var) {
        }
    }

    @Override
    public void info(Throwable e) {
        try {
            this.logger.info(e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void info(Throwable e, String msg) {
        try {
            this.logger.info(this.appendContextMessage(msg), e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void warn(String msg) {
        try {
            this.logger.warn(this.appendContextMessage(msg));
        } catch (Throwable var) {
        }
    }

    @Override
    public void warn(Throwable e) {
        try {
            this.logger.warn(e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void warn(Throwable e, String msg) {
        try {
            this.logger.warn(this.appendContextMessage(msg), e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void error(String msg) {
        try {
            this.logger.error(this.appendContextMessage(msg));
        } catch (Throwable var) {
        }
    }

    @Override
    public void error(String format, Object... args) {
        try {
            this.logger.error(this.appendContextMessage(format), args);
        } catch (Throwable var) {
        }
    }

    @Override
    public void error(Throwable e) {
        try {
            this.logger.error(e);
        } catch (Throwable var) {
        }
    }

    @Override
    public void error(Throwable e, String msg) {
        try {
            this.logger.error(this.appendContextMessage(msg), e);
        } catch (Throwable var) {
        }
    }

    @Override
    public boolean isTraceEnabled() {
        try {
            return this.logger.isTraceEnabled();
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public boolean isDebugEnabled() {
        try {
            return this.logger.isDebugEnabled();
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public boolean isInfoEnabled() {
        try {
            return this.logger.isInfoEnabled();
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public boolean isWarnEnabled() {
        try {
            return this.logger.isWarnEnabled();
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public boolean isErrorEnabled() {
        try {
            return this.logger.isErrorEnabled();
        } catch (Throwable e) {
            return false;
        }
    }
}
   