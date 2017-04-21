package com.hc360.mobileaccount.po;

public class AccountGiveMin {
	private int id;
	private String phone; //本人手机号
	private String friendphone; //推荐好友手机号
	private String createtime;  //创建时间
	private String state;   //状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFriendphone() {
		return friendphone;
	}
	public void setFriendphone(String friendphone) {
		this.friendphone = friendphone;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
