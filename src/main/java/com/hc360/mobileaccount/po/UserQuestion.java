package com.hc360.mobileaccount.po;

import java.util.List;

public class UserQuestion {
	private Long id;
	private Long userid;
	private String title;//用户的问题
	private String content;//问题描述
	private String audioUrl;//语音描述
	private Integer timeLength;//语音时长
	private String createtime;
	private Integer praiseCnt;
	private String headerimg;
	private String nickname;
	private String corpname;
    /**
     * 标签id集合 逗号隔开       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
    /**
     * 标签名称集合 逗号隔开       db_column: labelnames  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelnames;
	
	private Integer isTop;
	
	private Integer replyCnt;
	private Integer isConcern;//当前用户是否关注了该问题 0否 1是
	private Integer concernCnt;//关注数
	private Integer myReplyCnt;//我的回复数
	
	private Account user;
	
	private List<UserNreply> userNreplys;
	private List<UserPraise> userPraises;
	
	private UserNreply userHotReply;
	

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
	public Integer getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(Integer praiseCnt) {
		this.praiseCnt = praiseCnt;
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
	public List<UserNreply> getUserNreplys() {
		return userNreplys;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public Integer getMyReplyCnt() {
		return myReplyCnt;
	}
	public void setMyReplyCnt(Integer myReplyCnt) {
		this.myReplyCnt = myReplyCnt;
	}
	public Integer getConcernCnt() {
		return concernCnt;
	}
	public void setConcernCnt(Integer concernCnt) {
		this.concernCnt = concernCnt;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}
	public java.lang.String getLabelids() {
		return labelids;
	}
	public Integer getIsConcern() {
		return isConcern;
	}
	public void setIsConcern(Integer isConcern) {
		this.isConcern = isConcern;
	}
	public Account getUser() {
		return user;
	}
	public UserNreply getUserHotReply() {
		return userHotReply;
	}
	public Integer getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}
	public void setUserHotReply(UserNreply userHotReply) {
		this.userHotReply = userHotReply;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public void setLabelids(java.lang.String labelids) {
		this.labelids = labelids;
	}
	public java.lang.String getLabelnames() {
		return labelnames;
	}
	public void setLabelnames(java.lang.String labelnames) {
		this.labelnames = labelnames;
	}
}