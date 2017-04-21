package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserPraise;

public interface UserPraiseMapper{
	public List<UserPraise> getUserPraisesByParam(UserPraise p);
	public void insert(UserPraise p);
	public void deleteById(Long id);
}
