package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.ExerciseResult;

@Service("exerciseResultService")
public interface ExerciseResultService{
	public void insert(ExerciseResult result);
	public void update(ExerciseResult result);
	public void delete(Long id);
	
	public ExerciseResult getByRoomId(Long roomid);
	public ExerciseResult getResultAndPartnerByRoomId(Map<String,Object> map);
	public ExerciseResult getAudioUrlByRoomId(Long roomid);
	
	public int getExerciseResultCount(ExerciseResult result);
	public List<Account> getTodayExerciseUsers(String dateStr);
	public List<ExerciseResult> getExerciseResultList(ExerciseResult result);
	public int getExerciseResultUnionCount(String userId);
	public List<Account> getExerciseUserListByCourse(ExerciseResult result);
	public void saveAudioUrlByUserid(ExerciseResult result);
	
	public List<ExerciseResult> getAllNoDownloadResult();
}
