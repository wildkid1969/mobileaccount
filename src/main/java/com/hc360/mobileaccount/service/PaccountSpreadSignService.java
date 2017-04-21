package com.hc360.mobileaccount.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.PaccountSpreadSign;

@Service("paccountSpreadSignService")
public interface PaccountSpreadSignService{
	public PaccountSpreadSign getSpreadSignByParam(Map<String,Object> map);
	public void insert(PaccountSpreadSign param);
	
}
