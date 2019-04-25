package com.zc.time;

public class CurrentTime {

	private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 * 生成日期时间+message字符串
	 * @param message
	 * @return
	 */
	public static String logMessage(String message) {
		return TimeDate.timeString(TIME_PATTERN) + " " + message;
	}
	
}
