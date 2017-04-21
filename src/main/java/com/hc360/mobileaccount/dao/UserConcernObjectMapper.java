package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.UserConcernObject;

public interface UserConcernObjectMapper{
	public void insert(UserConcernObject obj);
	public void delete(UserConcernObject obj);
	public List<UserConcernObject> getUserConcernObjectList(UserConcernObject obj);
	public int getUserConcernObjectCount(UserConcernObject obj);

}
