package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Grade;

@Service("gradeService")
public interface GradeService{
	public List<Grade> getAllGradeList();
	public Grade getGradeById(int id);
	public Grade getNextGradeById(int id);
	public int getNeedStarsById(int id);
	
}
