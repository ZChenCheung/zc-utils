package com.zc.time;

public abstract class CalculateTime {

	public CalculateTime() {
	}
	
	public abstract void dealDate(int hour);
	public abstract void dealHour(int hour);
	public abstract void dealMonth(int hour);
	
	public CalculateTime CalculateTimeToHour(String startTime, String endTime) {
		int result = 0;
		
		String[] tmp = startTime.split(" ");
		int startYear = Integer.valueOf(tmp[0].split("-")[0]);
		int startMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int startDate = Integer.valueOf(tmp[0].split("-")[2]);
		int startHour = Integer.valueOf(tmp[1].split(":")[0]);
		
		tmp = endTime.split(" ");
		int endYear = Integer.valueOf(tmp[0].split("-")[0]);
		int endMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int endDate = Integer.valueOf(tmp[0].split("-")[2]);
		int endHour = Integer.valueOf(tmp[1].split(":")[0]);
		
		result = (endYear - startYear)*365*24 + (endMonth - startMonth)*30*24 + (endDate - startDate)*24 + (endHour - startHour);
		
		dealHour(result);
		
		return this;
	}
	
	public CalculateTime CalculateTimeToDate(String startTime, String endTime) {
		int result = 0;
		
		String[] tmp = startTime.split(" ");
		int startYear = Integer.valueOf(tmp[0].split("-")[0]);
		int startMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int startDate = Integer.valueOf(tmp[0].split("-")[2]);
		
		tmp = endTime.split(" ");
		int endYear = Integer.valueOf(tmp[0].split("-")[0]);
		int endMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int endDate = Integer.valueOf(tmp[0].split("-")[2]);
		
		result = (endYear - startYear)*365 + (endMonth - startMonth)*30 + (endDate - startDate);
		
		dealDate(result);
		
		return this;
	}
	
	public CalculateTime CalculateTimeToMonth(String startTime, String endTime) {
		int result = 0;
		
		String[] tmp = startTime.split(" ");
		int startYear = Integer.valueOf(tmp[0].split("-")[0]);
		int startMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int startDate = Integer.valueOf(tmp[0].split("-")[2]);
		
		tmp = endTime.split(" ");
		int endYear = Integer.valueOf(tmp[0].split("-")[0]);
		int endMonth = Integer.valueOf(tmp[0].split("-")[1]);
		int endDate = Integer.valueOf(tmp[0].split("-")[2]);
		
		int tittle = endDate - startDate > 0 ? 1 : 0;
		
		result = (endYear - startYear)*12 + (endMonth - startMonth)  + tittle;
		
		dealMonth(result);
		
		return this;
	}
	
}
