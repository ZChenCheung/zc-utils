package com.zc.util.logger;

import java.io.File;

public interface LoggerAdapter {
    Logger getLogger(Class<?> var1);

    Logger getLogger(String var1);

    Level getLevel();

    void setLevel(Level var1);

    File getFile();

    void setFile(File var1);
}