package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.Video;

public interface ChapterResourseMapper{
	public Video getChapterVideo(String chapterid);
	public List<Audio> getChapterAudioList(String chapterid);
	public List<Picture> getChapterPictureList(String chapterid);

}
