package com.hc360.mobileaccount.common;

/**
 * 云之讯常量
 * @author HC360
 *
 */
public class YZXConstant {
	
	/**
	 * 企业114的应用Id  String	必选
	 */
	public static final String APP_ID = "3f4b5914c3b647e8b7cd2bed93d5f69c";
	
	/**
	 * 云之讯 应用ID（测试环境）需要手动把云之讯用户信息update成测试DEMO绑定成的Client账号、密码
	 */
	public static final String APP_ID_TEST = "1a82f309d3fa45ec9bef32016431bc75";
	
	
	/**
	 * 114用户默认密码(使用MD5.md5Encode加密【lvtieandxiaoyu】的结果)
	 */
	public static final String DEFAULT_SECRET = "bd32689ad82fe9145f67f4fa7e042bdc";
	
	/**
	 * 开发者信息
	 */
	public static final String ACCOUNT_SID = "9a5d6c8c8f9feb180deaf63d771f76d4";
	public static final String AUTH_TOKEN = "3a85f0882e69b685b766e2b76531fd1e";
	public static final String REST_URL = "https://api.ucpaas.com";
	
	/**
	 * clientType	String	必选	0 开发者计费；1 云平台计费。默认为0
	 */
	public static final String CLIENT_TYPE_DEVELOPER = "0";
	public static final String CLIENT_TYPE_CLOUD = "1";
	
	/**
	 * 云之讯单价
	 */
	public static final double UNIT_PRICE = 0.055;
	
	/**
	 * chargeType	String	必选	0 充值；1 回收。
	 */
	public static final String CHARGE_TYPE_CHARGE = "0";
	public static final String CHARGE_TYPE_RECOVERY = "1";
	
	/**
	 * respCode	String	必选	请求状态码，取值000000（成功）
	 */
	public static final String RESP_CODE_SUCCESS = "000000";
	
}
