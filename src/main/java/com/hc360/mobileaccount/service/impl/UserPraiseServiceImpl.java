package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.UserPraiseMapper;
import com.hc360.mobileaccount.po.UserPraise;
import com.hc360.mobileaccount.service.UserPraiseService;

@Service("userPraiseService")
public class UserPraiseServiceImpl implements UserPraiseService{
	@Resource
	private UserPraiseMapper userPraiseMapper;

	@Override
	public List<UserPraise> getUserPraisesByParam(UserPraise p) {
		return userPraiseMapper.getUserPraisesByParam(p);
	}

	@Override
	public void insert(UserPraise p) {
		userPraiseMapper.insert(p);
	}

	@Override
	public void deleteById(Long id) {
		userPraiseMapper.deleteById(id);
	}
	
}
