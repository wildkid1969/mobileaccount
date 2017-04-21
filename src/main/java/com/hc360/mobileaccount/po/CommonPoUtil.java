package com.hc360.mobileaccount.po;

import java.util.Map;

public class CommonPoUtil {

	private Map<String, String> timeInterval;

	private Map<String, String> scoreTypeMap;
	
	private Map<String, LuckySocre> luckyMap;

	public Map<String, String> getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Map<String, String> timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Map<String, String> getScoreTypeMap() {
		return scoreTypeMap;
	}

	public void setScoreTypeMap(Map<String, String> scoreTypeMap) {
		this.scoreTypeMap = scoreTypeMap;
	}

	public Map<String, LuckySocre> getLuckyMap() {
		return luckyMap;
	}

	public void setLuckyMap(Map<String, LuckySocre> luckyMap) {
		this.luckyMap = luckyMap;
	}
}
