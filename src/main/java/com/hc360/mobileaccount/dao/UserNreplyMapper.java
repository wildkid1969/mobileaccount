package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserNreply;

public interface UserNreplyMapper{
	public void insert(UserNreply nreply);
	public List<UserNreply> getHotUserReply(UserNreply nreply);
	public int getUserReplyCount(UserNreply nreply);
	public int getUserPraiseCount(UserNreply nreply);
	
	public List<Long> getMyRelatedQuestionIdList(Long userid);
	public void deleteUserReply(Long replyid);
	public void deleteUserNReply(UserNreply nreply);

}
