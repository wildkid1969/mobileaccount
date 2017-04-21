package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserStudyLogMapper;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.UserStudyLog;
import com.hc360.mobileaccount.service.UserStudyLogService;

@Service("userStudyLogService")
public class UserStudyLogServiceImpl implements UserStudyLogService{
	@Resource
	private UserStudyLogMapper userStudyLogMapper;

	@Override
	public int getUserStudyLogCount(UserStudyLog log) {
		return userStudyLogMapper.getUserStudyLogCount(log);
	}

	@Override
	public List<Account> getChapterUserList(UserStudyLog log) {
		return userStudyLogMapper.getChapterUserList(log);
	}

	@Override
	public List<Account> getTodayStudyUserList(Map<String, Object> map) {
		return userStudyLogMapper.getTodayStudyUserList(map);
	}

	@Override
	public void insert(UserStudyLog log) {
		userStudyLogMapper.insert(log);
	}
	@Override
	public void update(UserStudyLog log) {
		userStudyLogMapper.update(log);
	}

	@Override
	public List<Long> getPopChapterids(int size) {
		return userStudyLogMapper.getPopChapterids(size);
	}

	@Override
	public List<UserStudyLog> getUserStudyLogList(UserStudyLog log) {
		return userStudyLogMapper.getUserStudyLogList(log);
	}
	
}
