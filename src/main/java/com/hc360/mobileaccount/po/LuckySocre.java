package com.hc360.mobileaccount.po;

import java.util.ArrayList;
import java.util.List;

public class LuckySocre {

	private String id; // 奖品标识

	private String name; // 奖品名称

	private String chance; // 中奖概率

	private String prod; // 奖品所需积分
	
	private String len; // 获赠时长 分钟
	
	private String type; // 0：抽奖、1：兑换奖品
	
	private String imgurl; // 图片
	
	private String url; // 要跳转的H5页面URL
	
	private String startTime; // 有效区间开始
	
	private String endTime; // 有效区间截止
	
	private String disable; // 0：可用、1：敬请期待

	private List<Integer> ls = new ArrayList<Integer>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChance() {
		return chance;
	}

	public void setChance(String chance) {
		this.chance = chance;
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public List<Integer> getLs() {
		int iChance = Integer.valueOf(this.chance);
		if (iChance <= 50) {
			for (int i = 1; i < 100; i++) {
				if (ls.size() >= iChance) {
					break;
				}
				if ((ls.size() < iChance) && (i % 2 == 0)) {
					ls.add(i);
				}
			}
		} else {
			for (int i = 1; i < 100; i++) {
				if ((ls.size() <= iChance)) {
					ls.add(i);
				} else {
					break;
				}
			}
		}

		return ls;
	}

	public void setLs(List<Integer> ls) {
		this.ls = ls;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}
}
