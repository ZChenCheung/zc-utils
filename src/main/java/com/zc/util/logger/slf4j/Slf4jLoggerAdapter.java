package com.zc.util.logger.slf4j;

import com.zc.util.logger.Level;
import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerAdapter;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Slf4jLoggerAdapter implements LoggerAdapter {
    private Level level;
    private File file;

    public Slf4jLoggerAdapter() {
    }

    @Override
    public Logger getLogger(String key) {
        return new Slf4jLogger(LoggerFactory.getLogger(key));
    }

    @Override
    public Logger getLogger(Class<?> key) {
        return new Slf4jLogger(LoggerFactory.getLogger(key));
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
