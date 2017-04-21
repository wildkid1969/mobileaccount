package com.hc360.mobileaccount.po;

import java.util.Date;

public class GlData {
	
	private String phone; // 用户手机号

	private String uid; // 分配给用户ID

	private String password; // 密码

	private String first; // 是否第一次注册 0 否 1 是

	private Date createtime;
	
	private String last_login_ip; //上次登录IP
	
	private String last_login_time;//上次登录时间
	
	private int status; //来显状态 0 2 关闭 1 开通
	
	private int balance; //账户总余额
	
	private int basic_balance;//基本账户余额 单位分
	
	private int gift_balance; // 赠送账户余额
	
	private String valid_time ;// 账户的过期时间
	
	private String gift_valid_time;//赠送的账户过期时间
	
	private String enable_flag;//账户状态 0 冻结 1 正常
	
	private String bind_time; // 绑定时间
	
	private String reg_time ; // 注册时间
	
	private String ctime; 
	
	private String channel_id;
	
	private String contact;
	
	private String div_per;
	
	private String channel_name;
	
	private String start_time; // 来显开始时间
	
	private String end_time; // 来显截止时间
	
	private int display_fee; // 开通的时候扣除费用
	
	private int flag; // 自动续费标识 1 未开通 2 已开通
	
	private int length ; // 剩余时长
	
	private int left_time; //剩余分钟数
	
	/**
	 *  套餐的前缀(这个决定剩余分钟数)
		备注：当prefix=0083时候，left_time需要除120才能得到真正的分钟数
			当prefix!=0083时候，则只需要除于60即可
	 * */
	private String prefix;
	
	private String effect_time; // 生效时间
	
	private int number; // 套餐购买数量 

	private int package_type;//套餐类型
	
	private int package_id; // 套餐ID
	
	private String package_name;//套餐名称
	
	private  Date updatetime = new Date();//获取余额的时间

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBasic_balance() {
		return basic_balance;
	}

	public void setBasic_balance(int basic_balance) {
		this.basic_balance = basic_balance;
	}

	public int getGift_balance() {
		return gift_balance;
	}

	public void setGift_balance(int gift_balance) {
		this.gift_balance = gift_balance;
	}

	public String getValid_time() {
		return valid_time;
	}

	public void setValid_time(String valid_time) {
		this.valid_time = valid_time;
	}

	public String getGift_valid_time() {
		return gift_valid_time;
	}

	public void setGift_valid_time(String gift_valid_time) {
		this.gift_valid_time = gift_valid_time;
	}

	public String getEnable_flag() {
		return enable_flag;
	}

	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}

	public String getBind_time() {
		return bind_time;
	}

	public void setBind_time(String bind_time) {
		this.bind_time = bind_time;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDiv_per() {
		return div_per;
	}

	public void setDiv_per(String div_per) {
		this.div_per = div_per;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getDisplay_fee() {
		return display_fee;
	}

	public void setDisplay_fee(int display_fee) {
		this.display_fee = display_fee;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public int getLeft_time() {
		return left_time;
	}

	public void setLeft_time(int left_time) {
		this.left_time = left_time;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getEffect_time() {
		return effect_time;
	}

	public void setEffect_time(String effect_time) {
		this.effect_time = effect_time;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPackage_type() {
		return package_type;
	}

	public void setPackage_type(int package_type) {
		this.package_type = package_type;
	}
	
	

}
