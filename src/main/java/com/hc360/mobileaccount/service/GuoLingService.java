/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:29
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service;

import com.hc360.mobileaccount.po.GlData;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18
 */
public interface GuoLingService {

	void insertUserGL(GlData data)  throws Exception;
	
	void callback(String uid) throws Exception;
	
	GlData selectByPhone(String phone) throws Exception;
	
	void updateGLBalanceLog(String phone,String flownumber,int length , int yzxbalacne);
	
	int getBalanceYZX(String phone);
}
