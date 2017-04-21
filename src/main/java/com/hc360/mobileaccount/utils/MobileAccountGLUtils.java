package com.hc360.mobileaccount.utils;

import com.hc360.mobileaccount.po.GLConfig;
import com.hc360.mobileaccount.po.GLReturn;
import com.hc360.mobileaccount.po.GLReturn1;

public class MobileAccountGLUtils {

	// 用户注册
	private static String URL_USERREG    = "http://bsp.szgloem.com/user_reg";
	// 用户登录
	private static String URL_USERLOGIN  = "http://bsp.szgloem.com/user_login";
	// 获取用户余额
	private static String URL_USERWALLET = "http://bsp.szgloem.com/user_wallet";
	// 获取用户套餐余额
	private static String URL_USERPACKAGE = "http://bsp.szgloem.com/user_package";	
	// 回拨电话
	private static String URL_CALLBACK   = "http://bsp.szgloem.com/call_back";
	// 来显操作
	private static String URL_SHOWNUM    = "http://bsp.szgloem.com/show_num";
	// 购买商品
	private static String URL_BUYGOODS   = "http://bsp.szgloem.com/buy_goods";

	private static GLConfig glConfig = new GLConfig();

	/**
	 * 注册
	 * */
	public static GLReturn GLUserRegGet(String phone, String invited) {

		// 判断是否为手机号码
		if (!MobileAccountUtils.isPhoneNumber(phone)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + phone + ts + glConfig.getKey());
		System.out.println("MD5 : " + md5.toString());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&phone=" + phone
					+ "&ts=" + ts + "&sign=" + sign);
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_USERREG, "?"+sb.toString());
			System.out.println(urlResult);
			GLReturn ur = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			byte [] cc = ur.getMsg().getBytes("UTF-8");
			System.out.println(new String(cc,"UTF-8"));
			return ur;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 登录
	 * */
	public static GLReturn GLUserLoginGet(String uid, String password) {

		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + ts + password + glConfig.getKey());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&account=" + uid
					+ "&password=" + password + "&ts=" + ts + "&sign=" + sign);
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_USERLOGIN, "?"+sb.toString());
			System.out.println(urlResult);
			GLReturn gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取用户余额
	 * */
	public static GLReturn GLUserWallet(String uid) {
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + ts + glConfig.getKey());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&uid=" + uid
					+ "&ts=" + ts + "&sign=" + sign);
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_USERWALLET, "?"+sb.toString());
			System.out.println(urlResult);
			GLReturn gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取用户套餐余额
	 * */
	public static GLReturn1 GLUserPackage(String uid) {
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + ts + glConfig.getKey());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&uid=" + uid
					+ "&ts=" + ts + "&sign=" + sign);
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_USERPACKAGE, "?"+sb.toString());
			System.out.println(new String(urlResult.getBytes("GBK"),"UTF-8"));
			GLReturn1 gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn1.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 回拨电话
	 * */
	public static GLReturn GLCallBack(String uid, String callee, int calltype) {
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + callee + ts + glConfig.getKey());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&uid=" + uid
					+ "&callee=" + callee + "&call_type=" + calltype + "&ts=" + ts + "&sign=" + sign);
			System.out.println(sb.toString());
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_CALLBACK, "?" + sb.toString());
			System.out.println(urlResult);
			GLReturn gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 来显操作
	 * */
	public static GLReturn GLShowNum(String uid, String phone, String optype) { // optype: open 开通 stop 关闭 search 查询
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + optype + ts + glConfig.getKey());
		try {
			String sign = MD5.md5Encode(md5.toString());
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&uid=" + uid
					+ "&phone=" + phone + "&op_type=" + optype + "&ts=" + ts + "&sign=" + sign);
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_SHOWNUM, "?"+sb.toString());
			System.out.println(urlResult);
			GLReturn gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 购买商品接口
	 * */

	public static GLReturn BuyGoods(String uid, String goodsId, int goodsNum, String orderNo) {
		StringBuffer sb = new StringBuffer();
		StringBuffer md5 = new StringBuffer();
		String ts = MobileAccountUtils.getNowtime();
		md5.append(glConfig.getBrandid() + glConfig.getClientid() + uid + goodsId + orderNo + glConfig.getKey());
		System.out.println("before : "+ md5.toString());
		try {
			String sign = MD5.md5Encode(md5.toString());
			System.out.println("after   :"+ sign);
			sb.append("brand_id=" + glConfig.getBrandid() + "&client_id=" + glConfig.getClientid() + "&uid=" + uid
					+ "&goods_id=" + goodsId + "&goods_num=" + goodsNum + "&order_no=" + orderNo + "&ts=" + ts
					+ "&sign=" + sign);
			System.out.println("urlParam  :"+ sb.toString());
			String urlResult = MobileAccountUtils.sendGet(MobileAccountGLUtils.URL_BUYGOODS, "?"+sb.toString());
			System.out.println(urlResult);
			GLReturn gr = MobileAccountUtils.getGson().fromJson(urlResult, GLReturn.class);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
