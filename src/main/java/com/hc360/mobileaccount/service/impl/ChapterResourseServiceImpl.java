package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.ChapterResourseMapper;
import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.Video;
import com.hc360.mobileaccount.service.ChapterResourseService;

@Service("chapterResourseService")
public class ChapterResourseServiceImpl implements ChapterResourseService{
	@Resource
	private ChapterResourseMapper chapterResourseMapper;

	@Override
	public Video getChapterVideo(String chapterid) {
		return chapterResourseMapper.getChapterVideo(chapterid);
	}

	@Override
	public List<Audio> getChapterAudioList(String chapterid) {
		return chapterResourseMapper.getChapterAudioList(chapterid);
	}

	@Override
	public List<Picture> getChapterPictureList(String chapterid) {
		return chapterResourseMapper.getChapterPictureList(chapterid);
	}
	
	
	
}
