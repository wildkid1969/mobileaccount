package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserSignIn;

public interface UserSignInMapper{
	public void insert(UserSignIn in); 
	public List<UserSignIn> getUserSignInList(UserSignIn in); 
	public UserSignIn getUserSignInByTime(UserSignIn in); 
}
