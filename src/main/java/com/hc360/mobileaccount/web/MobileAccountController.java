package com.hc360.mobileaccount.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.common.UmengConstant;
import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.CallLogStat;
import com.hc360.mobileaccount.po.CompanyInfo;
import com.hc360.mobileaccount.po.CompanyInfoEntity;
import com.hc360.mobileaccount.po.CompanyInfoList;
import com.hc360.mobileaccount.po.CorpInfo;
import com.hc360.mobileaccount.po.DownLoad;
import com.hc360.mobileaccount.po.JoinCorpMM;
import com.hc360.mobileaccount.po.MobileCallInfo;
import com.hc360.mobileaccount.po.NewsAndMsg;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.PersonTag;
import com.hc360.mobileaccount.po.PersonTagAction;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.po.QGInfoEntity;
import com.hc360.mobileaccount.po.QGUserInfo;
import com.hc360.mobileaccount.po.ResultOfCorpInfo;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.RewardList;
import com.hc360.mobileaccount.po.SpringFestivalActivity;
import com.hc360.mobileaccount.po.SuggestCorp;
import com.hc360.mobileaccount.po.YzxAccount;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.serviceoracle.Data114Service;
import com.hc360.mobileaccount.umeng.push.PushMain;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.PhoneAreaUtils;
import com.hc360.mobileaccount.utils.RC4;
import com.hc360.mobileaccount.utils.XssProtectUtils;
import com.hc360.rsf.config.ConfigLoader;
import com.hc360.rsf.config.RsfListener;
import com.hc360.rsf.kvdb.service.KVDBResult;
import com.hc360.rsf.kvdb.service.KVDBbcService;

