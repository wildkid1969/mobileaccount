/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:51
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.AccountMapper;
import com.hc360.mobileaccount.dao.GLMapper;
import com.hc360.mobileaccount.dao.MobileAccountMapper;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.BalanceLog;
import com.hc360.mobileaccount.po.GlData;
import com.hc360.mobileaccount.service.GuoLingService;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-19
 */
@Service
public class GuoLingServiceImpl implements GuoLingService {

	@Autowired
	private GLMapper glMapper;
	@Autowired
	private MobileAccountMapper maMapper;
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void insertUserGL(GlData data) throws Exception {
		glMapper.addAccount(data);
	}

	@Override
	public void callback(String uid) throws Exception {

	}

	@Override
	public GlData selectByPhone(String phone) throws Exception {
		return glMapper.selectByPhone(phone);
	}

	@Override
	public void updateGLBalanceLog(String phone, String flownumber, int length, int yzxbalance) {
		BalanceLog bLog = new BalanceLog();
		bLog.setPhone(phone);
		bLog.setLength(length);
		bLog.setYzxbalance(yzxbalance);
		bLog.setFlownumber(flownumber);
		maMapper.insertBalanceLog(bLog);
		accountMapper.clearYzxBalacne(phone);
	}

	public int getBalanceYZX(String phone) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "168");
		AccountYzx yzx = accountMapper.selectAccountYzx(map);
		if (yzx != null) {
			return Integer.valueOf(yzx.getBalance());
		} else {
			return 0;
		}
	}
}
