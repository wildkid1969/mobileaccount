package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.PushManager;

public interface PushManagerMapper{
	public void insert(PushManager push);
	public void update(PushManager push);
	public void delete(Long id);
	public PushManager getById(Long id);
	public List<PushManager> getPushManagerList(PushManager push);
}
