package com.hc360.mobileaccount.po;

public class SuggestCorp {
	/*
	 * 标识
	 */
	private String id;

	/*
	 * 企业名称
	 */
	private String corp_name;

	/*
	 * 纠错信息
	 */
	private String suggest;

	/*
	 * 联系方式
	 */
	private String phone;
	
	/*
	 * 是否已处理
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

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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