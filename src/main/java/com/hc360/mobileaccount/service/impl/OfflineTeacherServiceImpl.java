package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineTeacherMapper;
import com.hc360.mobileaccount.po.OfflineTeacher;
import com.hc360.mobileaccount.service.OfflineTeacherService;

@Service("offlineTeacherService")
public class OfflineTeacherServiceImpl implements OfflineTeacherService{
	@Resource
	private OfflineTeacherMapper offlineTeacherMapper;

	@Override
	public OfflineTeacher getById(Long id) {
		return offlineTeacherMapper.getById(id);
	}
	
}
