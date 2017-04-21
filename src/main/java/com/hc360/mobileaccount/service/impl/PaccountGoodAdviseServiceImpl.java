package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.PaccountGoodAdviseMapper;
import com.hc360.mobileaccount.po.PaccountGoodAdvise;
import com.hc360.mobileaccount.service.PaccountGoodAdviseService;

@Service("paccountGoodAdviseService")
public class PaccountGoodAdviseServiceImpl implements  PaccountGoodAdviseService{
	@Resource
	private PaccountGoodAdviseMapper paccountGoodAdviseMapper;

	@Override
	public void insert(PaccountGoodAdvise advise) {
		paccountGoodAdviseMapper.insert(advise);
	}
	
}
