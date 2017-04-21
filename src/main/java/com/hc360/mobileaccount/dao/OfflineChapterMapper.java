package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.OfflineChapter;

public interface OfflineChapterMapper{
	public List<OfflineChapter> getOfflineChapterList(OfflineChapter c);
	public OfflineChapter getById(Long id);
}
