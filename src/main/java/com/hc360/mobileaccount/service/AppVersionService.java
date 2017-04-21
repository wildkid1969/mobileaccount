package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.AppVersion;

@Service("appVersionService")
public interface AppVersionService{
	public void insert(AppVersion v);
	public void update(AppVersion v);
	public void delete(Long versionid);
	public AppVersion getById(Long versionid);
	
	public List<AppVersion> getVersionList(AppVersion v);
	
}
