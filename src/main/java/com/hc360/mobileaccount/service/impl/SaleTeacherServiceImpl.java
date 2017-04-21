package com.hc360.mobileaccount.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.AccountMapper;
import com.hc360.mobileaccount.dao.SaleTeacherMapper;
import com.hc360.mobileaccount.po.Account;
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
import com.hc360.mobileaccount.service.SaleTeacherService;

@Service
public class SaleTeacherServiceImpl implements SaleTeacherService {
	@Resource
	SaleTeacherMapper saleTeacherMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Teacher getTeacherInfoByPhone(String phone){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apptype", "114");
		map.put("phone", phone);
		String userid = accountMapper.getAccountIdByPhone(map);
		
		Teacher t = null;
		if(!StringUtils.isEmpty(userid)){
			t= saleTeacherMapper.getByUserId(Long.valueOf(userid));
		}
		return t;
	}

	@Override
	public int getCommentCount(long userID){
		return saleTeacherMapper.getCommentCount(userID);
	}
	
	@Override
	public int getFansCount(long userID){
		return saleTeacherMapper.getFansCount(userID);
	}
	
	@Override
	public String getFansStartDate(Map<String,Object> map){
		return saleTeacherMapper.getFansStartDate(map);
	}
	
	@Override
	public List<Course> getCourseList(long userID){
		return saleTeacherMapper.getCourseList(userID);
	}
	
	@Override
	public Course getCourseById(long courseID){
		return saleTeacherMapper.getCourseById(courseID);
	}
	
	@Override
	public int editCourse(Course course){
		return saleTeacherMapper.editCourse(course);
	}
	
	@Override
	public int editCourseGrade(Course course){
		return saleTeacherMapper.editCourseGrade(course);
	}
	
	@Override
	public int delCourse(int courseID){
		return saleTeacherMapper.delCourse(courseID);
	}
	
	@Override
	public int delCourseChapter(int courseID){
		return saleTeacherMapper.delCourseChapter(courseID);
	}
	
	@Override
	public int editCourseChapter(CourseChapter courseChapter){
		return saleTeacherMapper.editCourseChapter(courseChapter);
	}
	
	@Override
	public int addAudio(Audio audio){
		return saleTeacherMapper.addAudio(audio);
	}
	
	@Override
	public int addResourse(Map<String,Object> map){
		return saleTeacherMapper.addResourse(map);
	}

	@Override
	public int delAudio(long id){
		return saleTeacherMapper.delAudio(id);
	}

	@Override
	public int delChapter(long chapterID){
		return saleTeacherMapper.delChapter(chapterID);
	}

	@Override
	public int delChapterVideo(long chapterID){
		return saleTeacherMapper.delChapterVideo(chapterID);
	}
	
	@Override
	public int delChapterAudio(long chapterID){
		return saleTeacherMapper.delChapterAudio(chapterID);
	}
	
	@Override
	public int delChapterPicture(long chapterID){
		return saleTeacherMapper.delChapterPicture(chapterID);
	}

	@Override
	public int delResourse(long chapterid){
		return saleTeacherMapper.delResourse(chapterid);
	}

	@Override
	public int addCourse(Course course){
		return saleTeacherMapper.addCourse(course);
	}
	
	@Override
	public int addTeacherCourse(Map<String,Object> map){
		return saleTeacherMapper.addTeacherCourse(map);
	}
	
	@Override
	public int addCourseGrade(Map<String,Object> map){
		return saleTeacherMapper.addCourseGrade(map);
	}
	
	@Override
	public int addCourseChapter(CourseChapter courseChapter){
		return saleTeacherMapper.addCourseChapter(courseChapter);
	}
	
	@Override
	public List<PhotoAlbum> getAlbums(Map<String,Object> map){
		return saleTeacherMapper.getAlbums(map);
	}
	
	@Override
	public String getAlbumsStartDate(Map<String,Object> map){
		return saleTeacherMapper.getAlbumsStartDate(map);
	}

	@Override
	public int getAlbumsCount(Long userID){
		return saleTeacherMapper.getAlbumsCount(userID);
	}

	@Override
	public int delAlbums(long id){
		return saleTeacherMapper.delAlbums(id);
	}
	
	@Override
	public List<Fans> getFans(Map<String,Object> map){
		return saleTeacherMapper.getFans(map);
	}
	
	@Override
	public List<UserQuestion> getInvitedQuestions(Map<String,Object> map){
		return saleTeacherMapper.getInvitedQuestions(map);
	}

	@Override
	public int getInvitedQuestionsCount(long userID){
		return saleTeacherMapper.getInvitedQuestionsCount(userID);
	}

	@Override
	public int editReplyInvitedState(Map<String,Object> map){
		return saleTeacherMapper.editReplyInvitedState(map);
	}
	
	@Override
	public List<UserQuestion> getUserQuestions(Map<String,Object> map){
		return saleTeacherMapper.getUserQuestions(map);
	}
	
	@Override
	public int getUserQuestionsCount(Map<String,Object> map){
		return saleTeacherMapper.getUserQuestionsCount(map);
	}

