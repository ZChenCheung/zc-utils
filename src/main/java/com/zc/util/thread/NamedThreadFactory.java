package com.zc.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    protected static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    protected final AtomicInteger threadNum;

    protected final String prefix;

    protected final boolean daemon;

    protected final ThreadGroup threadGroup;

    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        this.threadNum = new AtomicInteger(1);
        this.prefix = prefix + "-thread-";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        this.threadGroup = s == null ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = this.prefix + this.threadNum.getAndIncrement();
        Thread result = new Thread(this.threadGroup, runnable, name, 0L);
        result.setDaemon(this.daemon);

        return result;
    }

    public ThreadGroup getThreadGroup() {
        return this.threadGroup;
    }

}
