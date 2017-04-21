package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hc360.mobileaccount.dao.UserNreplyMapper;
import com.hc360.mobileaccount.dao.UserReplyMapper;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.service.UserReplyService;

@Service("userReplyService")
public class UserReplyServiceImpl implements UserReplyService{
	@Resource
	private UserReplyMapper userReplyMapper;
	
	@Resource
	private UserNreplyMapper userNreplyMapper;

	@Override
	public void insert(UserReply reply) {
		userReplyMapper.insert(reply);
	}

	@Override
	public void addPraiseCnt(Long id) {
		userReplyMapper.addPraiseCnt(id);
	}

	@Override
	public void reducePraiseCnt(Long id) {
		userReplyMapper.reducePraiseCnt(id);
	}

	@Override
	public UserReply getById(Long id) {
		return userReplyMapper.getById(id);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class}) 
	public int saveUserReply(UserNreply nreply, UserReply reply) {
		int flag = 0;
		try{
			userReplyMapper.insert(reply);
			
			nreply.setReplyid(reply.getId());
			userNreplyMapper.insert(nreply);
		}catch(Exception e){
			flag = 1;
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return flag;
	}
	
}
