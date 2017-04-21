package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.Video;

@Service("chapterResourseService")
public interface ChapterResourseService{
	public Video getChapterVideo(String chapterid);
	public List<Audio> getChapterAudioList(String chapterid);
	public List<Picture> getChapterPictureList(String chapterid);
	
}
