package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserMessage {
	
	//columns START
    /**
     * msgid       db_column: msgid  
     * 
     * 
     * 
     */	
	private java.lang.Long msgid;
    /**
     * 发送人id       db_column: from_userid  
     * 
     * 
     * 
     */	
	private java.lang.Long fromUserid;
    /**
     * 接收方id       db_column: to_userid  
     * 
     * 
     * 
     */	
	private java.lang.Long toUserid;
    /**
     * 标题       db_column: title  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String title;
    /**
     * 提示内容       db_column: tips  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String tips;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 对象id       db_column: objectid  
     * 
     * 
     * 
     */	
	private java.lang.String objectid;
    /**
     * 留言内容       db_column: content  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String content;
    /**
     * 类型       db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
	
	/**
	 * 包含多个类型的type
	 */
	private String[] types;
	
	private Integer start;
	private Integer size;
	
	private Account user;
	//columns END

	public UserMessage(){
	}

	public UserMessage(
		java.lang.Long msgid
	){
		this.msgid = msgid;
	}

	public void setMsgid(java.lang.Long value) {
		this.msgid = value;
	}
	
	public java.lang.Long getMsgid() {
		return this.msgid;
	}
	public void setFromUserid(java.lang.Long value) {
		this.fromUserid = value;
	}
	
	public java.lang.Long getFromUserid() {
		return this.fromUserid;
	}
	public void setToUserid(java.lang.Long value) {
		this.toUserid = value;
	}
	
	public java.lang.Long getToUserid() {
		return this.toUserid;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setTips(java.lang.String value) {
		this.tips = value;
	}
	
	public java.lang.String getTips() {
		return this.tips;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setObjectid(java.lang.String value) {
		this.objectid = value;
	}
	
	public java.lang.String getObjectid() {
		return this.objectid;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Msgid",getMsgid())
			.append("FromUserid",getFromUserid())
			.append("ToUserid",getToUserid())
			.append("Title",getTitle())
			.append("Tips",getTips())
			.append("Createtime",getCreatetime())
			.append("Objectid",getObjectid())
			.append("Content",getContent())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMsgid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserMessage == false) return false;
		if(this == obj) return true;
		UserMessage other = (UserMessage)obj;
		return new EqualsBuilder()
			.append(getMsgid(),other.getMsgid())
			.isEquals();
	}
}

