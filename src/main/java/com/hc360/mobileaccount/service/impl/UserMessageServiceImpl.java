package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserMessageMapper;
import com.hc360.mobileaccount.po.UserMessage;
import com.hc360.mobileaccount.service.UserMessageService;

@Service("userMessageService")
public class UserMessageServiceImpl implements UserMessageService{
	@Resource
	private UserMessageMapper userMessageMapper;

	@Override
	public void insert(UserMessage msg) {
		userMessageMapper.insert(msg);
	}

	@Override
	public List<UserMessage> getUserMessageList(UserMessage msg) {
		return userMessageMapper.getUserMessageList(msg);
	}

	@Override
	public int getUserMessageCount(UserMessage msg) {
		return userMessageMapper.getUserMessageCount(msg);
	}

	@Override
	public int getUserChatMessageCount(UserMessage msg) {
		return userMessageMapper.getUserChatMessageCount(msg);
	}

	@Override
	public List<UserMessage> getUserChatMessageList(UserMessage msg) {
		return userMessageMapper.getUserChatMessageList(msg);
	}
	
}
