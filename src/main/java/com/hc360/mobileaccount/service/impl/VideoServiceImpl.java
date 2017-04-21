package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.VideoMapper;
import com.hc360.mobileaccount.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService{
	@Resource
	private VideoMapper videoMapper;

	@Override
	public void updateVideoPlayCnt(String id) {
		videoMapper.updateVideoPlayCnt(id);
	}
	
}
