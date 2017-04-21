package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ExerciseRoomMapper;
import com.hc360.mobileaccount.po.ExerciseRoom;
import com.hc360.mobileaccount.service.ExerciseRoomService;

@Service("exerciseRoomService")
public class ExerciseRoomServiceImpl implements ExerciseRoomService{
	@Resource
	private ExerciseRoomMapper exerciseRoomMapper;

	@Override
	public void insert(ExerciseRoom room) {
		exerciseRoomMapper.insert(room);
	}

	@Override
	public void update(ExerciseRoom room) {
		exerciseRoomMapper.update(room);
	}

	@Override
	public ExerciseRoom getById(Long roomid) {
		return exerciseRoomMapper.getById(roomid);
	}

	@Override
	public List<ExerciseRoom> getRoomList(ExerciseRoom room) {
		return exerciseRoomMapper.getRoomList(room);
	}
	
}
