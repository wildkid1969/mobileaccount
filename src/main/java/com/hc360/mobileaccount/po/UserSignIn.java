package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserSignIn {
	
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
     * 签到日期       db_column: signtime  
     * 
     * 
     * @NotNull 
     */	
	private java.util.Date signtime;
	private String signtimeStr;
	//columns END

	public UserSignIn(){
	}

	public UserSignIn(
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
	public void setSigntime(java.util.Date value) {
		this.signtime = value;
	}
	
	public java.util.Date getSigntime() {
		return this.signtime;
	}

	public String getSigntimeStr() {
		return signtimeStr;
	}

	public void setSigntimeStr(String signtimeStr) {
		this.signtimeStr = signtimeStr;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Signtime",getSigntime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserSignIn == false) return false;
		if(this == obj) return true;
		UserSignIn other = (UserSignIn)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

