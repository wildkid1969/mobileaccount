package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserQuestion;

@Service("userQuestionService")
public interface UserQuestionService{
	public void insert(UserQuestion uq);
	public UserQuestion getById(Long id);
	public List<UserQuestion> getUserQuestionList(Map<String,Object> map);
	public int getUserQuestionCount(Map<String,Object> map);
	public List<UserQuestion> getUserTopQuestionList(Map<String,Object> map);
	
	public int deleteById(Long id);
	public List<Long> getMyQuestionIdList(Long userid);
}
