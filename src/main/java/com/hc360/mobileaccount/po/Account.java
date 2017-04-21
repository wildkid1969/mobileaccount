package com.hc360.mobileaccount.po;


public class Account {
	
	
	/**
	 * @用户数字账号 系统分配
	 * */
	private Integer accountid;
	
	// 是否完善过个人信息  1 完善过 0 未完善
	private Integer ismodify;
	
	/**
	 * @用户密码
	 * */
	
	private String secret;
	
	/**
	 * @手机号登录
	 * */
	private String phone;
	
	/**
	 * @用户公司名称
	 * */	
	private String corpname;
	
	/**
	 * @联系方式
	 * */
	private String linkphone;
	
	/**
	 * @职位
	 * */	
	private String aposition;
	/**
	 * @行业方向
	 * */	
	private String mainind;
	
	/**
	 * @工作职责
	 * */	
	private String jobcontent;
	
	/**
	 * @用户名登录
	 * */
	private String username;
	
	/**
	 * @用户昵称
	 * */
	private String nickname;
	
	/**
	 * @性别 1 男 2 女
	 * */
	private Integer sex;
	
	/**
	 * @用户地址
	 * */
	private String address;
	
	/**
	 * @E-Mail
	 * */
	private String email;
	
	/**
	 * @创建时间
	 * */
	private String createtime;
	
	/**
	 *头像
	 * */
	private String headerimg;
	
	/**
	 * 是否有效 1 有效 0 无效
	 * */
	private Integer enable;
	
	/**
	 * 邀请码
	 * */
	private String invitation;
	
	/**
     * 工作地区       db_column: area  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String area;
    /**
     * 兴趣标签id，以逗号分隔       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
	private java.lang.String labelnames;
    /**
     * 工作年限       db_column: work_age  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer workAge;
    /**
     * 登陆时间       db_column: logintime  
     * 
     * 
     * 
     */	
	private java.util.Date logintime;
    /**
     * 用户类型0普通 1老师       db_column: type  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer userType;
    /**
     * 用户等级       db_column: level  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer level;
	private String levelName;
	
	 /**
     * 电话       db_column: phonenum  
     * 
     * 
     * @NotBlank @Length(max=20)
     */	
	private java.lang.String phonenum;
    /**
     * b_c 个数       db_column: b_c_num  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer bcnum;
    /**
     * 0 未开通  1 已开通  资质查询       db_column: infoseach  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Integer infoseach;
	
	/**
	 * 学习进度
	 */
	private Integer finishStudyCnt;
	
	private String umengid;
	
	/**
	 * 下一等级
	 */
	private String nextLevel;
	
	/**
	 * 用户签到的星期 如1，2，3
	 */
	private String userSignInWeeks;
	
	/**
	 * 等级章节总数
	 */
	private String levelTotal;
	
	/**
	 * 用户获得的积分数
	 */
	private String userScore;
	
	/**
	 * 云之讯 应用生成ID
	 * */
	private String appId;
	
	/**
	 * 注册类型
	 * */
	private String apptype; // 168 114 (168已废弃)
	
	/**
	 * 云之讯 client名称 有字母与数字组成
	 * */
	private String friendlyName;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public void setHeaderimg(String headerimg) {
		this.headerimg = headerimg;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getHeaderimg() {
		return headerimg;
	}

	public String getUmengid() {
		return umengid;
	}

	public void setUmengid(String umengid) {
		this.umengid = umengid;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String type) {
		this.apptype = type;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getMainind() {
		return mainind;
	}

	public void setMainind(String mainind) {
		this.mainind = mainind;
	}

	public String getJobcontent() {
		return jobcontent;
	}

	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}

	public String getAposition() {
		return aposition;
	}

	public void setAposition(String aposition) {
		this.aposition = aposition;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public Integer getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(Integer ismodify) {
		this.ismodify = ismodify;
	}

	public java.lang.String getArea() {
		return area;
	}

	public void setArea(java.lang.String area) {
		this.area = area;
	}

	public java.lang.String getLabelids() {
		return labelids;
	}

	public void setLabelids(java.lang.String labelids) {
		this.labelids = labelids;
	}

	public java.lang.Integer getWorkAge() {
		return workAge;
	}

	public void setWorkAge(java.lang.Integer workAge) {
		this.workAge = workAge;
	}

	public java.util.Date getLogintime() {
		return logintime;
	}

	public void setLogintime(java.util.Date logintime) {
		this.logintime = logintime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public java.lang.Integer getLevel() {
		return level;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public java.lang.String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(java.lang.String phonenum) {
		this.phonenum = phonenum;
	}

	public java.lang.Integer getBcnum() {
		return bcnum;
	}

	public void setBcnum(java.lang.Integer bcnum) {
		this.bcnum = bcnum;
	}

	public java.lang.Integer getInfoseach() {
		return infoseach;
	}

	public void setInfoseach(java.lang.Integer infoseach) {
		this.infoseach = infoseach;
	}

	public Integer getFinishStudyCnt() {
		return finishStudyCnt;
	}

	public void setFinishStudyCnt(Integer finishStudyCnt) {
		this.finishStudyCnt = finishStudyCnt;
	}

	public String getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(String nextLevel) {
		this.nextLevel = nextLevel;
	}

	public String getUserSignInWeeks() {
		return userSignInWeeks;
	}

	public void setUserSignInWeeks(String userSignInWeeks) {
		this.userSignInWeeks = userSignInWeeks;
	}

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public String getLevelTotal() {
		return levelTotal;
	}

	public void setLevelTotal(String levelTotal) {
		this.levelTotal = levelTotal;
	}

	public java.lang.String getLabelnames() {
		return labelnames;
	}

	public void setLabelnames(java.lang.String labelnames) {
		this.labelnames = labelnames;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


}
