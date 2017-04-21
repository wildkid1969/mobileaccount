package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.PushManagerMapper;
import com.hc360.mobileaccount.po.PushManager;
import com.hc360.mobileaccount.service.PushManagerService;

@Service("pushManagerService")
public class PushManagerServiceImpl implements PushManagerService{
	@Resource
	private PushManagerMapper pushManagerMapper;

	@Override
	public void insert(PushManager push) {
		pushManagerMapper.insert(push);
	}

	@Override
	public void update(PushManager push) {
		pushManagerMapper.update(push);
	}

	@Override
	public void delete(Long id) {
		pushManagerMapper.delete(id);
	}

	@Override
	public PushManager getById(Long id) {
		return pushManagerMapper.getById(id);
	}

	@Override
	public List<PushManager> getPushManagerList(PushManager push) {
		return pushManagerMapper.getPushManagerList(push);
	}
	
}
