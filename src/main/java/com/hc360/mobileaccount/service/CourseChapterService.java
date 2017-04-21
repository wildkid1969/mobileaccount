package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.UserReply;

@Service("courseChapterService")
public interface CourseChapterService{
	public List<CourseChapter> getChapterListByCourseId(String courseId);
	public CourseChapter getChapterById(String id);
	public int getUserIsPraise(CourseChapter chap);
	public List<UserReply> getChapterCommentList(Map<String,Object> map);
	public int getChapterCommentCount(String chapterid);
	public List<String> getRecommendChapterIdListByLabelId(Map<String,Object> map);
	
	public void addPraiseCnt(Long id);
	public void reducePraiseCnt(Long id);
	
	public List<CourseChapter> getAll();
	public List<CourseChapter> getStudiedChapLabelList(Long userid);
	public List<CourseChapter> getCourseChapterList(Map<String,Object> map);
	
}
