package com.hc360.mobileaccount.service;

import java.util.List;
import java.util.Map;

import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.Fans;
import com.hc360.mobileaccount.po.PhotoAlbum;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.Teacher;
import com.hc360.mobileaccount.po.TeacherReplyInvited;
import com.hc360.mobileaccount.po.UserComment;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserPraise;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.po.UserSubscribeTeacher;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.po.Video;

public interface SaleTeacherService {
	public Teacher getById(Long id);
	public Teacher getByUserId(Long userid);
	public List<Long> getTeacherIdsByLabelId(Long labelId);
	public List<Long> getAllTeacherIds();
	public void batchInsertTeacherReplyInvited(List<TeacherReplyInvited> inviteList);
	public void insertIntoUserSubscribeTeacher(UserSubscribeTeacher t);
	public void unsubscribeTeacher(UserSubscribeTeacher t);
	public List<Long> getTeacherCourseIds(Long userid);
	public int isSubscribeTeacher(UserSubscribeTeacher t);
	public List<Long> getSubUserIds(Long teacherid);
	public void insertTeacher(Teacher t);
	public void updateTeacher(Teacher t);
	public int getInvitedRepliesCount(Map<String,Object> map);
	public List<TeacherReplyInvited> getTeacherReplyInvitedList(TeacherReplyInvited in);
	public List<TeacherReplyInvited> getInvitedTeacherList(TeacherReplyInvited in);
	
	public Teacher getTeacherInfoByPhone(String phone);
	public int getCommentCount(long userID);
	public int getFansCount(long userID);
	public String getFansStartDate(Map<String,Object> map);
	public List<Course> getCourseList(long userID);
	public Course getCourseById(long courseID);
	public int editCourse(Course course);
	public int editCourseGrade(Course course);
	public int delCourse(int courseID);
	public int delCourseChapter(int courseID);
	public int editCourseChapter(CourseChapter courseChapter);
	public int addAudio(Audio audio);
	public int addResourse(Map<String,Object> map);
	public int delAudio(long id);
	public int delChapter(long chapterID);
	public int delChapterVideo(long chapterID);
	public int delChapterAudio(long chapterID);
	public int delChapterPicture(long chapterID);
	public int delResourse(long chapterid);
	public int addCourse(Course course);
	public int addTeacherCourse(Map<String,Object> map);
	public int addCourseGrade(Map<String,Object> map);
	public int addCourseChapter(CourseChapter courseChapter);
	public List<PhotoAlbum> getAlbums(Map<String,Object> map);
	public String getAlbumsStartDate(Map<String,Object> map);
	public int getAlbumsCount(Long userID);
	public int delAlbums(long id);
	public List<Fans> getFans(Map<String,Object> map);
	public List<UserQuestion> getInvitedQuestions(Map<String,Object> map);
	public int getInvitedQuestionsCount(long userID);
	public int editReplyInvitedState(Map<String,Object> map);
	public List<UserQuestion> getUserQuestions(Map<String,Object> map);
	public int getUserQuestionsCount(Map<String,Object> map);
	public List<UserTalk> getRepliedTalks(Map<String,Object> map);
	public int  getRepliedTalksCount(long userID);
	
	public List<UserNreply> getObjectSimpleReplys(Map<String,Object> map);
	public List<UserNreply> getObjectReplys(Map<String,Object> map);
	public List<UserNreply> getObjectOrderReplys(Map<String,Object> map);
	public List<UserNreply> getObjectSonReplys(Map<String,Object> map);
	
	public List<UserPraise> getUserPraises(Map<String,Object> map);
	public List<UserTalk> getUserTalks(Map<String,Object> map);
	public int getUserTalksCount(Map<String,Object> map);
	public int addAlbums(PhotoAlbum photoAlbum);
	public int addVideo(Video video);
	public int addPicture(Picture picture);
	public CourseChapter getCourseChapter(long chapterID);
	public Video getChapterVideo(long chapterID);
	public List<Audio> getChapterAudio(long chapterID);
	public List<Picture> getChapterPicture(long chapterID);
	public List<UserComment> getCourseComments(Map<String,Object> map);
	public int getCourseCommentsCount(long userID);
}