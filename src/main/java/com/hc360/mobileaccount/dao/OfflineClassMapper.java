package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.OfflineClass;

public interface OfflineClassMapper{
	public List<OfflineClass> getOfflineClassList(OfflineClass clazz);
	public int getOfflineClassCount(OfflineClass clazz);
	public OfflineClass getById(Long id);
}
