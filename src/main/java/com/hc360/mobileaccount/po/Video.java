package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Video {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * name       db_column: name  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String name;
	
	private String imgUrl;
    /**
     * timeLength       db_column: time_length  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer timeLength;
    /**
     * 标清播放地址       db_column: sd_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String sdUrl;
    /**
     * 高清播放地址       db_column: hd_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String hdUrl;
    /**
     * 超清播放地址       db_column: shd_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String shdUrl;
    /**
     * 播放次数       db_column: play_cnt  
     * 
     * 
     * 
     */	
	private java.lang.Integer playCnt;
	
	private String videofrom;
	//columns END

	public Video(){
	}

	public Video(
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
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setSdUrl(java.lang.String value) {
		this.sdUrl = value;
	}
	
	public java.lang.String getSdUrl() {
		return this.sdUrl;
	}
	public void setHdUrl(java.lang.String value) {
		this.hdUrl = value;
	}
	
	public java.lang.String getHdUrl() {
		return this.hdUrl;
	}
	public void setShdUrl(java.lang.String value) {
		this.shdUrl = value;
	}
	
	public java.lang.String getShdUrl() {
		return this.shdUrl;
	}
	public void setPlayCnt(java.lang.Integer value) {
		this.playCnt = value;
	}
	
	public java.lang.Integer getPlayCnt() {
		return this.playCnt;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getVideofrom() {
		return videofrom;
	}

	public void setVideofrom(String videofrom) {
		this.videofrom = videofrom;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("TimeLength",getTimeLength())
			.append("SdUrl",getSdUrl())
			.append("HdUrl",getHdUrl())
			.append("ShdUrl",getShdUrl())
			.append("PlayCnt",getPlayCnt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Video == false) return false;
		if(this == obj) return true;
		Video other = (Video)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

