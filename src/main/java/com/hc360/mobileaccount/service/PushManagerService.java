package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.PushManager;

@Service("pushManagerService")
public interface PushManagerService{
	public void insert(PushManager push);
	public void update(PushManager push);
	public void delete(Long id);
	public PushManager getById(Long id);
	public List<PushManager> getPushManagerList(PushManager push);
	
}
