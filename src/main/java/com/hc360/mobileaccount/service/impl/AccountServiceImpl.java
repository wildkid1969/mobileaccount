/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:51
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.dao.AccountMapper;
import com.hc360.mobileaccount.dao.LoanMapper;
import com.hc360.mobileaccount.dao.MobileAccountMapper;
import com.hc360.mobileaccount.dao.PaccountFreechargeLogMapper;
import com.hc360.mobileaccount.dao.ScoreMapper;
import com.hc360.mobileaccount.dao.YzxMapper;
import com.hc360.mobileaccount.daooracle.Data114Mapper;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.AccountGiveMin;
import com.hc360.mobileaccount.po.AccountSecret;
import com.hc360.mobileaccount.po.AccountYZM;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.BalanceLog;
import com.hc360.mobileaccount.po.CallLogStat;
import com.hc360.mobileaccount.po.CommonPoUtil;
import com.hc360.mobileaccount.po.CorpInfo;
import com.hc360.mobileaccount.po.CorpInfoExt;
import com.hc360.mobileaccount.po.DownLoad;
import com.hc360.mobileaccount.po.ExerciseResult;
import com.hc360.mobileaccount.po.GlData;
import com.hc360.mobileaccount.po.HotWord;
import com.hc360.mobileaccount.po.JoinCorpMM;
import com.hc360.mobileaccount.po.ListUserInfo;
import com.hc360.mobileaccount.po.LoanInfo;
import com.hc360.mobileaccount.po.MobileCallInfo;
import com.hc360.mobileaccount.po.NewsAndMsg;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.PaccountFreechargeLog;
import com.hc360.mobileaccount.po.PersonNewTag;
import com.hc360.mobileaccount.po.PersonTag;
import com.hc360.mobileaccount.po.PersonTagAction;
import com.hc360.mobileaccount.po.PersonTagContact;
import com.hc360.mobileaccount.po.PersonTagNewContact;
import com.hc360.mobileaccount.po.PersonTagNewRemark;
import com.hc360.mobileaccount.po.PersonTagRemark;
import com.hc360.mobileaccount.po.PhoneContact;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.po.SpringFestivalActivity;
import com.hc360.mobileaccount.po.SuggestCorp;
import com.hc360.mobileaccount.po.YzxCall;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.ExerciseResultService;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.ucpaas.client.JsonReqClient;
import com.ucpaas.utils.DateUtil;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-19
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private LoanMapper loanMapper;

	@Autowired
	private ScoreMapper scoreMapper;

	@Autowired
	private YzxMapper yzxMapper;

	@Autowired
	private Data114Mapper data114Mapper;

	@Autowired
	private MobileAccountMapper mobileAccountMapper;
	
	@Resource
	private PaccountFreechargeLogMapper paccountFreechargeLogMapper;
	
	@Resource
	private ExerciseResultService exerciseResultService;
	
	private int date;

	private JsonReqClient jsonReqClient = new JsonReqClient();

	private List<String> smsMsgIdList = new ArrayList<String>();

	private Map<String, Integer> map = new HashMap<String, Integer>();

	@Autowired
	private CommonPoUtil commonPoUtil;

	/**
	 * 给待激活用户发短信
	 * @throws Exception 
	 */
	@Override
	public String sendMessageActivedUser(){
		AccountYzx accountYzx = null;
		GregorianCalendar gc=new GregorianCalendar();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(new Date());
		gc.add(2,-1);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", df.format(gc.getTime()));
		gc.add(2,1);
		gc.add(5,-10);
		map.put("end", df.format(gc.getTime()));
		List<String> phones = accountMapper.selectActivedUser(map);
		map.clear();

		// 数量统计
		int count = 0;	//总数
		int success = 0; // 成功数
		int error = 0; // 异常数
		
		for(int i=0; i<phones.size(); i++){
			count++;
			map.put("phone", phones.get(i));
			map.put("apptype","114");
			accountYzx = accountMapper.selectAccountYzx(map);
			if(accountYzx == null)	continue;

			// 每个待激活用户 充值100分钟 发送短信
			String result = jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "0", "5.5", YZXConstant.APP_ID);
			PReturn yzxReturnMsg = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
			if(yzxReturnMsg != null && yzxReturnMsg.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)){
				updateYzxbalance(phones.get(i), "6000");
			}

			try {
				/**
				 * 营销短信
				 * 短信条数不够可以找彭总
				 * sn号DXX-HCW-010-00056
				 * pwd 595356
				 * mobilestatistic工程中
				 * CommonUtils.java 中 sendSmsForAD方法
				 *
				 */
				//【收到红包】您“企业114”账户获得100分钟通话红包，下载新版企业114查看：http://t.cn/RqdWUkr
//				MobileAccountUtils.sendShortMsg(phones.get(i), "a",33,23);
				success++;
			} catch (Exception e) {
				error++;
				// nothing
			}
		}

		return "{\"总数\":\""+count+"\",\"成功数\":\""+success+"\",\"异常数\":\""+error+"\"}";
	}

	/**
	 * 客户管理数据迁移
	 */
	@Override
	public String dataMigrationForCM(String phone){
		ReturnMsg rm = new ReturnMsg();
		List<PersonTag> personTagAll = null;
		if(StringUtils.isEmpty(phone)){
			personTagAll = mobileAccountMapper.selectPersonTagAll();
		}else{
			personTagAll = mobileAccountMapper.selectPersonTag(phone);
		}
		if(personTagAll == null){
			rm.setCode(300);
			rm.setMessage("没有数据。");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		dataMigrationForCMSub(personTagAll);
		rm.setCode(200);
		rm.setMessage("迁移数据成功。");

		return MobileAccountUtils.getGson().toJson(rm);
	}

	public void dataMigrationForCMSub(List<PersonTag> personTagAll) {
		PersonTagContact personTagContact = null;
		String cmid = "";
		UUID uuid = null;
		for(int i=0; i<personTagAll.size(); i++){
			uuid = UUID.randomUUID();
			if(StringUtils.isEmpty(personTagAll.get(i).getCorpid())){
				cmid = MobileAccountUtils.MD5("0"+i+uuid+personTagAll.get(i).getPhone());
			}else{
				cmid = MobileAccountUtils.MD5("1"+personTagAll.get(i).getCorpid()+uuid+personTagAll.get(i).getPhone());
			}

			List<PersonTagContact> personTagContacts = mobileAccountMapper.selectPersonTagContact(personTagAll.get(i).getCmid());
			if(personTagContacts == null || personTagContacts.size() == 0)		continue;
			
			for(int j=0; j<personTagContacts.size(); j++){
				personTagContact = personTagContacts.get(j);
				PersonTagNewContact personTagNewContact = new PersonTagNewContact();
				// cmid
				personTagNewContact.setCmid(cmid);
				// 联系人姓名
				personTagNewContact.setName(personTagContact.getName());
				// 联系人电话
				personTagNewContact.setPhone(personTagContact.getPhone());
				// 时间
				personTagNewContact.setCreatetime(MobileAccountUtils.dateToTimestamp(personTagContact.getCreatetime()));
				// CID
				personTagNewContact.setCid(String.valueOf(j));
				mobileAccountMapper.insertPersonTagNewContactByEntity(personTagNewContact);
			}

			List<PersonTagRemark> personTagRemarks = mobileAccountMapper.selectPersonTagRemark(personTagAll.get(i).getCmid());
			for(int j=0; j<personTagRemarks.size(); j++){
				PersonTagRemark personTagRemark = personTagRemarks.get(j);
				PersonTagNewRemark personTagNewRemark  = new PersonTagNewRemark();
				// cmid
				personTagNewRemark.setCmid(cmid);
				// 备注
				personTagNewRemark.setTagremark(personTagRemark.getTagremark());
				// 联系人名
				personTagNewRemark.setName(personTagContacts.get(0).getName());
				// 时间
				personTagNewRemark.setCreatetime(MobileAccountUtils.dateToTimestamp(personTagRemark.getCreatetime()));
				// CID
				personTagNewRemark.setCid(String.valueOf(j));
				mobileAccountMapper.insertPersonTagNewRemarkByEntity(personTagNewRemark);
			}

			PersonNewTag personNewTag = new PersonNewTag();
			// cmid
			personNewTag.setCmid(cmid);
			// 用户
			personNewTag.setPhone(personTagAll.get(i).getPhone());
			// 公司标识
			personNewTag.setCorpid(personTagAll.get(i).getCorpid());
			// 公司名称
			personNewTag.setName(personTagAll.get(i).getName());
			// 标记时间
			personNewTag.setTagdate(MobileAccountUtils.dateToTimestamp(personTagAll.get(i).getTagdate()));
			// 标记的电话
			personNewTag.setTagphone(personTagAll.get(i).getTagphone());
			// 是否提醒标志
			personNewTag.setAlarm(personTagAll.get(i).getAlarm());
			// 标记类别
			personNewTag.setTag(personTagAll.get(i).getTag());
			// 是否已提醒标志
			personNewTag.setSelfdata1(personTagAll.get(i).getSelfdata1());
			mobileAccountMapper.insertNewPersonTagByEntity(personNewTag);
		}
	}
	
	/**
	 * 显示云之讯账户（平台计费）是负数的数据
	 */
	@Override
	public String showYzxNegativeData(int start) {
		StringBuffer sb = new StringBuffer();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("size", 3000);
		List<AccountYzx> accountYzxs = accountMapper.selectAccountYzxAll(map);
		for (AccountYzx accountYzx : accountYzxs) {
			try {
				String result = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getPhone().trim(), accountYzx.getAppid());
				System.out.println("RESULT : " + result);
				PReturn pr = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
				if (pr.getResp().getClient().getClientType().equals("1")) {
					Double balance = Double.parseDouble(pr.getResp().getClient().getBalance());
					if (balance < 0) {
						sb.append(accountYzx.getPhone() + ":" + balance + "<br/>");
					}
				}
			} catch (Exception e) {
				sb.append(accountYzx.getPhone() + ": Error.<br/>");
			}
		}

		return sb.toString();
	}

	/**
	 * 同步云之讯数据
	 */
	@Override
	public void syncYzxData() {
		List<AccountYzx> accountYzxs = null;
		int start = 0;
		double balance = 0;
		double yuan = 0;
		
		int ccount=0;
		int rcount=0;
		int count=0;
		
		while(true){
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("start", start);
			map.put("size", 3000);
			accountYzxs = accountMapper.selectAccountYzxAll(map);
			
			System.out.println("size:"+accountYzxs.size());

			for (AccountYzx accountYzx : accountYzxs) {
				try {
					String result = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getPhone().trim(), accountYzx.getAppid());

					PReturn pr = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
					balance = Double.parseDouble(pr.getResp().getClient().getBalance());
					yuan = Double.parseDouble(accountYzx.getBalance())/60*0.055;
					
					if(balance>yuan){
						balance = balance - yuan;
						jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "1", "" + balance,
								accountYzx.getAppid());
						rcount++;
					}else if(balance<yuan){
						balance = yuan - balance;
						jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "0", "" + balance,
								accountYzx.getAppid());
						ccount++;
					}
					count++;
				} catch (Exception e) {
					System.out.println("Exception: " + accountYzx.getPhone());
					continue;
				}
			}

			if(accountYzxs.size() < 3000){
				break;
			}else{
				start += 3000;
			}
		}
		System.out.println("charge:"+ccount);
		System.out.println("recover:"+rcount);
		System.out.println("all:"+count);
	}

	/**
	 * 国领迁移
	 */
	@Override
	public void moveGlToYzx() {
		List<GlData> glDatas = accountMapper.selectAccountGlAll();

		for (GlData glData : glDatas) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("phone", glData.getPhone().trim());
				map.put("apptype", "114");
				AccountYzx accountYzx = accountMapper.selectAccountYzx(map);
				String result = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID,
						YZXConstant.AUTH_TOKEN, accountYzx.getPhone().trim(), accountYzx.getAppid());
				System.out.println("RESULT : " + result);
				PReturn pr = MobileAccountUtils.getGson().fromJson(result, PReturn.class);

				accountYzx.setClientType(pr.getResp().getClient().getClientType());
				accountYzx.setCreateDate(String.valueOf(DateUtil.getStringToDate(pr.getResp().getClient()
						.getCreateDate())));
				if (accountYzx.getClientType().equals("1")) {
					Double balance = Double.parseDouble(pr.getResp().getClient().getBalance());
					Double balance2 = glData.getBalance() * 0.055;
					Double diff = balance2 - balance;
					if (diff > 0) {
						jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID,
								YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "0", "" + diff,
								accountYzx.getAppid());
					} else if (diff < 0) {
						jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID,
								YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "1", "" + (-diff),
								accountYzx.getAppid());
					}
				}
				accountYzx.setBalance("" + glData.getBalance() * 60);
				accountMapper.updateAccountYzx(accountYzx);
			} catch (Exception e) {
				System.out.println("Exception: " + glData.getPhone());
				continue;
			}
		}
	}

	/**
	 * 云之讯充值
	 */
	@Override
	public int chargeAccountYzx(String phone, int balance, String flownumber) {
		int rtn = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountYzx accountYzx = accountMapper.selectAccountYzx(map);
		if (accountYzx != null) {
			// 指定活动期间内充值送双倍话费
			BalanceLog bLog = new BalanceLog();
			bLog.setPhone(phone);
			bLog.setFlownumber(flownumber);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date start = sdf.parse(commonPoUtil.getTimeInterval().get("start"));
				Date end = sdf.parse(commonPoUtil.getTimeInterval().get("end"));
				if (new Date().getTime() >= start.getTime() && new Date().getTime() < end.getTime()) {
					balance *= 2;
				}
				bLog.setYzxbalance(Integer.parseInt(accountYzx.getBalance()));
				bLog.setLength(balance);
				accountYzx.setBalance(String.valueOf(Integer.valueOf(accountYzx.getBalance()) + balance));
				if (accountYzx.getClientType().equals("1")) {
					Double dd = balance * 1.0 / 60 * 0.055;
					jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN,
							accountYzx.getClientNumber(), "0", "" + dd, accountYzx.getAppid());
				}
				accountMapper.updateAccountYzx(accountYzx);
				mobileAccountMapper.insertBalanceLog(bLog);
			} catch (ParseException e) {
				rtn = 1;
			}
		} else {
			rtn = 1;
		}
		return rtn;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class}) 
	public AccountYzx insert(Account account) {
		try {
			accountMapper.insert(account);
			System.out.print(account.getAccountid());

			AccountSecret accountSecret = new AccountSecret();
			accountSecret.setAccountid(String.valueOf(account.getAccountid()));
			accountSecret.setPhone(account.getPhone());
			accountSecret.setSecret(account.getSecret());
			accountSecret.setApptype(account.getApptype());
			accountSecret.setCreatetime(MobileAccountUtils.getNowtime());
			accountMapper.insertSecret(accountSecret);

			Score score = new Score();
			score.setAction(3);
			score.setAppType("114");
			score.setMarktime(MobileAccountUtils.getNowtime());
			score.setPhone(account.getPhone());
			score.setScorenum("0");
			score.setType("dl");
			score.setName("登陆");
			scoreMapper.insertScore(score);

			String chargeType = "1"; // 开发者计费 0 -> 1 修改为平台计费// 有邀请码的 要给邀请码用户充值10分钟话费 即0.55元
			if (account.getInvitation() != null && !account.getInvitation().isEmpty()) {
				jsonReqClient.createClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN,
						account.getAppId(), account.getInvitation(), chargeType, "0.55", account.getInvitation());
			}
			// 新注册用户赠送 30分钟话费 即1.65元
			String result = jsonReqClient.createClient(YZXConstant.ACCOUNT_SID,
					YZXConstant.AUTH_TOKEN, account.getAppId(), account.getFriendlyName(), chargeType,
					"1.65", account.getPhone());
			System.out.println(result);
			PReturn cc = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
			if (cc != null && cc.getResp() != null) {
				if (cc.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)) {
					AccountYzx yzx = cc.getResp().getClient();
					yzx.setPhone(account.getPhone());
					yzx.setCharge("1.65"); // 金额
					yzx.setClientType("1");
					yzx.setAppid(account.getAppId());
					yzx.setBalance(String.valueOf(1800)); // 时长 秒数
					yzx.setAccountId(String.valueOf(account.getAccountid()));
					yzx.setApptype(account.getApptype());
					yzx.setCreateDate(MobileAccountUtils.getNowtime());
					accountMapper.insertYZX(yzx);
					return yzx;
				} else if (cc.getResp().getRespCode().equals("103114")) { // 账号已经存在，需要重新获取账号信息
					String r = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID,
							YZXConstant.AUTH_TOKEN, account.getPhone(), account.getAppId());
					System.out.println(r);
					if (r != null) {
						PReturn pr = MobileAccountUtils.getGson().fromJson(r, PReturn.class);
						if (pr != null && pr.getResp() != null && pr.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)
								&& pr.getResp().getClient() != null) {
							AccountYzx yzx = pr.getResp().getClient();
							yzx.setPhone(account.getPhone());
							yzx.setAppid(account.getAppId());
							yzx.setClientNumber(pr.getResp().getClient().getClientNumber());
							yzx.setClientPwd(pr.getResp().getClient().getClientPwd());
							yzx.setAccountId(String.valueOf(account.getAccountid()));
							yzx.setApptype(account.getApptype());
							yzx.setBalance(String.valueOf(1800)); // 时长 秒数
							yzx.setAccountId(String.valueOf(account.getAccountid()));
							yzx.setCreateDate(MobileAccountUtils.getNowtime());
							accountMapper.insertYZX(yzx);
							return yzx;
						} else {
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						}
					} else {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}
				} else {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return null;
	}

	@Override
	public int insertSecret(AccountSecret accountSecret) {
		return accountMapper.insertSecret(accountSecret);
	}

	@Override
	public void insertYZM(AccountYZM yzm) {
		accountMapper.deleteYZM(yzm);
		accountMapper.insertYZM(yzm);
	}

	@Override
	public AccountYZM selectYZM(AccountYZM yzm) {

		return accountMapper.selectYZM(yzm);
	}

	public String getAccountId(String k, String apptype) {
		String accountid = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apptype", apptype);
		if (MobileAccountUtils.isPhoneNumber(k)) {
			map.put("phone", k);
			accountid = accountMapper.getAccountIdByPhone(map);
		} else {
			map.put("username", k);
			accountid = accountMapper.getAccountIdByUserName(k);
		}
		return accountid;
	}

	@Override
	public ReturnMsg getAccountPwd(String phone, String pwd, String type) {
		ReturnMsg rm = new ReturnMsg();
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("Login phone :" + phone + " ; secret : " + pwd);
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountSecret a = accountMapper.getAccountPwd(map);
		try {
			if (a != null && pwd.equals(a.getSecret())) {
				a.setLogintime(MobileAccountUtils.getNowtime());
				accountMapper.updatelogin(a);
				AccountYzx yzx = accountMapper.selectAccountYzx(map);
				if (yzx != null) {
					System.out.println(yzx.getClientNumber());
					rm.setClientNumber(yzx.getClientNumber());
					rm.setClientPwd(yzx.getClientPwd());
					rm.setCode(1);
					rm.setAccoundid(accountMapper.getAccountIdByPhone(map));
					rm.setBalance(Integer.valueOf(yzx.getBalance()) / 60);
				} else {
					System.out.println("asdasdasdasdadasd");
					rm.setCode(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rm.setCode(-1);
		}
		return rm;
	}

	@Override
	public AccountYzx getYzx(String phone, String apptype) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", apptype);
		return accountMapper.selectAccountYzx(map);
	}

	/**
	 * true 存在 false 不存在
	 * */
	@Override
	public boolean isExitsPhone(String phone, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		int count = accountMapper.countPhone(map);
		if (count > 0) {
			return true; // 存在
		}
		return false;
	}

	@Override
	public void deletephoneinfo(String phone) {
		accountMapper.deletephoneinfo(phone);
	}

	@Override
	public void updatepwd(AccountYZM yzm) {
		accountMapper.updatepwd(yzm);
	}

	@Override
	public int insertLoanInfo(LoanInfo loanInfo) {
		return loanMapper.insert(loanInfo);
	}

	@Override
	public void sendSms(String phone, String called, String appType) {
//		int num = 1;
		if (!MobileAccountUtils.isPhoneNumber(called)) {
			return;
		}
		int now = MobileAccountUtils.GetDateToInt();
		try {
			this.date = map.get("date");
			if (now != date) {
				map.clear();
				map.put("date", now);
			}
		} catch (Exception e) {
			map.clear();
			map.put("date", now);
		}
//		if (map.size() > 0) {
//			try {
//				num = map.get(called);
//			} catch (Exception e) {
//			}
//		}
		System.out.println("called:" + called);
		String callback = MobileAccountUtils.sendSms(phone, called, appType);
		System.out.println(callback);
		if (callback != null) {
			smsMsgIdList.add(callback.replaceAll("<[^>]*>", "").trim());
		}
		// if (!smsBlackList.contains(called) && num <= 3) {
		//
		// map.remove(called);
		// num += 1;
		// map.put(called, num);
		// }
	}

	
	/**
	 * 云之讯回调接口
	 */
	@Override
	public String insertYzxCallReq(String xmlstr) {
		//主叫号码
		String caller = MobileAccountUtils.getXMLValue(xmlstr, "caller");
		
		//被叫号码
		String called = MobileAccountUtils.getXMLValue(xmlstr, "called");
		
		//主叫类型   callertype	int	必选	主叫号码类型，0：Client账号，1：普通电话，2：userid
		String callertype = MobileAccountUtils.getXMLValue(xmlstr, "callertype");
		
		//callernum		String	必选	用户绑定的号码
		String callernum = MobileAccountUtils.getXMLValue(xmlstr, "callernum");
		
		//呼叫的唯一标识（sdk组件生成）
		String callid = MobileAccountUtils.getXMLValue(xmlstr, "callid");
		
		//0：直拨，1：免费，2：回拨
		int calltype = Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "calltype"));
		
		
		YzxCall callReq = new YzxCall();
		callReq.setAccountid(MobileAccountUtils.getXMLValue(xmlstr, "accountid"));
		callReq.setAppid(MobileAccountUtils.getXMLValue(xmlstr, "appid"));
		callReq.setCalled(called);
		callReq.setCalledtype(Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "calledtype")));
		callReq.setCaller(caller);
		callReq.setCallertype(Integer.valueOf(callertype));
		callReq.setCallid(MobileAccountUtils.getXMLValue(xmlstr, "callid"));
		
		//如果被叫用户不是企业114的用户则发送营销短信【暂时不发送了】
		String event = MobileAccountUtils.getXMLValue(xmlstr, "event");
		
