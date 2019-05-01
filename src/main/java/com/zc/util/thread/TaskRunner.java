package com.zc.util.thread;

public abstract class TaskRunner implements Runnable {
	private String taskName;
	private volatile boolean goon;
	private Thread task;
	
	private TaskGroup group;
	
	public TaskRunner(String taskName, TaskGroup group) {
		this.taskName = taskName;
		this.group = group;
		goon = false;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public Thread getTask() {
		return task;
	}
	
	public void startTask() {
		task = new Thread(this);
		synchronized (this) {
			task.start();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void taskRunning() throws Throwable;

	protected void beforeTaskRunning() {}
	protected void taskException(Throwable e) {}
	protected void taskFinally() {}
	protected void taskTerminate() {}
	
	public void stopTask() {
		goon = false;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			this.notify();
		}
		
		group.addTask(this);
		beforeTaskRunning();
		while (goon) {
			try {
				taskRunning();
			} catch (Throwable e) {
				if (goon) {
					taskException(e);
				}
			} finally {
				taskFinally();
			}
		}
		taskTerminate();
		group.remove(this);
	}

}
