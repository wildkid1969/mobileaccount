package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.GradeMapper;
import com.hc360.mobileaccount.po.Grade;
import com.hc360.mobileaccount.service.GradeService;

@Service("gradeService")
public class GradeServiceImpl implements GradeService{
	@Resource
	private GradeMapper gradeMapper;

	@Override
	public List<Grade> getAllGradeList() {
		return gradeMapper.getAllGradeList();
	}

	@Override
	public Grade getNextGradeById(int id) {
		return gradeMapper.getNextGradeById(id);
	}

	@Override
	public Grade getGradeById(int id) {
		return gradeMapper.getGradeById(id);
	}

	@Override
	public int getNeedStarsById(int id) {
		return gradeMapper.getNeedStarsById(id);
	}
	
}
