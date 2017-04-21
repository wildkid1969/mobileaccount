package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hc360.mobileaccount.dao.UserQuestionMapper;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.service.UserQuestionService;

@Service("userQuestionService")
public class UserQuestionServiceImpl implements UserQuestionService{
	@Resource
	private UserQuestionMapper userQuestionMapper;

	@Override
	public void insert(UserQuestion uq) {
		userQuestionMapper.insert(uq);
	}

	@Override
	public List<UserQuestion> getUserQuestionList(Map<String,Object> map) {
		return userQuestionMapper.getUserQuestionList(map);
	}

	@Override
	public int getUserQuestionCount(Map<String,Object> map) {
		return userQuestionMapper.getUserQuestionCount(map);
	}

	@Override
	public UserQuestion getById(Long id) {
		return userQuestionMapper.getById(id);
	}

	@Override
	public List<UserQuestion> getUserTopQuestionList(Map<String, Object> map) {
		return userQuestionMapper.getUserTopQuestionList(map);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int deleteById(Long id) {
		int flag = 0;
		try{
			userQuestionMapper.deleteUserReply(id);
			userQuestionMapper.delete(id);
			userQuestionMapper.deleteTeacherReplyInvited(id);
			userQuestionMapper.deleteUserNReply(id);
		}catch(Exception e){
			flag = 1;
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return flag;
	}

	@Override
	public List<Long> getMyQuestionIdList(Long userid) {
		return userQuestionMapper.getMyQuestionIdList(userid);
	}
	
}
