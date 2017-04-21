package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.AppVersion;

public interface AppVersionMapper{
	public void insert(AppVersion v);
	public void update(AppVersion v);
	public void delete(Long versionid);
	public AppVersion getById(Long versionid);
	
	public List<AppVersion> getVersionList(AppVersion v);
}
