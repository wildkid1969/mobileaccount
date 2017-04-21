package com.hc360.mobileaccount.dao;

import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserReply;

public interface UserReplyMapper{
	public void insert(UserReply reply);
	public UserReply getById(Long id);
	public void addPraiseCnt(Long id);
	public void reducePraiseCnt(Long id);
	
	public void saveUserReply(UserNreply nreply,UserReply reply);
	
}
