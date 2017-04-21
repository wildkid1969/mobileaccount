/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:20:23
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.daooracle;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.CorpInfo;
import com.hc360.mobileaccount.po.CorpInfoExt;
import com.hc360.mobileaccount.po.SearchCorpInfo;   
/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18    
 */
public interface Data114Mapper {
	List<CorpInfo> selectCorpInfo(String corpname);

	int selectCountByName(String corpname);

	List<CorpInfo> selectLikeCorpInfo(Map<String, Object> map);

	void corpInfoSort(Map<String, String> map);
	
	List<SearchCorpInfo> selectSimilarCorpInfo(Map<String, Object> map);

	SearchCorpInfo getCorpInfoById(String corpID);

	int updateCorpInfo(CorpInfo corpInfo);

	int insertCorpInfo(CorpInfo corpInfo);

	int deleteCorpInfo(long corpid);

	int countNum(String corpname);

	void updateComm(Map<String,Object> map);
	
	List<String> getCorpInfoByMp(String mp);

	CorpInfo selectCorpInfoByid(String corpid);

	List<SearchCorpInfo> selectCorpInfoByUserNames(Map<String,Object> map);

	List<CorpInfoExt> selectAccessCorp(long corpid);

	int saveAccessCorp(CorpInfoExt corpInfoExt);

	int updateAccessCorp(CorpInfoExt corpInfoExt);
}