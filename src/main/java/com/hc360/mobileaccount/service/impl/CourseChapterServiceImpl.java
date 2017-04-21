package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.CourseChapterMapper;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.service.CourseChapterService;

@Service("courseChapterService")
public class CourseChapterServiceImpl implements CourseChapterService{
	@Resource
	private CourseChapterMapper courseChapterMapper;

	@Override
	public List<CourseChapter> getChapterListByCourseId(String courseId) {
		return courseChapterMapper.getChapterListByCourseId(courseId);
	}

	@Override
	public CourseChapter getChapterById(String id) {
		return courseChapterMapper.getChapterById(id);
	}

	@Override
	public int getUserIsPraise(CourseChapter chap) {
		return courseChapterMapper.getUserIsPraise(chap);
	}

	@Override
	public List<UserReply> getChapterCommentList(Map<String,Object> map) {
		return courseChapterMapper.getChapterCommentList(map);
	}

	@Override
	public int getChapterCommentCount(String chapterid) {
		return courseChapterMapper.getChapterCommentCount(chapterid);
	}

	@Override
	public List<String> getRecommendChapterIdListByLabelId(
			Map<String, Object> map) {
		return courseChapterMapper.getRecommendChapterIdListByLabelId(map);
	}

	@Override
	public void addPraiseCnt(Long id) {
		courseChapterMapper.addPraiseCnt(id);
	}

	@Override
	public void reducePraiseCnt(Long id) {
		courseChapterMapper.reducePraiseCnt(id);
	}

	@Override
	public List<CourseChapter> getAll() {
		return courseChapterMapper.getAll();
	}

	@Override
	public List<CourseChapter> getStudiedChapLabelList(Long userid) {
		return courseChapterMapper.getStudiedChapLabelList(userid);
	}

	@Override
	public List<CourseChapter> getCourseChapterList(Map<String, Object> map) {
		return courseChapterMapper.getCourseChapterList(map);
	}
	
}
