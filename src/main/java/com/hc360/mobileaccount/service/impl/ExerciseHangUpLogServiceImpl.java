package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ExerciseHangUpLogMapper;
import com.hc360.mobileaccount.po.ExerciseHangUpLog;
import com.hc360.mobileaccount.service.ExerciseHangUpLogService;

@Service("exerciseHangUpLogService")
public class ExerciseHangUpLogServiceImpl implements ExerciseHangUpLogService{
	@Resource
	private ExerciseHangUpLogMapper exerciseHangUpLogMapper;

	@Override
	public void insert(ExerciseHangUpLog log) {
		exerciseHangUpLogMapper.insert(log);
	}

	@Override
	public ExerciseHangUpLog getByRoomid(Long roomid) {
		return exerciseHangUpLogMapper.getByRoomid(roomid);
	}
	
}
