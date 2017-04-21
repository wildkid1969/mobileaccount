package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineTeacherNclass;

@Service("offlineTeacherNclassService")
public interface OfflineTeacherNclassService{
	public List<OfflineTeacherNclass> getOfflineTeacherNclassList(OfflineTeacherNclass tnc);
}
