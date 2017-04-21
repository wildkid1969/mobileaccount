package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hc360.mobileaccount.dao.CourseNcommentMapper;
import com.hc360.mobileaccount.dao.UserReplyMapper;
import com.hc360.mobileaccount.po.CourseNcomment;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.service.CourseNcommentService;

@Service("courseNcommentService")
public class CourseNcommentServiceImpl implements CourseNcommentService{
	@Resource
	private CourseNcommentMapper courseNcommentMapper;
	
	@Resource UserReplyMapper userReplyMapper;

	@Override
	public void insert(CourseNcomment comment) {
		courseNcommentMapper.insert(comment);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class},isolation = Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED) 
	public int saveUserComment(UserReply reply, CourseNcomment comment) {
		int flag = 1;
		try{
			userReplyMapper.insert(reply);
			comment.setReplyid(reply.getId());
			courseNcommentMapper.insert(comment);
			
			flag = 0;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return flag;
	}
	
}
