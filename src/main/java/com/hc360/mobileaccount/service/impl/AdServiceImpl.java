package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.AdMapper;
import com.hc360.mobileaccount.po.Ad;
import com.hc360.mobileaccount.service.AdService;

@Service("adService")
public class AdServiceImpl implements AdService{
	@Resource
	private AdMapper adMapper;

	@Override
	public List<Ad> getAdList(Ad ad) {
		return adMapper.getAdList(ad);
	}

	@Override
	public void insert(Ad ad) {
		adMapper.insert(ad);
	}

	@Override
	public void update(Ad ad) {
		adMapper.update(ad);
	}

	@Override
	public Ad getById(Long id) {
		return adMapper.getById(id);
	}

	@Override
	public void delete(Long id) {
		adMapper.delete(id);
	}
	
}
