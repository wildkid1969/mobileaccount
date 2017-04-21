package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineClass {
	
	//columns START
    /**
     * classid       db_column: classid  
     * 
     * 
     * 
     */	
	private java.lang.Long classid;
    /**
     * 课程名称       db_column: class_name  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String className;
    /**
     * 课程描述       db_column: description  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String description;
    /**
     * 课程图片       db_column: img_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String imgUrl;
    /**
     * 上课时间       db_column: starttime  
     * 
     * 
     * 
     */	
	private String starttime;
    /**
     * 报名截止时间       db_column: deadline  
     * 
     * 
     * 
     */	
	private String deadline;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private String createtime;
    /**
     * 课程时长       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
    /**
     * 名额       db_column: people_cnt  
     * 
     * 
     * 
     */	
	private java.lang.Integer peopleCnt;
    /**
     * 上课地点       db_column: location  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String location;
    /**
     * 报名区域       db_column: area  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String area;
    /**
     * 费用       db_column: cost  
     * 
     * 
     * 
     */	
	private java.lang.Integer cost;
	private java.lang.Integer state;
	
	private java.lang.Integer start;
	private java.lang.Integer size;
	
	private List<OfflineChapter> chapterList;
	//columns END

	public OfflineClass(){
	}

	public OfflineClass(
		java.lang.Long classid
	){
		this.classid = classid;
	}

	public void setClassid(java.lang.Long value) {
		this.classid = value;
	}
	
	public java.lang.Long getClassid() {
		return this.classid;
	}
	public void setClassName(java.lang.String value) {
		this.className = value;
	}
	
	public java.lang.String getClassName() {
		return this.className;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	public void setStarttime(String value) {
		this.starttime = value;
	}
	
	public String getStarttime() {
		return this.starttime;
	}
	public void setDeadline(String value) {
		this.deadline = value;
	}
	
	public String getDeadline() {
		return this.deadline;
	}
	public void setCreatetime(String value) {
		this.createtime = value;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setPeopleCnt(java.lang.Integer value) {
		this.peopleCnt = value;
	}
	
	public java.lang.Integer getPeopleCnt() {
		return this.peopleCnt;
	}
	public void setLocation(java.lang.String value) {
		this.location = value;
	}
	
	public java.lang.String getLocation() {
		return this.location;
	}
	public void setArea(java.lang.String value) {
		this.area = value;
	}
	
	public java.lang.String getArea() {
		return this.area;
	}
	public void setCost(java.lang.Integer value) {
		this.cost = value;
	}
	
	public java.lang.Integer getStart() {
		return start;
	}

	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public void setStart(java.lang.Integer start) {
		this.start = start;
	}

	public java.lang.Integer getSize() {
		return size;
	}

	public void setSize(java.lang.Integer size) {
		this.size = size;
	}

	public List<OfflineChapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<OfflineChapter> chapterList) {
		this.chapterList = chapterList;
	}

	public java.lang.Integer getCost() {
		return this.cost;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Classid",getClassid())
			.append("ClassName",getClassName())
			.append("Description",getDescription())
			.append("ImgUrl",getImgUrl())
			.append("Starttime",getStarttime())
			.append("Deadline",getDeadline())
			.append("Createtime",getCreatetime())
			.append("TimeLength",getTimeLength())
			.append("PeopleCnt",getPeopleCnt())
			.append("Location",getLocation())
			.append("Area",getArea())
			.append("Cost",getCost())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getClassid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineClass == false) return false;
		if(this == obj) return true;
		OfflineClass other = (OfflineClass)obj;
		return new EqualsBuilder()
			.append(getClassid(),other.getClassid())
			.isEquals();
	}
}

