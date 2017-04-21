package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserReply;

@Service("userNreplyService")
public interface UserNreplyService{
	public void insert(UserNreply nreply);
	public int saveUserComment(UserReply reply,UserNreply nreply);
	public List<UserNreply> getHotUserReply(UserNreply nreply);
	public int getUserReplyCount(UserNreply nreply);
	public int getUserPraiseCount(UserNreply nreply);
	
	public List<Long> getMyRelatedQuestionIdList(Long userid);
	public void deleteUserReply(UserNreply nreply);
}
