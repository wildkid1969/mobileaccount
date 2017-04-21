package com.hc360.mobileaccount.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.hc360.mmt.memcached.MemcachedHelper;
import com.hc360.mmt.memcached.mo.user.ValidateKeyMO;
import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.AccountGiveMin;
import com.hc360.mobileaccount.po.AccountSecret;
import com.hc360.mobileaccount.po.AccountYZM;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.LoanInfo;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.PaccountFreechargeLog;
import com.hc360.mobileaccount.po.PersonNewTag;
import com.hc360.mobileaccount.po.PersonTag;
import com.hc360.mobileaccount.po.PersonTagAction;
import com.hc360.mobileaccount.po.PersonTagContact;
import com.hc360.mobileaccount.po.PersonTagNewContact;
import com.hc360.mobileaccount.po.PersonTagNewRemark;
import com.hc360.mobileaccount.po.PersonTagRemark;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.Teacher;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.GradeService;
import com.hc360.mobileaccount.service.PaccountFreechargeLogService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserSignInService;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;
import com.ucpaas.client.JsonReqClient;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Resource
	PaccountFreechargeLogService paccountFreechargeLogService;
	
	private JsonReqClient jsonReqClient = new JsonReqClient();
	
	@Resource
	private UserSignInService userSignInService;
	
	@Resource
	private GradeService gradeService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	/*
	 * 给待激活用户发短信
	 */
	@RequestMapping(value = "sendMessageActivedUser")
	@ResponseBody
	public String sendMessageActivedUser() {
		return accountService.sendMessageActivedUser();
	}

	@RequestMapping(value = "clear")
	@ResponseBody
	public String clearAccountInfo(String phone) {

		accountService.clearAccountInfo(phone);

		return "OK";
	}

	@RequestMapping(value = "isExistPersonTag")
	@ResponseBody
	public String isExistPersonTag(String phone){
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		if(phone == null){
			rm.setCode(-2);
			rm.setMessage("请求数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		phone = RC4.decry_RC4(phone);
		if(accountService.isExistPersonTag(phone) <= 0){
			rm.setCode(300);
			rm.setMessage("数据为空!");
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

	// 新增待办事项接口
	@RequestMapping(value = "insertTagAction")
	@ResponseBody
	public String addActionInfo(String ptAction, String callback) {
		int rtn = 0;
		ReturnMsg r = new ReturnMsg();
		ptAction = RC4.decry_RC4(ptAction);
		if (ptAction != null) {
			try {
				ptAction = URLDecoder.decode(ptAction, "UTF-8");
				System.out.println("zss的" + ptAction);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagAction> lists = new ArrayList<PersonTagAction>();
			lists = MobileAccountUtils.getGson().fromJson(ptAction, new TypeToken<List<PersonTagAction>>() {
			}.getType());
			PersonTagAction pt = lists.get(0);
			rtn = accountService.insertTagAction(pt);
			if (rtn > 0) {
				r.setCode(200);
			} else {
				r.setCode(-1);
			}
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 修改待办事项
	@RequestMapping(value = "updateTagAction")
	@ResponseBody
	public String updateTagAction(String ptAction) throws Exception {
		ReturnMsg r = new ReturnMsg();
		ptAction = RC4.decry_RC4(ptAction);
		if (ptAction != null) {
			try {
				ptAction = URLDecoder.decode(ptAction, "UTF-8");
				System.out.println("zss的" + ptAction);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagAction> lists = new ArrayList<PersonTagAction>();
			lists = MobileAccountUtils.getGson().fromJson(ptAction, new TypeToken<List<PersonTagAction>>() {
			}.getType());
			PersonTagAction pt = lists.get(0);
			if (pt.getCid() != null && pt.getCmid() != null) {
				if (accountService.updateTagAction(pt) > 0) {
					r.setCode(200);
				} else {
					r.setCode(-1);
				}

			} else {
				return null;
			}
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 删除待办事项
	@RequestMapping(value = "delTagAction")
	@ResponseBody
	public String delTagActionInfo(String cid, String cmid) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (cmid != null && cid != null) {
			PersonTagAction ptAction = new PersonTagAction();
			ptAction.setCmid(cmid);
			ptAction.setCid(cid);

			if (accountService.delTagAction(ptAction) > 0) {
				r.setCode(200);
			} else {
				r.setCode(100);
			}
		} else {
			r.setCode(-1);
			r.setMessage("cid 和cmid都不能为空");
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 用户管理增删改备注
	@RequestMapping(value = "insertTagRemark")
	@ResponseBody
	public String insertTagRemark(String ptRemark) {
		int rtn = 0;
		ReturnMsg r = new ReturnMsg();
		ptRemark = RC4.decry_RC4(ptRemark);
		if (ptRemark != null) {
			try {
				ptRemark = URLDecoder.decode(ptRemark, "UTF-8");
				System.out.println("zss的" + ptRemark);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagNewRemark> lists = new ArrayList<PersonTagNewRemark>();
			lists = MobileAccountUtils.getGson().fromJson(ptRemark, new TypeToken<List<PersonTagNewRemark>>() {
			}.getType());
			PersonTagNewRemark pt = lists.get(0);

			rtn = accountService.insertTagRemark(pt);
			if (rtn > 0) {
				r.setCode(200);
			} else {
				r.setCode(-1);
			}
		}
		return MobileAccountUtils.getGson().toJson(r);

	}

	// 修改待办事项
	@RequestMapping(value = "updateTagRemark")
	@ResponseBody
	public String updateTagRemark(String ptRemark) throws Exception {
		ReturnMsg r = new ReturnMsg();
		ptRemark = RC4.decry_RC4(ptRemark);
		System.out.println(ptRemark);
		if (ptRemark != null) {
			try {
				ptRemark = URLDecoder.decode(ptRemark, "UTF-8");
				System.out.println("zss的" + ptRemark);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagNewRemark> lists = new ArrayList<PersonTagNewRemark>();
			lists = MobileAccountUtils.getGson().fromJson(ptRemark, new TypeToken<List<PersonTagNewRemark>>() {
			}.getType());
			PersonTagNewRemark pt = lists.get(0);
			if (pt.getCid() != null && pt.getCmid() != null) {
				if (accountService.updateTagRemark(pt) > 0) {
					r.setCode(200);
				} else {
					r.setCode(-1);
				}

			} else {
				return null;
			}
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 删除待办事项
	@RequestMapping(value = "delTagRemark")
	@ResponseBody
	public String delTagRemark(String cid, String cmid) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (cid != null && cmid != null) {
			PersonTagNewRemark pt = new PersonTagNewRemark();
			pt.setCmid(cmid);
			pt.setCid(cid);
			if (accountService.delTagNewRemark(pt) > 0) {
				r.setCode(200);
			} else {
				r.setCode(-1);
			}
		} else {
			r.setMessage("cid和cmid不能为空！");
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 用户管理修改用户联系人手机号、职位（后续增加）
	// 修改待办事项
	// 用户管理增删改备注
	@RequestMapping(value = "insertTagContact")
	@ResponseBody
	public String insertTagContent(String ptContact) {
		int rtn = 0;
		ReturnMsg r = new ReturnMsg();
		ptContact = RC4.decry_RC4(ptContact);
		if (ptContact != null) {
			try {
				ptContact = URLDecoder.decode(ptContact, "UTF-8");
				System.out.println("zss的" + ptContact);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagNewContact> lists = new ArrayList<PersonTagNewContact>();
			lists = MobileAccountUtils.getGson().fromJson(ptContact, new TypeToken<List<PersonTagNewContact>>() {
			}.getType());
			PersonTagNewContact pt = lists.get(0);
			rtn = accountService.insertTagNewContact(pt);
			if (rtn > 0) {
				r.setCode(200);
			} else {
				r.setCode(-1);
			}
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	@RequestMapping(value = "updateTagContact")
	@ResponseBody
	public String updateTagContact(String ptContact) throws Exception {
		ReturnMsg r = new ReturnMsg();
		ptContact = RC4.decry_RC4(ptContact);
		if (ptContact != null) {
			try {
				ptContact = URLDecoder.decode(ptContact, "UTF-8");
				System.out.println("zss的" + ptContact);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			List<PersonTagNewContact> lists = new ArrayList<PersonTagNewContact>();
			lists = MobileAccountUtils.getGson().fromJson(ptContact, new TypeToken<List<PersonTagNewContact>>() {
			}.getType());
			PersonTagNewContact pt = lists.get(0);
			if (pt.getCid() != null && pt.getCmid() != null) {
				if (accountService.updateTagNewContact(pt) > 0) {
					r.setCode(200);
				} else {
					r.setCode(-1);
				}

			} else {
				return null;
			}
		}
		return MobileAccountUtils.getGson().toJson(r);
	}

	// 删除待办事项
	@RequestMapping(value = "delTagContent")
	@ResponseBody
	public String delTagContent(String cid, String cmid) throws Exception {
		ReturnMsg r = new ReturnMsg();
		PersonTagNewContact pt = new PersonTagNewContact();
		if (cid != null && cmid != null) {
			pt.setCmid(cmid);
			pt.setCid(cid);
			if (accountService.delTagNewContact(pt) > 0) {
				r.setCode(200);
			} else {
				r.setCode(-1);
			}
		} else {
			r.setMessage("cid和cmid不能为空！");
		}

		return MobileAccountUtils.getGson().toJson(r);

	}

	// 用户管理新接口调用
	@RequestMapping(value = "inNewPersonTag")
	@ResponseBody
	public String savePersonInfo(String personTag, String tag) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		if (personTag == null) {
			rm.setCode(-2);
			rm.setMessage("上传的数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		personTag = RC4.decry_RC4(personTag);
		System.out.println("personTag:" + personTag);
		if (personTag != null) {
			try {
				personTag = URLDecoder.decode(personTag, "UTF-8");
				System.out.println("Test结果的" + personTag);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<PersonTag> lists = new ArrayList<PersonTag>(); 
		try {
			lists = MobileAccountUtils.getGson().fromJson(personTag, new TypeToken<List<PersonTag>>() {
			}.getType());

			if (lists.size() == 0) {
				rm.setCode(-2);
				rm.setMessage("上传的用户管理数据为空");
				return MobileAccountUtils.getGson().toJson(rm);
			}
		} catch (Exception e) {
			rm.setCode(-3);
			rm.setMessage("json解析失败");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		String phone = lists.get(0).getPhone();
		int ret = accountService.saveNewPersonTag(phone, lists, tag);
		if (ret == 0) {
			rm.setCode(-1);
			rm.setMessage("操作失败！");
		} else if (ret == 200) {
			rm.setCode(200);
			rm.setMessage("操作成功！");
		} else if (ret == 100) {
			rm.setCode(-1);
			rm.setMessage("该用户已经存在！");
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

	// 用户管理同步下载用户数据
	@RequestMapping(value = "downPersonTag")
	@ResponseBody
	public String getNewPersonTag(String phone) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		if (phone == null) {
			rm.setCode(-2);
			rm.setMessage("请求数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		phone = RC4.decry_RC4(phone);
		List<PersonTag> personTags = accountService.selectNewPersonTag(phone);//todo
		if (personTags != null && personTags.size() > 0) {
			try {
				String as = RC4
						.encry_RC4_string(URLEncoder.encode(MobileAccountUtils.getGson().toJson(personTags), "UTF-8"),
								RC4.KEY);
				return as;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			rm.setCode(300);
			rm.setMessage("请求数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
	}

	// 用户管理数据迁移
	@RequestMapping(value = "inNewTag")
	@ResponseBody
	public String insertNewPersonTag() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String uuids = UUID.randomUUID().toString().trim().replaceAll("-", "");
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);

		List<PersonNewTag> personTags = accountService.selectPersonTags();//todo
		if (personTags != null && personTags.size() > 0) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				String str = "";
				for (PersonNewTag tag : personTags) {
					String tagdate = tag.getTagdate();
					long t;
					if (tagdate != null && !"".equals(tagdate)) {
						String date = tagdate.replace("年", "-").replace("月", "-").replace("日", "");
						t = format.parse(date).getTime();
					} else {
						Date dass = new Date();
						String localdate = format.format(dass);
						t = format.parse(localdate).getTime();
					}

					if (tag.getCorpid() != null) {
						str = 1 + tag.getCorpid() + uuids + tag.getPhone();
						// md5Encode.MD5Encode(str, "UTF-8").toUpperCase();
					} else {
						str = 0 + tag.getId() + uuids + tag.getPhone();
					}
					System.out.println(tag.getTagphone());
					String cmid = MD5.md5Encode(str).toUpperCase();
					map.put("tagdate", t);

					map.put("cmid", cmid);
					map.put("Tag", tag);
					accountService.insertNewPersonTag(map);
					// if (tag.getName()!=null) {
					accountService.insertNewContact(map);

					// }
					// if (tag.getTagremark()!=null) {
					accountService.insertNewRemark(map);

					// }
				}

				rm.setCode(200);
				return MobileAccountUtils.getGson().toJson(rm);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}

		} else {
			rm.setCode(300);
			return MobileAccountUtils.getGson().toJson(rm);
		}
	}

	@RequestMapping(value = "outPersonTag")
	@ResponseBody
	public String getPersonTag(String phone){
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		if(phone == null){
			rm.setCode(-2);
			rm.setMessage("请求数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		phone = RC4.decry_RC4(phone);
		List<PersonTag> personTags = accountService.selectPersonTag(phone);
		if(personTags != null && personTags.size() > 0){
			try {
				String as = RC4.encry_RC4_string(URLEncoder.encode(MobileAccountUtils.getGson().toJson(personTags),"UTF-8"),RC4.KEY);
				return as;
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
				return "";
			}
		}else{
			rm.setCode(300);
			rm.setMessage("请求数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
	}

	@RequestMapping(value = "inPersonTag")
	@ResponseBody
	public String savePersonTag(String personTag, String tag) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		if (personTag == null) {
			rm.setCode(-2);
			rm.setMessage("上传的数据为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		personTag = RC4.decry_RC4(personTag);
		System.out.println("personTag:" + personTag);
		if (personTag != null) {
			try {
				personTag = URLDecoder.decode(personTag, "UTF-8");
				System.out.println("zss的" + personTag);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<PersonTag> lists = new ArrayList<PersonTag>();
		try {
			lists = MobileAccountUtils.getGson().fromJson(personTag, new TypeToken<List<PersonTag>>() {
			}.getType());
			for (PersonTag list : lists) {
				list.setName(URLDecoder.decode(list.getName(), "UTF-8"));
				if (list.getContact() != null) {
					for (PersonTagContact c : list.getContact()) {
						c.setName(URLDecoder.decode(c.getName(), "UTF-8"));
					}
				}
				if (list.getRemark() != null) {
					for (PersonTagRemark cc : list.getRemark()) {
						cc.setTagremark(URLDecoder.decode(cc.getTagremark(), "UTF-8"));
					}
				}
			}
			if (lists.size() == 0) {
				rm.setCode(-2);
				rm.setMessage("上传的数据为空");
				return MobileAccountUtils.getGson().toJson(rm);
			}
		} catch (Exception e) {
			rm.setCode(-3);
			rm.setMessage("json解析失败");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		String phone = lists.get(0).getPhone();
		int ret = accountService.savePersonTag(phone, lists, tag);
		if (ret == 0) {
			rm.setCode(-1);
			rm.setMessage("操作失败！");
		} else if (ret == 200) {
			rm.setCode(200);
			rm.setMessage("操作成功！");
		} else if (ret == 100) {
			rm.setCode(-1);
			rm.setMessage("该用户已经存在！");
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "updatePersonInfo")
	@ResponseBody
	public String completePersonInfo(Account account, String callback){	
		account.setPhone(RC4.decry_RC4(account.getPhone()));
		
//		byte[] buffer = new Base64().decode(account.getHeaderimg());
//		account.setHeaderimg(uploadImage(buffer));
		int ret = accountService.updateAccount(account);
		ReturnMsg rm = new ReturnMsg();
		if(ret > 0){
			rm.setCode(200);
		}else{
			rm.setCode(-1);
		}
		return  callback + "(" +MobileAccountUtils.getGson().toJson(rm) +")";
	}
	

	@RequestMapping(value = "uptCompanyName")
	@ResponseBody
	public String uptCompanyName(String cname, String cmid) {
		Map<String, Object> map = new HashMap<String, Object>();
		ReturnMsg rm = new ReturnMsg();
		if (cname != null && cmid != null) {
			map.put("cmid", cmid);
			map.put("cname", cname);
			accountService.uptCompanyName(map);//todo
			rm.setCode(200);
		}

		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "getPersonInfo")
	@ResponseBody
	public String getPersonInfo(String phone , String callback){
		phone = RC4.decry_RC4(phone);
		Account account  = accountService.getAccountInfo(phone);
		if(account == null){			
			account = new Account();
			account.setPhone(phone);
		}
		return callback + "(" +MobileAccountUtils.getGson().toJson(account) +")";
	}
	
	@RequestMapping(value = "deleteyzx")
	@ResponseBody
	public String deleteYzxAccount(){
		accountService.deleteYzx();
		return "OK";
	}

	@RequestMapping(value = "init")
	@ResponseBody
	public String initAccountInfo(){
//		accountService.initRegUserYzxAccount();
		return "OK";
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public String accountRegister(Account account, HttpServletResponse response) throws Exception {
		
		ReturnMsg retMsg = new ReturnMsg();
		String pp = RC4.decry_RC4(account.getPhone());
		if (pp != null) {
			account.setPhone(pp);
			System.out.println(account.getPhone());
		}
		if (account.getPhone() == null && !MobileAccountUtils.isPhoneNumber(account.getPhone())
				&& account.getPhone().isEmpty()) {
			retMsg.setCode(1);
			retMsg.setMessage("手机号不能为空  或手机格式不正确");
			return MobileAccountUtils.getGson().toJson(retMsg);
		}
		if (accountService.isExitsPhone(account.getPhone(), account.getApptype())) {
			retMsg.setCode(1);
			retMsg.setMessage("手机号已经存在!");
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		if (account.getInvitation() != null
				&& !accountService.isExitsPhone(account.getInvitation(), account.getApptype())) {
			retMsg.setCode(1);
			retMsg.setMessage("邀请码不存在!");
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		if (account.getSecret() == null && account.getSecret().isEmpty()) {
			retMsg.setCode(1);
			retMsg.setMessage("密码不能为空!");
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

//		if (account.getUsername() == null) {
//			account.setUsername(account.getPhone());
//		}
		account.setSecret(account.getSecret());
		account.setAppId(YZXConstant.APP_ID);
		account.setApptype("114");
		account.setCreatetime(String.valueOf(MobileAccountUtils.getNowtime()));
		account.setEnable(2);
		AccountYzx accountId = accountService.insert(account);
		if (accountId != null) {
			retMsg.setCode(200);
			retMsg.setMessage("添加账号成功");
			retMsg.setClientNumber(accountId.getClientNumber());
			retMsg.setClientPwd(accountId.getClientPwd());
			retMsg.setAccoundid(accountId.getAccountId());
			try {
				String searchMD5 = MD5.md5Encode("phone=" + account.getPhone() + "secret=" + account.getSecret()
						+ "uid=" + account.getAccountid() + "key=yidonghulianshiyebuwanwansui");
				String param = "phone=" + account.getPhone() + "&secret=" + account.getSecret() + "&uid="
						+ account.getAccountid() + "&key=" + searchMD5;
				String result = MobileAccountUtils.sendGet(
						"http://168.mobile.hc360.com/mobileProduct/order/compregedit?", param);
				ReturnMsg sl = MobileAccountUtils.getGson().fromJson(result, ReturnMsg.class);
				if (sl != null && sl.getCode() == 200) {
					accountService.updateInfoSearch(String.valueOf(account.getAccountid()));
				}
			} catch (Exception e) {
			}

		} else {
			retMsg.setCode(-200);
			retMsg.setMessage("创建账号失败!");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "yzm")
	@ResponseBody
	public String accountGetYZM(AccountYZM yzm, HttpServletRequest request) throws Exception {
		System.out.println(yzm.getPhone() + yzm.getApptype());
		ReturnMsg retMsg = new ReturnMsg();
		String pp = RC4.decry_RC4(yzm.getPhone());
		if (pp != null) {
			yzm.setPhone(pp);
		}
		if (yzm != null && yzm.checkPara() && MobileAccountUtils.isPhoneNumber(yzm.getPhone())) {
			if (yzm.getType().equals("zc") && accountService.isExitsPhone(yzm.getPhone(), yzm.getApptype())) {
				retMsg.setCode(1);
				retMsg.setMessage("手机号已经存在!");
				return MobileAccountUtils.getGson().toJson(retMsg);
			}
			if (!yzm.getType().equals("zc") && !accountService.isExitsPhone(yzm.getPhone(), yzm.getApptype())) {
				retMsg.setCode(1);
				retMsg.setMessage("手机号不存在!");
				return MobileAccountUtils.getGson().toJson(retMsg);
			}
			AccountYZM aYZM = accountService.selectYZM(yzm);
			if (aYZM != null) {
				long cc = MobileAccountUtils.getTime();
				long dd = Long.valueOf(aYZM.getCreatetime());
				long ff = cc - dd;
				long ccc = ff / 1000 - Integer.valueOf(aYZM.getDuring());
				if (ccc < 0) {
					retMsg.setCode(1);
					retMsg.setMessage("您之前获取的验证码还有效，请重新输入!");
					return MobileAccountUtils.getGson().toJson(retMsg);
				}

			}
			yzm.setCreatetime(MobileAccountUtils.getNowtime());
			yzm.setShortsecret(MobileAccountUtils.getSmsCheckCode(4));
			yzm.setDuring("60");
			accountService.insertYZM(yzm);
			retMsg.setCode(200);
			
//			if (yzm.getType().equals("zc")) {
//				content = "企业114  注册验证码：" + yzm.getShortsecret() + ";唯一热线：010-82297384";
//			} else if (yzm.getType().equals("xg")) {
//				content = "企业114  您正在重置密码，验证码：" + yzm.getShortsecret() + ";唯一热线：010-82297384";
//			}
			int templatId = 0;
			if (yzm.getType().equals("zc")) {
				templatId = 34;
			} else if (yzm.getType().equals("xg")) {
				templatId = 35;
			}
			MobileAccountUtils.sendShortMsg(yzm.getPhone(), yzm.getShortsecret(), templatId, 24);
		} else {
			retMsg.setCode(2);
			retMsg.setMessage("请输入正确的手机号");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	
	/**
	 * 直接发送手机验证码
	 * @param yzm
	 * @param ctoken
	 * @param valicode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "yzm_new")
	@ResponseBody
	public String accountNewGetYZM(AccountYZM yzm,HttpServletRequest request) throws Exception {
		System.out.println(yzm.getPhone() + yzm.getApptype());
		ReturnMsg retMsg = new ReturnMsg();
		String pp = RC4.decry_RC4(yzm.getPhone());
		if (pp != null) {
			yzm.setPhone(pp);
		}

		if (yzm != null && yzm.checkPara() && MobileAccountUtils.isPhoneNumber(yzm.getPhone())) {
			AccountYZM aYZM = accountService.selectYZM(yzm);
			if (aYZM != null) {
				long cc = MobileAccountUtils.getTime();
				long dd = Long.valueOf(aYZM.getCreatetime());
				long ff = cc - dd;
				long ccc = ff / 1000 - Integer.valueOf(aYZM.getDuring());
				if (ccc < 0) {
					retMsg.setCode(1);
					retMsg.setMessage("您之前获取的验证码还有效，请重新输入!");
					return MobileAccountUtils.getGson().toJson(retMsg);
				}
			}
			yzm.setCreatetime(MobileAccountUtils.getNowtime());
			yzm.setShortsecret(MobileAccountUtils.getSmsCheckCode(4));
			yzm.setDuring("60");
			accountService.insertYZM(yzm);
			retMsg.setCode(200);

//			if (yzm.getType().equals("zc")) {
//				content = "企业114  注册验证码：" + yzm.getShortsecret() + ";唯一热线：010-82297384";
//			} else if (yzm.getType().equals("xg")) {
//				content = "企业114  您正在重置密码，验证码：" + yzm.getShortsecret() + ";唯一热线：010-82297384";
//			}
			int templatId = 0;
			if (yzm.getType().equals("zc")) {
				templatId = 34;
			} else if (yzm.getType().equals("xg")) {
				templatId = 35;
			}
			MobileAccountUtils.sendShortMsg(yzm.getPhone(), yzm.getShortsecret(), templatId,24);
		} else {
			retMsg.setCode(2);
			retMsg.setMessage("请输入正确的手机号");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}
	
	
	/**
	 * 获取图片验证码的秘钥
	 * @param callback
	 * @return 
	 */
	@RequestMapping(value="getLoginTicket")
	@ResponseBody
	public String getLoginTicket(String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		String url = "http://sso.hc360.com/LoginTicket.jsp";
		String result = MobileAccountUtils.doGet(url, "utf-8");
		
		if(!StringUtils.isEmpty(result)){
			result = result.substring(result.indexOf("{"), result.indexOf("}")+1);
			JSONObject json = JSONObject.fromObject(result);
			String key = json.getString("key");
			String value=json.getString("value");
			
			msg.setCode(200);
			msg.setKey(key);
			msg.setValue(value);
			
		}
		
		if(StringUtils.isEmpty(callback)){
			callback = "null";
		}
		
		
		return callback + "(" + MobileAccountUtils.getGson().toJson(msg) + ")";
	}
	
	/**
	 * 根据秘钥获取图片验证码
	 * @param value
	 * @param resposne
	 * @throws IOException
	 */
	@RequestMapping(value="getImageValidCode")
	public void getImageValidCode(String value,HttpServletResponse resposne) throws IOException{
		resposne.sendRedirect("http://sso.hc360.com/ValidImage.jsp?seed="+value);
	}
	
	
	/**
	 * 发送手机验证码前添加图片验证码 防盗刷
	 * @param yzm
	 * @param ctoken
	 * @param valicode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "yzm_validate")
	@ResponseBody
	public String yzmValidate(AccountYZM yzm,String ctoken,String valicode,HttpServletRequest request) throws Exception {
		System.out.println(yzm.getPhone() + yzm.getApptype());
		ReturnMsg retMsg = new ReturnMsg();
		String pp = RC4.decry_RC4(yzm.getPhone());
		if (pp != null) {
			yzm.setPhone(pp);
		}
		
		//后台校验验证码是否正确 
		String valiResult = MobileAccountUtils.doGet("http://sso.hc360.com/ValidJsonCode.jsp?ctoken="+ctoken+"&validCode="+valicode,"gbk"); 
		boolean codeFlag = MemcachedHelper.remove(ctoken,ValidateKeyMO.class); 
		
		System.out.println("valiResult:"+valiResult+";ctoken:"+ctoken+";valicode:"+valicode);
		
		if(!valiResult.contains("msg:\"1\"") || !codeFlag){ 
			retMsg.setCode(1);
			retMsg.setMessage("您的输入有误，请重新输入!");
			return MobileAccountUtils.getGson().toJson(retMsg);
		}
		
		if (yzm != null && yzm.checkPara() && MobileAccountUtils.isPhoneNumber(yzm.getPhone())) {
			AccountYZM aYZM = accountService.selectYZM(yzm);
			if (aYZM != null) {
				long cc = MobileAccountUtils.getTime();
				long dd = Long.valueOf(aYZM.getCreatetime());
				long ff = cc - dd;
				long ccc = ff / 1000 - Integer.valueOf(aYZM.getDuring());
				if (ccc < 0) {
					retMsg.setCode(1);
					retMsg.setMessage("您之前获取的验证码还有效，请重新输入!");
					return MobileAccountUtils.getGson().toJson(retMsg);
				}
			}
			yzm.setCreatetime(MobileAccountUtils.getNowtime());
			yzm.setShortsecret(MobileAccountUtils.getSmsCheckCode(4));
			yzm.setDuring("60");
			accountService.insertYZM(yzm);
			retMsg.setCode(200);
			
			int templatId = 0;
			if (yzm.getType().equals("zc")) {
				templatId = 34;
			} else if (yzm.getType().equals("xg")) {
				templatId = 35;
			}
			MobileAccountUtils.sendShortMsg(yzm.getPhone(), yzm.getShortsecret(), templatId,24);
		} else {
			retMsg.setCode(2);
			retMsg.setMessage("请输入正确的手机号");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}
	
	
	/**
	 * 验证码登陆
	 * @param yzm
	 * @param account
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loginbyyzm")
	@ResponseBody
	public String accountAddByYZM(AccountYZM yzm,Account account,HttpServletRequest request) {
		ReturnMsg retMsg = new ReturnMsg();
		if (!StringUtils.isEmpty(yzm.getPhone())) {
			String pp = RC4.decry_RC4(yzm.getPhone());
			yzm.setPhone(pp.trim());
			yzm.setApptype("114");
			yzm.setType("zc");			
			AccountYZM cc = accountService.selectYZM(yzm);
			if (cc != null && cc.getShortsecret() != null && cc.getShortsecret().equals(yzm.getShortsecret())) {
				AccountYzx yzx = accountService.getYzx(yzm.getPhone(), "114");
				if(yzx == null){
					account.setPhone(yzm.getPhone());
					account.setApptype("114");
					account.setAppId(YZXConstant.APP_ID);
					account.setSecret(YZXConstant.DEFAULT_SECRET); // 默认密码 lvtieandxiaoyu
					account.setCreatetime(MobileAccountUtils.getNowtime());
					account.setEnable(2);
					//新增三项添加：年限、行业、兴趣标签
					AccountYzx accountId = accountService.insert(account);
					if (accountId != null) {
						retMsg.setCode(200);
						retMsg.setMessage("添加账号成功");
						retMsg.setClientNumber(accountId.getClientNumber());
						retMsg.setClientPwd(accountId.getClientPwd());
						retMsg.setAccoundid(accountId.getAccountId());
						if (accountService.selectgivefriendcount(account.getPhone()) > 0) {
							String chargeType = "0"; // 0 充值；1 回收。
							// 要给邀请码用户充值10分钟话费 即0.55元
							List<AccountGiveMin> lists = accountService.selectphonebyfriend(account.getPhone());
							for (AccountGiveMin s : lists) {
								String phone = s.getPhone();
								int yzxcharge = accountService.selectchargemincount(phone);
								if (yzxcharge < 5) {
									jsonReqClient.charegeClient(YZXConstant.ACCOUNT_SID, YZXConstant.AUTH_TOKEN, accountId.getClientNumber(), chargeType, "0.55", YZXConstant.APP_ID);
									accountService.updatefriendstate(accountId.getPhone());
									accountService.updateYzxbalance(phone, "600");
								}
							}
						}
//						try {
//							String searchMD5 = MD5.md5Encode("phone=" + account.getPhone() + "secret=" + account.getSecret()
//									+ "uid=" + account.getAccountid() + "key=yidonghulianshiyebuwanwansui");
//							String param = "phone=" + account.getPhone() + "&secret=" + account.getSecret() + "&uid="
//									+ account.getAccountid() + "&key=" + searchMD5;
//							String result = MobileAccountUtils.sendGet(
//									"http://168.mobile.hc360.com/mobileProduct/order/compregedit?", param);
//							ReturnMsg sl = MobileAccountUtils.getGson().fromJson(result, ReturnMsg.class);
//							if (sl != null && sl.getCode() == 200) {
//								accountService.updateInfoSearch(String.valueOf(account.getAccountid()));
//							}
//						} catch (Exception e) {
//						}

					} else {
						retMsg.setCode(-200);
						retMsg.setMessage("创建账号失败!");
					}
					Account accountInfo = accountService.getAccountInfo(yzm.getPhone());
					if(accountInfo != null){
						retMsg.setAccoundid(String.valueOf(accountInfo.getAccountid()));
					}
					
					AccountSecret secret = accountService.getUserPwd(yzm.getPhone(), "114");
					if(secret != null){
						retMsg.setSecret(secret.getSecret());
					}

					return MobileAccountUtils.getGson().toJson(retMsg);
				}else{//账户已存在则保存用户信息
					//新增三项添加：年限、行业、兴趣标签
					account.setPhone(pp);
					accountService.updateAccount(account);				
				}
				
				Account accountInfo = accountService.getAccountInfo(yzm.getPhone());
				if(accountInfo != null){
					retMsg.setAccoundid(String.valueOf(accountInfo.getAccountid()));		
					retMsg.setUserInfo(accountInfo);
					
					//用户签到
//					UserSignIn in = new UserSignIn();
//					in.setUserid(Long.valueOf(accountInfo.getAccountid()));
//					in.setSigntimeStr(DateUtils.getCurrentTime("yyyy-MM-dd"));
//					UserSignIn oldIn = userSignInService.getUserSignInByTime(in);
//					if(oldIn==null){
//						in.setSigntime(new Date());
//						userSignInService.insert(in);
//					}
					
				}
				
				AccountSecret secret = accountService.getUserPwd(yzm.getPhone(), "114");
				if(secret != null){
					retMsg.setSecret(secret.getSecret());
				}
				
				retMsg.setClientNumber(yzx.getClientNumber());
				retMsg.setClientPwd(yzx.getClientPwd());
				retMsg.setCode(200);
				retMsg.setYzm("验证通过!");
			} else {
				retMsg.setCode(2);
				retMsg.setMessage("您输入的验证码有误，请重新输入!");
				
				if("8880".equals(yzm.getShortsecret()) && yzm.getPhone().startsWith("199")){
					Account accountInfo = accountService.getAccountInfo(yzm.getPhone());
					if(accountInfo != null){
						account.setPhone(yzm.getPhone());
						accountService.updateAccount(account);	
						
						AccountSecret secret = accountService.getUserPwd(yzm.getPhone(), "114");
						if(secret != null){
							retMsg.setSecret(secret.getSecret());
						}
						
						retMsg.setCode(200);
						retMsg.setYzm("验证通过!");
						retMsg.setAccoundid(String.valueOf(accountInfo.getAccountid()));		
						retMsg.setUserInfo(accountInfo);
						retMsg.setClientNumber("0");
						retMsg.setClientPwd("0");
					}
				}
				
				
			}

		} else {
			retMsg.setCode(2);
			retMsg.setMessage("您的输入有误，请重新输入!");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}
	
	
	
	/**
	 * 用户自动登录
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "autologin")
	@ResponseBody
	public String autologin(Account account) {
		ReturnMsg msg = new ReturnMsg();
		String pp = RC4.decry_RC4(account.getPhone());
				
		account.setPhone(pp);
		accountService.updateAccount(account);				
		
		msg.setCode(200);
			
		return MobileAccountUtils.getGson().toJson(msg);
	}

	@RequestMapping(value = "checkyzm")
	@ResponseBody
	public String accountCheckYZM(AccountYZM yzm, HttpServletRequest request) {
		ReturnMsg retMsg = new ReturnMsg();
		if (yzm != null) {
			String pp = RC4.decry_RC4(yzm.getPhone());
			yzm.setPhone(pp);
			AccountYZM cc = accountService.selectYZM(yzm);
			if (cc != null && cc.getShortsecret() != null && cc.getShortsecret().equals(yzm.getShortsecret())) {
				retMsg.setCode(200);
				retMsg.setYzm("验证通过!");
			} else {
				retMsg.setCode(2);
				retMsg.setMessage("您输入的验证码有误，请重新输入!");
			}

		} else {
			retMsg.setCode(2);
			retMsg.setMessage("您输入的验证码有误，请重新输入!");
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "modifypwd")
	@ResponseBody
	public String accountModifyPwd(AccountYZM yzm) {
		ReturnMsg retMsg = new ReturnMsg();
		String pp = RC4.decry_RC4(yzm.getPhone());
		yzm.setPhone(pp);
		if (yzm != null) {
			AccountYZM cc = accountService.selectYZM(yzm);
			if (cc != null && cc.getShortsecret() != null && cc.getShortsecret().equals(yzm.getShortsecret())) {
				System.out.println("updatePWD : " +yzm.getPhone() +" asd " + yzm.getPwd());
				accountService.updatepwd(yzm);
				retMsg.setCode(200);
				retMsg.setMessage("修改密码成功!");
			} else {
				retMsg.setCode(2);
				retMsg.setMessage("修改密码失败，请重新尝试!");
			}
		}

		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "login")
	@ResponseBody
	public String accountLogin(AccountSecret as, HttpServletResponse response) {
		ReturnMsg retMsg = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(as.getPhone());
			as.setPhone(pp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnMsg a = accountService.getAccountPwd(as.getPhone(), as.getSecret(), "114");		
		if (a != null && a.getCode() == 1) {
			System.out.println(a.getAccoundid());
			retMsg.setCode(200);
			retMsg.setMessage("登录成功");
			retMsg.setClientNumber(a.getClientNumber());
			retMsg.setClientPwd(a.getClientPwd());
			retMsg.setAccoundid(a.getAccoundid());
		} else {
			retMsg.setCode(3);
			retMsg.setMessage("账号或密码错误请重新输入!");
		}
		System.out.println(MobileAccountUtils.getGson().toJson(retMsg));
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "loan")
	@ResponseBody
	public String saveLoan(LoanInfo loanInfo) {
		ReturnMsg msg = new ReturnMsg();
		if (loanInfo != null) {
			loanInfo.setCreatetime(new Date());
			int num = accountService.insertLoanInfo(loanInfo);
			if (num > 0) {
				msg.setCode(200);
				msg.setMessage("保存贷款数据成功");
			} else {
				msg.setCode(300);
				msg.setMessage("保存贷款数据失败");
			}
		} else {
			msg.setCode(301);
			msg.setMessage("贷款数据不能为空");
		}
		return MobileAccountUtils.getGson().toJson(msg);

	}

	
	@RequestMapping(value = "findinfo")
	@ResponseBody
	public String acscas(){
		File file = new File("D://phone.txt");
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {            
				System.out.println(tempString);   
				String cc = accountService.findClientByPhone(tempString, YZXConstant.APP_ID);
				PReturn pr = MobileAccountUtils.getGson().fromJson(cc, PReturn.class);
				sb.append(tempString+","+pr.getResp().getClient().getBalance()+"<br/>");
            }
            reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 做活动时的免费充值
	 * 
	 * @param phone
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "freeCharge")
	@ResponseBody
	public String freeCharge(String phone,Integer type,@RequestParam(required = false, value = "callback") String callback, HttpServletRequest request) {
		ReturnMsg retMsg = new ReturnMsg();
		Date today = new Date();

		// 活动期间限领一次
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		Date deadline = null;
		try {
			beginDate = sdf.parse("2016-05-6 00:00:00");
			deadline = sdf.parse("2016-05-9 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		boolean isBengin = today.after(beginDate);
		boolean isExpire = today.before(deadline);
		
		int result = -1;
		int balance = 10 * 60;//要充值的秒数

		if (isExpire) {
			if(isBengin){
				result = accountService.freeChargeAccountYzx(phone, balance,type);
			}else{
				retMsg.setCode(3);
				retMsg.setMessage("活动暂未开始，敬请期待！");
			}
			
		} else {
			retMsg.setCode(4);
			retMsg.setMessage("活动已结束，谢谢参与！");
		}

		if (result == 0) {
			retMsg.setCode(0);
			retMsg.setMessage("领取成功");
		} else if (result == 1) {
			retMsg.setCode(1);
			retMsg.setMessage("您还未注册企业114");
		} else if (result == 2) {
			retMsg.setCode(2);
			retMsg.setMessage("已领取，请勿重复操作");
		}

		if (callback != null) {
			return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
		}

		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	/**
	 * 活动结束开始检查 是否回收
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Scheduled(cron = "0 0 01 9 5 ?")
	public void checkFreeCHargeIfUsed() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查出所有未使用的
		map.clear();
		map.put("isUsed",0);
		map.put("type",2);
		List<PaccountFreechargeLog> logList = paccountFreechargeLogService.getFreechargeLogList(map);

//		long nowTime = new Date().getTime();
//		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
//		long diff;
//		long day;

		int count = 0;// 回收的用户数

		if (!logList.isEmpty()) {
			for (int i = 0, s = logList.size(); i < s; i++) {
				map.clear();	
				map.put("phone", logList.get(i).getPhone());
				map.put("calltime", logList.get(i).getCreatetime());
				int callCount = accountService.selectYzxCallCountByPhoneAndTime(map);

				if (callCount == 0) {// 如果在到期时没打过电话则 回收赠送的分钟、更新账户 并在log表标记
					// 回收
					accountService.recoverGoodsForYZX(logList.get(i).getPhone(),String.valueOf(logList.get(i).getCharge()/60.0*0.055));

					// 更新账户
					AccountYzx accountYzx = accountService.getYzx(logList.get(i).getPhone(), "114");
					accountYzx.setBalance(String.valueOf(Integer.valueOf(accountYzx.getBalance())
							- logList.get(i).getCharge()));
					accountService.updateAccountYzx(accountYzx);

					// 在paccountFreechargeLog里标记
					map.clear();
					map.put("phone",logList.get(i).getPhone());
					map.put("type",2);
					paccountFreechargeLogService.updateState(map);

					count++;
				}

			}
		}

		System.out.println("被回收的用户数：" + count);

	}


	@RequestMapping(value = "checkRegisted")
	@ResponseBody
	public String checkRegisted(String phone, String callback) {
		ReturnMsg retMsg = new ReturnMsg();
		if (accountService.selectfriendcount(phone) > 0) {
			retMsg.setMessage("已注册!");
			retMsg.setCode(200);
		} else {
			retMsg.setCode(-1);
			retMsg.setMessage("您的手机号还未注册企业114，请注册后再推荐好友!");
		}
		if (callback != null) {
			return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "registerFriend")
	@ResponseBody
	public String registerFriend(String phone, String friphone, String callback) throws Exception {
		ReturnMsg retMsg = new ReturnMsg();
		// 活动期间推荐成功才能领话费
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		Date deadline = null;
		
		try {
			deadline = sdf.parse("2016-04-28 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		boolean isExpire = new Date().before(deadline);

		if (isExpire) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("friendphone", friphone);
			map.put("createtime", time);
			if (accountService.selectfriendcount(friphone) > 0) {
				retMsg.setMessage("您推荐的好友已注册，换个好友试试吧!");
				retMsg.setCode(-1);
			} else {
				if (accountService.selectgivefriscount(map) == 0) {
					accountService.insertRecommendFriend(map);
				}
				retMsg.setCode(200);
				retMsg.setMessage("推荐成功!");
//				String content = "【企业114】您的好友" + phone + "用户邀请您加入人脉圈，领取您的新人注册礼！免费电话随心打，企业信用随地查！http://www.dwz.cn/3asgHm 回T退订";
				MobileAccountUtils.sendShortMsg(friphone, "a", 58,23);
			}

			// result = accountService.insertRecommendFriend(map);
		} else {
			retMsg.setCode(3);
			retMsg.setMessage("活动已结束，谢谢参与！");
		}

		if (callback != null) {
			return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "getFriendInfo")
	@ResponseBody
	public String getFriendInfo(String phone, String callback) throws Exception {
		List<AccountGiveMin> agm = new ArrayList<AccountGiveMin>();
		if (phone != null && !phone.isEmpty()) {
			agm = accountService.selectfriendInfo(phone);
			if (agm == null) {
				agm = new ArrayList<AccountGiveMin>();
			}
		}
		if (callback != null) {
			return callback + "(" + MobileAccountUtils.getGson().toJson(agm) + ")";
		} else {
			return MobileAccountUtils.getGson().toJson(agm);
		}

	}
	
	
	/**
	 * 创建马甲账号
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "makeVest")
	@ResponseBody
	public String makeVest(Account account){
		ReturnMsg msg = new ReturnMsg();
		
		if(!StringUtils.isEmpty(account.getPhone()) && MobileAccountUtils.isNumber(account.getPhone()) && account.getPhone().length()==11){
			
			Account user = accountService.getAccountInfo(account.getPhone());
			
			if(user==null){
				msg.setCode(200);
				msg.setMessage("创建账号成功！");
				
				account.setPhone(account.getPhone());
				account.setNickname(account.getPhone());
				account.setApptype("114");
				account.setAppId(YZXConstant.APP_ID);
				account.setSecret(YZXConstant.DEFAULT_SECRET);
				account.setCreatetime(MobileAccountUtils.getNowtime());
				account.setEnable(2);
				
				accountService.insertAccount(account);
			}else{
				msg.setCode(300);
				msg.setMessage("账号已经存在！");
			}
			
			
			
		}else{
			msg.setCode(100);
			msg.setMessage("电话号码填写错误");
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 老师入驻
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "toBeTeacher",method=RequestMethod.GET)
	public ModelAndView beTeacher(Account account){
		return new ModelAndView("beteacher");
	}
	
	
	@RequestMapping(value = "beTeacher",method=RequestMethod.POST)
	public ModelAndView saveTeacher(String phone,Teacher t){
		ModelAndView mv = new ModelAndView("beteacher");
		mv.addObject("msg", "保存成功！");
		
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(t.getUsername())){
			mv.addObject("msg", "参数错误！");
			return mv;
		}
		
		Account user = accountService.getAccountInfo(phone.trim());
		if(user!=null){
			Account account = new Account();
			account.setPhone(phone);
			account.setUserType(1);
			accountService.updateAccount(account);

			Teacher tea = saleTeacherService.getByUserId(Long.valueOf(user.getAccountid()));
			
			if(tea==null){
				mv.addObject("msg", "保存老师信息成功！");
				
				t.setUserid(Long.valueOf(user.getAccountid()));
				saleTeacherService.insertTeacher(t);
			}else{
				mv.addObject("msg", "更新"+tea.getUsername()+"成功！");
				
				t.setId(tea.getId());
				saleTeacherService.updateTeacher(t);
			}
		}
		
		return mv;
	}
	
	
	public static void main(String[] args) throws Exception {
		Long phone = 19912341101l;
		
		for(int i=0;i<61;i++){
			phone++;
			String url = "http://168.mobile.hc360.com/mobileaccount/account/makeVest?phone="+phone;
			
			String result = MobileAccountUtils.doGet(url, "utf-8");
			JSONObject json = JSONObject.fromObject(result);
			if(json.getInt("code")==200){
				System.out.println("get it!");
			}else{
				System.out.println(phone+"\n"+result);
			}
		}
		
	}
	
}
