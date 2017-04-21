package com.hc360.mobileaccount.po;

public class Teacher{
	
	private Long id;
	
	private Long userid;
	
	private String username;
	
	private String labelids;
	
	private String labelnames;
	
	/*
	 * 讲师背景图
	 */
	private String imgUrl;

	/*
	 * 讲师描述
	 */
	private String description;

	/*
	 * 默认 已认证
	 */
	private Boolean isAuth = true;

	/*
	 * 课程被评论数
	 */
	private Integer comments;

	/*
	 * 粉丝数
	 */
	private Integer fans;
	
	/**
	 * 课程数
	 */
	private Integer coursecnt;
	
	private Integer isSub;
	
	private Account user;//老师对应的普通用户
	
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getFans() {
		return fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}

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

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLabelids() {
		return labelids;
	}

	public void setLabelids(String labelids) {
		this.labelids = labelids;
	}

	public String getLabelnames() {
		return labelnames;
	}

	public void setLabelnames(String labelnames) {
		this.labelnames = labelnames;
	}

	public Integer getCoursecnt() {
		return coursecnt;
	}

	public void setCoursecnt(Integer coursecnt) {
		this.coursecnt = coursecnt;
	}

	public Integer getIsSub() {
		return isSub;
	}

	public void setIsSub(Integer isSub) {
		this.isSub = isSub;
	}
}