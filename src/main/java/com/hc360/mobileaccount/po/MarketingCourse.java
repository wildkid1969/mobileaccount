package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MarketingCourse {
	
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
     * @Length(max=100)
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
     * 销售的对练步骤,结尾加分隔符\r\n       db_column: steps  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String steps;
	 /**
     * 客户的对练步骤,结尾加分隔符\r\n      db_column: steps  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String partnerSteps;
    /**
     * 课程封面       db_column: cover_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String coverUrl;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 评价内容,以；分隔       db_column: evaluate  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String evaluate;
    /**
     * 课程总分       db_column: score  
     * 
     * 
     * 
     */	
	private java.lang.Double score;
    /**
     * 兴趣名称集合 逗号隔开       db_column: labelnames  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String labelnames;
    /**
     * 兴趣id集合 逗号隔开       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
    /**
     * 课程状态0未启用 1启用       db_column: enable  
     * 
     * 
     * @Max(127)
     */	
	private Integer enable;
	
	private Integer start;
	private Integer size;
	
	private List<Account> userList;
	//columns END

	public MarketingCourse(){
	}

	public MarketingCourse(
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
	public void setSteps(java.lang.String value) {
		this.steps = value;
	}
	
	public java.lang.String getSteps() {
		return this.steps;
	}
	public void setCoverUrl(java.lang.String value) {
		this.coverUrl = value;
	}
	
	public java.lang.String getCoverUrl() {
		return this.coverUrl;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setEvaluate(java.lang.String value) {
		this.evaluate = value;
	}
	
	public java.lang.String getEvaluate() {
		return this.evaluate;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setLabelnames(java.lang.String value) {
		this.labelnames = value;
	}
	
	public java.lang.String getLabelnames() {
		return this.labelnames;
	}
	public void setLabelids(java.lang.String value) {
		this.labelids = value;
	}
	
	public java.lang.String getLabelids() {
		return this.labelids;
	}
	public void setEnable(Integer value) {
		this.enable = value;
	}
	
	public Integer getEnable() {
		return this.enable;
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

	public List<Account> getUserList() {
		return userList;
	}

	public void setUserList(List<Account> userList) {
		this.userList = userList;
	}

	public java.lang.String getPartnerSteps() {
		return partnerSteps;
	}

	public void setPartnerSteps(java.lang.String partnerSteps) {
		this.partnerSteps = partnerSteps;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("Steps",getSteps())
			.append("CoverUrl",getCoverUrl())
			.append("Createtime",getCreatetime())
			.append("Evaluate",getEvaluate())
			.append("Score",getScore())
			.append("Labelnames",getLabelnames())
			.append("Labelids",getLabelids())
			.append("Enable",getEnable())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MarketingCourse == false) return false;
		if(this == obj) return true;
		MarketingCourse other = (MarketingCourse)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

