package com.hc360.mobileaccount.po;

public class QuestionAndPost {
	
	private String id;
	private String content;
	private String createtime;
	
	private String imageUrls;
	
	private String audioUrl;
	private Integer timeLength;
	private UserNreply userHotReply;
	
	private String nickname;
	private String headerimg;
	private Integer praiseCnt;
	private Integer replyCnt;
	
	private Integer type;//1问题 2帖子

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public Integer getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}

	public UserNreply getUserHotReply() {
		return userHotReply;
	}

	public void setUserHotReply(UserNreply userHotReply) {
		this.userHotReply = userHotReply;
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

	public Integer getPraiseCnt() {
		return praiseCnt;
	}

	public void setPraiseCnt(Integer praiseCnt) {
		this.praiseCnt = praiseCnt;
	}

	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "id=" + id + ", content=" + content
				+ ", createtime=" + createtime + ", imageUrls=" + imageUrls
				+ ", audioUrl=" + audioUrl + ", timeLength=" + timeLength
				+ ", userHotReply=" + userHotReply + ", nickname=" + nickname
				+ ", headerimg=" + headerimg + ", praiseCnt=" + praiseCnt
				+ ", replyCnt=" + replyCnt + ", type=" + type;
	}
	

}
