package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.PaccountFreechargeLog;

public interface PaccountFreechargeLogService{
	public PaccountFreechargeLog getByPhone(Map<String,Object> map);
	public void insert(PaccountFreechargeLog param);
	public List<PaccountFreechargeLog> getFreechargeLogList(Map<String,Object> map);
	public void updateState(Map<String,Object> map);
}
