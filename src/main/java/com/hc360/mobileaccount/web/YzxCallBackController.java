package com.hc360.mobileaccount.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.ucpaas.client.JsonReqClient;

@Controller
@RequestMapping("/yzx")
public class YzxCallBackController {

	@Autowired
	private AccountService accountService;
	
	private JsonReqClient jsonReqClient = new JsonReqClient();
	
	
	
	// 发起请求 //呼叫鉴权请求接口 
	@RequestMapping(value = "start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String start(@RequestBody String requestBody) {
		System.out.println("authen:"+requestBody);
		if (requestBody != null && !requestBody.isEmpty()) {
			//0：直拨，1：免费，2：回拨
			int calltype = Integer.valueOf(MobileAccountUtils.getXMLValue(requestBody, "calltype"));
			String result = accountService.insertYzxCallReq(requestBody);
			System.out.println("calltype:"+calltype);
			
			if(calltype==1){
				System.out.println("calltype2:"+calltype);
				return MobileAccountUtils.getYzxAuthenCallBackXml();
			}else{
				return result;
			}
			
		} else {
			return MobileAccountUtils.getCallBackYzxXml("-1", "this string is null");
		}

	}
	

	// 建立请求
	@RequestMapping(value = "accept", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String accept(@RequestBody String requestBody) throws Exception {
		System.out.println(requestBody);
		if (requestBody != null && !requestBody.isEmpty()) {			
			return accountService.insertYzxCallReq(requestBody);
		} else {
			return MobileAccountUtils.getCallBackYzxXml("-1", "this string is null");
		}
	}

	// 呼叫结束
	@RequestMapping(value = "finish", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String finish(@RequestBody String requestBody) {
		System.out.println(requestBody);
		if (requestBody != null && !requestBody.isEmpty()) {
			return accountService.insertYzxCallReq(requestBody);
		} else {
			return MobileAccountUtils.getCallBackYzxXml("-1", "this string is null");
		}
	}
	
	
	
	
	
	/////////////////////////////////////以下是云之讯相关的非回调接口//////////////////////////////////////////////////////////////////
	/**
	 * 创建测试账号
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="createTestClient")
	@ResponseBody
	public String createTestClient(String phone){
		Account account = accountService.getAccountInfo(phone);
		
		String result = jsonReqClient.createClient(YZXConstant.ACCOUNT_SID,
				YZXConstant.AUTH_TOKEN, YZXConstant.APP_ID_TEST, account.getFriendlyName(), "1",
				"0.55", account.getPhone());
		System.out.println(result);
		PReturn cc = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
		if (cc != null && cc.getResp() != null) {
			return cc.getResp().getRespCode();
		}
		
		return "fail";
	}
	
	
	/**
	 * 关闭测试账号
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value="closeTestClient")
	@ResponseBody
	public String closeTestClient(String clientId){
		String result = jsonReqClient.closeClient(YZXConstant.ACCOUNT_SID,
				YZXConstant.AUTH_TOKEN,clientId,YZXConstant.APP_ID_TEST);
		System.out.println(result);
		PReturn cc = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
		if (cc != null && cc.getResp() != null) {
			return cc.getResp().getRespCode();
		}
		
		return "fail";
	}
	
	
	@RequestMapping(value = "close")
	@ResponseBody
	public String closeClient(String phone) {
		ReturnMsg msg = new ReturnMsg();
		if (accountService.deleteCilentNumber(phone, null)) {
			msg.setCode(200);
		} else {
			msg.setCode(-1);
			msg.setMessage("删除失败!");
		}
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 查询正式的账号信息
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "find")
	@ResponseBody
	public String findClient(String phone) {
		return accountService.findClientByPhone(phone, YZXConstant.APP_ID);
	}
	
	
	/**
	 * 查询测试的账号信息
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "findTest")
	@ResponseBody
	public String findTestClient(String phone) {
		return accountService.findClientByPhone(phone, YZXConstant.APP_ID_TEST);
	}

	
	public static void main(String[] args) throws Exception {
		String s = MobileAccountUtils.getYzxAuthenCallBackXml();
		System.out.println(s);
		
		System.out.println(MD5.md5Encode("9a5d6c8c8f9feb180deaf63d771f76d4"+"62347059801954tRwDt102751148910"+"3a85f0882e69b685b766e2b76531fd1e"));
	}
}