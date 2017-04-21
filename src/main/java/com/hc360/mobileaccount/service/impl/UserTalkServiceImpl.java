package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserTalkMapper;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.service.UserTalkService;

@Service("userTalkService")
public class UserTalkServiceImpl implements UserTalkService{
	@Resource
	private UserTalkMapper userTalkMapper;

	@Override
	public void insert(UserTalk talk) {
		userTalkMapper.insert(talk);
	}

	@Override
	public List<UserTalk> getUserTalkList(UserTalk talk) {
		return userTalkMapper.getUserTalkList(talk);
	}

	@Override
	public int getUserTalkCount(UserTalk talk) {
		return userTalkMapper.getUserTalkCount(talk);
	}

	@Override
	public List<Account> getTodayTalkUsers(String dateStr) {
		return userTalkMapper.getTodayTalkUsers(dateStr);
	}

	@Override
	public UserTalk getById(Long id) {
		return userTalkMapper.getById(id);
	}
	
}
