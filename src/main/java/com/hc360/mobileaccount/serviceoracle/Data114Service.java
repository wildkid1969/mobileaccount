/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:29
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.serviceoracle;

import java.util.List;

import com.hc360.mobileaccount.po.CorpInfo; 
import com.hc360.mobileaccount.po.ResultOfCorpInfo;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18
 */
public interface Data114Service {
	List<CorpInfo> selectCorpInfo(String corpName);

	int updateCorpInfo(CorpInfo corpInfo);
	
	int insertCorpInfo(CorpInfo corpInfo);

	int deleteCorpInfo(long corpid);

	ResultOfCorpInfo selectLikeCorpInfo(String corpName,int begin, int end);
	
	/**
	 * @param phone 呗拨叫电话
	 * @param mark 是否打通 1 打通 2 未打通
	 * */
	void updateComm(String phone, String mark);

	String getCorpInfoByMp(String mp); 

	CorpInfo selectCorpInfoByid(String corpid); 

	/**
	 * 查询相似企业Service
	 */
	String selectSimilarCorpInfo(String w, String corpName, String lon, String lat);

	/**
	 * 排序企业临时表 Service
	 */
	String corpInfoSort();

	/*
	 * 搜索无结果时调用的企业搜索接口
	 */
	String getCorpInfoForNoData(String w,String lon,String lat,String n,String e);
	
	/*
	 * 通过公司ID取得公司信息
	 */
	String getCorpInfoById(String corpID);

	/*
	 * 企业浏览记录Service
	 */
	String accessCorpInfo(String corpName);
}