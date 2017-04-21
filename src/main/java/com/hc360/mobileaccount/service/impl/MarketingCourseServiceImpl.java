package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.MarketingCourseMapper;
import com.hc360.mobileaccount.po.MarketingCourse;
import com.hc360.mobileaccount.service.MarketingCourseService;

@Service("marketingCourseService")
public class MarketingCourseServiceImpl implements MarketingCourseService{
	@Resource
	private MarketingCourseMapper marketingCourseMapper;

	@Override
	public MarketingCourse getById(Long id) {
		return marketingCourseMapper.getById(id);
	}
	
	@Override
	public MarketingCourse getNextById(Long id) {
		return marketingCourseMapper.getNextById(id);
	}

	@Override
	public List<MarketingCourse> getCourseList(MarketingCourse course) {
		return marketingCourseMapper.getCourseList(course);
	}

	@Override
	public int getCourseCount(MarketingCourse course) {
		return marketingCourseMapper.getCourseCount(course);
	}

	@Override
	public List<MarketingCourse> getRecommendCourses(Map<String, Object> map) {
		return marketingCourseMapper.getRecommendCourses(map);
	}

	@Override
	public void insert(MarketingCourse course) {
		marketingCourseMapper.insert(course);
	}

	@Override
	public void update(MarketingCourse course) {
		marketingCourseMapper.update(course);
	}
	
}
