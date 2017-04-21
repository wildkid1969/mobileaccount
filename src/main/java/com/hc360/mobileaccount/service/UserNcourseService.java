package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserNcourse;

@Service("userNcourseService")
public interface UserNcourseService{
	public void insert(UserNcourse unc);
	public void delete(UserNcourse unc);
	public List<Long> getUserNCourseIds(String userid);
	public void subcribeTeacherAllCourses(List<UserNcourse> ucList);
	public void unsubcribeTeacherAllCourses(Map<String,Object> map);
	
}
