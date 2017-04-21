
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:20:23
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.dao;


import java.util.Map;

import com.hc360.mobileaccount.po.YzxCall;

/**
 * 
 * 
 * @project mobileaccount
 * @author 
 * @version 1.0
 * @date   
 */
public interface YzxMapper {
	
	int insertCallReq(YzxCall callReq);
	
	int consumeTime(YzxCall callReq);
	
	int insertCallInfo();
	
	int selectCountByClientNumber(Map<String,Object> map);
	
}
