package com.hc360.mobileaccount.po;

import java.util.List;

public class UserNreply extends UserReply{
    /**
     * 回复对象id 如talkid、questionid       db_column: objectid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long objectid;
    /**
     * userid       db_column: userid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long userid;
	/**
	 * username
	 * 
	 * 
	 * @NotNull 
	 */	
	private java.lang.String nickname;
	/**
	 * headerimg
	 * 
	 * 
	 * @NotNull 
	 */	
	private java.lang.String headerimg;
    /**
     * 回复评论id       db_column: linkid  
     * 
     * 
     * 
     */	
	private java.lang.Long linkid;
	/**
	 * 被回复者
	 * 
	 * 
	 * 
	 */	
	private java.lang.String linkname;
    /**
     * 评论id       db_column: replyid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long replyid;
    /**
     * 类型1评测 2评论       db_column: reply_type  
     * 
     * 
     * @Max(127)
     */	
	private Integer replyType;
    /**
     * 用户类型       db_column: user_type  
     * 
     * 
     * @Max(127)
     */	
	private Integer userType;
	
	private Integer replyCnt;
	
	private Integer isPraise;//当前用户是否赞了 0否 1是
	
	private Integer size;
	
	private List<UserPraise> praiseList;
	
	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	private String labelids;
	private String labelnames;
	//columns END

	public void setObjectid(java.lang.Long value) {
		this.objectid = value;
	}
	
	public java.lang.Long getObjectid() {
		return this.objectid;
	}
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}
	
	public java.lang.Long getUserid() {
		return this.userid;
	}
	public void setLinkid(java.lang.Long value) {
		this.linkid = value;
	}
	
	public java.lang.Long getLinkid() {
		return this.linkid;
	}
	public void setReplyid(java.lang.Long value) {
		this.replyid = value;
	}
	
	public java.lang.Long getReplyid() {
		return this.replyid;
	}

	public void setReplyType(Integer value) {
		this.replyType = value;
	}
	
	public Integer getReplyType() {
		return this.replyType;
	}
	public void setUserType(Integer value) {
		this.userType = value;
	}
	
	public Integer getUserType() {
		return this.userType;
	}

	public java.lang.String getNickname() {
		return nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getHeaderimg() {
		return headerimg;
	}

	public void setHeaderimg(java.lang.String headerimg) {
		this.headerimg = headerimg;
	}

	public java.lang.String getLinkname() {
		return linkname;
	}

	public void setLinkname(java.lang.String linkname) {
		this.linkname = linkname;
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getIsPraise() {
		return isPraise;
	}

	public List<UserPraise> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<UserPraise> praiseList) {
		this.praiseList = praiseList;
	}

	public void setIsPraise(Integer isPraise) {
		this.isPraise = isPraise;
	}
}