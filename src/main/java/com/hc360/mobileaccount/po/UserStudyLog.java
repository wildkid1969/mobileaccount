package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserStudyLog {
	
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
    /**
     * courseid       db_column: courseid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long courseid;
    /**
     * chapterid       db_column: chapterid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long chapterid;
    /**
     * 初次学习时间       db_column: createtime  
     * 
     * 
     * @NotNull 
     */	
	private java.util.Date createtime;
    /**
     * 最后一次学习时间       db_column: endtime  
     * 
     * 
     * 
     */	
	private java.util.Date endtime;
    /**
     * 学习完成时间       db_column: finishtime  
     * 
     * 
     * 
     */	
	private java.util.Date finishtime;
    /**
     * 学习时长 单位 分钟       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
	private java.lang.Long videopoint;//视频断点（进度,单位秒）
    /**
     * 用户完成状态0未完成 1完成       db_column: state  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer state;
	private Integer chaptype;//章节类型 1视频、2录音
	
	private String dateStr;
	//columns END

	public UserStudyLog(){
	}

	public UserStudyLog(
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
	public void setCourseid(java.lang.Long value) {
		this.courseid = value;
	}
	
	public java.lang.Long getCourseid() {
		return this.courseid;
	}
	public void setChapterid(java.lang.Long value) {
		this.chapterid = value;
	}
	
	public java.lang.Long getChapterid() {
		return this.chapterid;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setFinishtime(java.util.Date value) {
		this.finishtime = value;
	}
	
	public java.util.Date getFinishtime() {
		return this.finishtime;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}

	public java.lang.Long getVideopoint() {
		return videopoint;
	}

	public void setVideopoint(java.lang.Long videopoint) {
		this.videopoint = videopoint;
	}

	public Integer getChaptype() {
		return chaptype;
	}

	public void setChaptype(Integer chaptype) {
		this.chaptype = chaptype;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Courseid",getCourseid())
			.append("Chapterid",getChapterid())
			.append("Createtime",getCreatetime())
			.append("Endtime",getEndtime())
			.append("Finishtime",getFinishtime())
			.append("TimeLength",getTimeLength())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserStudyLog == false) return false;
		if(this == obj) return true;
		UserStudyLog other = (UserStudyLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

