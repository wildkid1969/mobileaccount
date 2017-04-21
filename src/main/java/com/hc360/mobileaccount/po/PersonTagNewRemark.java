package com.hc360.mobileaccount.po;

public class PersonTagNewRemark {
	private int id;
	
	private String cmid;
	
	private String tagremark = "";
	
	private String name =""; 
	
	private String createtime = "";
	private String cid=""; //客户端传过来的id （方便修改删除用）

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCmid() {
		return cmid;
	}

	public void setCmid(String cmid) {
		this.cmid = cmid;
	}

	public String getTagremark() {
		return tagremark;
	}

	public void setTagremark(String tagremark) {
		this.tagremark = tagremark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
