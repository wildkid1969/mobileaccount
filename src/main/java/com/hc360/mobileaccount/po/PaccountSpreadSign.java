package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaccountSpreadSign {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * 在AppStore里的Id       db_column: appid  
     * 
     * 
     * 
     */	
	private java.lang.Long appid;
    /**
     * 用户的idfa       db_column: idfa  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String idfa;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
	//columns END

	public PaccountSpreadSign(){
	}

	public PaccountSpreadSign(
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
	public void setAppid(java.lang.Long value) {
		this.appid = value;
	}
	
	public java.lang.Long getAppid() {
		return this.appid;
	}
	public void setIdfa(java.lang.String value) {
		this.idfa = value;
	}
	
	public java.lang.String getIdfa() {
		return this.idfa;
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
			.append("Appid",getAppid())
			.append("Idfa",getIdfa())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PaccountSpreadSign == false) return false;
		if(this == obj) return true;
		PaccountSpreadSign other = (PaccountSpreadSign)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

