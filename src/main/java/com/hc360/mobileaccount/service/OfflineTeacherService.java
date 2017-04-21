package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineTeacher;

@Service("offlineTeacherService")
public interface OfflineTeacherService{
	public OfflineTeacher getById(Long id);
}
