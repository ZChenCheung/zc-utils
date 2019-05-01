package com.zc.util.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskGroup {
	private final Map<String, TaskRunner> taskMap;
	
	public TaskGroup() {
		taskMap = Collections.synchronizedMap(new HashMap<>());
	}

	public void remove(String taskName) {
		remove(taskName, taskMap.get(taskName));
	}
	
	public void remove(String taskName, TaskRunner task) {
		if (!taskMap.containsKey(taskName)) {
			return;
		}
		taskMap.remove(taskName, task);
		if (taskMap.size() <= 0) {
			afterAllTaskTerminate();
		}
	}
	
	public void remove(TaskRunner task) {
		remove(task.getTaskName(), task);
	}
	
	protected void afterAllTaskTerminate() {}
	
	public void terminateAll() {
		if (taskMap.isEmpty()) {
			return;
		}
		for (TaskRunner task : taskMap.values()) {
			task.taskTerminate();
		}
		taskMap.clear();
		afterAllTaskTerminate();
	}
	
	public void addTask(TaskRunner task) {
		addTask(task.getTaskName(), task);
	}
	
	public void addTask(String taskName, TaskRunner task) {
		taskMap.put(taskName, task);
	}

	
}
