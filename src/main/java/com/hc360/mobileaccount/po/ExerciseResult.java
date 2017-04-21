package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ExerciseResult {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * roomid       db_column: roomid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long roomid;
    /**
     * courseid       db_column: courseid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long courseid;
    /**
     * userid       db_column: userid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long userid;
    /**
     * otherid       db_column: otherid  
     * 
     * 
     * 
     */	
	private java.lang.Long partnerid;
    /**
     * 评价       db_column: evaluate  
     * 
     * 
     * @NotBlank @Length(max=65535)
     */	
	private java.lang.String evaluate;
	
	
	private String audioUrl;
    /**
     * 通话时长 单位：秒       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
    /**
     * 对练时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 对练总分数       db_column: score  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Double score;
    /**
     * 是否通过0否1是       db_column: is_pass  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer isPass;
	private Integer isDown;
	
	private Integer roleType;
	
	
	private String courseName;
	private Account partner;
	private MarketingCourse course;
	
	private List<UserNreply> userNreplys;
	private List<UserPraise> userPraises;
	
	private Integer start;
	private Integer size;
	//columns END

	public ExerciseResult(){
	}

	public ExerciseResult(
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
	public void setRoomid(java.lang.Long value) {
		this.roomid = value;
	}
	
	public java.lang.Long getRoomid() {
		return this.roomid;
	}
	public void setCourseid(java.lang.Long value) {
		this.courseid = value;
	}
	
	public java.lang.Long getCourseid() {
		return this.courseid;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setPartnerid(java.lang.Long value) {
		this.partnerid = value;
	}
	
	public java.lang.Long getPartnerid() {
		return this.partnerid;
	}
	public void setEvaluate(java.lang.String value) {
		this.evaluate = value;
	}
	
	public java.lang.String getEvaluate() {
		return this.evaluate;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setIsPass(Integer value) {
		this.isPass = value;
	}
	
	public Integer getIsPass() {
		return this.isPass;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getIsDown() {
		return isDown;
	}

	public void setIsDown(Integer isDown) {
		this.isDown = isDown;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Account getPartner() {
		return partner;
	}

	public MarketingCourse getCourse() {
		return course;
	}

	public void setCourse(MarketingCourse course) {
		this.course = course;
	}

	public void setPartner(Account partner) {
		this.partner = partner;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public List<UserNreply> getUserNreplys() {
		return userNreplys;
	}

	public void setUserNreplys(List<UserNreply> userNreplys) {
		this.userNreplys = userNreplys;
	}

	public List<UserPraise> getUserPraises() {
		return userPraises;
	}

	public void setUserPraises(List<UserPraise> userPraises) {
		this.userPraises = userPraises;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Roomid",getRoomid())
			.append("Courseid",getCourseid())
			.append("Userid",getUserid())
			.append("Partnerid",getPartnerid())
			.append("Evaluate",getEvaluate())
			.append("TimeLength",getTimeLength())
			.append("Createtime",getCreatetime())
			.append("Score",getScore())
			.append("IsPass",getIsPass())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExerciseResult == false) return false;
		if(this == obj) return true;
		ExerciseResult other = (ExerciseResult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

