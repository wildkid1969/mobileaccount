package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserNcourseMapper;
import com.hc360.mobileaccount.po.UserNcourse;
import com.hc360.mobileaccount.service.UserNcourseService;

@Service("userNcourseService")
public class UserNcourseServiceImpl implements UserNcourseService{
	@Resource
	private UserNcourseMapper userNcourseMapper;

	@Override
	public void insert(UserNcourse unc) {
		userNcourseMapper.insert(unc);
	}

	@Override
	public List<Long> getUserNCourseIds(String userid) {
		return userNcourseMapper.getUserNCourseIds(userid);
	}

	@Override
	public void subcribeTeacherAllCourses(List<UserNcourse> ucList) {
		userNcourseMapper.subcribeTeacherAllCourses(ucList);
	}

	@Override
	public void delete(UserNcourse unc) {
		userNcourseMapper.delete(unc);
	}

	@Override
	public void unsubcribeTeacherAllCourses(Map<String, Object> map) {
		userNcourseMapper.unsubcribeTeacherAllCourses(map);
	}
	
}
