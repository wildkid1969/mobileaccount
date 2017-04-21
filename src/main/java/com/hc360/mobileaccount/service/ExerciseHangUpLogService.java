package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.ExerciseHangUpLog;

@Service("exerciseHangUpLogService")
public interface ExerciseHangUpLogService{
	public void insert(ExerciseHangUpLog log);
	public ExerciseHangUpLog getByRoomid(Long roomid);
	
}
