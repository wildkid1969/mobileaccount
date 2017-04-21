package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineRegisterUserMapper;
import com.hc360.mobileaccount.po.OfflineRegisterUser;
import com.hc360.mobileaccount.service.OfflineRegisterUserService;

@Service("offlineRegisterUserService")
public class OfflineRegisterUserServiceImpl implements OfflineRegisterUserService{
	@Resource
	private OfflineRegisterUserMapper offlineRegisterUserMapper;

	@Override
	public OfflineRegisterUser getById(Long id) {
		return offlineRegisterUserMapper.getById(id);
	}

	@Override
	public OfflineRegisterUser getByPhone(String phone) {
		return offlineRegisterUserMapper.getByPhone(phone);
	}

	@Override
	public void insert(OfflineRegisterUser user) {
		offlineRegisterUserMapper.insert(user);
	}
	
}
