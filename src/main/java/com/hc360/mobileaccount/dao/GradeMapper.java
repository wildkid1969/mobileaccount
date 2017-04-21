package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Grade;

public interface GradeMapper{
	public List<Grade> getAllGradeList();
	public Grade getGradeById(int id);
	public Grade getNextGradeById(int id);
	public int getNeedStarsById(int id);
}
