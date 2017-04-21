package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.PaccountFreechargeLogMapper;
import com.hc360.mobileaccount.po.PaccountFreechargeLog;
import com.hc360.mobileaccount.service.PaccountFreechargeLogService;

@Service("paccountFreechargeLogService")
public class PaccountFreechargeLogServiceImpl implements  PaccountFreechargeLogService{
	@Autowired
	private PaccountFreechargeLogMapper paccountFreechargeLogMapper;

	@Override
	public PaccountFreechargeLog getByPhone(Map<String,Object> map) {
		return paccountFreechargeLogMapper.getByPhone(map);
	}

	@Override
	public void insert(PaccountFreechargeLog param) {
		paccountFreechargeLogMapper.insert(param);
	}

	@Override
	public List<PaccountFreechargeLog> getFreechargeLogList(Map<String,Object> map) {
		return paccountFreechargeLogMapper.getFreechargeLogList(map);
	}

	@Override
	public void updateState(Map<String,Object> map) {
		paccountFreechargeLogMapper.updateState(map);
	}
	
	
}
