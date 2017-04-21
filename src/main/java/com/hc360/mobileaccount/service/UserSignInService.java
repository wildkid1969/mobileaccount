package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserSignIn;

@Service("userSignInService")
public interface UserSignInService{
	public void insert(UserSignIn in); 
	public List<UserSignIn> getUserSignInList(UserSignIn in); 
	public UserSignIn getUserSignInByTime(UserSignIn in); 
}
