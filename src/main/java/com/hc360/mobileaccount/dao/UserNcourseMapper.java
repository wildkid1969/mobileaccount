package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.UserNcourse;

public interface UserNcourseMapper{
	public void insert(UserNcourse unc);
	public void delete(UserNcourse unc);
	public List<Long> getUserNCourseIds(String userid);
	public void subcribeTeacherAllCourses(List<UserNcourse> ucList);
	public void unsubcribeTeacherAllCourses(Map<String,Object> map);

}
