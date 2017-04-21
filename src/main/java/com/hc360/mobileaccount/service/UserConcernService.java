package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserConcern;

@Service("userConcernService")
public interface UserConcernService{
	public void insert(UserConcern con);
	public void delete(UserConcern con);
	public List<UserConcern> getUserConcernList(UserConcern con);
	public List<UserConcern> getFriendList(UserConcern con);
	
}
