/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����10:18:51
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ScoreMapper;
import com.hc360.mobileaccount.po.LuckySocre;
import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.po.ScoreDraw;
import com.hc360.mobileaccount.service.ScoreService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

/**
 * 
 * @project mobileaccount
 * @author szx
 * @version 1.0
 * @date 2015-5-19
 */
@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;

	@Override
	public int updateScore(Score score) {
		int ret = 1;
		scoreMapper.insertScoreLog(score);
		if(score.getType().equals("wsxx")){
			return ret;
		}

		if (score.getAction() == 1) {
			try {
				ret = scoreMapper.insertScore(score);
			} catch (Exception e) {
				ret = scoreMapper.updateScoreAdd(score);
			}
		} else if (score.getAction() == 2) {
			ret = scoreMapper.updateScoredel(score);
		}

		return ret;
	}

	@Override
	public Score selectScore(String phone) {
		return scoreMapper.selectScore(phone);
	}

	@Override
	public int luckySelect(LuckySocre ls, String phone) {		
		
		int random = MobileAccountUtils.getRandom(100);
		int c = 0;
		if(ls.getLs().contains(random)){
			c = 1;
		}
		ScoreDraw scoreDraw = new ScoreDraw();
		scoreDraw.setPhone(phone);
		scoreDraw.setScore(ls.getProd());
		if(c == 1){
			scoreDraw.setResult(ls.getName());
		}else{
			scoreDraw.setResult("没中奖");
		}
		scoreMapper.insertScoreDraw(scoreDraw);
		return c;
	}

	@Override
	public void insertScoreDraw(ScoreDraw scoreDraw) {
		scoreMapper.insertScoreDraw(scoreDraw);		
	}

	@Override
	public String getScoreDraw(String phone) {
		List<ScoreDraw> ll = scoreMapper.selectScoreDraw(phone);		
		if(ll == null)
			return null;
		
		return MobileAccountUtils.getGson().toJson(ll);
	}
	
	@Override
	public List<Score> getScoreInfo(String phone){
		return scoreMapper.getScoreLog(phone);
	}

	@Override
	public int countScoreLog(Score score) {		
		return scoreMapper.countScoreLog(score);
	}

	@Override
	public int updateScoredel(Score score) {
		String cc = score.getScorenum();
		score.setScorenum("-"+cc);
		scoreMapper.insertScoreLog(score);
		score.setScorenum(cc);
		try {
			return 	scoreMapper.insertScore(score);
		} catch (Exception e) {
			//e.printStackTrace();
			return scoreMapper.updateScoredel(score);
		}
		
		
	}

}
