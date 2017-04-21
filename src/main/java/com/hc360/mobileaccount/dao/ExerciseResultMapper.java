package com.hc360.mobileaccount.dao;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.ExerciseResult;

public interface ExerciseResultMapper{
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
