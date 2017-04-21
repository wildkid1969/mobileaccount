package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineChapter {
	
	//columns START
    /**
     * chapterid       db_column: chapterid  
     * 
     * 
     * 
     */	
	private java.lang.Long chapterid;
    /**
     * 章节编号       db_column: chapter_number  
     * 
     * 
     * 
     */	
	private java.lang.Long chapterNumber;
    /**
     * 章节名称       db_column: chapter_name  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String chapterName;
    /**
     * 章节描述       db_column: description  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String description;
    /**
     * 课程id       db_column: classid  
     * 
     * 
     * 
     */	
	private java.lang.Long classid;
    /**
     * 章节时长       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
    /**
     * 章节价格 单位：元       db_column: price  
     * 
     * 
     * 
     */	
	private java.lang.Double price;
    /**
     * 章节受众       db_column: audiences  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String audiences;
    /**
     * 章节收益       db_column: gains  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String gains;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 状态0未启用 1启用       db_column: state  
     * 
     * 
     * @Max(127)
     */	
	private Integer state;
	
	private Long teacherid;
	private String teacherName;
	//columns END

	public OfflineChapter(){
	}

	public OfflineChapter(
		java.lang.Long chapterid
	){
		this.chapterid = chapterid;
	}

	public void setChapterid(java.lang.Long value) {
		this.chapterid = value;
	}
	
	public java.lang.Long getChapterid() {
		return this.chapterid;
	}
	public void setChapterNumber(java.lang.Long value) {
		this.chapterNumber = value;
	}
	
	public java.lang.Long getChapterNumber() {
		return this.chapterNumber;
	}
	public void setChapterName(java.lang.String value) {
		this.chapterName = value;
	}
	
	public java.lang.String getChapterName() {
		return this.chapterName;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setClassid(java.lang.Long value) {
		this.classid = value;
	}
	
	public java.lang.Long getClassid() {
		return this.classid;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setPrice(java.lang.Double value) {
		this.price = value;
	}
	
	public java.lang.Double getPrice() {
		return this.price;
	}
	public void setAudiences(java.lang.String value) {
		this.audiences = value;
	}
	
	public java.lang.String getAudiences() {
		return this.audiences;
	}
	public void setGains(java.lang.String value) {
		this.gains = value;
	}
	
	public java.lang.String getGains() {
		return this.gains;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}

	public Long getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Chapterid",getChapterid())
			.append("ChapterNumber",getChapterNumber())
			.append("ChapterName",getChapterName())
			.append("Description",getDescription())
			.append("Classid",getClassid())
			.append("TimeLength",getTimeLength())
			.append("Price",getPrice())
			.append("Audiences",getAudiences())
			.append("Gains",getGains())
			.append("Createtime",getCreatetime())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getChapterid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineChapter == false) return false;
		if(this == obj) return true;
		OfflineChapter other = (OfflineChapter)obj;
		return new EqualsBuilder()
			.append(getChapterid(),other.getChapterid())
			.isEquals();
	}
}

