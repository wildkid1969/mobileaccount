package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.AppVersionMapper;
import com.hc360.mobileaccount.po.AppVersion;
import com.hc360.mobileaccount.service.AppVersionService;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService{
	@Resource
	private AppVersionMapper appVersionMapper;

	@Override
	public void insert(AppVersion v) {
		appVersionMapper.insert(v);
	}

	@Override
	public void update(AppVersion v) {
		appVersionMapper.update(v);
	}

	@Override
	public void delete(Long versionid) {
		appVersionMapper.delete(versionid);
	}

	@Override
	public List<AppVersion> getVersionList(AppVersion v) {
		return appVersionMapper.getVersionList(v);
	}

	@Override
	public AppVersion getById(Long versionid) {
		return appVersionMapper.getById(versionid);
	}
	
}
