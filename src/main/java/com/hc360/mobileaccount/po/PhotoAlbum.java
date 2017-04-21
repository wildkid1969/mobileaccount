package com.hc360.mobileaccount.po;

public class PhotoAlbum {
	/*
	 * 标识
	 */
	private Long id;

	/*
	 * 图片地址
	 */
	private String url;
	
	/*
	 * 上传日期
	 */
	private String createtime;
	
	/*
	 * 讲师标识
	 */
	private String userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}