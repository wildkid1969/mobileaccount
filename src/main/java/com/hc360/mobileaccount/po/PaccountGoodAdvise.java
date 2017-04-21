package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaccountGoodAdvise {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * phone       db_column: phone  
     * 
     * 
     * @Length(max=20)
     */	
	private java.lang.String phone;
    /**
     * content       db_column: content  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String content;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 是否检查       db_column: is_check  
     * 
     * 
     * @Max(127)
     */	
	private Integer isCheck;
    /**
     * 检查时间       db_column: check_time  
     * 
     * 
     * 
     */	
	private java.util.Date checkTime;
    /**
     * 意见等级       db_column: rank  
     * 
     * 
     * 
     */	
	private java.lang.Integer rank;
	//columns END

	public PaccountGoodAdvise(){
	}

	public PaccountGoodAdvise(
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
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setIsCheck(Integer value) {
		this.isCheck = value;
	}
	
	public Integer getIsCheck() {
		return this.isCheck;
	}
	public void setCheckTime(java.util.Date value) {
		this.checkTime = value;
	}
	
	public java.util.Date getCheckTime() {
		return this.checkTime;
	}
	public void setRank(java.lang.Integer value) {
		this.rank = value;
	}
	
	public java.lang.Integer getRank() {
		return this.rank;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Phone",getPhone())
			.append("Content",getContent())
			.append("Createtime",getCreatetime())
			.append("IsCheck",getIsCheck())
			.append("CheckTime",getCheckTime())
			.append("Rank",getRank())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PaccountGoodAdvise == false) return false;
		if(this == obj) return true;
		PaccountGoodAdvise other = (PaccountGoodAdvise)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

