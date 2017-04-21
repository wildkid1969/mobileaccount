package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineRegisterUser {
	
	//columns START
    /**
     * userid       db_column: userid  
     * 
     * 
     * 
     */	
	private java.lang.Long userid;
    /**
     * name       db_column: name  
     * 
     * 
     * @Length(max=64)
     */	
	private java.lang.String name;
    /**
     * phone       db_column: phone  
     * 
     * 
     * @Length(max=20)
     */	
	private java.lang.String phone;
    /**
     * qq       db_column: qq  
     * 
     * 
     * @Length(max=20)
     */	
	private java.lang.String qq;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
	//columns END

	public OfflineRegisterUser(){
	}

	public OfflineRegisterUser(
		java.lang.Long userid
	){
		this.userid = userid;
	}

	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setQq(java.lang.String value) {
		this.qq = value;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Userid",getUserid())
			.append("Name",getName())
			.append("Phone",getPhone())
			.append("Qq",getQq())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineRegisterUser == false) return false;
		if(this == obj) return true;
		OfflineRegisterUser other = (OfflineRegisterUser)obj;
		return new EqualsBuilder()
			.append(getUserid(),other.getUserid())
			.isEquals();
	}
}

