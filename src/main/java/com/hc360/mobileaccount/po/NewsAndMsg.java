package com.hc360.mobileaccount.po;

public class NewsAndMsg {
	
	private int newsId;//id
	
	private String msgTitle="";//标题
	
	private String  msgUrl="";//跳转地址
	private String msgPic="";//图片地址
	private String msgTime="";//新闻、消息时间
	private String time=""; //图片展示时间
	private String keywords="";//搜索关键字

	private String msgDesc;//新闻、消息内容
	
	private String type; //类型：1、2广告 3：新闻 4：通知 5：企业活动
	
	private String count; //新消息新闻数量
	
	private String remark1; //来源（来日某个作者某个日报社） 
	//remark2 默认图标识 0  msgPic2 msgPic3备用图片字段
 
	public int getNewsId() {
		return newsId; 
	}
 
	public void setNewsId(int newId) {
		this.newsId = newId; 
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgUrl() {
		return msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	public String getMsgPic() {
		return msgPic;
	}

	public void setMsgPic(String msgPic) {
		this.msgPic = msgPic;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
}
