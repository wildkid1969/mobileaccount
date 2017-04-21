package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.MarketingCourse;

@Service("marketingCourseService")
public interface MarketingCourseService{
	public void insert(MarketingCourse course);
	public void update(MarketingCourse course);
	public MarketingCourse getById(Long id);
	public MarketingCourse getNextById(Long id);
	public List<MarketingCourse> getCourseList(MarketingCourse course);
	public int getCourseCount(MarketingCourse course);
	public List<MarketingCourse> getRecommendCourses(Map<String,Object> map);
}
