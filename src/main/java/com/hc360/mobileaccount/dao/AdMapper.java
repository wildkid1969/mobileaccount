package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Ad;

public interface AdMapper{
	public void insert(Ad ad);
	public void update(Ad ad);
	public void delete(Long id);
	public Ad getById(Long id);
	public List<Ad> getAdList(Ad ad);
}
