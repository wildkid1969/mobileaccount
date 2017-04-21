package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserMessage;

public interface UserMessageMapper{
	public void insert(UserMessage msg);
	public int getUserMessageCount(UserMessage msg);
	public List<UserMessage> getUserMessageList(UserMessage msg);
	
	public int getUserChatMessageCount(UserMessage msg);
	public List<UserMessage> getUserChatMessageList(UserMessage msg);
}
