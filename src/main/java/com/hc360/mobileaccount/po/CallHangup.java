package com.hc360.mobileaccount.po;

public class CallHangup extends YzxCall{
	
	private String confid; // 群聊ID
	
	private String starttime; //开始通话时间  时间格式如：2014-06-16 16:47:28
	
	private String stoptime; // 结束通话时间  时间格式如：2014-06-16 16:47:28
	
	private int length ; // 通话时长 秒数
	
	private String recordurl; // 通话录音完整下载地址
	
	private String userData; // 用户自定义数据字符串
	
	private int reason; // 挂机原因 0：正常挂断；1：余额不足；2：媒体超时； 
						// 3：无法接通；4：拒接；5：超时未接；6：拒接或超时未接； 
						// 7：平台服务器网络错误；8：用户请求取消通话；9：第三方鉴权错误；255：其他原因。
	private int subreason; //挂机原因补充描述 1 主叫挂断 2 被叫挂断； 目前当reason=0时有效
	public String getConfid() {
		return confid;
	}
	public void setConfid(String confid) {
		this.confid = confid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getStoptime() {
		return stoptime;
	}
	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRecordurl() {
		return recordurl;
	}
	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}
	public String getUserData() {
		return userData;
	}
	public void setUserData(String userData) {
		this.userData = userData;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public int getSubreason() {
		return subreason;
	}
	public void setSubreason(int subreason) {
		this.subreason = subreason;
	}
}
