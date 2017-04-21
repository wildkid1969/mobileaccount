package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineTeacher {
	
	//columns START
    /**
     * teacherid       db_column: teacherid  
     * 
     * 
     * 
     */	
	private java.lang.Long teacherid;
    /**
     * 讲师姓名       db_column: name  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String name;
    /**
     * 头像       db_column: avatar  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String avatar;
    /**
     * 讲师职位       db_column: position  
     * 
     * 
     * @Length(max=250)
     */	
	private java.lang.String position;
    /**
     * 讲师描述       db_column: description  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String description;
    /**
     * 视频       db_column: video_key  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String videoKey;
    /**
     * 视频时长       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
	//columns END

	public OfflineTeacher(){
	}

	public OfflineTeacher(
		java.lang.Long teacherid
	){
		this.teacherid = teacherid;
	}

	public void setTeacherid(java.lang.Long value) {
		this.teacherid = value;
	}
	
	public java.lang.Long getTeacherid() {
		return this.teacherid;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setAvatar(java.lang.String value) {
		this.avatar = value;
	}
	
	public java.lang.String getAvatar() {
		return this.avatar;
	}
	public void setPosition(java.lang.String value) {
		this.position = value;
	}
	
	public java.lang.String getPosition() {
		return this.position;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setVideoKey(java.lang.String value) {
		this.videoKey = value;
	}
	
	public java.lang.String getVideoKey() {
		return this.videoKey;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Teacherid",getTeacherid())
			.append("Name",getName())
			.append("Avatar",getAvatar())
			.append("Position",getPosition())
			.append("Description",getDescription())
			.append("VideoKey",getVideoKey())
			.append("TimeLength",getTimeLength())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTeacherid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineTeacher == false) return false;
		if(this == obj) return true;
		OfflineTeacher other = (OfflineTeacher)obj;
		return new EqualsBuilder()
			.append(getTeacherid(),other.getTeacherid())
			.isEquals();
	}
}

