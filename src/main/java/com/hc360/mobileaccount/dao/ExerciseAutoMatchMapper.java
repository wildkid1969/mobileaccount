package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.ExerciseAutoMatch;

public interface ExerciseAutoMatchMapper{
	public void insert(ExerciseAutoMatch match);
	public void update(ExerciseAutoMatch match);
	public void deleteByUserid(Long userid);
	public List<ExerciseAutoMatch> getAutoMathUserList(ExerciseAutoMatch match);
	public int getAutoMatchCount(ExerciseAutoMatch match);
	public List<ExerciseAutoMatch> getAutoMatchList(ExerciseAutoMatch match);
}
