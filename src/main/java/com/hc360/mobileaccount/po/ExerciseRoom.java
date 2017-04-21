package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ExerciseRoom {
	
	//columns START
    /**
     * roomid       db_column: roomid  
     * 
     * 
     * 
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
     * 销售       db_column: userid  
     * 
     * 
     * 
     */	
	private java.lang.Long userid;
    /**
     * 陪练客户       db_column: partnerid  
     * 
     * 
     * 
     */	
	private java.lang.Long partnerid;
    /**
     * 对练时的录音       db_column: audio_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String audioUrl;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 状态:0 准备匹配 1 匹配成功 2 准备开始对练 3 对练中 4 通话结束 5 评价中 6 对练结束       db_column: state  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer state;
	
	private Integer role;
	
	private Long myId;
	//columns END

	public ExerciseRoom(){
		
	}

	public ExerciseRoom(
		java.lang.Long roomid
	){
		this.roomid = roomid;
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
	public void setAudioUrl(java.lang.String value) {
		this.audioUrl = value;
	}
	
	public java.lang.String getAudioUrl() {
		return this.audioUrl;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}

	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Roomid",getRoomid())
			.append("Courseid",getCourseid())
			.append("Userid",getUserid())
			.append("Partnerid",getPartnerid())
			.append("AudioUrl",getAudioUrl())
			.append("Createtime",getCreatetime())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoomid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExerciseRoom == false) return false;
		if(this == obj) return true;
		ExerciseRoom other = (ExerciseRoom)obj;
		return new EqualsBuilder()
			.append(getRoomid(),other.getRoomid())
			.isEquals();
	}
}

