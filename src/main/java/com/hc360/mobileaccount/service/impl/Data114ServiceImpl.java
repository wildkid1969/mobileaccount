/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:51
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.daooracle.Data114Mapper;
import com.hc360.mobileaccount.po.CorpInfo;
import com.hc360.mobileaccount.po.CorpInfoExt;
import com.hc360.mobileaccount.po.LinkBaseInfo;
import com.hc360.mobileaccount.po.QGInfoEntity;
import com.hc360.mobileaccount.po.ResultOfCorpInfo;
import com.hc360.mobileaccount.po.SearchCorpInfo;
import com.hc360.mobileaccount.po.SearchCorpInfoList;
import com.hc360.mobileaccount.serviceoracle.Data114Service;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-19
 */
@Service
public class Data114ServiceImpl implements Data114Service {

	@Autowired
	private Data114Mapper data114Mapper;

	/*
	 * 企业浏览记录Service
	 */
	@Override
	public String accessCorpInfo(String corpName) {
		List<CorpInfo> corpInfoList = data114Mapper.selectCorpInfo(corpName);
		if(corpInfoList != null && corpInfoList.size() > 0){
			CorpInfo corpInfo = corpInfoList.get(0);
			CorpInfoExt corpInfoExt = new CorpInfoExt();
			corpInfoExt.setCorpid(corpInfo.getCorpid());

			List<CorpInfoExt> corpInfoExtList = data114Mapper.selectAccessCorp(corpInfoExt.getCorpid());
			if(corpInfoExtList != null && corpInfoExtList.size() > 0){
				corpInfoExt = corpInfoExtList.get(0);
				corpInfoExt.setAccessnum(corpInfoExt.getAccessnum()+1);
				data114Mapper.updateAccessCorp(corpInfoExt);
			}else{
				corpInfoExt.setAccessnum(1);
				data114Mapper.saveAccessCorp(corpInfoExt);
			}
		}

		return "OK";
	}

