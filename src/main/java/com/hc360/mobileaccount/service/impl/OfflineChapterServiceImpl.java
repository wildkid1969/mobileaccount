package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.OfflineChapterMapper;
import com.hc360.mobileaccount.po.OfflineChapter;
import com.hc360.mobileaccount.service.OfflineChapterService;

@Service("offlineChapterService")
public class OfflineChapterServiceImpl implements OfflineChapterService{
	@Resource
	private OfflineChapterMapper offlineChapterMapper;

	@Override
	public List<OfflineChapter> getOfflineChapterList(OfflineChapter c) {
		return offlineChapterMapper.getOfflineChapterList(c);
	}

	@Override
	public OfflineChapter getById(Long id) {
		return offlineChapterMapper.getById(id);
	}
	
}
