package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.Course;

public interface CourseMapper{
	public List<Long> getRecommendCourseIdListByLabelId(Map<String,Object> map);
	public Course getCourseById(Long id);
	public List<Course> getCourseListOfGradeByGradeId(int gradeId);
	public List<Long> getCourseIdsByLabelId(Long labelId);
	public List<Course> getTeacherNewlyCourseList(Map<String,Object> map);
	
	public List<Course> getRandomCourses(int size);
	public List<String> getCourseLabels();
	public Course getCoursePraiseCntAndReplyCntById(Long id);
}
