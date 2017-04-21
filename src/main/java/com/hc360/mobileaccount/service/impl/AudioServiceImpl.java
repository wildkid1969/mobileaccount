package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.AudioMapper;
import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.service.AudioService;

@Service("audioService")
public class AudioServiceImpl implements AudioService{
	@Resource
	private AudioMapper audioMapper;
	
	
	@Override
	public void updateAudioPlayCnt(String id){
		audioMapper.updateAudioPlayCnt(id);
	}


	@Override
	public Audio getById(Long id) {
		return audioMapper.getById(id);
	}
}
