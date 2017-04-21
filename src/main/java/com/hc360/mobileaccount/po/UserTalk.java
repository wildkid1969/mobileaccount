package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserTalk {
	
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
     * @NotNull 
     */	
	private java.lang.Long userid;
	private java.lang.String nickname;
	private java.lang.String headerimg;
	private java.lang.String corpname;
	private List<UserNreply> userNreplys;
	private List<UserPraise> userPraises;
    /**
     * 录音链接       db_column: audio_url  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String audioUrl;
    /**
     * 是否显示姓名       db_column: is_show_name  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer isShowName;
    /**
     * 描述       db_column: content  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String content;
    /**
     * 标签id集合       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 兴趣名称集合 逗号隔开       db_column: labelnames  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String labelnames;
	
	private int timeLength;
	
	private Account user;
	
	private Integer start;
	private Integer size;
	//columns END

	public UserTalk(){
	}

	public UserTalk(
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
	public void setAudioUrl(java.lang.String value) {
		this.audioUrl = value;
	}
	
	public java.lang.String getAudioUrl() {
		return this.audioUrl;
	}
	public void setIsShowName(Integer value) {
		this.isShowName = value;
	}
	
	public Integer getIsShowName() {
		return this.isShowName;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setLabelids(java.lang.String value) {
		this.labelids = value;
	}
	
	public java.lang.String getLabelids() {
		return this.labelids;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setLabelnames(java.lang.String value) {
		this.labelnames = value;
	}
	
	public java.lang.String getLabelnames() {
		return this.labelnames;
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

	public java.lang.String getCorpname() {
		return corpname;
	}

	public void setCorpname(java.lang.String corpname) {
		this.corpname = corpname;
	}

	public List<UserNreply> getUserNreplys() {
		return userNreplys;
	}

	public void setUserNreplys(List<UserNreply> userNreplys) {
		this.userNreplys = userNreplys;
	}

	public List<UserPraise> getUserPraises() {
		return userPraises;
	}

	public void setUserPraises(List<UserPraise> userPraises) {
		this.userPraises = userPraises;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
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

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("AudioUrl",getAudioUrl())
			.append("IsShowName",getIsShowName())
			.append("Content",getContent())
			.append("Labelids",getLabelids())
			.append("Createtime",getCreatetime())
			.append("Labelnames",getLabelnames())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserTalk == false) return false;
		if(this == obj) return true;
		UserTalk other = (UserTalk)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

