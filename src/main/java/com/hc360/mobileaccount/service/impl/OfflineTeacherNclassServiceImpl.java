package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineTeacherNclassMapper;
import com.hc360.mobileaccount.po.OfflineTeacherNclass;
import com.hc360.mobileaccount.service.OfflineTeacherNclassService;

@Service("offlineTeacherNclassService")
public class OfflineTeacherNclassServiceImpl implements OfflineTeacherNclassService{
	@Resource
	private OfflineTeacherNclassMapper offlineTeacherNclassMapper;

	@Override
	public List<OfflineTeacherNclass> getOfflineTeacherNclassList(
			OfflineTeacherNclass tnc) {
		return offlineTeacherNclassMapper.getOfflineTeacherNclassList(tnc);
	}
	
}
