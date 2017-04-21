package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserConcernMapper;
import com.hc360.mobileaccount.po.UserConcern;
import com.hc360.mobileaccount.service.UserConcernService;

@Service("userConcernService")
public class UserConcernServiceImpl implements UserConcernService{
	@Resource
	private UserConcernMapper userConcernMapper;

	@Override
	public void insert(UserConcern con) {
		userConcernMapper.insert(con);
	}

	@Override
	public void delete(UserConcern con) {
		userConcernMapper.delete(con);
	}

	@Override
	public List<UserConcern> getUserConcernList(UserConcern con) {
		return userConcernMapper.getUserConcernList(con);
	}

	@Override
	public List<UserConcern> getFriendList(UserConcern con) {
		return userConcernMapper.getFriendList(con);
	}
	
}
