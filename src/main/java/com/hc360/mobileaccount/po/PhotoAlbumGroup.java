package com.hc360.mobileaccount.po;

import java.util.List;

public class PhotoAlbumGroup {
	private String createtime;
	private List<PhotoAlbum> photoAlbums;

	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public List<PhotoAlbum> getPhotoAlbums() {
		return photoAlbums;
	}
	public void setPhotoAlbums(List<PhotoAlbum> photoAlbums) {
		this.photoAlbums = photoAlbums;
	}
}