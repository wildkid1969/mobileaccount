package com.hc360.mobileaccount.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.GLReturn;
import com.hc360.mobileaccount.po.GlData;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.GuoLingService;
import com.hc360.mobileaccount.utils.MobileAccountGLUtils;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping("/gl/")
public class GLController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private GuoLingService glService;

	// 发起请求
	@RequestMapping(value = "selectsdk", method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String selectSDK(String phone) {
		ReturnMsg rm = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
		} catch (Exception e) {
			phone = null;
		}

		if (phone == null || phone.isEmpty()) {
			rm.setCode(-1);
			rm.setMessage("手机号不能为空!");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		rm = accountService.selectSDKPhone(phone);

		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "userreg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String UserRegGl(String phone) {
		ReturnMsg rm = new ReturnMsg();
		if (phone == null || phone.isEmpty()) {
			rm.setCode(-1);
			rm.setMessage("手机号不能为空!");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		if (MobileAccountUtils.isPhoneNumber(phone)) {
			ReturnMsg c = accountService.selectSDKPhone(phone);
			if (c != null && c.getCode() == 1) {
				rm.setCode(-1);
				rm.setMessage("账号已经存在!");
				return MobileAccountUtils.getGson().toJson(rm);
			}
			GLReturn ccc = MobileAccountGLUtils.GLUserRegGet(phone, null);

			try {
				if (ccc.getCode() == 0) {
					ccc.getData().setPhone(phone);
					ccc.getData().setPassword(RC4.decry_RC4(ccc.getData().getPassword()));
					glService.insertUserGL(ccc.getData());
					rm.setCode(200);
					GLReturn cc = MobileAccountGLUtils.GLShowNum(ccc.getData().getUid(), phone, "open");
					if (cc.getCode() == 0) {
						rm.setCode(200);
					} else {
						rm.setCode(1);
						rm.setMessage("账号注册成功，但开通来显失败!");
					}
				} else {
					rm.setCode(-1);
					rm.setMessage(ccc.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "yue", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPhoneLength(String phone, String callback) {
		ReturnMsg rm = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
		} catch (Exception e) {
			phone = null;
		}
		if (phone == null || phone.isEmpty()) {
			rm.setCode(-1);
			rm.setMessage("手机号不能为空!");
			if(callback != null ){
				return callback + "(" + MobileAccountUtils.getGson().toJson(rm) + ")";
			}
			return MobileAccountUtils.getGson().toJson(rm);
		}

		AccountYzx yzx = accountService.getYzx(phone,"114");
		if (yzx != null) {
			rm.setCode(200);
			rm.setBalance(Integer.valueOf(yzx.getBalance()) / 60);
		}
		if(callback != null ){
			return callback + "(" + MobileAccountUtils.getGson().toJson(rm) + ")";
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "callback", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String callbackGL(String phone, String called) throws Exception {
		ReturnMsg rm = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
			String cc = RC4.decry_RC4(called);
			called = cc;
		} catch (Exception e) {
			phone = null;
			called = null;
		}
		if (phone == null || phone.isEmpty() || called == null || called.isEmpty()) {
			rm.setCode(-1);
			rm.setMessage("手机号不能为空!");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		GlData gl = glService.selectByPhone(phone);

		if (gl != null) {
			GLReturn ccc = MobileAccountGLUtils.GLCallBack(gl.getUid(), called, 0);
			if (ccc.getCode() == 0) {
				rm.setCode(200);
			} else {
				rm.setCode(1);
				rm.setMessage("回拨失败!");
			}
		} else {
			rm.setCode(1);
			rm.setMessage("账号不存在!");
		}

		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "buyGoods", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String buyGoodsGL(String phone, int goodsnum, String flownumber) throws Exception {
		ReturnMsg rm = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
		} catch (Exception e) {
			phone = null;
		}
		if (phone == null || goodsnum <= 0 || flownumber == null) {
			rm.setCode(1);
			rm.setMessage("手机号码 、 商品Id和订单号 不能为空!");
			return MobileAccountUtils.getGson().toJson(rm);
		}

		if(accountService.chargeAccountYzx(phone, goodsnum*60, flownumber) == 0){
			rm.setCode(200);
		}else{
			rm.setCode(1);
			rm.setMessage("购买商品失败!");
		}

		return MobileAccountUtils.getGson().toJson(rm);
	}

	@RequestMapping(value = "shownum", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String showNum(String phone, String optype, String showphone) throws Exception {
		ReturnMsg rm = new ReturnMsg();
		if (phone == null || optype == null) {
			rm.setCode(1);
			rm.setMessage("手机号码 、 操作类型 不能为空!");
			return MobileAccountUtils.getGson().toJson(rm);
		}
		GlData gl = glService.selectByPhone(phone);

		if (gl != null) {
			GLReturn ccc = MobileAccountGLUtils.GLShowNum(gl.getUid(), showphone, optype);
			if (ccc.getCode() == 0) {
				rm.setCode(200);
			} else {
				rm.setCode(1);
				rm.setMessage("开通来显失败!");
			}
		} else {
			rm.setCode(1);
			rm.setMessage("账号不存在!");
		}
		return MobileAccountUtils.getGson().toJson(rm);
	}

}
