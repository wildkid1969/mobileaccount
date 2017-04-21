package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.UserTalk;

public interface UserTalkMapper{
	public void insert(UserTalk talk);
	public UserTalk getById(Long id);
	public List<UserTalk> getUserTalkList(UserTalk talk);
	public int getUserTalkCount(UserTalk talk);
	public List<Account> getTodayTalkUsers(String dateStr);
}
