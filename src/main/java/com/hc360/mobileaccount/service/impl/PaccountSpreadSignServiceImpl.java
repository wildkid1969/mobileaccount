package com.hc360.mobileaccount.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.PaccountSpreadSignMapper;
import com.hc360.mobileaccount.po.PaccountSpreadSign;
import com.hc360.mobileaccount.service.PaccountSpreadSignService;

@Service("paccountSpreadSignService")
public class PaccountSpreadSignServiceImpl implements  PaccountSpreadSignService{
	@Resource
	private PaccountSpreadSignMapper paccountSpreadSignMapper;

	@Override
	public PaccountSpreadSign getSpreadSignByParam(Map<String, Object> map) {
		return paccountSpreadSignMapper.getSpreadSignByParam(map);
	}

	@Override
	public void insert(PaccountSpreadSign param) {
		paccountSpreadSignMapper.insert(param);
	}
	
}
