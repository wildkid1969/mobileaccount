package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineClassMapper;
import com.hc360.mobileaccount.po.OfflineClass;
import com.hc360.mobileaccount.service.OfflineClassService;

@Service("offlineClassService")
public class OfflineClassServiceImpl implements OfflineClassService{
	@Resource
	private OfflineClassMapper offlineClassMapper;

	@Override
	public List<OfflineClass> getOfflineClassList(OfflineClass clazz) {
		return offlineClassMapper.getOfflineClassList(clazz);
	}

	@Override
	public OfflineClass getById(Long id) {
		return offlineClassMapper.getById(id);
	}

	@Override
	public int getOfflineClassCount(OfflineClass clazz) {
		return offlineClassMapper.getOfflineClassCount(clazz);
	}
	
}
