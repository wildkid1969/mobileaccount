package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserMessage;

@Service("userMessageService")
public interface UserMessageService{
	public void insert(UserMessage msg);
	public int getUserMessageCount(UserMessage msg);
	public List<UserMessage> getUserMessageList(UserMessage msg);
	
	public int getUserChatMessageCount(UserMessage msg);
	public List<UserMessage> getUserChatMessageList(UserMessage msg);
}
