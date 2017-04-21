package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Course {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * 课程名称       db_column: name  
     * 
     * 
     * @NotBlank @Length(max=100)
     */	
	private java.lang.String name;
    /**
     * 课程描述       db_column: description  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String description;
    /**
     * 课程标签（形如1，2，3）       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
	private java.lang.String labelNames;
    /**
     * 课程封面图片链接       db_column: cover_url  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String coverUrl;
    /**
     * 课程类型 1：视频2：录音       db_column: type  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer type;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 课程状态0未启用 1启用       db_column: enable  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer enable;
	
	/**
	 * 课程所属的老师
	 */
	private Long teacherId;
	private String teacherName;
	
	/**
	 * 课程参与用户数
	 */
	private int userStudyCnt;
	/**
	 * 课程完成用户数
	 */
	private int userFinishCnt;
	/**
	 * 课程包含的章节总数
	 */
	private Integer chapterCnt;
	private String gradeId;
	private String gradeName;
	
	private Integer praiseCnt;
	private Integer replyCnt;
	
	
	private List<Account> todayStudyUserList;
	//columns END

	public Course(){
	}

	public Course(
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
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setLabelids(java.lang.String value) {
		this.labelids = value;
	}
	
	public java.lang.String getLabelids() {
		return this.labelids;
	}
	public void setCoverUrl(java.lang.String value) {
		this.coverUrl = value;
	}
	
	public java.lang.String getCoverUrl() {
		return this.coverUrl;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setEnable(Integer value) {
		this.enable = value;
	}
	
	public Integer getEnable() {
		return this.enable;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public java.lang.String getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(java.lang.String labelNames) {
		this.labelNames = labelNames;
	}

	public int getUserFinishCnt() {
		return userFinishCnt;
	}

	public int getUserStudyCnt() {
		return userStudyCnt;
	}

	public void setUserStudyCnt(int userStudyCnt) {
		this.userStudyCnt = userStudyCnt;
	}

	public void setUserFinishCnt(int userFinishCnt) {
		this.userFinishCnt = userFinishCnt;
	}

	public Integer getPraiseCnt() {
		return praiseCnt;
	}

	public void setPraiseCnt(Integer praiseCnt) {
		this.praiseCnt = praiseCnt;
	}

	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	public Integer getChapterCnt() {
		return chapterCnt;
	}

	public List<Account> getTodayStudyUserList() {
		return todayStudyUserList;
	}

	public void setTodayStudyUserList(List<Account> todayStudyUserList) {
		this.todayStudyUserList = todayStudyUserList;
	}

	public void setChapterCnt(Integer chapterCnt) {
		this.chapterCnt = chapterCnt;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("Labelids",getLabelids())
			.append("CoverUrl",getCoverUrl())
			.append("Type",getType())
			.append("Createtime",getCreatetime())
			.append("Enable",getEnable())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Course == false) return false;
		if(this == obj) return true;
		Course other = (Course)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

