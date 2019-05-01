package com.zc.util.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class CreatRuntime implements Runnable {
	private final long timeout;
	private volatile boolean start;
	public final static long DEFAULT_TIME_OUT = 333L;
	
	public CreatRuntime() {
		this(DEFAULT_TIME_OUT);
	}
	
	public CreatRuntime(long timeout) {
		start = true;
		this.timeout = timeout <= 0 ? DEFAULT_TIME_OUT : timeout;
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		start = false;
	}
	
	public abstract void dealTime(String str);
	
	@Override
	public void run() {
		while (start) {
			Calendar today = Calendar.getInstance();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(today.getTime());
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dealTime(str);
		}
	}

}
