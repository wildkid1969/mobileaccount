package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.PaccountGoodAdvise;

@Service("paccountGoodAdviseService")
public interface PaccountGoodAdviseService{
	public void insert(PaccountGoodAdvise advise);
}
