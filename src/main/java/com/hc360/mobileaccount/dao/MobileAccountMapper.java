/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:20:23
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.AccountGiveMin;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.BalanceLog;
import com.hc360.mobileaccount.po.CallLogStat;
import com.hc360.mobileaccount.po.CallStatExcelLog;
import com.hc360.mobileaccount.po.DownLoad;
import com.hc360.mobileaccount.po.HotWord;
import com.hc360.mobileaccount.po.JoinCorpMM;
import com.hc360.mobileaccount.po.MobileCallInfo;
import com.hc360.mobileaccount.po.NewsAndMsg;
import com.hc360.mobileaccount.po.PersonNewTag;
import com.hc360.mobileaccount.po.PersonTag;
import com.hc360.mobileaccount.po.PersonTagAction;
import com.hc360.mobileaccount.po.PersonTagContact;
import com.hc360.mobileaccount.po.PersonTagNewContact;
import com.hc360.mobileaccount.po.PersonTagNewRemark;
import com.hc360.mobileaccount.po.PersonTagRemark;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.po.SpringFestivalActivity;
import com.hc360.mobileaccount.po.SuggestCorp;

/**
 * 
 * 
 * @project mobileaccount
 * @author
 * @version 1.0
 * @date
 */
public interface MobileAccountMapper {
	List<PersonTag> selectPersonTagAll();
	void insertPersonTagNewContactByEntity(PersonTagNewContact personTagNewContact);
	void insertPersonTagNewRemarkByEntity(PersonTagNewRemark personTagNewRemark);
	void insertNewPersonTagByEntity(PersonNewTag personNewTag);

	List<PersonNewTag> selectPersonTags();
	
	void insertNewPersonTag(Map<String,Object> map);
	
	void insertNewContact(Map<String,Object> map);

	void insertNewRemark(Map<String,Object> map);

	int insert(MobileCallInfo mobileCallInfo);

	int update(MobileCallInfo mobileCallInfo);
	int selectCmidNewcount(String cmid);

	MobileCallInfo selectById(int id);
	void uptCompany(Map<String,Object> map);

	void insertBalanceLog(BalanceLog balanceLog);

	void insertAppCallLog(CallLogStat callLogStat);

	void updateAppCallLog(CallLogStat callLogStat);

	int countAppCallLog(String msgid);

	void insertPhoneFirend(Map<String, Object> map);
	
	void insertRecommendFriend(Map<String, Object> map);
	int selectfriendcount(String phone);
	int selectgivefriendcount(String phone);
	int selectchargemincount(String phone);
	
	int selectgivefriscount(Map<String, Object> map);
	
	List<AccountGiveMin> selectfriendInfo(String phone);
	List<AccountGiveMin> selectphonebyfriend(String phone);
	void updatefriendstate(String phone);
	void insertStatSearch(Map<String, Object> map);

	void insertSms(Map<String, Object> map);

	List<CallStatExcelLog> selectCallStatSwIsNotNull(Map<String, Object> map);
	
	List<CallStatExcelLog> selectCallStatYZXNotCall(Map<String, Object> map);

	List<String> selectBlackList();

	List<CallStatExcelLog> selectCallStatExcelLog(Map<String, Object> map);

	int selectYZXTimesByDay(String phone);
	
	void insertQiuGouInfo(Map<String,Object> map);
	
	int selectCountQiuGouInfo(Map<String,Object> map);
	
	void saveFeedback(Map<String,Object> map);
	int selectCmidcount(String cmid); 
	int joinCorp(JoinCorpMM joinCorp);
	
	int suggestCorp(SuggestCorp suggestCorp);
	void updateSendAction(int id);
	int insertPersonTag(Map<String,Object> map);
	int insertPersonNewTag(Map<String,Object> map);

	int deletePersonTag(String phone);
	
	List<PersonTag> selectPersonTag(String phone);
	List<PersonTag> selectPersonNewTag(String phone);

	int insertPersonTagRemark(Map<String,Object> map);
	
