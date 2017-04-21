package com.hc360.mobileaccount.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ExerciseResultMapper;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.ExerciseResult;
import com.hc360.mobileaccount.service.ExerciseResultService;

@Service("exerciseResultService")
public class ExerciseResultServiceImpl implements ExerciseResultService{
	@Resource
	private ExerciseResultMapper exerciseResultMapper;

	@Override
	public int getExerciseResultCount(ExerciseResult result) {
		return exerciseResultMapper.getExerciseResultCount(result);
	}

	@Override
	public List<Account> getTodayExerciseUsers(String dateStr) {
		return exerciseResultMapper.getTodayExerciseUsers(dateStr);
	}

	@Override
	public List<ExerciseResult> getExerciseResultList(ExerciseResult result) {
		return exerciseResultMapper.getExerciseResultList(result);
	}

	@Override
	public int getExerciseResultUnionCount(String userId) {
		return exerciseResultMapper.getExerciseResultUnionCount(userId);
	}

	@Override
	public void insert(ExerciseResult result) {
		exerciseResultMapper.insert(result);
	}

	@Override
	public List<Account> getExerciseUserListByCourse(ExerciseResult result) {
		return exerciseResultMapper.getExerciseUserListByCourse(result);
	}

	@Override
	public void saveAudioUrlByUserid(ExerciseResult result) {
		exerciseResultMapper.saveAudioUrlByUserid(result);
	}

	@Override
	public ExerciseResult getByRoomId(Long roomid) {
		return exerciseResultMapper.getByRoomId(roomid);
	}
	@Override
	public ExerciseResult getResultAndPartnerByRoomId(Map<String,Object> map) {
		return exerciseResultMapper.getResultAndPartnerByRoomId(map);
	}

	@Override
	public void update(ExerciseResult result) {
		exerciseResultMapper.update(result);
	}

	@Override
	public List<ExerciseResult> getAllNoDownloadResult() {
		return exerciseResultMapper.getAllNoDownloadResult();
	}

	@Override
	public ExerciseResult getAudioUrlByRoomId(Long roomid) {
		return exerciseResultMapper.getAudioUrlByRoomId(roomid);
	}

	@Override
	public void delete(Long id) {
		exerciseResultMapper.delete(id);
	}
	
}