//		if(event.equalsIgnoreCase("callhangup")){
//			Map<String, Object> maps = new HashMap<String, Object>();
//			maps.put("phone", called);
//			maps.put("apptype", "114");
//			String accountId = accountMapper.getAccountIdByPhone(maps);
//			
//			if(org.apache.commons.lang.StringUtils.isEmpty(accountId)){
//				//发短信
////			String content = "您的商友通过企业114联系到您，优质客户贵在积累，免费下载一下吧:http://t.cn/RqsJcZy【慧聪网】";
//				try {
//					MobileAccountUtils.sendShortMsg(called, "a",33,23);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		
		callReq.setCalltype(calltype);
		callReq.setEvent(event);
		callReq.setCalltime(new Date());
		callReq.setUserData(MobileAccountUtils.getXMLValue(xmlstr, "userData"));
		if (callReq.getEvent() != null && event.equals("callreq")) {
			callReq.setCallerchargetype(Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "callerchargetype")));
			callReq.setCallerbalance(Float.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "callerbalance")));
		} else if (callReq.getEvent() != null && event.equals("callhangup")) {//挂起
			callReq.setConfid(MobileAccountUtils.getXMLValue(xmlstr, "confid"));
			try {
				callReq.setLength(Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "length")));
				callReq.setRecordurl(MobileAccountUtils.getXMLValue(xmlstr, "recordurl"));
				System.out.println("hangup:"+callReq.getRecordurl());
				
				if(callReq.getLength() > 0){// 已经建立通话并且有通话时长的情况下
					Map<String, Object> map = new HashMap<String, Object>();
					
					/* 如果有消费情况，减少云之讯的余额 */
					if(calltype==2){
						map.put("clientNumber", callReq.getCaller());
						map.put("length", Math.ceil(callReq.getLength() / 60.0) * 60);
						accountMapper.cutYzxbalance(map);
					}
					
					
					//保存通话录音
					if(!StringUtils.isEmpty(callReq.getRecordurl()) && calltype==1){
						map.clear();
						map.put("apptype", "114");
						
						if("0".equals(callertype)){
							map.put("phone", callernum);
						}else if("1".equals(callertype)){
							map.put("phone", caller);
						}
						
						String userid = accountMapper.getAccountIdByPhone(map);
						System.out.println("userid:"+userid);
						
						String downloadAudioUrl = callReq.getRecordurl() + "?sig="+MD5.md5Encode(YZXConstant.ACCOUNT_SID+callid+YZXConstant.AUTH_TOKEN);
						System.out.println("downUrl:"+downloadAudioUrl);

						ExerciseResult result = new ExerciseResult();
						result.setUserid(Long.valueOf(userid));
						result.setAudioUrl(downloadAudioUrl);
						result.setTimeLength(callReq.getLength());
						result.setIsDown(0);
						exerciseResultService.saveAudioUrlByUserid(result);
					}
					
					
					/* 发送营销短信功能 【暂时不发送了】 */
					// 发送营销短信，暂时不考虑通话时长
					// AccountYzx yzx =
					// accountMapper.selectYzxByClientNumber(callReq.getCaller());
					// if (yzx != null) {
					// sendSms(yzx.getPhone(), callReq.getCalled(),
					// yzx.getApptype());
					// }
				}
				callReq.setSubreason(Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "subreason")));
				callReq.setReason(Integer.valueOf(MobileAccountUtils.getXMLValue(xmlstr, "reason")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			callReq.setStoptime(MobileAccountUtils.getXMLValue(xmlstr, "stoptime"));
			callReq.setStarttime(MobileAccountUtils.getXMLValue(xmlstr, "starttime"));
			callReq.setRecordurl(MobileAccountUtils.getXMLValue(xmlstr, "recordurl"));
		}

		int ret = yzxMapper.insertCallReq(callReq);
		if (ret >= 0) {
			return MobileAccountUtils.getCallBackYzxXml("0", null);
		} else {
			return MobileAccountUtils.getCallBackYzxXml("1", "failed");
		}
	}

	@Override
	public ReturnMsg selectSDKPhone(String phone) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(2);
		System.out.println("RM : " + rm.getCode());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountYzx yzx = accountMapper.selectAccountYzx(map);
		if (yzx != null) {
			rm.setClientNumber(yzx.getClientNumber());
			rm.setClientPwd(yzx.getClientPwd());
			System.out.println("yzx.getBalance : " + yzx.getBalance());
			if (Integer.valueOf(yzx.getBalance()) < 60) {
				rm.setCode(4);
				rm.setMessage("余额不足请充值!");
			}
		} else {
			rm.setMessage("云之讯账号不存在!");
		}
		return rm;
	}

	@Override
	public int insertCallInfo(MobileCallInfo callInfo) {
		int id = 0;
		if (callInfo.getId() <= 0) {
			callInfo.setStarttime(new Date());
			id = mobileAccountMapper.insert(callInfo);
		} else {
			MobileCallInfo mci = mobileAccountMapper.selectById(callInfo.getId());

			callInfo.setEndtime(new Date());
			callInfo.setLength((callInfo.getEndtime().getTime() - mci.getStarttime().getTime()) / 1000);
			mobileAccountMapper.update(callInfo);
		}
		return id;
	}

	@Override
	public AccountSecret getUserPwd(String phone, String apptype) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", apptype);
		return accountMapper.getAccountPwd(map);
	}

	@Override
	public boolean deleteCilentNumber(String phone, String type) {
		String result = jsonReqClient.closeClient(YZXConstant.ACCOUNT_SID,
				YZXConstant.AUTH_TOKEN, phone, YZXConstant.APP_ID);
		System.out.println("YZX : " + result);

		return true;

	}

	@Override
	public String findClientByPhone(String phone, String appid) {
		String result = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID,
				YZXConstant.AUTH_TOKEN, phone, appid);
		System.out.println("RESULT : " + result);
		return result;
	}

	public String buyGoodsForYZX(String phone, String charge) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountYzx yzx = accountMapper.selectAccountYzx(map);
		if (yzx != null) {
			return jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN,
					yzx.getClientNumber(), "0", charge, YZXConstant.APP_ID);
		} else {
			return null;
		}
	}

	public String recoverGoodsForYZX(String phone, String charge) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountYzx yzx = accountMapper.selectAccountYzx(map);
		if (yzx != null) {
			return jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN,
					yzx.getClientNumber(), "1", charge, YZXConstant.APP_ID);
		} else {
			return null;
		}
	}

	@Override
	public String selectPhoneByAccountId(String accountid) {
		return accountMapper.selectPhoneByAccountId(accountid);
	}

	@Override
	public void BCUserInfo(List<PhoneContact> pc) {
		if (pc != null) {
			for (PhoneContact p : pc) {
				StringBuffer listStr = new StringBuffer();
				for (com.hc360.mobileaccount.po.Number s : p.getNumbers()) {
					listStr.append(s.getNumber() + ",");
				}
				String ccc = MobileAccountUtils.sendGet("http://openapi.m.hc360.com/openapi/v1/Corplib/linkinfo/",
						listStr.toString().trim());
				System.out.println(ccc);
				ListUserInfo lui = MobileAccountUtils.getGson().fromJson(ccc, ListUserInfo.class);
				if (lui != null) {
					System.out.print(lui.getLinkinfos().size());
				}
			}
		}
	}

	@Override
	public void updateInfoSearch(String accountid) {
		accountMapper.updateInfoSearch(accountid);
	}

	@Override
	public void insertAppCallLog(CallLogStat callLogStat) {
		// if (callLogStat.getMsgid() != null &&
		// !callLogStat.getMsgid().isEmpty()) {
		// if (mobileAccountMapper.countAppCallLog(callLogStat.getMsgid()) > 0)
		// {
		// mobileAccountMapper.updateAppCallLog(callLogStat);
		// } else {
		// mobileAccountMapper.insertAppCallLog(callLogStat);
		// }
		// } else {
		mobileAccountMapper.insertAppCallLog(callLogStat);
		// }
	}

	@Override
	public void callCorpInfo(CallLogStat callLogStat) {
		if (callLogStat.getCorpName() == null || callLogStat.getCorpName().equals(""))
			return;

		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		List<CorpInfo> corpInfoList = data114Mapper.selectCorpInfo(callLogStat.getCorpName());
		if (corpInfoList != null && corpInfoList.size() > 0) {
			CorpInfo corpInfo = corpInfoList.get(0);
			CorpInfoExt corpInfoExt = new CorpInfoExt();
			corpInfoExt.setCorpid(corpInfo.getCorpid());

			List<CorpInfoExt> corpInfoExtList = data114Mapper.selectAccessCorp(corpInfoExt.getCorpid());
			if (corpInfoExtList != null && corpInfoExtList.size() > 0) {
				corpInfoExt = corpInfoExtList.get(0);
				corpInfoExt.setCallnum(corpInfoExt.getCallnum() + 1);
				if (Integer.parseInt(callLogStat.getDuring()) > 0) {
					corpInfoExt.setCallednum(corpInfoExt.getCallednum() + 1);
				}
				corpInfoExt.setCalledrate(df.format(corpInfoExt.getCallednum() * 1.0 / corpInfoExt.getCallnum()));
				data114Mapper.updateAccessCorp(corpInfoExt);
			} else {
				corpInfoExt.setAccessnum(1);
				corpInfoExt.setCallnum(1);
				if (Integer.parseInt(callLogStat.getDuring()) > 0) {
					corpInfoExt.setCallednum(1);
				}
				corpInfoExt.setCalledrate(df.format(corpInfoExt.getCallednum() * 1.0 / corpInfoExt.getCallnum()));
				data114Mapper.saveAccessCorp(corpInfoExt);
			}
		}
	}

	@Override
	public void insertPhoneFirend(Map<String, Object> map) {
		mobileAccountMapper.insertPhoneFirend(map);
	}
	@Override
	public void insertRecommendFriend(Map<String, Object> map) {
		mobileAccountMapper.insertRecommendFriend(map);
	}
	@Override
	public int selectfriendcount(String phone){
		return mobileAccountMapper.selectfriendcount(phone);
	}
	
	@Override
	public int selectgivefriendcount(String phone){
		return mobileAccountMapper.selectgivefriendcount(phone);
	}
	@Override
	public void updatefriendstate(String phone) {
		mobileAccountMapper.updatefriendstate(phone);
	}
	 
	@Override
	public void insertStatSearch(Map<String, Object> map) {
		mobileAccountMapper.insertStatSearch(map);
	}
	public List<AccountGiveMin> selectphonebyfriend(String phone){
		return mobileAccountMapper.selectphonebyfriend(phone);
	}

	
	@Override
	public int selectYZXTimesByDay(String phone) {
		return mobileAccountMapper.selectYZXTimesByDay(phone);
	}

	@Override
	public void insertQiuGouInfo(String phone, String requestType, String called) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("calltime", new Date());
		if (requestType != null && requestType.equals("qiugou")) {
			map.put("request", 1);
		} else {
			map.put("request", 2);
		}
		map.put("called", called);
		mobileAccountMapper.insertQiuGouInfo(map);
	}

	@Override
	public int selectQiuGouInfo(String phone, String requestType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		if (requestType != null && requestType.equals("qiugou")) {
			map.put("request", 1);
		} else {
			map.put("request", 2);
		}
		return mobileAccountMapper.selectCountQiuGouInfo(map);
	}

	@Override
	public void updateYzxbalance(String phone, String length) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("length", length);
		accountMapper.updateYzxbalance(map);

	}

	@Override
	public void clearAccountInfo(String phone) {
		accountMapper.deleteaccountInfo(phone);
		accountMapper.deleteaccountSecret(phone);
		accountMapper.deleteaccountYZM(phone);
		accountMapper.deleteaccountYZX(phone);
		accountMapper.deleteaccountScore(phone);
		accountMapper.deleteaccountScoreLog(phone);
	}

	@Override
	public void saveFeedBack(String deviceid, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceid", deviceid);
		map.put("message", message);
		mobileAccountMapper.saveFeedback(map);

	}

	@Override
	public int joinCorp(JoinCorpMM joinCorp) {
		int rtn = 0;
		try {
			rtn = mobileAccountMapper.joinCorp(joinCorp);
		} catch (Exception e) {
			e.printStackTrace();
			rtn = 0;
		}
		return rtn;
	}

	@Override
	public int suggestCorp(SuggestCorp suggestCorp) {
		int rtn = 0;
		try {
			rtn = mobileAccountMapper.suggestCorp(suggestCorp);
		} catch (Exception e) {
			e.printStackTrace();
			rtn = 0;
		}
		return rtn;
	}

	@Override
	public void initRegUserYzxAccount() {
		List<Account> phones = accountMapper.getPhone();
		if (phones == null) {
			return;
		}
		for (Account phone : phones) {
			AccountYzx yzx = new AccountYzx();
			String r = jsonReqClient.findClientByMobile(YZXConstant.ACCOUNT_SID,
					YZXConstant.AUTH_TOKEN, phone.getPhone(), YZXConstant.APP_ID);
			if (r != null) {
				PReturn pr = MobileAccountUtils.getGson().fromJson(r, PReturn.class);
				if (pr != null && pr.getResp() != null && pr.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)
						&& pr.getResp().getClient() != null) {
					yzx.setPhone(phone.getPhone());
					yzx.setAppid(YZXConstant.APP_ID);
					yzx.setClientNumber(pr.getResp().getClient().getClientNumber());
					yzx.setClientPwd(pr.getResp().getClient().getClientPwd());
					yzx.setAccountId(String.valueOf(phone.getAccountid()));
					yzx.setApptype("114");
					yzx.setBalance(String.valueOf(1800)); // 时长 秒数
					try {
						accountMapper.insertYZX(yzx);
					} catch (Exception e) {
						System.out.println("asdfasdfasdfasdf:");
						e.printStackTrace();
					}
				}
			}

		}
	}

	@Override
	public void deleteYzx() {
		accountMapper.updateChongzhi();
	}

	@Override
	public Account getAccountInfo(String phone) {
		return accountMapper.selectAccount(phone);
	}
	@Override
	public Account getAccountInfoById(String accountId) {
		return accountMapper.getAccountInfoById(accountId);
	}
	public void uptCompanyName(Map<String,Object> map){
		mobileAccountMapper.uptCompany(map);
	}

	@Override
	public int updateAccount(Account account) {
		return accountMapper.updateAccount(account);
	}

	@Override
	public List<PersonTag> selectNewPersonTag(String phone) {
		List<PersonTag> personTags = mobileAccountMapper.selectPersonNewTag(phone);//todo
		if (personTags != null) {
			for (PersonTag personTag : personTags) {
				personTag.setNewcontact(mobileAccountMapper.selectPersonTagNewContact(personTag.getCmid()));
				personTag.setNewremark(mobileAccountMapper.selectPersonTagNewRemark(personTag.getCmid()));
				personTag.setAction(mobileAccountMapper.selectPersonTagAction(personTag.getCmid()));
			}

		} else {
			personTags = new ArrayList<PersonTag>();
		}
		return personTags;
	}

	@Override
	public List<PersonTag> selectPersonTag(String phone) {
		List<PersonTag> personTags = mobileAccountMapper.selectPersonTag(phone);
		if (personTags != null) {
			for (PersonTag personTag : personTags) {
				personTag.setContact(mobileAccountMapper.selectPersonTagContact(personTag.getCmid()));
				personTag.setRemark(mobileAccountMapper.selectPersonTagRemark(personTag.getCmid()));
			}
		} else {
			personTags = new ArrayList<PersonTag>();
		}
		return personTags;
	}

	@Override
	public int deletePersonTag(String phone) {
		return mobileAccountMapper.deletePersonTag(phone);
	}

	// 新版本用户管理
	@Override
	public int saveNewPersonTag(String phone, List<PersonTag> personTagPo, String tag) {
		if (personTagPo == null || personTagPo.size() == 0) {
			return 0;
		}
		List<PersonTag> ptags = mobileAccountMapper.selectPersonNewTag(phone);//todo
		String cmid = personTagPo.get(0).getCmid();
		int ret = 0;
		if (tag.equals("add")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personTags", personTagPo);
			int cmidcount = mobileAccountMapper.selectCmidNewcount(cmid);//todo
			if (cmidcount == 0) {
				mobileAccountMapper.insertPersonNewTag(map);//todo
				for (PersonTag p : personTagPo) { 

					if (p.getNewcontact() != null && p.getNewcontact().size() > 0) {
						Map<String, Object> mapContact = new HashMap<String, Object>();
						mapContact.put("PersonTagNewContacts", p.getNewcontact());
						for (PersonTagNewContact cc : p.getNewcontact()) {
							if (mobileAccountMapper.selectContactcount(cc) == 0) { 
								mobileAccountMapper.insertPersonTagNewContact(mapContact);
							}
						}
					}
					if (p.getNewremark() != null && p.getNewremark().size() > 0) {
						Map<String, Object> mapRemark = new HashMap<String, Object>();
						mapRemark.put("PersonTagNewRemarks", p.getNewremark());
						for (PersonTagNewRemark cc : p.getNewremark()) {
							if (mobileAccountMapper.selectRemarkcount(cc) == 0) {
								mobileAccountMapper.insertPersonTagNewRemark(mapRemark);
							}
						}
					}
					if (p.getAction() != null && p.getAction().size() > 0) {
						Map<String, Object> mapAction = new HashMap<String, Object>();
						mapAction.put("PersonTagActions", p.getAction()); 
						mapAction.put("phone", phone);
						
						for (PersonTagAction cc : p.getAction()) {
							if (mobileAccountMapper.selectActioncount(cc) == 0) {
								mobileAccountMapper.insertPersonTagAction(mapAction);
							}
						}
					}
				}
				ret = 200;
			} else {// 如果存在相同数据则进行修改操作
				updateTag(phone,cmid, personTagPo);
				ret = 200;
			}
		} else if (tag.equals("del")) {
			if (ptags != null && ptags.size() > 0) { // 删除现有的数据

				if (cmid != null) {
					mobileAccountMapper.deletePersonTagNewContact(cmid);
					mobileAccountMapper.deletePersonTagNewRemark(cmid);
					mobileAccountMapper.deletePersonTagAction(cmid);
					mobileAccountMapper.deletePersonNewTags(cmid);
				}
				ret = 200;
			} else {
				ret = 100;
			}

		} else {
			if (cmid != null) {
				updateTag(phone,cmid, personTagPo);
				ret = 200;
			}

		}

		return ret;
	}

	public void updateTag(String phone,String cmid, List<PersonTag> personTagPo) {
		mobileAccountMapper.deletePersonTagNewContact(cmid);
		mobileAccountMapper.deletePersonTagNewRemark(cmid);
		mobileAccountMapper.deletePersonTagAction(cmid);
		mobileAccountMapper.deletePersonNewTags(cmid);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("personTags", personTagPo);
		mobileAccountMapper.insertPersonNewTag(map2);
		for (PersonTag p : personTagPo) {
			if (p.getNewcontact() != null && p.getNewcontact().size() > 0) {
				Map<String, Object> mapContact = new HashMap<String, Object>();
				mapContact.put("PersonTagNewContacts", p.getNewcontact());
				mobileAccountMapper.insertPersonTagNewContact(mapContact);
			}
			if (p.getNewremark() != null && p.getNewremark().size() > 0) {
				Map<String, Object> mapRemark = new HashMap<String, Object>();
				mapRemark.put("PersonTagNewRemarks", p.getNewremark());
				mobileAccountMapper.insertPersonTagNewRemark(mapRemark);
			}
			if (p.getAction() != null && p.getAction().size() > 0) {
				Map<String, Object> mapAction = new HashMap<String, Object>();
				mapAction.put("PersonTagActions", p.getAction()); 
				mapAction.put("phone", phone);
				mobileAccountMapper.insertPersonTagAction(mapAction);
			}
		}
	}

	// 增删改待办事项
	@Override
	public int updateTagAction(PersonTagAction ptAction) {
		return mobileAccountMapper.updateTagAction(ptAction);

	}

	@Override
	public int insertTagAction(PersonTagAction ptAction) {
		return mobileAccountMapper.insertTagAction(ptAction);
	}

	@Override
	public int delTagAction(PersonTagAction ptAction) {
		return mobileAccountMapper.delPersonTagAction(ptAction);

	}

	@Override
	public int savePersonTag(String phone, List<PersonTag> personTagPo, String tag) {
		if (personTagPo == null || personTagPo.size() == 0) {
			return 0;
		}
		List<PersonTag> personTags = mobileAccountMapper.selectPersonTag(phone);

		String cmid = personTagPo.get(0).getCmid();
		int ret = 0;
		if (tag.equals("del")) {
			if (personTags != null && personTags.size() > 0) { // 删除现有的数据

				if (cmid != null) {
					mobileAccountMapper.deletePersonTagContact(cmid);
					mobileAccountMapper.deletePersonTagRemark(cmid);
					mobileAccountMapper.deletePersonTags(cmid);
				}
				ret = 200;
			}
		} else if (tag.equals("add")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personTags", personTagPo);

			try {
				int cmidcount = mobileAccountMapper.selectCmidcount(cmid);
				if (cmidcount == 0) {
					mobileAccountMapper.insertPersonTag(map);
					for (PersonTag p : personTagPo) {
						if (p.getContact() != null && p.getContact().size() > 0) {
							Map<String, Object> mapContact = new HashMap<String, Object>();
							mapContact.put("PersonTagContacts", p.getContact());
							mobileAccountMapper.insertPersonTagContact(mapContact);
						}
						if (p.getRemark() != null && p.getRemark().size() > 0) {
							Map<String, Object> mapRemark = new HashMap<String, Object>();
							mapRemark.put("PersonTagRemarks", p.getRemark());
							mobileAccountMapper.insertPersonTagRemark(mapRemark);
						}
					}
					ret = 200;
				} else {
					ret = 100;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			if (cmid != null) {
				mobileAccountMapper.deletePersonTagContact(cmid);
				mobileAccountMapper.deletePersonTagRemark(cmid);
				mobileAccountMapper.deletePersonTags(cmid);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("personTags", personTagPo);
				mobileAccountMapper.insertPersonTag(map2);
				for (PersonTag p : personTagPo) {
					if (p.getContact() != null && p.getContact().size() > 0) {
						Map<String, Object> mapContact = new HashMap<String, Object>();
						mapContact.put("PersonTagContacts", p.getContact());
						mobileAccountMapper.insertPersonTagContact(mapContact);
					}
					if (p.getRemark() != null && p.getRemark().size() > 0) {
						Map<String, Object> mapRemark = new HashMap<String, Object>();
						mapRemark.put("PersonTagRemarks", p.getRemark());
						mobileAccountMapper.insertPersonTagRemark(mapRemark);
					}
				}
				ret = 200;
			}

		}

		return ret;
	}

	@Override
	public int insertPhoneActionResult(String phone, String actiontype, String gltype) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		map.put("actiontype", actiontype);
		AccountYzx yzx = accountMapper.selectAccountYzx(map);
		if (yzx == null) {
			return -1;
		}

		int ss = mobileAccountMapper.countLuckyResult(map);

		if (ss > 0) { // 已经参与了
			return -3;
		}

		map.put("clientNumber", yzx.getClientNumber());
		map.put("begintime", MobileAccountUtils.strToDate(null, "2015-12-01 00:00:00"));
		map.put("endtime", MobileAccountUtils.strToDate(null, "2016-12-15 00:00:00"));
		int callcount = yzxMapper.selectCountByClientNumber(map);
		if (callcount > 0) {
			map.put("marktime", new Date());
			map.put("result", gltype);
			try {
				mobileAccountMapper.insertLuckyResult(map);
				return 1;
			} catch (Exception e) {
				return -2;
			}

		} else {
			return -1;
		}

	}

	@Override
	public void savePushClientInfo(String phone, String token, String ticker) {
		PushClientInfo po = new PushClientInfo();
		po.setPhone(phone);
		po.setTicker(ticker);
		po.setToken(token);
//		mobileAccountMapper.delPushByPhone(phone);
		mobileAccountMapper.insertPush(po);
	}

	@Override
	public PushClientInfo getPushCilentInfoByPhone(String phone) {
		return mobileAccountMapper.selectPushByPhone(phone);
	}

	@Override
	public List<PersonTag> selectAllPersonTag() {
		return mobileAccountMapper.selectAllPersonTag();
	}
	@Override
	public List<PersonTagAction> selectAllTagAction() {
		return mobileAccountMapper.selectAllTagAction();
	}
	@Override
	public void updateSend(int id) {
		mobileAccountMapper.updateSend(id);
	}
	@Override
	public void updateSendAction(int id) {
		mobileAccountMapper.updateSendAction(id);
	}
	
	@Override
	public int isExistPersonTag(String phone) {
		return mobileAccountMapper.isExistPersonTag(phone);
	}

	@Override
	public String updatePhoneBalanceOf30() {
		List<AccountYzx> listYZX = mobileAccountMapper.selectPhoneBalanceZero();
		StringBuffer error = new StringBuffer();
		error.append("充值失败的手机号有:");
		for (AccountYzx yzx : listYZX) {
			String callback = jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID,
					YZXConstant.AUTH_TOKEN, yzx.getClientNumber(), "1", "1800",
					YZXConstant.APP_ID);
			try {
				PReturn yzxReturnMsg = MobileAccountUtils.getGson().fromJson(callback, PReturn.class);
				if (yzxReturnMsg != null && yzxReturnMsg.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)) {
					this.updateYzxbalance(yzx.getPhone(), "1800");
				}
			} catch (Exception e) {
				System.out.println("充值失败! 手机号 : " + yzx.getPhone());
				error.append(yzx.getPhone() + ",");
				e.printStackTrace();
			}
		}
		return error.toString();
	}

	@Override
	public List<AccountYzx> selectAccountYzxList() {
		return accountMapper.selectAccountYzxList();
	}

	// 春节活动
	@Override
	public void updateTaxic(String city) {
		mobileAccountMapper.updateTaxic(city);
	}

	// 春节活动
	@Override
	public String getTaxicTop(String callback) {
		List<SpringFestivalActivity> sfas = mobileAccountMapper.getTaxicTop();
		String json = MobileAccountUtils.getGson().toJson(sfas);
		if (callback != null) {
			json = callback + "(" + json + ")";
		}
		return json;
	}

	// 春节活动
	@Override
	public SpringFestivalActivity getCorpofHome(String city) {
		return mobileAccountMapper.getCorpofHome(city);
	}

	// 企业114最后一次启动时间记录
	@Override
	public void insertFinalTime(String deviceid, String phonenum, String finaltime) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long mTime = Long.valueOf(finaltime);
		String d = format.format(new Date(mTime));

		SpringFestivalActivity po = new SpringFestivalActivity();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceid", deviceid);
		map.put("phonenum", phonenum);
		String deid = mobileAccountMapper.getDeviceid(map);
		if (deid != null) {
			po.setFinaltime(d);
			po.setPhonenum(phonenum);
			po.setDeviceid(deviceid);
			mobileAccountMapper.updateFinalTime(po);
		} else {
			po.setPhonenum(phonenum);
			po.setDeviceid(deviceid);
			po.setFinaltime(d);
			mobileAccountMapper.insertFinalTime(po);
		}
	}

	@Override
	public String getHotWord(int n1, int n2) {
		List<HotWord> hotWords = mobileAccountMapper.getHotWord();
		List<String> words = new ArrayList<String>();
		List<String> tmp = new ArrayList<String>();
		List<HotWord> corpWords = new ArrayList<HotWord>();
		List<HotWord> tmp2 = new ArrayList<HotWord>();

		for (int i = 0; i < hotWords.size(); i++) {
			if (hotWords.get(i).getType() == '0') {
				words.add(hotWords.get(i).getK1());
			} else if (hotWords.get(i).getType() == '1') {
				corpWords.add(hotWords.get(i));
			}
		}

		if (words.size() <= n1) {
			tmp = words;
		} else {
			int rdm = (int) Math.floor(Math.random() * words.size());
			for (int i = rdm; tmp.size() < n1; i++) {
				tmp.add(words.get(i % words.size()));
			}
		}

		if (corpWords.size() <= n2) {
			tmp2 = corpWords;
		} else {
			int rdm = (int) Math.floor(Math.random() * corpWords.size());
			for (int i = rdm; tmp2.size() < n2; i++) {
				tmp2.add(corpWords.get(i % corpWords.size()));
			}
		}

		return "{\"word\":" + MobileAccountUtils.getGson().toJson(tmp) + ",\"corpWord\":"
				+ MobileAccountUtils.getGson().toJson(tmp2) + "}";
	}

	@Override
	public NewsAndMsg selectPicture(Map<String, Object> map) {
		return mobileAccountMapper.selectPicture(map);
	}

	@Override
	public NewsAndMsg selectAutoPicture() {
		return mobileAccountMapper.selectAutoPicture();
	}

	@Override
	public List<NewsAndMsg> selectNewsInfo(Map<String, Object> map) {
		return mobileAccountMapper.selectNewsInfo(map);
	}

	@Override
	public DownLoad selectDownloadUrl(String version) {
		return mobileAccountMapper.selectDownloadUrl(version);
	}

	public NewsAndMsg selectNewsInfoById(int id) {
		return mobileAccountMapper.selectNewsInfoById(id);
	}

	@Override
	public int updateNewsInfo(NewsAndMsg newsInfo) {
		return mobileAccountMapper.updateNewsInfo(newsInfo);
	}

	@Override
	public int insertNewsInfo(NewsAndMsg newsInfo) {
		return mobileAccountMapper.insertNewsInfo(newsInfo);
	}

	@Override
	public int delNewsInfo(int id) {
		return mobileAccountMapper.deleteNewsInfo(id);
	}

	public List<NewsAndMsg> selectNewsCount() {
		return mobileAccountMapper.selectNewsCount();
	}

	/**
	 * 获取消息中心最新内容
	 * 
	 * @return
	 */
	public List<NewsAndMsg> selectNewsTitle() {
		return mobileAccountMapper.selectNewsTitle();
	}

	@Override
	public int selectCmidcount(String cmid) {

		return mobileAccountMapper.selectCmidcount(cmid);
	} 
	@Override
	public int selectCmidNewcount(String cmid) {

		return mobileAccountMapper.selectCmidNewcount(cmid);
	} 
	
	@Override
	public int insertTagRemark(PersonTagNewRemark ptRemark) {
		if (mobileAccountMapper.selectRemarkcount(ptRemark) == 0) {
			return mobileAccountMapper.insertTagRemark(ptRemark);
		} else {
			return -1;
		}
	}
	@Override
	public List<Account> getReachLevelUserByLevelId(Map<String, Object> map) {
		return accountMapper.getReachLevelUserByLevelId(map);
	}

	@Override
	public int updateTagRemark(PersonTagNewRemark ptRemark) {
		return mobileAccountMapper.updateTagRemark(ptRemark);
	}

	@Override
	public int delTagNewRemark(PersonTagNewRemark ptRemark) {
		return mobileAccountMapper.delTagNewRemark(ptRemark);
	}

	@Override
	public int updateTagNewContact(PersonTagNewContact ptContact) {
		return mobileAccountMapper.updateTagNewContact(ptContact);
	}

	@Override
	public int delTagNewContact(PersonTagNewContact ptContact) {
		return mobileAccountMapper.delTagNewContact(ptContact);
	}

	@Override
	public int insertTagNewContact(PersonTagNewContact ptContact) {
		return mobileAccountMapper.insertTagNewContact(ptContact);
	}
 

	/**
	 * 免费充值
	 */
	@Override
	public int freeChargeAccountYzx(String phone, int balance,int type) {
		int rtn = 1;//默认账户不存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("apptype", "114");
		AccountYzx accountYzx = accountMapper.selectAccountYzx(map);
		
		//查询是否免费充值过
		map.clear();
		map.put("phone", phone);
		map.put("type", type);
		PaccountFreechargeLog freechargeLog = paccountFreechargeLogMapper.getByPhone(map);
		
		if (accountYzx != null) {
			if(freechargeLog==null){
					//要充值的金额
					String charge = String.valueOf(balance*1.0/60*0.055);
					//调用云之讯服务去充值
					jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountYzx.getClientNumber(), "0", charge, accountYzx.getAppid());
					
					rtn = 0;//充值成功标记返回值
					
					//更新用户云之讯的账户
					accountYzx.setBalance(String.valueOf(Integer.valueOf(accountYzx.getBalance()) + balance));
					accountMapper.updateAccountYzx(accountYzx);
					
					//保存免费充值记录
					PaccountFreechargeLog accountFreechargeLog = new PaccountFreechargeLog();
					accountFreechargeLog.setPhone(phone);
					accountFreechargeLog.setCharge(balance);//要充值的时长 秒
					accountFreechargeLog.setCreatetime(new Date());
					accountFreechargeLog.setIsUsed(0);//是否使用赠送的分钟 0：否 1：已使用
					accountFreechargeLog.setType(type);
					accountFreechargeLog.setRemark("母亲节");
					paccountFreechargeLogMapper.insert(accountFreechargeLog);
				
			}else{
				rtn=2;//已经免费充值过
			}
			
		}
		
		return rtn;
	}

	@Override
	public void updateAccountYzx(AccountYzx yzx) {
		accountMapper.updateAccountYzx(yzx);
	}

	@Override
	public int selectYzxCallCountByPhoneAndTime(Map<String, Object> map) {
		return accountMapper.selectYzxCallCountByPhoneAndTime(map);
	}

	@Override
	public int selectchargemincount(String phone) {
		return mobileAccountMapper.selectchargemincount(phone);
	}

	@Override
	public 	List<AccountGiveMin> selectfriendInfo(String phone) {
		return mobileAccountMapper.selectfriendInfo(phone);
	}

	@Override
	public int selectgivefriscount(Map<String, Object> map) { 
			return mobileAccountMapper.selectgivefriscount(map);
		}

	@Override
	public List<PersonNewTag> selectPersonTags() {
		return mobileAccountMapper.selectPersonTags();
	}

	@Override
	public void insertNewPersonTag(Map<String, Object> map) {
		mobileAccountMapper.insertNewPersonTag(map);
		
	}
	@Override
	public void insertNewContact(Map<String, Object> map) {
		mobileAccountMapper.insertNewContact(map);
		
	}
	@Override
	public void insertNewRemark(Map<String, Object> map) {
		mobileAccountMapper.insertNewRemark(map);
		
	}

	@Override
	public void updatePushClientInfo(PushClientInfo info) {
		mobileAccountMapper.updatePushClientInfo(info);
	}
	@Override
	public void updatePushClientInfoTokenNull(PushClientInfo info) {
		mobileAccountMapper.updatePushClientInfoTokenNull(info);
	}
	@Override
	public void deletePushClientInfoByPhone(String phone){
		mobileAccountMapper.deletePushClientInfoByPhone(phone);
	}
	@Override
	public List<PushClientInfo> getPushCilentInfoList(){
		return mobileAccountMapper.getPushCilentInfoList();
	}

	@Override
	public List<Account> getNologinUserList(String dateStr) {
		return accountMapper.getNologinUserList(dateStr);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class}) 
	public AccountYzx insertAccount(Account account) {
		try{
			accountMapper.insert(account);
	
			AccountSecret accountSecret = new AccountSecret();
			accountSecret.setAccountid(String.valueOf(account.getAccountid()));
			accountSecret.setPhone(account.getPhone());
			accountSecret.setSecret(account.getSecret());
			accountSecret.setApptype(account.getApptype());
			accountSecret.setCreatetime(MobileAccountUtils.getNowtime());
			accountMapper.insertSecret(accountSecret);
	
			Score score = new Score();
			score.setMarktime(MobileAccountUtils.getNowtime());
			score.setPhone(account.getPhone());
			score.setScorenum("0");
			scoreMapper.insertScore(score);
			
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return null;
	}
}
 
   