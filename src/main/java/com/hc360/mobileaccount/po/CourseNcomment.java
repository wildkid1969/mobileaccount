package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CourseNcomment {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * courseid       db_column: courseid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long courseid;
    /**
     * chapterid       db_column: chapterid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long chapterid;
    /**
     * userid       db_column: userid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long userid;
    /**
     * replyid       db_column: replyid  
     * 
     * 
     * 
     */	
	private java.lang.Long replyid;
	
	private UserReply reply;
	private Account user;
	//columns END

	public CourseNcomment(){
	}

	public CourseNcomment(
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
	public void setCourseid(java.lang.Long value) {
		this.courseid = value;
	}
	
	public java.lang.Long getCourseid() {
		return this.courseid;
	}
	public void setChapterid(java.lang.Long value) {
		this.chapterid = value;
	}
	
	public java.lang.Long getChapterid() {
		return this.chapterid;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setReplyid(java.lang.Long value) {
		this.replyid = value;
	}
	
	public java.lang.Long getReplyid() {
		return this.replyid;
	}

	public UserReply getReply() {
		return reply;
	}

	public void setReply(UserReply reply) {
		this.reply = reply;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Courseid",getCourseid())
			.append("Chapterid",getChapterid())
			.append("Userid",getUserid())
			.append("Replyid",getReplyid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CourseNcomment == false) return false;
		if(this == obj) return true;
		CourseNcomment other = (CourseNcomment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

