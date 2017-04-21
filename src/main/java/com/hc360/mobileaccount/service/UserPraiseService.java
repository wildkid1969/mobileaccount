package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserPraise;

@Service("userPraiseService")
public interface UserPraiseService{
	public List<UserPraise> getUserPraisesByParam(UserPraise p);
	public void insert(UserPraise p);
	public void deleteById(Long id);
}
