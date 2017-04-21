package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Ad;

@Service("adService")
public interface AdService{
	public void insert(Ad ad);
	public void update(Ad ad);
	public void delete(Long id);
	public Ad getById(Long id);
	public List<Ad> getAdList(Ad ad);
}
