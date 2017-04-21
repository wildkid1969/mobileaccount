package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.OfflineUserRegister;

public interface OfflineUserRegisterMapper{
	public void insert(OfflineUserRegister r);
	public List<OfflineUserRegister> getOfflineUserRegisterList(OfflineUserRegister r);
}
