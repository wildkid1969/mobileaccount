package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.ExerciseRoom;

@Service("exerciseRoomService")
public interface ExerciseRoomService{
	public void insert(ExerciseRoom room);
	public void update(ExerciseRoom room); 
	public ExerciseRoom getById(Long roomid);
	public List<ExerciseRoom> getRoomList(ExerciseRoom room);
}
