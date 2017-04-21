package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.PaccountFreechargeLog;


public interface PaccountFreechargeLogMapper{
	public void insert(PaccountFreechargeLog param);
	public PaccountFreechargeLog getByPhone(Map<String,Object> map);
	public List<PaccountFreechargeLog> getFreechargeLogList(Map<String,Object> map);
	public void updateState(Map<String,Object> map);
}
