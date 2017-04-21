package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineUserRegister {
	
	//columns START
    /**
     * registerid       db_column: registerid  
     * 
     * 
     * 
     */	
	private java.lang.Long registerid;
    /**
     * userid       db_column: userid  
     * 
     * 
     * 
     */	
	private java.lang.Long userid;
    /**
     * classid       db_column: classid  
     * 
     * 
     * 
     */	
	private java.lang.Long classid;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 上课地点       db_column: location  
     * 
     * 
     * @Length(max=255)
     */	
	private java.lang.String location;
	//columns END

	public OfflineUserRegister(){
	}

	public OfflineUserRegister(
		java.lang.Long registerid
	){
		this.registerid = registerid;
	}

	public void setRegisterid(java.lang.Long value) {
		this.registerid = value;
	}
	
	public java.lang.Long getRegisterid() {
		return this.registerid;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setClassid(java.lang.Long value) {
		this.classid = value;
	}
	
	public java.lang.Long getClassid() {
		return this.classid;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setLocation(java.lang.String value) {
		this.location = value;
	}
	
	public java.lang.String getLocation() {
		return this.location;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Registerid",getRegisterid())
			.append("Userid",getUserid())
			.append("Classid",getClassid())
			.append("Createtime",getCreatetime())
			.append("Location",getLocation())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRegisterid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineUserRegister == false) return false;
		if(this == obj) return true;
		OfflineUserRegister other = (OfflineUserRegister)obj;
		return new EqualsBuilder()
			.append(getRegisterid(),other.getRegisterid())
			.isEquals();
	}
}

