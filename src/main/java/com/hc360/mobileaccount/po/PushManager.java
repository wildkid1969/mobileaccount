package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PushManager {
	
	//columns START
    /**
     * pushManagerId       db_column: push_manager_id  
     * 
     * 
     * 
     */	
	private java.lang.Long pushManagerId;
    /**
     * 发送方用户id       db_column: from_userid  
     * 
     * 
     * 
     */	
	private String fromPhone;
    /**
     * 接收方用户id       db_column: to_userid  
     * 
     * 
     * @NotNull 
     */	
	private String toPhone;
    /**
     * ticker       db_column: ticker  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String ticker;
    /**
     * 推送的内容标题       db_column: title  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String title;
    /**
     * 要推送的内容       db_column: content  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String content;
    /**
     * 对象id       db_column: objectid  
     * 
     * 
     * 
     */	
	private java.lang.String objectid;
    /**
     * objectid的类型       db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
    /**
     * 界面地址       db_column: app_activity  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String appActivity;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 推送时间       db_column: pushtime  
     * 
     * 
     * 
     */	
	private java.util.Date pushtime;
    /**
     * 推送类型       db_column: push_type  
     * 
     * 
     * @Max(127)
     */	
	private Integer pushType;
	//columns END

	public PushManager(){
	}

	public PushManager(
		java.lang.Long pushManagerId
	){
		this.pushManagerId = pushManagerId;
	}

	public void setPushManagerId(java.lang.Long value) {
		this.pushManagerId = value;
	}
	
	public java.lang.Long getPushManagerId() {
		return this.pushManagerId;
	}
	public void setFromPhone(String value) {
		this.fromPhone = value;
	}
	
	public String getFromPhone() {
		return this.fromPhone;
	}
	public void setToPhone(String value) {
		this.toPhone = value;
	}
	
	public String getToPhone() {
		return this.toPhone;
	}
	public void setTicker(java.lang.String value) {
		this.ticker = value;
	}
	
	public java.lang.String getTicker() {
		return this.ticker;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setObjectid(java.lang.String value) {
		this.objectid = value;
	}
	
	public java.lang.String getObjectid() {
		return this.objectid;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setAppActivity(java.lang.String value) {
		this.appActivity = value;
	}
	
	public java.lang.String getAppActivity() {
		return this.appActivity;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setPushtime(java.util.Date value) {
		this.pushtime = value;
	}
	
	public java.util.Date getPushtime() {
		return this.pushtime;
	}
	public void setPushType(Integer value) {
		this.pushType = value;
	}
	
	public Integer getPushType() {
		return this.pushType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("PushManagerId",getPushManagerId())
			.append("FromPhone",getFromPhone())
			.append("ToPhone",getToPhone())
			.append("Ticker",getTicker())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("Objectid",getObjectid())
			.append("Type",getType())
			.append("AppActivity",getAppActivity())
			.append("Createtime",getCreatetime())
			.append("Pushtime",getPushtime())
			.append("PushType",getPushType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPushManagerId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PushManager == false) return false;
		if(this == obj) return true;
		PushManager other = (PushManager)obj;
		return new EqualsBuilder()
			.append(getPushManagerId(),other.getPushManagerId())
			.isEquals();
	}
}

