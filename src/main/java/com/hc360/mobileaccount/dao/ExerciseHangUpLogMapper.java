package com.hc360.mobileaccount.dao;

import com.hc360.mobileaccount.po.ExerciseHangUpLog;

public interface ExerciseHangUpLogMapper{
	public void insert(ExerciseHangUpLog log);
	public ExerciseHangUpLog getByRoomid(Long roomid);
}
