package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.OfflineChapter;

@Service("offlineChapterService")
public interface OfflineChapterService{
	public List<OfflineChapter> getOfflineChapterList(OfflineChapter c);
	public OfflineChapter getById(Long id);
}
