package com.hc360.mobileaccount.po;


import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CourseChapter {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * name       db_column: name  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String name;
	
	private String coverUrl;
    /**
     * courseid       db_column: courseid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long courseid;
	private java.lang.String courseName;
    /**
     * 标签（形如1，2，3）       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
    /**
     * 赞数       db_column: praise_cnt  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer praiseCnt;
    /**
     * 章节类型（视频、录音）       db_column: type  
     * 
     * 
     * @Max(127)
     */	
	private Integer type;
    /**
     * 课程状态0未启用 1启用       db_column: enable  
     * 
     * 
     * @Max(127)
     */	
	private Integer enable;
    /**
     * 兴趣名称集合 逗号隔开       db_column: labelnames  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String labelnames;
	
	private int finishUserCnt;
	private int userCommentCnt;
	private String gradeId;
	private String gradeName;
	private String userid;
	
	private String teacherId;
	private String teacherName;
	
	private int isPraise;//是否点赞  0 1
	private Long videopoint;//视频观看进度
	
	private List<Account> userlist;
	
	private List<UserReply> commentList;
	
	private List<CourseChapter> recommendChapterList;
	
	private Video video;
	private List<Audio> audioList;
	private List<Picture> pictureList;
	//columns END

	public CourseChapter(){
	}

	public CourseChapter(
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
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setCourseid(java.lang.Long value) {
		this.courseid = value;
	}
	
	public java.lang.Long getCourseid() {
		return this.courseid;
	}
	public void setLabelids(java.lang.String value) {
		this.labelids = value;
	}
	
	public java.lang.String getLabelids() {
		return this.labelids;
	}
	public void setPraiseCnt(java.lang.Integer value) {
		this.praiseCnt = value;
	}
	
	public java.lang.Integer getPraiseCnt() {
		return this.praiseCnt;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setEnable(Integer value) {
		this.enable = value;
	}
	
	public Integer getEnable() {
		return this.enable;
	}
	public void setLabelnames(java.lang.String value) {
		this.labelnames = value;
	}
	
	public java.lang.String getLabelnames() {
		return this.labelnames;
	}

	public List<Account> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<Account> userlist) {
		this.userlist = userlist;
	}

	public int getFinishUserCnt() {
		return finishUserCnt;
	}

	public void setFinishUserCnt(int finishUserCnt) {
		this.finishUserCnt = finishUserCnt;
	}

	public int getUserCommentCnt() {
		return userCommentCnt;
	}

	public Long getVideopoint() {
		return videopoint;
	}

	public void setVideopoint(Long videopoint) {
		this.videopoint = videopoint;
	}

	public void setUserCommentCnt(int userCommentCnt) {
		this.userCommentCnt = userCommentCnt;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}

	public List<UserReply> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<UserReply> commentList) {
		this.commentList = commentList;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public List<Audio> getAudioList(){
		return audioList;
	}
	public void setAudioList(List<Audio> audioList){
		this.audioList = audioList;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}


	public List<CourseChapter> getRecommendChapterList() {
		return recommendChapterList;
	}

	public void setRecommendChapterList(List<CourseChapter> recommendChapterList) {
		this.recommendChapterList = recommendChapterList;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}

	public java.lang.String getCourseName() {
		return courseName;
	}

	public void setCourseName(java.lang.String courseName) {
		this.courseName = courseName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Courseid",getCourseid())
			.append("Labelids",getLabelids())
			.append("PraiseCnt",getPraiseCnt())
			.append("Type",getType())
			.append("Enable",getEnable())
			.append("Labelnames",getLabelnames())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CourseChapter == false) return false;
		if(this == obj) return true;
		CourseChapter other = (CourseChapter)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

