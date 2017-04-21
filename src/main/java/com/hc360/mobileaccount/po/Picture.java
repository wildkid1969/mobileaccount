package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Picture {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * url       db_column: url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String url;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
	//columns END

	public Picture(){
	}

	public Picture(
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
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Url",getUrl())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Picture == false) return false;
		if(this == obj) return true;
		Picture other = (Picture)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

