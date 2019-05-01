package com.zc.util.time;

/**
 * 精准定时任务
 */
public abstract class AbstractClocker {
	public static final int DEFAULT_TIME = 1000;
	private long clockTime;
	private volatile boolean goon;
	
	private Thread runner;
	private Thread worker;
	
	public AbstractClocker() {
		goon = false;
	}
	
	public AbstractClocker(long clockTiem) {
		this.clockTime = clockTiem;
	}
	
	public AbstractClocker setClockTime(long clockTime) {
		this.clockTime = clockTime;
		return this;
	}

	protected abstract void startClock();
	protected abstract void beforeClock();
	protected abstract void workOnClock();
	protected abstract void endClock();
	
	public void start() {
		if (goon) { return; }
		goon = true;
		
		worker = new Thread(new Worker());
		worker.setPriority(Thread.MAX_PRIORITY);
		worker.start();
		
		runner = new Thread(new RunTime(), "Clock");
		synchronized (runner) {
			try {
				runner.start();
				runner.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		goon = false;
	}
	
	class Worker implements Runnable {

		Worker() {
		}
		
		@Override
		public void run() {
			while (goon) {
				synchronized (worker) {
					try {
						worker.wait();
						if (goon) {
							workOnClock();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	class RunTime implements Runnable {

		RunTime() {
		}
		
		@Override
		public void run() {
			synchronized (runner) {
				runner.notify();
			}
			
			startClock();
			if (clockTime <= 0) {
				clockTime = DEFAULT_TIME;
			}
			while (goon) {
				synchronized (worker) {
					try {
						worker.wait(clockTime);
						worker.notify();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			endClock();
		}
	
	}
	
}