	int insertPersonTagNewRemark(Map<String,Object> map);
	
	int insertPersonTagAction(Map<String,Object> map);
	
	int insertPersonTagContact(Map<String,Object> map);
	int insertPersonTagNewContact(Map<String,Object> map);

	List<PersonTagContact> selectPersonTagContact(String cmid);
	
	void deletePersonTagContact(String cmid);
	
	void deletePersonTags(String cmid);
	void deletePersonNewTags(String cmid);
	List<PersonTagRemark> selectPersonTagRemark(String cmid);
	
	List<PersonTagNewContact> selectPersonTagNewContact(String cmid);

	List<PersonTagNewRemark> selectPersonTagNewRemark(String cmid);
	
	List<PersonTagAction> selectPersonTagAction(String cmid);

	void deletePersonTagRemark(String cmid);
	
	void deletePersonTagNewRemark(String cmid);
	void deletePersonTagNewContact(String cmid);

	void deletePersonTagAction(String cmid);
	 /*
	 *@param map 保存企业114活动相关信息， 参与人的手机phone 参与时间 marktime 活动类型 actiontype 参与结果 result
	 * */
	void insertLuckyResult(Map<String,Object> map);
	
	int countLuckyResult(Map<String,Object> map);
	
	void insertPush(PushClientInfo pushClientInfo);
	void delPushByPhone(String phone);
	PushClientInfo selectPushByPhone(String phone);
	public void updatePushClientInfo(PushClientInfo info);
	public void updatePushClientInfoTokenNull(PushClientInfo info);
	public void deletePushClientInfoByPhone(String phone);
	public List<PushClientInfo> getPushCilentInfoList();
	
	List<PersonTag> selectAllPersonTag();
	List<PersonTagAction> selectAllTagAction();
	void updateSend(int id);
	
	int isExistPersonTag(String phone);
	
	List<AccountYzx> selectPhoneBalanceZero();

	// 获得家乡繁荣度数据
	SpringFestivalActivity getCorpofHome(String city);

	// 更新指定城市的家乡繁荣度的分享次数
	void updateTaxic(String city);

	// 获得家乡繁荣度分享次数前三甲
	List<SpringFestivalActivity> getTaxicTop();

	//记录最后一次启动时间
	void insertFinalTime(SpringFestivalActivity po);
	String getDeviceid(Map<String,Object> map);
	void updateFinalTime(SpringFestivalActivity po);
	List<HotWord> getHotWord();
	
	DownLoad  selectDownloadUrl(String version); 
	 
	List<NewsAndMsg> selectNewsInfo(Map<String,Object> map); 
	
	NewsAndMsg	selectNewsInfoById(int id);

	
	int updateNewsInfo(NewsAndMsg newsInfo);

	int insertNewsInfo(NewsAndMsg newsInfo);

	int deleteNewsInfo(int id);

	NewsAndMsg selectPicture(Map<String,Object> map);
	List<NewsAndMsg> selectNewsCount();
	 
	List<NewsAndMsg> selectNewsTitle();

	NewsAndMsg selectAutoPicture();

	int selectRemarkcount(PersonTagNewRemark ptRemark);
	
	int selectActioncount(PersonTagAction ptaction);
	
	int selectContactcount(PersonTagNewContact ptcontact);
	//待办事项增删改
	int updateTagAction(PersonTagAction ptaction);
	int insertTagAction(PersonTagAction ptaction);
	int delPersonTagAction(PersonTagAction ptaction);
	//增删改用户管理模块的备注信息
	int insertTagRemark(PersonTagNewRemark ptRemark);
	int updateTagRemark(PersonTagNewRemark ptRemark);
	int delTagNewRemark(PersonTagNewRemark ptRemark);
	//新增修改删除联系人信息
	int insertTagNewContact(PersonTagNewContact ptContact);
	
	int updateTagNewContact(PersonTagNewContact ptContact) ;
	
	int delTagNewContact(PersonTagNewContact ptContact);
}
