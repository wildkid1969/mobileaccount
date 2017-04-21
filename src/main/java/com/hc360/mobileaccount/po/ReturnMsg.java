package com.hc360.mobileaccount.po;

import java.util.List;

public class ReturnMsg {
	
	/*
	 * @code 200 成功   非200不成功 具体内容再议
	 * */
	private int code;
			
	/*
	 * @message 返回信息说明
	 * */
	private String message ="";
	
	private int balance; //通话剩余时长
	
	private String secret ; // 密码
	
	private String ismodify; // 是否完善过个人信息 1 完善 0 未完善
	
	/*
	 * @yzm 短信验证码
	 * */
	private String yzm;
	
	private String accoundid;
	
	
	/**
	 * 积分
	 * */
	private String score;
	
	private String clientNumber;
	
	private String clientPwd;
	
	private String requesttype;
	
	/**
	 * id 
	 * */
	private int id;
	
	private Account userInfo;
	private List<Account> userList;
	private List<Ad> adList;
	private Object msgBody;
	private Object dataBody;
	
	private Integer page;//当前页码
	private Integer totalPage;//总页数
	private Integer total;//总数
	
	private String key;
	private String value;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	public String getAccoundid() {
		return accoundid;
	}

	public void setAccoundid(String accoundid) {
		this.accoundid = accoundid;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(String ismodify) {
		this.ismodify = ismodify;
	}

	public Account getUserInfo() {
		return userInfo;
	}

	public List<Ad> getAdList() {
		return adList;
	}

	public Object getDataBody() {
		return dataBody;
	}

	public void setDataBody(Object dataBody) {
		this.dataBody = dataBody;
	}

	public void setAdList(List<Ad> adList) {
		this.adList = adList;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setUserInfo(Account userInfo) {
		this.userInfo = userInfo;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	public List<Account> getUserList() {
		return userList;
	}

	public void setUserList(List<Account> userList) {
		this.userList = userList;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
