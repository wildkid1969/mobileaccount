package com.hc360.mobileaccount.po;

import java.util.List;

public class UserComment {
	private Long id;
	private Long userid;
	private String headerimg;
	private String nickname;
	private String corpname;
	private String content;
	private String createtime;
	private String praiseCnt;
	private Long chapterId;
	private String chapterName;
	private Long courseId;
	private String courseName;
	private List<UserNreply> userNreplys;
	private List<UserPraise> userPraises;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getHeaderimg() {
		return headerimg;
	}
	public void setHeaderimg(String headerimg) {
		this.headerimg = headerimg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(String praiseCnt) {
		this.praiseCnt = praiseCnt;
	}
	public Long getChapterId() {
		return chapterId;
	}
	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
}