package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ExerciseAutoMatchMapper;
import com.hc360.mobileaccount.po.ExerciseAutoMatch;
import com.hc360.mobileaccount.service.ExerciseAutoMatchService;

@Service("exerciseAutoMatchService")
public class ExerciseAutoMatchServiceImpl implements ExerciseAutoMatchService{
	@Resource
	private ExerciseAutoMatchMapper exerciseAutoMatchMapper;

	@Override
	public void insert(ExerciseAutoMatch match) {
		exerciseAutoMatchMapper.insert(match);
	}

	@Override
	public void deleteByUserid(Long userid) {
		exerciseAutoMatchMapper.deleteByUserid(userid);
	}

	@Override
	public List<ExerciseAutoMatch> getAutoMathUserList(ExerciseAutoMatch match) {
		return exerciseAutoMatchMapper.getAutoMathUserList(match);
	}

	@Override
	public void update(ExerciseAutoMatch match) {
		exerciseAutoMatchMapper.update(match);
	}

	@Override
	public int getAutoMatchCount(ExerciseAutoMatch match) {
		return exerciseAutoMatchMapper.getAutoMatchCount(match);
	}

	@Override
	public List<ExerciseAutoMatch> getAutoMatchList(ExerciseAutoMatch match) {
		return exerciseAutoMatchMapper.getAutoMatchList(match);
	}
	
}
