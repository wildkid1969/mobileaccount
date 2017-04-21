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

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.AccountSecret;
import com.hc360.mobileaccount.po.AccountYZM;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.GlData;

/**
 * 
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18
 */
public interface AccountMapper {

	// 保存账号
	int insert(Account account);
	
	Account selectAccount(String phone);
	Account getAccountInfoById(String accountId);
	
	int updateAccount(Account account);

	List<AccountYzx> selectAccountYzxAll(Map<String, Integer> map);

	List<GlData> selectAccountGlAll();
	
	List<String> selectActivedUser(Map<String, Object> map);
	
	public List<Account> getNologinUserList(String dateStr);

	void updateAccountYzx(AccountYzx yzx);

	void deleteYzx();
	
	void updateChongzhi();

	// 保存密码
	int insertSecret(AccountSecret accountSecret);

	// 更新状态是否可用
	int updateAccountStatus(Account account);

	// 保存验证码
	void insertYZM(AccountYZM yzm);

	AccountYZM selectYZM(AccountYZM yzm);

	AccountSecret getAccountPwd(Map<String,Object> map);

	String getAccountIdByPhone(Map<String,Object> map);

	String getAccountIdByUserName(String username);

	void updatelogin(AccountSecret as);

	int deleteYZM(AccountYZM yzm);
	
	//判断电话号码是否存在
	int countPhone(Map<String,Object> map);
	
	void deletephoneinfo(String phone);
	
	//修改密码
	void updatepwd(AccountYZM yzm);
	
	// 保存云之讯账号
	void insertYZX(AccountYzx yzx);
	
	AccountYzx selectAccountYzx(Map<String,Object> map);
	
	List<AccountYzx> selectAccountYzxList();
	
	AccountYzx selectYzxByClientNumber(String clientNumber);
	
	//清空云之讯通话时长
	void clearYzxBalacne(String phone);
	
	String selectPhoneByAccountId(String accountid);
	
	void updateInfoSearch(String accountid);
	
	void updateYzxbalance(Map<String,Object> map);
	
	void cutYzxbalance(Map<String,Object> map);

	void deleteaccountInfo(String phone);
	void deleteaccountSecret(String phone);
	void deleteaccountYZM(String phone);
	void deleteaccountYZX(String phone);
	void deleteaccountScore(String phone);
	void deleteaccountScoreLog(String phone);
	
	List<Account> getPhone();
	
	int selectYzxCallCountByPhoneAndTime(Map<String,Object> map);
	public List<Account> getReachLevelUserByLevelId(Map<String,Object> map);
}
