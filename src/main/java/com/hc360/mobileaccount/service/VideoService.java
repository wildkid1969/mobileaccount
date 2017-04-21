package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

@Service("videoService")
public interface VideoService{
	public void updateVideoPlayCnt(String id);
	
}
