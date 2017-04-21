/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:29
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.AccountGiveMin;
import com.hc360.mobileaccount.po.AccountSecret;
import com.hc360.mobileaccount.po.AccountYZM;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.CallLogStat; 
import com.hc360.mobileaccount.po.DownLoad;
import com.hc360.mobileaccount.po.JoinCorpMM;
import com.hc360.mobileaccount.po.LoanInfo;
import com.hc360.mobileaccount.po.MobileCallInfo;
import com.hc360.mobileaccount.po.NewsAndMsg;
import com.hc360.mobileaccount.po.PersonNewTag;
import com.hc360.mobileaccount.po.PersonTag; 
import com.hc360.mobileaccount.po.PersonTagAction;  
import com.hc360.mobileaccount.po.PersonTagNewContact;
import com.hc360.mobileaccount.po.PersonTagNewRemark;
import com.hc360.mobileaccount.po.PhoneContact;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.SpringFestivalActivity;
import com.hc360.mobileaccount.po.SuggestCorp;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18
 */
public interface AccountService {
	String sendMessageActivedUser();

	List<PersonNewTag> selectPersonTags();
	
	void insertNewPersonTag(Map<String,Object> map);
	
	void insertNewContact(Map<String,Object> map);
	
	void insertNewRemark(Map<String,Object> map);
	
	int isExistPersonTag(String phone);
	
	void updateInfoSearch(String accountid);
	
	Account getAccountInfo(String phone);
	Account getAccountInfoById(String accountId);
	public List<Account> getNologinUserList(String dateStr);
	
	void uptCompanyName(Map<String,Object> map);
 
	int updateAccount(Account account);
	int selectCmidNewcount(String cmid);

	void updateAccountYzx(AccountYzx yzx);
	
	/**
	 * 客户管理数据迁移
	 */
	String dataMigrationForCM(String phone);

	/**
	 * 显示云之讯账户（平台计费）是负数的数据
	 */
	String showYzxNegativeData(int start);

	/**
	 * 同步云之讯数据
	 */
	void syncYzxData();

	/**
	 * 国领迁移
	 */
	void moveGlToYzx();

	/**
	 * 云之讯充值
	 */
	int chargeAccountYzx(String phone, int balance, String flownumber);
	
	//免费充值
	int freeChargeAccountYzx(String phone, int balance,int type);

	void deleteYzx();
	
	void initRegUserYzxAccount();

	AccountYzx insert(Account account);
	
	AccountYzx insertAccount(Account account);

	int insertSecret(AccountSecret accountSecret);

	void insertYZM(AccountYZM yzm);

	AccountYZM selectYZM(AccountYZM yzm);
	
	List<AccountYzx> selectAccountYzxList();

	ReturnMsg getAccountPwd(String accountid, String pwd, String type);
	
	boolean deleteCilentNumber(String clientNumber, String appId);
	
	
	/**
	 *  true 存在 false 不存在
	 * @param phone
	 * @param type
	 * @return
	 */
	boolean isExitsPhone(String phone, String type);
	
	void deletephoneinfo(String phone);
	
	void updatepwd(AccountYZM yzm);
	
	/**
	 * 保存贷款信息
	 * */
	int insertLoanInfo(LoanInfo loanInfo);
	
	String insertYzxCallReq(String xml);
	
	ReturnMsg selectSDKPhone(String phone);
	 
	/**
	 * 保存用户打电话记录
	 * */
	int insertCallInfo(MobileCallInfo callInfo);
	
	AccountSecret getUserPwd(String phone , String type);
	
	String findClientByPhone(String phone,String appid);

	AccountYzx getYzx(String phone,String apptype);

	String buyGoodsForYZX(String phone, String charge);

	String recoverGoodsForYZX(String phone, String charge);

	String selectPhoneByAccountId(String accountid);
	
	void BCUserInfo(List<PhoneContact> pc);
	
	void insertAppCallLog(CallLogStat callLogStat);
	
	void callCorpInfo(CallLogStat callLogStat);
	
	void insertPhoneFirend(Map<String,Object> map);
	List<AccountGiveMin> selectfriendInfo(String phone);

	int selectfriendcount(String phone); 
	
	int selectgivefriendcount(String phone); 
	List<AccountGiveMin> selectphonebyfriend(String phone);
	int selectchargemincount(String phone);

