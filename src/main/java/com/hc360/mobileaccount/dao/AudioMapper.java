package com.hc360.mobileaccount.dao;

import com.hc360.mobileaccount.po.Audio;

public interface AudioMapper{
	public Audio getById(Long id);
	public void updateAudioPlayCnt(String id);
}
