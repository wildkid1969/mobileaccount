package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserConcern;

public interface UserConcernMapper{
	public void insert(UserConcern con);
	public void delete(UserConcern con);
	public List<UserConcern> getUserConcernList(UserConcern con);
	public List<UserConcern> getFriendList(UserConcern con);
}
