package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineUserRegister;

@Service("offlineUserRegisterService")
public interface OfflineUserRegisterService{
	public void insert(OfflineUserRegister r);
	public List<OfflineUserRegister> getOfflineUserRegisterList(OfflineUserRegister r);
	
}
