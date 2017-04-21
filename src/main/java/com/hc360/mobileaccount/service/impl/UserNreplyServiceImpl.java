package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hc360.mobileaccount.dao.UserNreplyMapper;
import com.hc360.mobileaccount.dao.UserReplyMapper;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.service.UserNreplyService;

@Service("userNreplyService")
public class UserNreplyServiceImpl implements UserNreplyService{
	@Resource
	private UserReplyMapper userReplyMapper;
	
	@Resource
	private UserNreplyMapper userNreplyMapper;

	@Override
	public void insert(UserNreply nreply) {
		userNreplyMapper.insert(nreply);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class}) 
	public int saveUserComment(UserReply reply,UserNreply nreply) {
		int flag = 1;
		try{
			userReplyMapper.insert(reply);
			nreply.setReplyid(reply.getId());
			userNreplyMapper.insert(nreply);
			
			flag=0;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return flag;
	}

	@Override
	public List<UserNreply> getHotUserReply(UserNreply nreply) {
		return userNreplyMapper.getHotUserReply(nreply);
	}

	@Override
	public int getUserReplyCount(UserNreply nreply) {
		return userNreplyMapper.getUserReplyCount(nreply);
	}
	@Override
	public int getUserPraiseCount(UserNreply nreply) {
		return userNreplyMapper.getUserPraiseCount(nreply);
	}

	@Override
	public List<Long> getMyRelatedQuestionIdList(Long userid) {
		return userNreplyMapper.getMyRelatedQuestionIdList(userid);
	}

	@Override
	public void deleteUserReply(UserNreply nreply) {
		userNreplyMapper.deleteUserReply(nreply.getReplyid());
		userNreplyMapper.deleteUserNReply(nreply);
	}
	
}
