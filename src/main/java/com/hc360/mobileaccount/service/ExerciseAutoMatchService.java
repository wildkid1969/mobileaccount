package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.ExerciseAutoMatch;

@Service("exerciseAutoMatchService")
public interface ExerciseAutoMatchService{
	public void insert(ExerciseAutoMatch match);
	public void update(ExerciseAutoMatch match);
	public void deleteByUserid(Long userid);
	public List<ExerciseAutoMatch> getAutoMathUserList(ExerciseAutoMatch match);
	public int getAutoMatchCount(ExerciseAutoMatch match);
	public List<ExerciseAutoMatch> getAutoMatchList(ExerciseAutoMatch match);
 	
}
