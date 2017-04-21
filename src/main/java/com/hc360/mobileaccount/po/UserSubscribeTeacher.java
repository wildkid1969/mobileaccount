package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserSubscribeTeacher {
	
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
     * teacherid       db_column: teacherid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long teacherid;
    /**
     * subtime       db_column: subtime  
     * 
     * 
     * @NotNull 
     */	
	private java.util.Date subtime;
	//columns END

	public UserSubscribeTeacher(){
	}

	public UserSubscribeTeacher(
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
	public void setTeacherid(java.lang.Long value) {
		this.teacherid = value;
	}
	
	public java.lang.Long getTeacherid() {
		return this.teacherid;
	}
	public void setSubtime(java.util.Date value) {
		this.subtime = value;
	}
	
	public java.util.Date getSubtime() {
		return this.subtime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Teacherid",getTeacherid())
			.append("Subtime",getSubtime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserSubscribeTeacher == false) return false;
		if(this == obj) return true;
		UserSubscribeTeacher other = (UserSubscribeTeacher)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