	void insertRecommendFriend(Map<String,Object> map); 

	void insertStatSearch(Map<String,Object> map);
	
	void sendSms(String phone, String called, String appType);
	
	int selectYZXTimesByDay(String phone);
	
	void insertQiuGouInfo(String phone,String requestType, String called);
	
	int selectQiuGouInfo(String phone, String requestType);
	
	void updateYzxbalance(String phone, String length);
	
	void clearAccountInfo(String phone);
	
	void saveFeedBack(String deviceid,String message);
	
	int joinCorp(JoinCorpMM joinCorp);
	
	int suggestCorp(SuggestCorp suggestCorp);
	
	int deletePersonTag(String phone);
	int selectCmidcount(String cmid);
	List<PersonTag> selectPersonTag(String phone);
	
	int saveNewPersonTag(String phone, List<PersonTag> personTag,String tag);
	
	int savePersonTag(String phone, List<PersonTag> personTag,String tag);
	//增删改待办事项
	int updateTagAction(PersonTagAction ptAction);
	
	int insertTagAction(PersonTagAction ptAction); 

	int delTagAction(PersonTagAction ptAction);
	//增删改备注
	int insertTagRemark(PersonTagNewRemark ptRemark);
	
	int updateTagRemark(PersonTagNewRemark ptRemark);

	int delTagNewRemark(PersonTagNewRemark ptRemark);
	//修改联系人信息
	int insertTagNewContact(PersonTagNewContact ptContact);
	
	int updateTagNewContact(PersonTagNewContact ptContact) ;

	int delTagNewContact(PersonTagNewContact ptContact) ;
 	
	//活动相关参与获奖情况
	int insertPhoneActionResult(String phone,String actiontype,String gltype);
	
	//友盟推送
	public void savePushClientInfo(String phone, String token, String ticker);
	public PushClientInfo getPushCilentInfoByPhone(String phone);
	public void updatePushClientInfo(PushClientInfo info);
	public void updatePushClientInfoTokenNull(PushClientInfo info);
	public void deletePushClientInfoByPhone(String phone);
	public List<PushClientInfo> getPushCilentInfoList();
	
	List<PersonTag> selectAllPersonTag();
	List<PersonTagAction> selectAllTagAction();
	public List<PersonTag> selectNewPersonTag(String phone);
	
	void updateSend(int id);
	void updateSendAction(int id);
	String updatePhoneBalanceOf30(); // 给云之讯话费为0 的用户充值30分钟
	SpringFestivalActivity getCorpofHome(String city);
	void updateTaxic(String city);
	String getTaxicTop(String callback);
	//最后一次启动时间记录
    void insertFinalTime(String deviceid,String phonenum,String finaltime);
	String getHotWord(int n1, int n2);

	//--------------------开始新增新闻消息
	/** 
	 * 获取广告图片
	 */
	NewsAndMsg selectPicture(Map<String,Object> map);
	//获取默认图
	NewsAndMsg selectAutoPicture();
	
	/**
	 * 查询所有新闻信息
	 * @return
	 */
    	List<NewsAndMsg> selectNewsInfo(Map<String,Object> map);
    	/**
    	 * 查询下载地址
    	 * @return
    	 */
        DownLoad selectDownloadUrl(String version); 
        	
    	//查询详情
    	NewsAndMsg	selectNewsInfoById(int id);
		/**
		 * 
		 * 修改新闻消息
		 */
		int updateNewsInfo(NewsAndMsg newsInfo);
		/**
		 * 新增新闻
		 * @param corpInfo
		 * @return
		 */
		int insertNewsInfo(NewsAndMsg newsInfo);
		/**
		 * 删除新闻消息
		 */
		int delNewsInfo(int id);
		/*
		 * 统计新用户进来消息中心数量
		 */
		List<NewsAndMsg> selectNewsCount();
		/**
		 * 获取消息中心最新内容
		 * @return
		 */
		List<NewsAndMsg>  selectNewsTitle();
		
		/**
		 * 根据时间和手机号查询打电话的次数
		 * @param map
		 * @return
		 */
		int selectYzxCallCountByPhoneAndTime(Map<String,Object> map);

		void updatefriendstate(String phone); 
		
		int selectgivefriscount(Map<String, Object> map);
		public List<Account> getReachLevelUserByLevelId(Map<String,Object> map);
}
