package com.zc.util.logger.jcl;

import com.zc.util.logger.Level;
import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerAdapter;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class JclLoggerAdapter implements LoggerAdapter {
    private Level level;
    private File file;

    public JclLoggerAdapter() {
    }

    @Override
    public Logger getLogger(String name) {
        return new JclLogger(LogFactory.getLog(name));
    }

    @Override
    public Logger getLogger(Class<?> clazz) {
        return new JclLogger(LogFactory.getLog(clazz));
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }


}
