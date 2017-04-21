package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineUserRegisterMapper;
import com.hc360.mobileaccount.po.OfflineUserRegister;
import com.hc360.mobileaccount.service.OfflineUserRegisterService;

@Service("offlineUserRegisterService")
public class OfflineUserRegisterServiceImpl implements OfflineUserRegisterService{
	@Resource
	private OfflineUserRegisterMapper offlineUserRegisterMapper;

	@Override
	public void insert(OfflineUserRegister r) {
		offlineUserRegisterMapper.insert(r);
	}

	@Override
	public List<OfflineUserRegister> getOfflineUserRegisterList(
			OfflineUserRegister r) {
		return offlineUserRegisterMapper.getOfflineUserRegisterList(r);
	}
	
}
