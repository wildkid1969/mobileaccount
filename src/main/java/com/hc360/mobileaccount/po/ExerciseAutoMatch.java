package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ExerciseAutoMatch {
	
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
     * courseid       db_column: courseid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long courseid;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
	
	private String nickname;
	private String headerimg;
	private String phone;
	private String corpname;
	private String aposition;
	
	private Long myId;
	
	private int size;
	
	private int rand;
	//columns END

	public ExerciseAutoMatch(){
	}

	public ExerciseAutoMatch(
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
	public void setCourseid(java.lang.Long value) {
		this.courseid = value;
	}
	
	public java.lang.Long getCourseid() {
		return this.courseid;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeaderimg() {
		return headerimg;
	}

	public void setHeaderimg(String headerimg) {
		this.headerimg = headerimg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getAposition() {
		return aposition;
	}

	public void setAposition(String aposition) {
		this.aposition = aposition;
	}

	public int getRand() {
		return rand;
	}

	public void setRand(int rand) {
		this.rand = rand;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Courseid",getCourseid())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExerciseAutoMatch == false) return false;
		if(this == obj) return true;
		ExerciseAutoMatch other = (ExerciseAutoMatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

