
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:20:23
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.dao;

import com.hc360.mobileaccount.po.GlData;



/**
 * 
 * 
 * @project mobileaccount
 * @author 
 * @version 1.0
 * @date   
 */
public interface GLMapper {	
	
	int countPhone(String phone);
	
	int addAccount(GlData glData);
	
	int updateAccount(GlData glData);
	
	GlData selectByPhone(String phone);
}