	@Override
	public List<UserTalk> getRepliedTalks(Map<String,Object> map){
		return saleTeacherMapper.getRepliedTalks(map);
	}
	
	@Override
	public int  getRepliedTalksCount(long userID){
		return saleTeacherMapper.getRepliedTalksCount(userID);
	}

	@Override
	public List<UserNreply> getObjectSimpleReplys(Map<String,Object> map){
		return saleTeacherMapper.getObjectSimpleReplys(map);
	}
	@Override
	public List<UserNreply> getObjectReplys(Map<String,Object> map){
		return saleTeacherMapper.getObjectReplys(map);
	}
	@Override
	public List<UserNreply> getObjectOrderReplys(Map<String,Object> map){
		return saleTeacherMapper.getObjectOrderReplys(map);
	}
	@Override
	public List<UserNreply> getObjectSonReplys(Map<String,Object> map){
		return saleTeacherMapper.getObjectSonReplys(map);
	}

	@Override
	public List<UserPraise> getUserPraises(Map<String,Object> map){
		return saleTeacherMapper.getUserPraises(map);
	}
	
	@Override
	public List<UserTalk> getUserTalks(Map<String,Object> map){
		return saleTeacherMapper.getUserTalks(map);
	}
	
	@Override
	public int getUserTalksCount(Map<String,Object> map){
		return saleTeacherMapper.getUserTalksCount(map);
	}
	
	@Override
	public int addAlbums(PhotoAlbum photoAlbum){
		return saleTeacherMapper.addAlbums(photoAlbum);
	}
	
	@Override
	public int addVideo(Video video){
		return saleTeacherMapper.addVideo(video);
	}
	
	@Override
	public int addPicture(Picture picture){
		return saleTeacherMapper.addPicture(picture);
	}
	
	@Override
	public CourseChapter getCourseChapter(long chapterID){
		return saleTeacherMapper.getCourseChapter(chapterID);
	}
	
	@Override
	public Video getChapterVideo(long chapterID){
		return saleTeacherMapper.getChapterVideo(chapterID);
	}
	
	@Override
	public List<Audio> getChapterAudio(long chapterID){
		return saleTeacherMapper.getChapterAudio(chapterID);
	}
	
	@Override
	public List<Picture> getChapterPicture(long chapterID){
		return saleTeacherMapper.getChapterPicture(chapterID);
	}
	
	@Override
	public List<UserComment> getCourseComments(Map<String,Object> map){
		return saleTeacherMapper.getCourseComments(map);
	}
	
	@Override
	public int getCourseCommentsCount(long userID){
		return saleTeacherMapper.getCourseCommentsCount(userID);
	}

	@Override
	public Teacher getById(Long id) {
		return saleTeacherMapper.getById(id);
	}
	@Override
	public Teacher getByUserId(Long userid) {
		Teacher t = saleTeacherMapper.getByUserId(userid);
		if(t!=null){
			Account user = accountMapper.getAccountInfoById(String.valueOf(userid));
			if(user!=null){
				t.setUser(user);
			}
		}
		
		return t;
	}

	@Override
	public List<Long> getTeacherIdsByLabelId(Long labelId) {
		return saleTeacherMapper.getTeacherIdsByLabelId(labelId);
	}

	@Override
	public List<Long> getAllTeacherIds() {
		return saleTeacherMapper.getAllTeacherIds();
	}

	@Override
	public void batchInsertTeacherReplyInvited(
			List<TeacherReplyInvited> inviteList) {
		saleTeacherMapper.batchInsertTeacherReplyInvited(inviteList);
	}

	@Override
	public List<Long> getTeacherCourseIds(Long userid) {
		return saleTeacherMapper.getTeacherCourseIds(userid);
	}

	@Override
	public void insertIntoUserSubscribeTeacher(UserSubscribeTeacher t) {
		saleTeacherMapper.insertIntoUserSubscribeTeacher(t);
	}
	@Override
	public void unsubscribeTeacher(UserSubscribeTeacher t) {
		saleTeacherMapper.unsubscribeTeacher(t);
	}

	@Override
	public int isSubscribeTeacher(UserSubscribeTeacher t) {
		return saleTeacherMapper.isSubscribeTeacher(t);
	}

	@Override
	public List<Long> getSubUserIds(Long teacherid) {
		return saleTeacherMapper.getSubUserIds(teacherid);
	}

	@Override
	public void insertTeacher(Teacher t) {
		saleTeacherMapper.insertTeacher(t);
	}

	@Override
	public void updateTeacher(Teacher t) {
		saleTeacherMapper.updateTeacher(t);
	}

	@Override
	public int getInvitedRepliesCount(Map<String, Object> map) {
		return saleTeacherMapper.getInvitedRepliesCount(map);
	}

	@Override
	public List<TeacherReplyInvited> getTeacherReplyInvitedList(
			TeacherReplyInvited in) {
		return saleTeacherMapper.getTeacherReplyInvitedList(in);
	}
	@Override
	public List<TeacherReplyInvited> getInvitedTeacherList(
			TeacherReplyInvited in) {
		return saleTeacherMapper.getInvitedTeacherList(in);
	}
}