package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Ad {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * 广告标题       db_column: name  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String name;
    /**
     * 广告描述       db_column: description  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String description;
    /**
     * 图片链接       db_column: img_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String imgUrl;
    /**
     * 跳转链接       db_column: go_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String goUrl;
	
	/**
	 * 对象id 根据type判断
	 */
	private Long objectid;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 广告类型 1课程 2老师       db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
    /**
     * 状态 0下线 1上线       db_column: state  
     * 
     * 
     * @Max(127)
     */	
	private Integer state;
    /**
     * 排序标识       db_column: sort  
     * 
     * 
     * @Max(127)
     */	
	private Integer sort;
	
	private String[] types;
	//columns END

	public Ad(){
	}

	public Ad(
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
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	public void setGoUrl(java.lang.String value) {
		this.goUrl = value;
	}
	
	public java.lang.String getGoUrl() {
		return this.goUrl;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setSort(Integer value) {
		this.sort = value;
	}
	
	public Integer getSort() {
		return this.sort;
	}

	public Long getObjectid() {
		return objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("ImgUrl",getImgUrl())
			.append("GoUrl",getGoUrl())
			.append("Createtime",getCreatetime())
			.append("Type",getType())
			.append("State",getState())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Ad == false) return false;
		if(this == obj) return true;
		Ad other = (Ad)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

