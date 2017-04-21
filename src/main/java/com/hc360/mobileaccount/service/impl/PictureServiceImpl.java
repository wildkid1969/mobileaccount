package com.hc360.mobileaccount.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.PictureMapper;
import com.hc360.mobileaccount.service.PictureService;

@Service("pictureService")
public class PictureServiceImpl implements PictureService{
	@Resource
	private PictureMapper pictureMapper;
	
}
