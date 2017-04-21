package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineRegisterUser;

@Service("offlineRegisterUserService")
public interface OfflineRegisterUserService{
	public OfflineRegisterUser getById(Long id);
	public OfflineRegisterUser getByPhone(String phone);
	public void insert(OfflineRegisterUser user);
}
