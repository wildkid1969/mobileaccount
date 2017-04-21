package com.hc360.mobileaccount.po;

// 奖品列表
public class Reward {
	
	private String rewardName; // 奖品名称
	
	private String rewardImg;  // 奖品图片地址 
	
	private String rewardDuring; // 有效期

	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}

	public String getRewardImg() {
		return rewardImg;
	}

	public void setRewardImg(String rewardImg) {
		this.rewardImg = rewardImg;
	}

	public String getRewardDuring() {
		return rewardDuring;
	}

	public void setRewardDuring(String rewardDuring) {
		this.rewardDuring = rewardDuring;
	}

}