	/*
	 * 搜索无结果时调用的企业搜索接口
	 */
	@Override
	public String getCorpInfoForNoData(String w, String lon, String lat, String n, String e) {
		SearchCorpInfoList searchCorpInfoList = new SearchCorpInfoList();
		String url = "http://s.hc360.com/getmmtlast.cgi?mc=seller&sys=sd&q=1&w=" + w + "&n=" + n + "&e=" + e;
		String json = MobileAccountUtils.doGet(url, "gbk");
		QGInfoEntity qgInfoEntity = MobileAccountUtils.getGson().fromJson(json, QGInfoEntity.class);
		if (qgInfoEntity != null && qgInfoEntity.getSearchResultInfo() != null
				&& qgInfoEntity.getSearchResultInfo().size() > 0) {
			String[] usernames = new String[qgInfoEntity.getSearchResultInfo().size()];
			for (int i = 0; i < qgInfoEntity.getSearchResultInfo().size(); i++) {
				usernames[i] = qgInfoEntity.getSearchResultInfo().get(i).getSearchResultfoUserName();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lon", lon);
			map.put("lat", lat);
			map.put("usernames", usernames);
			List<SearchCorpInfo> searchCorpInfos = data114Mapper.selectCorpInfoByUserNames(map);
			try {
				searchCorpInfoList.setSearchKeyWordMd5(MD5.md5Encode(w));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			searchCorpInfoList.setSearchResultfoAllNum(qgInfoEntity.getSearchResultfoAllNum());
			searchCorpInfoList.setSearchResultfoNum(qgInfoEntity.getSearchResultfoNum());
			searchCorpInfoList.setSearchResultInfo(searchCorpInfos);
		} 

		return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
	}

	/*
	 * 通过公司ID取得公司信息
	 */
	@Override
	public String getCorpInfoById(String corpID) {
		SearchCorpInfo searchCorpInfo = data114Mapper.getCorpInfoById(corpID);
		return MobileAccountUtils.getGson().toJson(searchCorpInfo);
	}

	@Override
	public List<CorpInfo> selectCorpInfo(String corpName) {
		return data114Mapper.selectCorpInfo(corpName);
	}

	@Override
	public int updateCorpInfo(CorpInfo corpInfo) {
		return data114Mapper.updateCorpInfo(corpInfo);
	}

	@Override
	public int insertCorpInfo(CorpInfo corpInfo) {
		if(data114Mapper.selectCountByName(corpInfo.getCorpname()) > 0){
			return -1;
		}
		return data114Mapper.insertCorpInfo(corpInfo);
	}

	@Override
	public int deleteCorpInfo(long corpid) {
		return data114Mapper.deleteCorpInfo(corpid);
	}

	@Override
	public ResultOfCorpInfo selectLikeCorpInfo(String corpName, int begin, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResultOfCorpInfo r = new ResultOfCorpInfo();
		map.put("corpname", corpName);
		map.put("begin", begin);
		map.put("end", end);
		List<CorpInfo> corpInfoList = data114Mapper.selectLikeCorpInfo(map);
		r.setPageNum(begin);
		r.setPagesize(end-begin+1);
		r.setCorpInfoList(corpInfoList);
		r.setAllNum(data114Mapper.countNum(corpName));
		return r;
	}

	public class RunnableService implements Runnable {

		private long corpid;

		private String mark;

		@Override
		public void run() {
			Map<String, Object> map = new Hashtable<String, Object>();
			map.put("corpid", corpid);
			map.put("mark", mark);
			data114Mapper.updateComm(map);
		}

		public RunnableService(long corpid, String mark) {
			this.corpid = corpid;
			this.mark = mark;
		}

		public long getCorpid() {
			return corpid;
		}

		public void setCorpid(long corpid) {
			this.corpid = corpid;
		}

		public String getMark() {
			return mark;
		}

		public void setMark(String mark) {
			this.mark = mark;
		}

	}

	@Override
	public void updateComm(String phone, String mark) {
		String ccc = MobileAccountUtils.sendGet("http://openapi.m.hc360.com/openapi/v1/Corplib/linkbaseinfo/",
				RC4.encry_RC4_string(phone, RC4.KEY));
		LinkBaseInfo baseInfo = MobileAccountUtils.getGson().fromJson(ccc, LinkBaseInfo.class);
		if (baseInfo != null && baseInfo.getBaseinfos() != null) {
			try {
				MobileAccountUtils.doExecutorsService(new RunnableService(baseInfo.getBaseinfos().get(0).getCorpid(),
						mark));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getCorpInfoByMp(String mp) {
		List<String> tmp = new ArrayList<String>(); 
		List<String> corpNames = data114Mapper.getCorpInfoByMp(mp);
		if(corpNames.size() <= 6){
			tmp = corpNames;
		}else{
			int rdm = (int)Math.floor(Math.random()*corpNames.size());
			for(int i=rdm; tmp.size()<6; i++){
				tmp.add(corpNames.get(i%corpNames.size()));
			}
		}
		return MobileAccountUtils.getGson().toJson(tmp);
	}

	@Override
	public CorpInfo selectCorpInfoByid(String corpid) {
		return data114Mapper.selectCorpInfoByid(corpid);
	}

	/**
	 * 排序企业临时表   减少由排序导致的查询等待时间
	 */
	@Override
	public String corpInfoSort() {
		Map<String,String> map = new HashMap<String, String>();
		data114Mapper.corpInfoSort(map);

		return map.get("OUT_RETURN");
	}

	/**
	 * 获取相似企业Service
	 */
	@Override
	public String selectSimilarCorpInfo(String w, String corpName, String lon, String lat) {
		SearchCorpInfoList searchCorpInfoList = new SearchCorpInfoList();
		List<CorpInfo> corpInfoList = data114Mapper.selectCorpInfo(corpName);
		Map<String, Object> map = new HashMap<String, Object>();
		// 同省份的相似企业
		map.put("corpid", corpInfoList.get(0).getCorpid());
		map.put("province", corpInfoList.get(0).getProvince());
		map.put("lon", lon);
		map.put("lat", lat);
		map.put("w", w);
		List<SearchCorpInfo> searchCorpInfos = data114Mapper.selectSimilarCorpInfo(map);
		if(searchCorpInfos != null && searchCorpInfos.size() >= 10){
			searchCorpInfoList.setSearchResultInfo(searchCorpInfos);
			return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
		}

		// 全国的相似企业
		map.remove("province");
		searchCorpInfos = data114Mapper.selectSimilarCorpInfo(map);
		if(searchCorpInfos != null && searchCorpInfos.size() >= 10){
			searchCorpInfoList.setSearchResultInfo(searchCorpInfos);
			return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
		}

		// 同行业同省份的相似企业
		map.remove("w");
		map.put("province", corpInfoList.get(0).getProvince());
		map.put("industry", corpInfoList.get(0).getMainarea());
		searchCorpInfos = data114Mapper.selectSimilarCorpInfo(map);
		if(searchCorpInfos != null && searchCorpInfos.size() >= 10){
			searchCorpInfoList.setSearchResultInfo(searchCorpInfos);
			return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
		}

		// 同行业全国的相似企业
		map.remove("province");
		searchCorpInfos = data114Mapper.selectSimilarCorpInfo(map);
		if(searchCorpInfos != null && searchCorpInfos.size() > 0){
			searchCorpInfoList.setSearchResultInfo(searchCorpInfos);
			return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
		}

		return MobileAccountUtils.getGson().toJson(searchCorpInfoList);
	}
}