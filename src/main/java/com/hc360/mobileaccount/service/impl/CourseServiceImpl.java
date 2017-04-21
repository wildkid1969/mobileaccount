package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.CourseMapper;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{
	@Resource
	private CourseMapper courseMapper;

	@Override
	public List<Long> getRecommendCourseIdListByLabelId(Map<String,Object> map) {
		return courseMapper.getRecommendCourseIdListByLabelId(map);
	}

	@Override
	public Course getCourseById(Long id) {
		return courseMapper.getCourseById(id);
	}

	@Override
	public List<Course> getCourseListOfGradeByGradeId(int gradeId) {
		return courseMapper.getCourseListOfGradeByGradeId(gradeId);
	}

	@Override
	public List<Long> getCourseIdsByLabelId(Long labelId) {
		return courseMapper.getCourseIdsByLabelId(labelId);
	}

	@Override
	public List<Course> getTeacherNewlyCourseList(Map<String,Object> map) {
		return courseMapper.getTeacherNewlyCourseList(map);
	}

	@Override
	public List<Course> getRandomCourses(int size) {
		return courseMapper.getRandomCourses(size);
	}

	@Override
	public List<String> getCourseLabels() {
		return courseMapper.getCourseLabels();
	}

	@Override
	public Course getCoursePraiseCntAndReplyCntById(Long id) {
		return courseMapper.getCoursePraiseCntAndReplyCntById(id);
	}
	
}
