package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserReply;

@Service("userReplyService")
public interface UserReplyService{
	public void insert(UserReply reply);
	public UserReply getById(Long id);
	public void addPraiseCnt(Long id);
	public void reducePraiseCnt(Long id);
	
	/**
	 * 保存用户评论录音和回答提问的回复数据
	 * @param nreply
	 * @param reply
	 * @return
	 */
	public int saveUserReply(UserNreply nreply,UserReply reply);
	
}
