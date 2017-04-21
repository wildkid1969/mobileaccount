package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserReply {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * content       db_column: content  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String content;
    /**
     * imgUrl       db_column: img_url  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String imgUrl;
    /**
     * audioUrl       db_column: audio_url  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String audioUrl;
	
	private int timeLength;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * @NotNull 
     */	
	private java.util.Date createtime;
    /**
     * praiseCnt       db_column: praise_cnt  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer praiseCnt;
	//columns END

	public UserReply(){
	}

	public UserReply(
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
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	public void setAudioUrl(java.lang.String value) {
		this.audioUrl = value;
	}
	
	public java.lang.String getAudioUrl() {
		return this.audioUrl;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setPraiseCnt(java.lang.Integer value) {
		this.praiseCnt = value;
	}
	
	public java.lang.Integer getPraiseCnt() {
		return this.praiseCnt;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Content",getContent())
			.append("ImgUrl",getImgUrl())
			.append("AudioUrl",getAudioUrl())
			.append("Createtime",getCreatetime())
			.append("PraiseCnt",getPraiseCnt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserReply == false) return false;
		if(this == obj) return true;
		UserReply other = (UserReply)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

