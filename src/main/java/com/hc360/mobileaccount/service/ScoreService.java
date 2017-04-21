/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:29
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service;

import java.util.List;

import com.hc360.mobileaccount.po.LuckySocre;
import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.po.ScoreDraw;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-18
 */
public interface ScoreService {

	/**
	 * 更新积分 新建用户积分 增加用户积分 消费用户积分
	 **/
	int updateScore(Score score);

	/**
	 * 获取用户积分
	 * */

	Score selectScore(String phone);

	/**
	 * 积分抽奖接口
	 * */

	int luckySelect(LuckySocre ls, String phone);
	
	
	void insertScoreDraw(ScoreDraw scoreDraw);
	
	String getScoreDraw(String phone);
	
	/**
	 * 判断当前是否已经获取积分
	 * */
	int countScoreLog(Score score);
	
	int updateScoredel(Score score);
	
	List<Score> getScoreInfo(String phone);

}
