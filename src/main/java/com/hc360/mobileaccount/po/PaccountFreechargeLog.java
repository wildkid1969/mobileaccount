package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class PaccountFreechargeLog {
	
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
     * 充值金额 单位：秒       db_column: charge  
     * 
     * 
     * 
     */	
	private java.lang.Integer charge;
    /**
     * updatetime       db_column: updatetime  
     * 
     * 
     * 
     */	
	private java.util.Date updatetime;
	/**
	 * createtime       db_column: createtime  
	 * 
	 * 
	 * 
	 */	
	private java.util.Date createtime;
	
	/**
	 * 是否使用赠送的分钟 0：否 1：已使用    	db_column:is_used
	 */
	private Integer isUsed;
	
	private Integer type;
	private String remark;
	//columns END

	public PaccountFreechargeLog(){
	}

	public PaccountFreechargeLog(
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
	public void setCharge(java.lang.Integer value) {
		this.charge = value;
	}
	
	public java.lang.Integer getCharge() {
		return this.charge;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Phone",getPhone())
			.append("Charge",getCharge())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PaccountFreechargeLog == false) return false;
		if(this == obj) return true;
		PaccountFreechargeLog other = (PaccountFreechargeLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

