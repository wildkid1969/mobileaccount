package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.ExerciseRoom;

public interface ExerciseRoomMapper{
	public void insert(ExerciseRoom room);
	public void update(ExerciseRoom room); 
	public ExerciseRoom getById(Long roomid);
	public List<ExerciseRoom> getRoomList(ExerciseRoom room);
}
