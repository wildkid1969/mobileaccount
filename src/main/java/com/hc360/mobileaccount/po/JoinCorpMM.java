package com.hc360.mobileaccount.po;

public class JoinCorpMM {
	/*
	 * 标识
	 */
	private String id;

	/*
	 * 公司名
	 */
	private String corp_name;

	/*
	 * 法人
	 */
	private String legal_name;

	/*
	 * 联系方式
	 */
	private String contact;

	/*
	 * 企业简介
	 */
	private String introduction;

	/*
	 * 主营商品
	 */
	private String main_product;

	/*
	 * 是否已经处理
	 */
	private String disable;

	/*
	 * 创建时间
	 */
	private String createtime;

	/*
	 * 更新时间
	 */
	private String updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMain_product() {
		return main_product;
	}

	public void setMain_product(String main_product) {
		this.main_product = main_product;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}