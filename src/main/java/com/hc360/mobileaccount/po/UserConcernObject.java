package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserConcernObject {
	
	//columns START
    /**
     * concernObjectId       db_column: concern_object_id  
     * 
     * 
     * 
     */	
	private java.lang.Long concernObjectId;
    /**
     * 用户id       db_column: userid  
     * 
     * 
     * 
     */	
	private java.lang.Long userid;
    /**
     * 对象id       db_column: objectid  
     * 
     * 
     * 
     */	
	private java.lang.Long objectid;
    /**
     * 关注时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 关注类型 1话术评测 2问题       db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
	
	private Integer start;
	private Integer size;
	//columns END

	public UserConcernObject(){
	}

	public UserConcernObject(
		java.lang.Long concernObjectId
	){
		this.concernObjectId = concernObjectId;
	}

	public void setConcernObjectId(java.lang.Long value) {
		this.concernObjectId = value;
	}
	
	public java.lang.Long getConcernObjectId() {
		return this.concernObjectId;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setObjectid(java.lang.Long value) {
		this.objectid = value;
	}
	
	public java.lang.Long getObjectid() {
		return this.objectid;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ConcernObjectId",getConcernObjectId())
			.append("Userid",getUserid())
			.append("Objectid",getObjectid())
			.append("Createtime",getCreatetime())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getConcernObjectId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserConcernObject == false) return false;
		if(this == obj) return true;
		UserConcernObject other = (UserConcernObject)obj;
		return new EqualsBuilder()
			.append(getConcernObjectId(),other.getConcernObjectId())
			.isEquals();
	}
}

