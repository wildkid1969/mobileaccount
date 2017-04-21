
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:20:23
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.po.ScoreDraw;

/**
 * 
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18    
 */
public interface ScoreMapper {
	
	int insertScoreLog(Score score);
	
	int insertScore(Score score) throws Exception;
	
	int updateScoreAdd(Score score);
	
	int updateScoredel(Score score);
	
	Score selectScore(String phone);
	
	void insertScoreDraw(ScoreDraw scoreDraw);
	
	List<ScoreDraw> selectScoreDraw(String phone);
	
	int countScoreLog(Score score);
	
	List<Score> getScoreLog(String phone);
	
}
