package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserSignInMapper;
import com.hc360.mobileaccount.po.UserSignIn;
import com.hc360.mobileaccount.service.UserSignInService;

@Service("userSignInService")
public class UserSignInServiceImpl implements  UserSignInService{
	@Resource
	private UserSignInMapper userSignInMapper;

	@Override
	public void insert(UserSignIn in) {
		userSignInMapper.insert(in);
	}

	@Override
	public List<UserSignIn> getUserSignInList(UserSignIn in) {
		return userSignInMapper.getUserSignInList(in);
	}

	@Override
	public UserSignIn getUserSignInByTime(UserSignIn in) {
		return userSignInMapper.getUserSignInByTime(in);
	}
	
}
