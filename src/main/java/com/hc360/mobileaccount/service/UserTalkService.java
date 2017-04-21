package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.UserTalk;

@Service("userTalkService")
public interface UserTalkService{
	public void insert(UserTalk talk);
	public UserTalk getById(Long id);
	public List<UserTalk> getUserTalkList(UserTalk talk);
	public int getUserTalkCount(UserTalk talk);
	public List<Account> getTodayTalkUsers(String dateStr);
	
}
