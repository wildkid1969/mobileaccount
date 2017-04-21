package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserPraise {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * userid       db_column: userid  
     * 
     * 
     * 
     */	
	private java.lang.Long userid;
	/**
	 * username
	 * 
	 * 
	 * 
	 */	
	private java.lang.String nickname;
	/**
	 * headerimg
	 * 
	 * 
	 * 
	 */	
	private java.lang.String headerimg;
	/**
	 * labelids
	 * 
	 * 
	 * 
	 */	
	private java.lang.String labelids;
	/**
	 * labelNames
	 * 
	 * 
	 * 
	 */	
	private java.lang.String labelNames;
    /**
     * objectid       db_column: objectid  
     * 
     * 
     * 
     */	
	private java.lang.Long objectid;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 点赞对象类型1话术评测 2营销对练 3章节评论 4用户提问 5章节      db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
	//columns END

	public UserPraise(){
	}

	public UserPraise(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setObjectid(java.lang.Long value) {
		this.objectid = value;
	}
	
	public java.lang.Long getObjectid() {
		return this.objectid;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}

	public java.lang.String getNickname() {
		return nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getHeaderimg() {
		return headerimg;
	}

	public void setHeaderimg(java.lang.String headerimg) {
		this.headerimg = headerimg;
	}

	public java.lang.String getLabelids() {
		return labelids;
	}

	public void setLabelids(java.lang.String labelids) {
		this.labelids = labelids;
	}

	public java.lang.String getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(java.lang.String labelNames) {
		this.labelNames = labelNames;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Objectid",getObjectid())
			.append("Createtime",getCreatetime())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserPraise == false) return false;
		if(this == obj) return true;
		UserPraise other = (UserPraise)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

