package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TeacherReplyInvited {
	
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
     * talkid或者questionid       db_column: invitedid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long invitedid;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * @NotNull 
     */	
	private java.util.Date createtime;
    /**
     * 是否已评测0否 1是       db_column: is_reply  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer isReply;
    /**
     * 类型1话术评测2用户提问       db_column: type  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer type;
	//columns END

	public TeacherReplyInvited(){
	}

	public TeacherReplyInvited(
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
	public void setInvitedid(java.lang.Long value) {
		this.invitedid = value;
	}
	
	public java.lang.Long getInvitedid() {
		return this.invitedid;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setIsReply(Integer value) {
		this.isReply = value;
	}
	
	public Integer getIsReply() {
		return this.isReply;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Invitedid",getInvitedid())
			.append("Createtime",getCreatetime())
			.append("IsReply",getIsReply())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TeacherReplyInvited == false) return false;
		if(this == obj) return true;
		TeacherReplyInvited other = (TeacherReplyInvited)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