@Controller
@RequestMapping("/call")
public class MobileAccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private Data114Service data114Service;

	@Autowired
	private RewardList rewardList;

	public static String linkinfoUrl = "http://openapi.m.hc360.com/openapi/v1/Corplib/linkinfo/";
	public static String baseinfoUrl = "http://openapi.m.hc360.com/openapi/v1/Corplib/baseinfo/";

	public static String companyUrl = "http://detail.b2b.hc360.com/detail/turbine/template/moblie,vmoblie,getProduct_categories.html?page=1&pagesize=20&seriesid=00&username=";

	/**
	 * 客户管理数据迁移
	 * 
	 */
	@RequestMapping(value = "dataMigrationForCM", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String dataMigrationForCM(String phone) {
		return accountService.dataMigrationForCM(phone);
	}

	/**
	 * 搜索无结果时调用的企业搜索接口
	 * 
	 */
	@RequestMapping(value = "getCorpInfoForNoData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfoForNoData(String w, String lon, String lat, String n, String e) {

		// 搜索无结果时调用的企业搜索接口
		return data114Service.getCorpInfoForNoData(w, lon, lat, n, e);

	}

	/**
	 * 通过公司ID取得公司信息
	 * 
	 */
	@RequestMapping(value = "getCorpInfoById", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfoById(String corpID) {
		Pattern pattern = Pattern.compile("[0-9]*");
		if(StringUtils.isEmpty(corpID) || !pattern.matcher(corpID).matches()){
			return null;
		}
		return data114Service.getCorpInfoById(corpID);
	}

	/**
	 * 企业浏览记录接口
	 * 
	 */
	@RequestMapping(value = "accessCorpInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String accessCorpInfo(String corpName) {
		return data114Service.accessCorpInfo(corpName);
	}

	/**
	 * 获取相似企业接口
	 */
	@RequestMapping(value = "similercorpinfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getSimilerCorpInfo(String w, String corpName, String lon, String lat) throws Exception {
		return data114Service.selectSimilarCorpInfo(w, corpName, lon, lat);
	}

	/**
	 * 排序企业临时表接口
	 */
	@RequestMapping(value = "corpInfoSort", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String corpInfoSort() {
		return data114Service.corpInfoSort();
	}

	/**
	 * 显示云之讯账户（平台计费）是负数的数据
	 * 
	 */
	@RequestMapping(value = "showYzxNegativeData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String showYzxNegativeData(int start) {
		// 显示云之讯账户（平台计费）是负数的数据
		return accountService.showYzxNegativeData(start);
	}

	/**
	 * 同步云之讯数据
	 * 
	 * @return "OK"
	 */
	@RequestMapping(value = "syncYzxData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syncYzxData() {
		// 同步云之讯数据 使用时请解除注释
//		accountService.syncYzxData();
		
		return "OK";
	}

	@RequestMapping(value = "YzxResetBalanceTo30", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateYzxBalanceOf30() {
//		accountService.updatePhoneBalanceOf30();
		return "ok";
	}

	@RequestMapping(value = "userCharge", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String singleUserCharge(String phone, String charge) {
		AccountYzx yzx = accountService.getYzx(phone, "114");
		if (yzx != null) {
			return accountService.buyGoodsForYZX(phone, charge);
		} else {
			return "null";
		}
	}

	@RequestMapping(value = "userRecover", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String singleUserRecover(String phone, String charge) {
		AccountYzx yzx = accountService.getYzx(phone, "114");
		if (yzx != null) {
			return accountService.recoverGoodsForYZX(phone, charge);
		} else {
			return "null";
		}
	}

	@RequestMapping(value = "batchYzxCharge", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String batchYzxCharge() {
		List<AccountYzx> listAccountYzx = new ArrayList<AccountYzx>();//accountService.selectAccountYzxList();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		int charge1 = 0;
		for (AccountYzx yzx : listAccountYzx) {
			if (yzx.getClientType() != null && yzx.getClientType().equals("1") && yzx.getBalance() != null
					&& Integer.valueOf(yzx.getBalance()) > 0) {
				PReturn yzxReturn = MobileAccountUtils.getGson().fromJson(
						accountService.findClientByPhone(yzx.getPhone(), YZXConstant.APP_ID),
						PReturn.class);
				if (yzxReturn != null && yzxReturn.getResp() != null
						&& yzxReturn.getResp().getRespCode().equals(YZXConstant.RESP_CODE_SUCCESS)) {
					if (yzxReturn.getResp().getClient() != null) {
						String charge = yzxReturn.getResp().getClient().getBalance();
						Double iCharge = Double.valueOf(charge);
						Integer wCharge = Integer.valueOf(yzx.getBalance());
						double w = (double) (wCharge * 0.055 / 60);
						double c = Math.abs(iCharge - w);
						if (c > 0) { // 两者差值 大于0 充值
							System.out.println("给该账号:" + yzx.getPhone() + "充值金额: " + c);
							accountService.buyGoodsForYZX(yzx.getPhone(), String.valueOf(c));
							charge1 += c;
							i++;
						}
					}
				}
			}
		}
		sb.append("充值个数：" + i + "   充值总金额为：" + charge1);
		return sb.toString();
	}

	/**
	 * 取得热门搜索词和热门搜索企业
	 */
	@RequestMapping(value = "getHotWord", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getHotWord(int n1, int n2, String callback) {
		String rtn = accountService.getHotWord(n1, n2);
		if (callback != null) {
			return callback + "(" + rtn + ")";
		}
		return rtn;
	}

	/**
	 * 根据当前企业的主营产品，匹配6条企业
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "getCorpInfoByMp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfoByMp(String mp, String callback) throws UnsupportedEncodingException {
		String rtn = data114Service.getCorpInfoByMp(mp);
		if (callback != null) {
			return callback + "(" + rtn + ")";
		}
		return rtn;
	}

	@RequestMapping(value = "luck", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String actionChoujiang(String phone, String actiontype, String callback) throws Exception {
		int ret = 1;
		if (phone != null && MobileAccountUtils.isPhoneNumber(phone)) {
			ret = accountService.insertPhoneActionResult(phone, actiontype, "1");
		}
		String ss = "";
		if (ret > 0) { // 中奖用户需要赠送话费 30分钟
			try {
				// 否则要给云之讯账号充值
				String result = accountService.buyGoodsForYZX(phone, "1.65");
				System.out.println(result);
				PReturn yzxReturnMsg = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
				if (yzxReturnMsg != null && yzxReturnMsg.getResp() != null
						&& yzxReturnMsg.getResp().getRespCode().equals("000000")) {
					accountService.updateYzxbalance(phone, "1800");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ss = "{\"code\":200}";
		} else {
			ss = "{\"code\":300}";
		}

		if (callback != null) {
			return callback + "(" + ss + ")";
		}
		return ss;
	}

	@RequestMapping(value = "getSameProduct", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getSameProduct(String bcid, String callback, String page, String pagesize) {
		if (bcid == null || bcid.isEmpty() || bcid.equals("null")) {
			return callback + "({\"lstResult\":[]})";
		}
		String url = "http://openapi.m.hc360.com/openapi/v1/productDetail/getSameProduct/" + bcid + "?page=" + page
				+ "&pagesize=" + pagesize;
		String result = MobileAccountUtils.doGet(url, "UTF-8");

		return callback + "(" + result + ")";

	}

	@RequestMapping(value = "getProductDetail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getProductDetail(String bcid, String callback) {
		if (bcid == null || bcid.isEmpty() || bcid.equals("null")) {
			return callback + "(null)";
		}
		String url = "http://openapi.m.hc360.com/openapi/v1/productDetail/getProductDetail/" + bcid;
		String result = MobileAccountUtils.doGet(url, "UTF-8");

		return callback + "(" + result + ")";

	}

	@RequestMapping(value = "feedback", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String saveFeedBack(String deviceid, String message, String callback) {
		if(StringUtils.isEmpty(deviceid) || StringUtils.isEmpty(message)){
			return "{\"code\": 100}";
		}
		
		deviceid = XssProtectUtils.cleanXSS(deviceid);
		message = XssProtectUtils.cleanXSS(message);
		
		accountService.saveFeedBack(deviceid, message);
		
		if (callback != null) {
			return callback + "({\"code\": 200})";
		}
		return "{\"code\": 200}";
	}

	@RequestMapping(value = "joinCorp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String joinCorp(JoinCorpMM joinCorp, String callback) {
		int rtn = accountService.joinCorp(joinCorp);
		String code = rtn <= 0 ? "300" : "200";
		if (callback != null) {
			return callback + "({\"code\": " + code + "})";
		}
		return "{\"code\": " + code + "}";
	}

	@RequestMapping(value = "suggestCorp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String suggestCorp(SuggestCorp suggestCorp, String callback) {
		int rtn = accountService.suggestCorp(suggestCorp);
		String code = rtn <= 0 ? "300" : "200";
		if (callback != null) {
			return callback + "({\"code\": " + code + "})";
		}
		return "{\"code\": " + code + "}";
	}

	@RequestMapping(value = "phonearea", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPhoneArea(String phone, String callback) throws Exception {
		String p = RC4.decry_RC4(phone);
		String result = "{ \"area\" : \"" + URLEncoder.encode(PhoneAreaUtils.getMobileFrom(p), "UTF-8") + "\"}";
		if (callback != null) {
			return callback + "(" + result + ")";
		}
		return result;
	}

	@RequestMapping(value = "corpinfodata", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfoDataForUpdate(String corpname, int pageNow, int pagesize, String callback)
			throws Exception {
		String result = "";
		if (corpname != null && !corpname.isEmpty()) {
			try {
				if (pageNow > 0) {
					pageNow = (pageNow - 1) * pagesize;
				} else {
					pageNow = 0;
				}
			} catch (Exception e) {
				pageNow = 0;
			}
			int end = pageNow + pagesize;
			ResultOfCorpInfo corp = data114Service.selectLikeCorpInfo(corpname, pageNow + 1, end);
			if (corp != null) {
				result = MobileAccountUtils.getReturnCallback(corp, callback);
			} else {
				result = MobileAccountUtils.getReturnCallback(new CorpInfo(), callback);
			}

		} else {
			result = MobileAccountUtils.getReturnCallback(new CorpInfo(), callback);
		}
		return result;
	}

	// 春节活动之家乡繁荣度数据获取
	@RequestMapping(value = "getCorpofHomeAcitivity", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpofHome(String city, String callback) throws Exception {
		SpringFestivalActivity sfa = accountService.getCorpofHome(city);
		String json = MobileAccountUtils.getGson().toJson(sfa);
		if (callback != null) {
			json = callback + "(" + json + ")";
		}
		return json;
	}

	// 春节活动之更新指定城市的家乡繁荣度的分享次数
	@RequestMapping(value = "updateTaxic", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateTaxic(String city, String callback) throws Exception {
		ReturnMsg r = new ReturnMsg();
		accountService.updateTaxic(city);
		r.setCode(200);
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	// 获得家乡繁荣度分享次数前三甲
	@RequestMapping(value = "getTaxicTop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTaxicTop(String callback) throws Exception {
		return accountService.getTaxicTop(callback);
	}

	// 最后一次启动时间记录
	@RequestMapping(value = "finaltimelog", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String insertFinalTime(String deviceid, String phonenum, String finaltime) throws Exception {
		if (deviceid == null || deviceid == "") {
			return "param error!";
		}

		accountService.insertFinalTime(deviceid, phonenum, finaltime);

		return "OK";
	}

	@RequestMapping(value = "corpinfobyid", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfoDataById(String corpid, String callback) throws Exception {
		String result = "";
		if (corpid != null && !corpid.isEmpty()) {
			CorpInfo corp = data114Service.selectCorpInfoByid(corpid);
			if (corp != null) {
				result = MobileAccountUtils.getReturnCallback(corp, callback);
			} else {
				result = MobileAccountUtils.getReturnCallback(new CorpInfo(), callback);
			}
		} else {
			result = MobileAccountUtils.getReturnCallback(new CorpInfo(), callback);
		}
		return result;
	}

	@RequestMapping(value = "updatecorpinfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getUpdateCorpInfoData(CorpInfo corpInfo, String callback) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (corpInfo != null) {
			if (data114Service.updateCorpInfo(corpInfo) > 0) {
				r.setCode(200);
			} else {
				r.setCode(100);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	@RequestMapping(value = "insertCorpInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String insertCorpInfo(CorpInfo corpInfo, String callback) throws Exception {
		int rtn = 0;
		ReturnMsg r = new ReturnMsg();
		if (corpInfo != null) {
			rtn = data114Service.insertCorpInfo(corpInfo);
			if (rtn > 0) {
				r.setCode(200);
			} else if (rtn == -1) {
				r.setCode(100);
			} else {
				r.setCode(-1);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	@RequestMapping(value = "deleteCorpInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteCorpInfo(long corpid, String callback) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (corpid > 0) {
			if (data114Service.deleteCorpInfo(corpid) > 0) {
				r.setCode(200);
			} else {
				r.setCode(100);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	@RequestMapping(value = "corpinfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCorpInfo(String corpname, String callback) throws Exception {
		if (corpname != null && !corpname.isEmpty()) {
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyName(corpname);
			List<CorpInfo> corp = data114Service.selectCorpInfo(corpname);
			if (corp != null && corp.size() > 0 && corp.get(0).getUsername() != null) {
				String companyStr = MobileAccountUtils.doGet(companyUrl + corp.get(0).getUsername(), "GBK");
				companyInfo = MobileAccountUtils.getGson().fromJson(companyStr, CompanyInfo.class);
				companyInfo.setIntroduce(corp.get(0).getIntroduce());
				if (callback != null) {
					return callback + "(" + MobileAccountUtils.getGson().toJson(companyInfo) + ")";
				} else {
					return MobileAccountUtils.getGson().toJson(companyInfo);
				}
			} else {
				if (callback != null) {
					return callback + "(" + MobileAccountUtils.getGson().toJson(companyInfo) + ")";
				} else {
					return MobileAccountUtils.getGson().toJson(companyInfo);
				}
			}
		} else {
			if (callback != null) {
				return callback + "({\"code\":-1})";
			} else {
				return "-1";
			}
		}
	}

	@RequestMapping(value = "start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String callstart(HttpServletRequest request, HttpServletResponse response, MobileCallInfo mci,
			String callback) throws Exception {
		// 发送营销短信功能
		mci.setCallid(RC4.decry_RC4(mci.getCalledid()));
		mci.setCalledid(RC4.decry_RC4(mci.getCalledid()));
		mci.setPhone(RC4.decry_RC4(mci.getPhone()));
		accountService.sendSms(mci.getCallid(), RC4.decry_RC4(mci.getCalledid()), mci.getAppyype());
		int id = accountService.insertCallInfo(mci);
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		rm.setId(id);
		rm.setMessage("保存成功!");
		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "end", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String callhangup(HttpServletRequest request, HttpServletResponse response, MobileCallInfo mci)
			throws Exception {
		accountService.insertCallInfo(mci);
		return "";
	}


	@RequestMapping(value = "version", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String viewVer() {
		return "1.0.0.5";
	}

	@RequestMapping(value = "rewardlist", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getLuckyGoods(String phone, String callback) {

		return MobileAccountUtils.getGson().toJson(rewardList);
	}

	@RequestMapping(value = "qginfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getQiuGouInfo(String bcid, String callback) throws Exception {

		String url = "http://s.hc360.com/getmmtlast.cgi?c=求购信息&bc_id=" + bcid;
		String qgInfo = MobileAccountUtils.doGet(url, "gbk");
		QGInfoEntity qg = MobileAccountUtils.getGson().fromJson(qgInfo, QGInfoEntity.class);
		if (qg != null && qg.getSearchResultInfo() != null && qg.getSearchResultInfo().size() > 0) {

			// 获取求购详情
			ConfigLoader configLoader = RsfListener.getConfigLoader();
			KVDBbcService kvdbbcService = (KVDBbcService) configLoader.getServiceProxyBean("kvdbbcService");

			KVDBResult kr = kvdbbcService.getBakOrOn("eee", bcid);
			byte[] ret = null;
			if (kr.getState() == 0) {
				System.out.println("获取成功！！");
				ret = kr.getValue();
			} else {
				// 异常类型 1:KVDB禁用；2:参数无效；3:程序异常；
				if (kr.getExType() == 1) {
					System.out.println("获取失败！！1:KVDB禁用");
					ret = kr.getValue();
					System.out.println(new String(ret, "UTF-8"));
					// 应用处理
				} else if (kr.getExType() == 2) {
					System.out.println("获取失败！！2:参数无效");
					// 应用处理
				} else if (kr.getExType() == 3) {
					System.out.println("获取失败！！3:程序异常");
					// 应用处理
				}
			}
			String result = new String(ret, "GBK");
			if (result != null && !result.isEmpty()) {
				qg.getSearchResultInfo().get(0).setSearchResultfoText(result);
			}

			url = "http://openapi.m.hc360.com/openapi/v1/company/getInfo/"
					+ qg.getSearchResultInfo().get(0).getSearchResultfoUserName();
			qgInfo = MobileAccountUtils.doGet(url, "utf-8");
			System.out.println(qgInfo);
			QGUserInfo qgUserInfo = MobileAccountUtils.getGson().fromJson(qgInfo, QGUserInfo.class);
			if (qgUserInfo != null) {
				qg.setUserPhone(qgUserInfo.getPhone());
				qg.setContact(qgUserInfo.getContact());
				qg.setTelphone(qgUserInfo.getMp());
				if (qgUserInfo.getAddress() == null || qgUserInfo.getAddress().trim().isEmpty()) {
					qg.setAddress(qg.getSearchResultInfo().get(0).getSearchResultfoZone());
				} else {
					qg.setAddress(qgUserInfo.getAddress());
				}
			}

		}
		return MobileAccountUtils.getGson().toJson(qg);
	}

	@RequestMapping(value = "count", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String callCount(String phone, String requesttype, String called, String callback, String deviceid) { // 13381310302

		ReturnMsg rm = new ReturnMsg();
		if (phone == null || phone.isEmpty()) {
			rm.setCode(-1);
			rm.setMessage("手机号码为空");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		phone = RC4.decry_RC4(phone);

		AccountYzx yzx = accountService.getYzx(phone, "114");
		if (yzx != null) {
			if (requesttype != null && requesttype.equals("qiugou")) {
				int qgCount = accountService.selectQiuGouInfo(phone, requesttype);
				accountService.insertQiuGouInfo(phone, requesttype, called);
				if (qgCount < 10) {
					rm.setCode(200);
					return MobileAccountUtils.getGson().toJson(rm);
				} else {
					rm.setCode(300);
					rm.setMessage("您今日查看求购信息联系方式（10次）已用完，请明天再来");
					return MobileAccountUtils.getGson().toJson(rm);
				}
			}
			boolean cc = true;
			if (deviceid != null) {
				cc = false;
			}
			if (cc) {
				int count = accountService.selectYZXTimesByDay(yzx.getClientNumber());
				int nLastNum = 8 - count;
				if (count == 3) {
					rm.setCode(300);
					rm.setMessage("您今日剩余免费电话次数(" + nLastNum + ")");
					return MobileAccountUtils.getGson().toJson(rm);
				} else if (count == 8) {
					rm.setCode(400);
					rm.setMessage("您今日免费电话拨打次数已用完，请明天再来。同时，建议您使用本地电话。");
					return MobileAccountUtils.getGson().toJson(rm);
				} else {
					rm.setCode(200);
					return MobileAccountUtils.getGson().toJson(rm);
				}
			} else {
				rm.setCode(200);
				return MobileAccountUtils.getGson().toJson(rm);
			}
		} else {
			rm.setCode(500);
			rm.setMessage("账号不存在！");
			return MobileAccountUtils.getGson().toJson(rm);
		}
	}

	@RequestMapping(value = "stat", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String statSearchComany(String companyname, int level, String callback) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyname", companyname);
		map.put("level", level);
		map.put("marktime", new Date());
		accountService.insertStatSearch(map);
		if (callback != null) {
			return callback + "({\"code\":0})";
		} else {
			return "{\"code\":0}";
		}
	}

	@RequestMapping(value = "sc", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String searchCompany(String k, String callback) {
		if (k == null || TextUtils.isEmpty(k)) {
			if (callback != null) {
				return callback + "({\"level\": 1})";
			} else {
				return "{\"level\": 1}";
			}
		}
		String[] kl = k.split(",");

		String url = "http://168s.mobile.hc360.com/get168.cgi?";
		String sign = MobileAccountUtils.getSingForLei("n=0", "e=10");
		String level = "";
		for (String l : kl) {
			String linkinfo = MobileAccountUtils.doGet(url + "w=" + l + "&n=0&e=10&sign=" + sign, "GBK");
			CompanyInfoList asd = MobileAccountUtils.getGson().fromJson(linkinfo, CompanyInfoList.class);
			if (asd != null && asd.getSearchResultInfo().size() > 0) {
				CompanyInfoEntity cc = asd.getSearchResultInfo().get(0);
				level += MobileAccountUtils.levelGrade(cc.getSearchResultfoCapital(),
						cc.getSearchResultfoPublicYearTime())
						+ ",";
			} else {
				level += "1,";
			}
		}
		if (callback != null) {
			if (level.equals("")) {
				return callback + "({\"level\": \"1\"})";
			} else {
				return callback + "({\"level\":\"" + level.substring(0, level.length() - 1) + "\"})";
			}
		} else {
			if (level.equals("")) {
				return "{\"level\": \"1\"}";
			} else {
				return "{\"level\":\"" + level.substring(0, level.length() - 1) + "\"}";
			}
		}
	}

	@RequestMapping(value = "yzx/balance", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String yzxBalacne() {
		YzxAccount yzx = MobileAccountUtils.getYzxAccountHc();
		if (yzx == null) {
			return "找不到";
		}
		return String.valueOf(yzx.getBalance());
	}

	/**
	 * 友盟绑定
	 */
	@RequestMapping(value = "push/bind", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String bindPushUMeng(String phone, String ticker, String token) {
		accountService.savePushClientInfo(phone, token, ticker);
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		return MobileAccountUtils.getGson().toJson(rm);
	}

	/*
	 * 重发商机 请求参数： username //用户名 way //重发方式（1,一键重发。2，单个重发。3，批量重发） businType
	 * //重发商机类型（1，在线商机。2过期商机） sort //排序方式（1 ,升序。2，降序 只有一键重发时传入） bcid
	 * //商机id(只有单个重发和批量重发需要传入，使用逗号隔开）
	 */
	@RequestMapping(value = "onekey/resend", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String onkeyReSend(String username, String sort, String callback) {
		String url = "http://openapi.m.hc360.com/openapi/v1/sellerSupply/chanceRepeated/" + username;
		String param = "sort=" + sort;
		param += "&way=1&businType=1";
		String result = MobileAccountUtils.sendPost(url, param);
		if (callback != null) {
			result = callback + "(" + result + ")";
		}
		return result;
	}

	@RequestMapping(value = "log", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String callLog(HttpServletRequest request, HttpServletResponse response, CallLogStat callLogStat)
			throws Exception {
		ReturnMsg rm = new ReturnMsg();
		String userAgent = MD5.md5Encode(request.getHeader("User-Agent"));
		if (callLogStat.getP1() == null || callLogStat.getP1().equals("null")) {
			if (userAgent.length() > 15) {
				callLogStat.setP1(userAgent.substring(0, 14));
			} else {
				callLogStat.setP1(userAgent);
			}
		} else {
			callLogStat.setP1(RC4.decry_RC4(callLogStat.getP1()));
		}
		if ((callLogStat.getP2() == null || callLogStat.getP2().equals("null")) && callLogStat.getCorpid() != null
				&& !callLogStat.getCorpid().equals("null")) {
			callLogStat.setP2corpid(callLogStat.getCorpid());
		}
		if (callLogStat.getP2() != null && !callLogStat.getP2().equals("null")) {
			callLogStat.setP2(RC4.decry_RC4(callLogStat.getP2()));
		}
		String pl = callLogStat.getP1() + "," + callLogStat.getP2();
		pl = RC4.encry_RC4_string(pl, RC4.KEY);

		accountService.insertAppCallLog(callLogStat);
		accountService.callCorpInfo(callLogStat);
		rm.setCode(200);
		return MobileAccountUtils.getGson().toJson(rm);

	}

	@Scheduled(cron = "0 0/30 6-23 * * ? ")
	public void timer() {
		List<PersonTag> alarmList = accountService.selectAllPersonTag();
		PushMain push = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		for (PersonTag alarm : alarmList) {
			PushClientInfo pushClientInfo = accountService.getPushCilentInfoByPhone(alarm.getPhone());
			if (pushClientInfo != null) {
				try {
					push.sendAndroidUnicast(pushClientInfo.getToken(), "【企业114】重要提醒：" + alarm.getName(), "重要提醒",
							alarm.getCmid());
					accountService.updateSend(alarm.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
    //推送待办事项
	@Scheduled(cron = "0 0/30 6-23 * * ? ") 
	public void Actiontimer() {   
		List<PersonTagAction> alarmList = accountService.selectAllTagAction();
		PushMain push = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		for (PersonTagAction alarm : alarmList) {
			PushClientInfo pushClientInfo = accountService.getPushCilentInfoByPhone(alarm.getPhone());
			if (pushClientInfo != null) {
				try {
					push.sendActiontoAndroid(pushClientInfo.getToken(), "【企业114】重要提醒：" + alarm.getAction(), "重要提醒",
							alarm.getCmid());
					accountService.updateSendAction(alarm.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 查询下载路径
	 * 
	 */
	@RequestMapping(value = "getDownUrl", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getDownUrl(String version) throws Exception {
		String result = ""; 
		 DownLoad ms=new DownLoad();
		DownLoad down =   accountService.selectDownloadUrl(version);
		if (down!=null) { 
			result = MobileAccountUtils.getGson().toJson(down);
		} else {
			ms.setCode(-1);
			ms.setMessage("没有该版本号的下载内容");
			result = MobileAccountUtils.getGson().toJson(ms);
		}
		 
		return result;

	}
	 
 
	/**
	 * 查询广告
	 * 
	 */
	@RequestMapping(value = "getpicbytype", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPictureByType(String type, String keywords) throws Exception {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("type", type);
		NewsAndMsg news = accountService.selectPicture(map);
		NewsAndMsg autoPic = accountService.selectAutoPicture();
		if (type.equals("1")) {
			if (news != null) {
				result = MobileAccountUtils.getGson().toJson(news);
			} else {
				result = MobileAccountUtils.getGson().toJson(new NewsAndMsg());

			}
		} else if (type.equals("2")) {
			if (news != null) {
				result = MobileAccountUtils.getGson().toJson(news);
			} else {
				if (autoPic != null) {
					result = MobileAccountUtils.getGson().toJson(autoPic);
				} else {
					result = MobileAccountUtils.getGson().toJson(new NewsAndMsg());
				}

			}

		}
		return result;

	}

	/**
	 * 查询企业新闻
	 * 
	 */
	@RequestMapping(value = "getNewsInfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getNewsInfo(String type, int pageNow, int pagesize, String callback) throws Exception {
		String result = "";

		// type =3 新闻消息 =4 通知消息 5企业活动
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("begin", pageNow);
		map.put("end", pagesize);
		List<NewsAndMsg> news = accountService.selectNewsInfo(map);
		if (news != null) {
			result = MobileAccountUtils.getGson().toJson(news);
		} else {
			result = null;
		}
		if (callback != null) {
			result = callback + "(" + result + ")";
		}
		return result;

	}

	/**
	 * 查询企业新闻详情
	 * 
	 */
	@RequestMapping(value = "getNewsInfoById", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getNewsInfoById(int id, String callback) throws Exception {
		String result = "";
		NewsAndMsg news = accountService.selectNewsInfoById(id);
		news.setNewsId(id);
		result = MobileAccountUtils.getGson().toJson(news);
		if (callback != null) {
			result = callback + "(" + result + ")";
		}
		return result;

	}

	/**
	 * 查询企业新闻title
	 * 
	 */
	@RequestMapping(value = "getNewsTitle", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String selectNewsTitle() throws Exception {
		String result = "";
		// type =3 新闻消息 =4 通知消息 5企业活动
		List<NewsAndMsg> news = accountService.selectNewsTitle();
		if (news != null) {
			result = MobileAccountUtils.getGson().toJson(news);
		} else {
			result = null;
		}
		return result;

	}

	// 消息中心数量统计
	@RequestMapping(value = "getNewscount", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String selectNewscount() throws Exception {
		String result = "";
		try {
			List<NewsAndMsg> count = accountService.selectNewsCount();
			result = MobileAccountUtils.getGson().toJson(count);
		} catch (Exception e) {
			return null;
		}

		return result;
	}

	@RequestMapping(value = "updateNewsMsg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateNewsMsg(NewsAndMsg newmsg, String callback) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (newmsg != null) {
			if (accountService.updateNewsInfo(newmsg) > 0) {
				r.setCode(200);
			} else {
				r.setCode(100);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	@RequestMapping(value = "insertNewsMsg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String insertNewsMsg(NewsAndMsg newsinfo, String callback) throws Exception {
		int rtn = 0;
		ReturnMsg r = new ReturnMsg();
		if (newsinfo != null) {
			System.out.println("插入之前" + newsinfo.getNewsId());
			rtn = accountService.insertNewsInfo(newsinfo);
			System.out.println("插入之hou " + newsinfo.getNewsId());

			if (rtn > 0) {
				r.setCode(200);
				r.setAccoundid(String.valueOf(newsinfo.getNewsId()));
			} else if (rtn == -1) {
				r.setCode(100);
			} else {
				r.setCode(-1);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}

	@RequestMapping(value = "delNewsMsg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String delNewsMsg(int id, String callback) throws Exception {
		ReturnMsg r = new ReturnMsg();
		if (id > 0) {
			if (accountService.delNewsInfo(id) > 0) {
				r.setCode(200);
			} else {
				r.setCode(100);
			}
		} else {
			r.setCode(-1);
		}
		return MobileAccountUtils.getReturnCallback(r, callback);
	}
}
