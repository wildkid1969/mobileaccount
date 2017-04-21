package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.UserConcernObject;

@Service("userConcernObjectService")
public interface UserConcernObjectService{
	public void insert(UserConcernObject obj);
	public void delete(UserConcernObject obj);
	public List<UserConcernObject> getUserConcernObjectList(UserConcernObject obj);
	public int getUserConcernObjectCount(UserConcernObject obj);
	
}
