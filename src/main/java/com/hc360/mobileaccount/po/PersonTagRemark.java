package com.hc360.mobileaccount.po;


public class PersonTagRemark {
	
	private int id;
	
	private String cmid;
	
	private String tagremark = "";
	
	private String createtime = "";

	public String getCmid() {
		return cmid;
	}

	public void setPid(String cmid) {
		this.cmid = cmid;
	}


	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagremark() {
		return tagremark;
	}

	public void setTagremark(String tagremark) {
		this.tagremark = tagremark;
	}
	
	

}
