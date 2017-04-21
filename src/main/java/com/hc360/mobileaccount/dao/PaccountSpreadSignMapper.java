package com.hc360.mobileaccount.dao;

import java.util.Map;

import com.hc360.mobileaccount.po.PaccountSpreadSign;

public interface PaccountSpreadSignMapper{
	public PaccountSpreadSign getSpreadSignByParam(Map<String,Object> map);
	public void insert(PaccountSpreadSign param);
}
