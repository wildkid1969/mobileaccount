package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Grade {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Integer id;
    /**
     * 名称       db_column: name  
     * 
     * 
     * @NotBlank @Length(max=100)
     */	
	private java.lang.String name;
    /**
     * 需要学习的章节数       db_column: chapter_cnt  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer chapterCnt;
    /**
     * 描述       db_column: description  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String description;
    /**
     * 是否可用       db_column: enable  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer enable;
    /**
     * 备注       db_column: remark  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String remark;
	
	public List<Course> courseList;
	//columns END

	public Grade(){
	}

	public Grade(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setChapterCnt(java.lang.Integer value) {
		this.chapterCnt = value;
	}
	
	public java.lang.Integer getChapterCnt() {
		return this.chapterCnt;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setEnable(Integer value) {
		this.enable = value;
	}
	
	public Integer getEnable() {
		return this.enable;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("ChapterCnt",getChapterCnt())
			.append("Description",getDescription())
			.append("Enable",getEnable())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Grade == false) return false;
		if(this == obj) return true;
		Grade other = (Grade)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

