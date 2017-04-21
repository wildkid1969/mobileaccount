package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserConcernObjectMapper;
import com.hc360.mobileaccount.po.UserConcernObject;
import com.hc360.mobileaccount.service.UserConcernObjectService;

@Service("userConcernObjectService")
public class UserConcernObjectServiceImpl implements UserConcernObjectService{
	@Resource
	private UserConcernObjectMapper userConcernObjectMapper;

	@Override
	public void insert(UserConcernObject obj) {
		userConcernObjectMapper.insert(obj);
	}

	@Override
	public void delete(UserConcernObject obj) {
		userConcernObjectMapper.delete(obj);
	}

	@Override
	public List<UserConcernObject> getUserConcernObjectList(
			UserConcernObject obj) {
		return userConcernObjectMapper.getUserConcernObjectList(obj);
	}

	@Override
	public int getUserConcernObjectCount(UserConcernObject obj) {
		return userConcernObjectMapper.getUserConcernObjectCount(obj);
	}
	
}
