package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.CourseNcomment;
import com.hc360.mobileaccount.po.UserReply;

@Service("courseNcommentService")
public interface CourseNcommentService{
	public void insert(CourseNcomment comment);
	public int saveUserComment(UserReply reply,CourseNcomment comment);
	
}
