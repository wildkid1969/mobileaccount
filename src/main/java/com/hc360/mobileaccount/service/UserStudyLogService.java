package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.UserStudyLog;

@Service("userStudyLogService")
public interface UserStudyLogService{
	public void insert(UserStudyLog log);
	public void update(UserStudyLog log);
	public int getUserStudyLogCount(UserStudyLog log);
	public List<Account> getChapterUserList(UserStudyLog log);
	public List<Account> getTodayStudyUserList(Map<String,Object> map);
	
	public List<Long> getPopChapterids(int size);
	public List<UserStudyLog> getUserStudyLogList(UserStudyLog log);
	
}
