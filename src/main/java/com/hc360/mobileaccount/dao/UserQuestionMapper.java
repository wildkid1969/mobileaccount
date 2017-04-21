package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hc360.mobileaccount.po.UserQuestion;

public interface UserQuestionMapper{
	public void insert(UserQuestion uq);
	public UserQuestion getById(Long id);
	public List<UserQuestion> getUserQuestionList(Map<String,Object> map);
	public int getUserQuestionCount(Map<String,Object> map);
	public List<UserQuestion> getUserTopQuestionList(Map<String,Object> map);
	
	public void delete(Long id);
	public void deleteTeacherReplyInvited(Long id);
	public void deleteUserReply(Long id);
	public void deleteUserNReply(Long id);
	
	public List<Long> getMyQuestionIdList(@Param(value="userid")Long userid);
}
