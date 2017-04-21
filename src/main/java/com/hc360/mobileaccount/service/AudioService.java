package com.hc360.mobileaccount.service;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Audio;

@Service("audioService")
public interface AudioService{
	public Audio getById(Long id);
	public void updateAudioPlayCnt(String id);
}
