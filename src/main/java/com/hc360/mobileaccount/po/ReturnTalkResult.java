package com.hc360.mobileaccount.po;

import java.util.List;

/**
 * 话术评测首页返回的结果集
 * 
 * @author HC360
 *
 */
public class ReturnTalkResult {
	private int code;
	private String message;
	
	/**
	 * 我的话术评测数
	 */
	private int myTalkCount;
	/**
	 * 我的对练数
	 */
	private int myExerciseCount;
	
	/**
	 * 参与话术评测的用户数
	 */
	private int talkUserCount;
	/**
	 * 参与对练的用户数
	 */
	private int exerciseUserCount;
	
	/**
	 * 今日参数话术评测的用户集合
	 */
	private List<Account> talkUserList;
	
	/**
	 * 今日参数营销对练的用户集合
	 */
	private List<Account> exerciseUserList;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMyTalkCount() {
		return myTalkCount;
	}

	public void setMyTalkCount(int myTalkCount) {
		this.myTalkCount = myTalkCount;
	}

	public int getMyExerciseCount() {
		return myExerciseCount;
	}

	public void setMyExerciseCount(int myExerciseCount) {
		this.myExerciseCount = myExerciseCount;
	}

	public int getTalkUserCount() {
		return talkUserCount;
	}

	public void setTalkUserCount(int talkUserCount) {
		this.talkUserCount = talkUserCount;
	}

	public int getExerciseUserCount() {
		return exerciseUserCount;
	}

	public void setExerciseUserCount(int exerciseUserCount) {
		this.exerciseUserCount = exerciseUserCount;
	}

	public List<Account> getTalkUserList() {
		return talkUserList;
	}

	public void setTalkUserList(List<Account> talkUserList) {
		this.talkUserList = talkUserList;
	}

	public List<Account> getExerciseUserList() {
		return exerciseUserList;
	}

	public void setExerciseUserList(List<Account> exerciseUserList) {
		this.exerciseUserList = exerciseUserList;
	}
}
