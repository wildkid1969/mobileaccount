package com.hc360.mobileaccount.dao;

import com.hc360.mobileaccount.po.OfflineRegisterUser;

public interface OfflineRegisterUserMapper{
	public OfflineRegisterUser getById(Long id);
	public OfflineRegisterUser getByPhone(String phone);
	public void insert(OfflineRegisterUser user);
}
