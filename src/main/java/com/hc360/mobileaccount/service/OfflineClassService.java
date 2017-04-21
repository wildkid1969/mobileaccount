package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineClass;

@Service("offlineClassService")
public interface OfflineClassService{
	public List<OfflineClass> getOfflineClassList(OfflineClass clazz);
	public int getOfflineClassCount(OfflineClass clazz);
	public OfflineClass getById(Long id);
}
