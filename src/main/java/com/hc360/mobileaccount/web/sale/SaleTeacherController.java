package com.hc360.mobileaccount.web.sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.Fans;
import com.hc360.mobileaccount.po.FansGroup;
import com.hc360.mobileaccount.po.PhotoAlbum;
import com.hc360.mobileaccount.po.PhotoAlbumGroup;
import com.hc360.mobileaccount.po.ReturnValue;
import com.hc360.mobileaccount.po.Teacher;
import com.hc360.mobileaccount.po.UserComment;
import com.hc360.mobileaccount.po.UserNcourse;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserNcourseService;
import com.hc360.mobileaccount.service.UserNreplyService;
import com.hc360.mobileaccount.service.UserReplyService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;
import com.hc360.mobileaccount.utils.RegexStringUtils;

@Controller
@RequestMapping("saleTeacher")
public class SaleTeacherController {
	@Resource
	private SaleTeacherService saleTeacherService;
	@Resource
	private UserReplyService userReplyService;
	@Resource
	private UserNreplyService userNreplyService;
	@Resource
	private UserNcourseService userNcourseService;
	@Resource
	private AccountService accountService;
	/*
	 * 相册请求最少条数
	 */
	public static final int ALNUM_LIMIT_SIZE = 50;
	/*
	 * 分页条数
	 */
	public static final int PAGE_SIZE = 10;

	/*
	 * 取得讲师基本信息（小头像、名称、默认已认证、banner图）、评论数、粉丝数
	 */
	@RequestMapping("getTeacherInfo")
	@ResponseBody
	public String getTeacherInfo(String phone){
		ReturnValue rv = new ReturnValue();
		phone = RC4.decry_RC4(phone);
		if(phone == null || !RegexStringUtils.isValideMobile(phone)){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Teacher teacher = saleTeacherService.getTeacherInfoByPhone(phone);
		if(teacher == null || teacher.getUser().getAccountid() == 0){
			rv.setCode(404);
			rv.setLevel("warn");
			rv.setMsg("没有数据");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		teacher.setComments(saleTeacherService.getCommentCount(teacher.getUserid()));
		teacher.setFans(saleTeacherService.getFansCount(teacher.getUserid()));
		rv.setData(teacher);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 取得讲师的课程列表（图片、标题、讲师名称、讲师标签） 
	 */
	@RequestMapping("getCourseList")
	@ResponseBody
	public String getCourseList(Long userID){
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		List<Course> courses = saleTeacherService.getCourseList(userID);
		if(courses.size() == 0){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setData(new ArrayList<Course>());
			return MobileAccountUtils.getGson().toJson(rv);
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setData(courses);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 根据课程ID取得讲师的一个课程（图片、标题、讲师名称、讲师标签） 
	 */
	@RequestMapping("getCourseById")
	@ResponseBody
	public String getCourseById(Long courseID){
		ReturnValue rv = new ReturnValue();
		if(courseID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Course course = saleTeacherService.getCourseById(courseID);
		if(course == null){
			rv.setCode(404);
			rv.setLevel("warn");
			rv.setMsg("没有数据");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setData(course);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 上传课程接口（课程banner图、标题、等级、标签）
	 */
	@RequestMapping("addCourse")
	@ResponseBody
	public String addCourse(Course course){
		ReturnValue rv = new ReturnValue();
		if(course == null || course.getTeacherId() == 0 || StringUtils.isEmpty(course.getName()) || StringUtils.isEmpty(course.getCoverUrl())){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 添加课程
		saleTeacherService.addCourse(course);
		// 添加课程和讲师对应关系
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", course.getTeacherId());
		map.put("courseID", course.getId());
		saleTeacherService.addTeacherCourse(map);
		// 添加课程等级
//		map.put("gradeId", course.getGradeId());
//		saleTeacherService.addCourseGrade(map);
		
		//添加到用户的订阅表里
		//查询订阅老师的用户id
		List<Long> userids = saleTeacherService.getSubUserIds(course.getTeacherId());
		if(!userids.isEmpty()){
			for(Long uid:userids){
				UserNcourse unc = new UserNcourse();
				unc.setUserid(uid);
				unc.setCourseid(course.getId());
				userNcourseService.insert(unc);
			}
		}
		

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 编辑课程接口（课程banner图、标题、描述、等级、标签）
	 */
	@RequestMapping("editCourse")
	@ResponseBody
	public String editCourse(Course course){
		ReturnValue rv = new ReturnValue();
		if(course == null || course.getId() == 0 || StringUtils.isEmpty(course.getName()) || StringUtils.isEmpty(course.getCoverUrl())){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 更新课程等级
//		saleTeacherService.editCourseGrade(course);
		// 更新课程
		saleTeacherService.editCourse(course);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 删除课程接口（删除课件、删除章节、删除课程）
	 */
	@RequestMapping("delCourse")
	@ResponseBody
	public String delCourse(int courseID){
		ReturnValue rv = new ReturnValue();
		if(courseID == 0){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 删除课程章节
		saleTeacherService.delCourseChapter(courseID);
		// 删除课程
		saleTeacherService.delCourse(courseID);
		
		//删除所有用户订阅的课程
		UserNcourse unc = new UserNcourse();
		unc.setCourseid(Long.valueOf(courseID));
		userNcourseService.delete(unc);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 取得一个课程下所有章节的列表（章节banner图、标题、参与人头像和总人数）UserCourseController.getChapterList
	 */

	/*
	 * 取得所有标签 SaleUserController.getAllLabels
	 */

	/*
	 * 取得一个章节信息(章节名、课程ID、标签、封面、课件)
	 */
	@RequestMapping("getCourseChapter")
	@ResponseBody
	public String getCourseChapter(Long chapterID){
		ReturnValue rv = new ReturnValue();
		if(chapterID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		CourseChapter courseChapter = saleTeacherService.getCourseChapter(chapterID);
		if(courseChapter == null){
			rv.setCode(404);
			rv.setLevel("warn");
			rv.setMsg("没有数据");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		courseChapter.setVideo(saleTeacherService.getChapterVideo(chapterID));
		courseChapter.setAudioList(saleTeacherService.getChapterAudio(chapterID));
		courseChapter.setPictureList(saleTeacherService.getChapterPicture(chapterID));

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setData(courseChapter);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 上传章节接口（章节banner图、标题、标签、关联的课程、课件【一个视频或者多个音频】）
	 */
	@RequestMapping("addCourseChapter")
	@ResponseBody
	public String addCourseChapter(String courseChapterJson){
		ReturnValue rv = new ReturnValue();
		CourseChapter courseChapter = MobileAccountUtils.getGson().fromJson(courseChapterJson, CourseChapter.class);
		if(courseChapter == null || StringUtils.isEmpty(courseChapter.getName()) || StringUtils.isEmpty(courseChapter.getCourseid()) || StringUtils.isEmpty(courseChapter.getCoverUrl())){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 保存课件资源（视频、音频、图片）
		if(courseChapter.getVideo() != null){
			saleTeacherService.addVideo(courseChapter.getVideo());
		}

		if(courseChapter.getAudioList() != null){
			for(int i=0; i<courseChapter.getAudioList().size(); i++){
				courseChapter.getAudioList().get(i).setUrl(RC4.decry_RC4(courseChapter.getAudioList().get(i).getUrl()));
				saleTeacherService.addAudio(courseChapter.getAudioList().get(i));
			}
		}

		if(courseChapter.getPictureList() != null){
			for(int i=0; i<courseChapter.getPictureList().size(); i++){
				saleTeacherService.addPicture(courseChapter.getPictureList().get(i));
			}
		}
		// 保存课程章节
		saleTeacherService.addCourseChapter(courseChapter);
		// 保存课件
		if(courseChapter.getVideo() != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getVideo().getId());
			map.put("type", 1);
			saleTeacherService.addResourse(map);
		}
		for(int i=0; courseChapter.getAudioList()!=null && i<courseChapter.getAudioList().size(); i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getAudioList().get(i).getId());
			map.put("type", 2);
			saleTeacherService.addResourse(map);
		}
		for(int i=0; courseChapter.getPictureList()!=null && i<courseChapter.getPictureList().size(); i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getPictureList().get(i).getId());
			map.put("type", 3);
			saleTeacherService.addResourse(map);
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 编辑章节接口（章节banner图、标题、标签、关联的课程、课件【一个视频或者多个音频和多个图片】）
	 */
	@RequestMapping("editCourseChapter")
	@ResponseBody
	public String editCourseChapter(String courseChapterJson){
		ReturnValue rv = new ReturnValue();
		CourseChapter courseChapter = MobileAccountUtils.getGson().fromJson(courseChapterJson, CourseChapter.class);
		if(courseChapter == null || courseChapter.getId() == 0 || StringUtils.isEmpty(courseChapter.getName()) || StringUtils.isEmpty(courseChapter.getCourseid()) || StringUtils.isEmpty(courseChapter.getCoverUrl())){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 删除课件
		saleTeacherService.delChapterVideo(courseChapter.getId());
		saleTeacherService.delChapterAudio(courseChapter.getId());
		saleTeacherService.delChapterPicture(courseChapter.getId());
		saleTeacherService.delResourse(courseChapter.getId());
		// 保存课件资源（视频、音频、图片）
		if(courseChapter.getVideo() != null){
			saleTeacherService.addVideo(courseChapter.getVideo());
		}

		if(courseChapter.getAudioList() != null){
			String audioUrl = "";
			for(int i=0; i<courseChapter.getAudioList().size(); i++){
				audioUrl = courseChapter.getAudioList().get(i).getUrl();
				if(!audioUrl.contains("mobileaccount")){
					audioUrl = RC4.decry_RC4(audioUrl);
				}
				courseChapter.getAudioList().get(i).setUrl(audioUrl);
				saleTeacherService.addAudio(courseChapter.getAudioList().get(i));
			}
		}

		if(courseChapter.getPictureList() != null){
			for(int i=0; i<courseChapter.getPictureList().size(); i++){
				saleTeacherService.addPicture(courseChapter.getPictureList().get(i));
			}
		}
		// 编辑课程章节
		saleTeacherService.editCourseChapter(courseChapter);
		// 保存课件
		if(courseChapter.getVideo() != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getVideo().getId());
			map.put("type", 1);
			saleTeacherService.addResourse(map);
		}
		for(int i=0; courseChapter.getAudioList()!=null && i<courseChapter.getAudioList().size(); i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getAudioList().get(i).getId());
			map.put("type", 2);
			saleTeacherService.addResourse(map);
		}
		for(int i=0; courseChapter.getPictureList()!=null && i<courseChapter.getPictureList().size(); i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", courseChapter.getId());
			map.put("resourceid", courseChapter.getPictureList().get(i).getId());
			map.put("type", 3);
			saleTeacherService.addResourse(map);
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 删除章节接口（删除课件、删除章节）
	 */
	@RequestMapping("delChapter")
	@ResponseBody
	public String delChapter(int chapterID){
		ReturnValue rv = new ReturnValue();
		if(chapterID == 0){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		// 删除章节
		saleTeacherService.delChapter(chapterID);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 取得相册列表 
	 */
	@RequestMapping("getAlbums")
	@ResponseBody
	public String getAlbums(String endTime, Long userID) throws ParseException{
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Date dd = null;
		try{
			dd = new Date(Long.parseLong(endTime));
		}catch(Exception e){
			dd = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(dd);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("endDate", endDate);
		map.put("userID", userID);
		map.put("start", ALNUM_LIMIT_SIZE-1);
		String startDate = saleTeacherService.getAlbumsStartDate(map);

		int totalNum=saleTeacherService.getAlbumsCount(userID);

		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userID", userID);
		List<PhotoAlbum> photoAlbums = saleTeacherService.getAlbums(map);
		if(photoAlbums.size() == 0){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setTotalNum(0);
			rv.setStartDate(null);
			rv.setData(new ArrayList<PhotoAlbumGroup>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		List<PhotoAlbumGroup> photoAlbumGroupList = new ArrayList<PhotoAlbumGroup>();
		for(int i=0; i<photoAlbums.size(); i++){
			if(photoAlbumGroupList.size() == 0 || !photoAlbumGroupList.get(photoAlbumGroupList.size()-1).getCreatetime().equals(photoAlbums.get(i).getCreatetime())){
				PhotoAlbumGroup pag = new PhotoAlbumGroup();
				pag.setCreatetime(photoAlbums.get(i).getCreatetime());
				pag.setPhotoAlbums(new ArrayList<PhotoAlbum>());
				photoAlbumGroupList.add(pag);
			}
			photoAlbumGroupList.get(photoAlbumGroupList.size()-1).getPhotoAlbums().add(photoAlbums.get(i));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setTotalNum(totalNum);
		if(startDate == null){
			rv.setStartDate(null);
		}else{
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(sdf.parse(startDate));
			calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);
			rv.setStartDate(calendar.getTimeInMillis());
		}
		rv.setData(photoAlbumGroupList);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 批量删除相册
	 */
	@RequestMapping("delAlbums")
	@ResponseBody
	@Transactional(rollbackFor = {Exception.class})
	public String delAlbums(String idsJson){
		ReturnValue rv = new ReturnValue();
		List<Long> ids =  MobileAccountUtils.getGson().fromJson(idsJson, new TypeToken<ArrayList<Long>>() {}.getType());
		if(ids == null || ids.size() == 0){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		for(int i=0; i<ids.size(); i++){
			saleTeacherService.delAlbums(ids.get(i));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 上传相册
	 */
	@RequestMapping("addAlbums")
	@ResponseBody
	public String addAlbums(String photoAlbumsJson){
		ReturnValue rv = new ReturnValue();
		List<PhotoAlbum> photoAlbums =  MobileAccountUtils.getGson().fromJson(photoAlbumsJson, new TypeToken<ArrayList<PhotoAlbum>>() {}.getType());
		if(photoAlbums == null || photoAlbums.size() == 0){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		for(int i=0; i<photoAlbums.size(); i++){
			saleTeacherService.addAlbums(photoAlbums.get(i));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 录音评测邀请列表（求助者头像、名称、公司，评测内容，音频URL）
	 */
	@RequestMapping("getUserTalks")
	@ResponseBody
	public String getUserTalks(String page, Long userID,Integer isReply){
		ReturnValue rv = new ReturnValue();
		if(isReply == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		if(!MobileAccountUtils.isNumber(page) || Integer.parseInt(page) < 1){
			page = "1";
		}
		int pageNum = Integer.parseInt(page);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(userID !=null){
			map.put("userid", userID);
		}
		map.put("isReply", isReply);
		int total=saleTeacherService.getUserTalksCount(map);
		int totalPage=0;
		if((total%PAGE_SIZE)>0){
			totalPage=total/PAGE_SIZE + 1;
		}else{
			totalPage=total/PAGE_SIZE;
		}

		if(total <= 0 || pageNum > totalPage){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setPage(0);
			rv.setTotalPage(0);
			rv.setTotalNum(0);
			rv.setData(new ArrayList<UserQuestion>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		
		map.put("start", (pageNum-1)*PAGE_SIZE);
		map.put("size", PAGE_SIZE);
		List<UserTalk> userTalks = saleTeacherService.getUserTalks(map);
		
		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setPage(pageNum);
		rv.setTotalPage(totalPage);
		rv.setTotalNum(total);
		rv.setData(userTalks);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 已经评测的录音列表
	 */
	@RequestMapping("getRepliedTalks")
	@ResponseBody
	public String getRepliedTalks(String page, Long userID){
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		if(!MobileAccountUtils.isNumber(page) || Integer.parseInt(page) < 1){
			page = "1";
		}
		int pageNum = Integer.parseInt(page);
		int total=saleTeacherService.getRepliedTalksCount(userID);
		int totalPage=0;
		if((total%PAGE_SIZE)>0){
			totalPage=total/PAGE_SIZE + 1;
		}else{
			totalPage=total/PAGE_SIZE;
		}

		if(total <= 0 || pageNum > totalPage){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setPage(0);
			rv.setTotalPage(0);
			rv.setTotalNum(0);
			rv.setData(new ArrayList<UserQuestion>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (pageNum-1)*PAGE_SIZE);
		map.put("size", PAGE_SIZE);
		map.put("userID", userID);
		List<UserTalk> userTalks = saleTeacherService.getRepliedTalks(map);

		map.put("replyType", 1);
		map.put("type", 1);
		for(int i=0; i<userTalks.size(); i++){
			map.put("objectID", userTalks.get(i).getId());
			userTalks.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
			userTalks.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setPage(pageNum);
		rv.setTotalPage(totalPage);
		rv.setTotalNum(total);
		rv.setData(userTalks);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 录音评测接口
	 */
	@RequestMapping("doInvitedTalks")
	@ResponseBody
	public String doInvitedTalks(Long userID, Long talkID, UserReply userReply){
		ReturnValue rv = new ReturnValue();
		if(userID == null || talkID == null || (StringUtils.isEmpty(userReply.getContent())&&StringUtils.isEmpty(userReply.getAudioUrl()))){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		
		if(!StringUtils.isEmpty(userReply.getAudioUrl())){
			userReply.setAudioUrl(RC4.decry_RC4(userReply.getAudioUrl()));
		}
		
		userReplyService.insert(userReply);
		
		
		UserNreply userNreply = new UserNreply();
		userNreply.setObjectid(talkID);
		userNreply.setUserid(userID);
		userNreply.setReplyid(userReply.getId());
		userNreply.setReplyType(1);
		userNreply.setUserType(1);
		userNreplyService.insert(userNreply);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", userID);
		map.put("invitedID", talkID);
		saleTeacherService.editReplyInvitedState(map);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 取得用户提问列表（提问者头像、名称、公司，内容）
	 */
	@RequestMapping("getInvitedQuestions")
	@ResponseBody
	public String getInvitedQuestions(String page, Long userID){
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		if(!MobileAccountUtils.isNumber(page) || Integer.parseInt(page) < 1){
			page = "1";
		}
		int pageNum = Integer.parseInt(page);
		int total=saleTeacherService.getInvitedQuestionsCount(userID);
		int totalPage=0;
		if((total%PAGE_SIZE)>0){
			totalPage=total/PAGE_SIZE + 1;
		}else{
			totalPage=total/PAGE_SIZE;
		}

		if(total <= 0 || pageNum > totalPage){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setPage(0);
			rv.setTotalPage(0);
			rv.setTotalNum(0);
			rv.setData(new ArrayList<UserQuestion>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (pageNum-1)*PAGE_SIZE);
		map.put("size", PAGE_SIZE);
		map.put("userID", userID);
		List<UserQuestion> userQuestions = saleTeacherService.getInvitedQuestions(map);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setPage(pageNum);
		rv.setTotalPage(totalPage);
		rv.setTotalNum(total);
		rv.setData(userQuestions);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 取得已解答的问题列表
	 */
	@RequestMapping("getRepliedQuestions")
	@ResponseBody
	public String getRepliedQuestions(String page, Long userID){
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		if(!MobileAccountUtils.isNumber(page) || Integer.parseInt(page) < 1){
			page = "1";
		}
		int pageNum = Integer.parseInt(page);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userID);
		map.put("isReply", 1);
		int total=saleTeacherService.getUserQuestionsCount(map);
		
		int totalPage=0;
		if((total%PAGE_SIZE)>0){
			totalPage=total/PAGE_SIZE + 1;
		}else{
			totalPage=total/PAGE_SIZE;
		}

		if(total <= 0 || pageNum > totalPage){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setPage(0);
			rv.setTotalPage(0);
			rv.setTotalNum(0);
			rv.setData(new ArrayList<UserQuestion>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		map.put("start", (pageNum-1)*PAGE_SIZE);
		map.put("size", PAGE_SIZE);
		List<UserQuestion> userQuestions = saleTeacherService.getUserQuestions(map);

		map.put("replyType", 4);
		map.put("type", 4);
		for(int i=0; i<userQuestions.size(); i++){
			map.put("objectID", userQuestions.get(i).getId());
			userQuestions.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
			userQuestions.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setPage(pageNum);
		rv.setTotalPage(totalPage);
		rv.setTotalNum(total);
		rv.setData(userQuestions);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 用户提问解答接口（音频、内容）
	 */
	@RequestMapping("doInvitedQuestions")
	@ResponseBody
	public String doInvitedQuestions(Long userID, Long questionID, UserReply userReply){
		ReturnValue rv = new ReturnValue();
		if(userID == null || questionID == null ||(StringUtils.isEmpty(userReply.getAudioUrl()) && StringUtils.isEmpty(userReply.getContent()))){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}
		
		if(!StringUtils.isEmpty(userReply.getAudioUrl())){
			userReply.setAudioUrl(RC4.decry_RC4(userReply.getAudioUrl()));
		}
		
		userReplyService.insert(userReply);
		
		Account user = accountService.getAccountInfoById(String.valueOf(userID));
		UserNreply userNreply = new UserNreply();
		userNreply.setObjectid(questionID);
		userNreply.setUserid(userID);
		userNreply.setReplyid(userReply.getId());
		userNreply.setReplyType(4);
		userNreply.setUserType(user.getUserType());
		userNreplyService.insert(userNreply);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", userID);
		map.put("invitedID", questionID);
		saleTeacherService.editReplyInvitedState(map);

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 粉丝接口（总数、头像、姓名、时间）
	 */
	@RequestMapping("getFans")
	@ResponseBody
	public String getFans(String endTime, Long userID) throws ParseException{
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Date dd = null;
		try{
			dd = new Date(Long.parseLong(endTime));
		}catch(Exception e){
			dd = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(dd);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("endDate", endDate);
		map.put("userID", userID);
		map.put("start", ALNUM_LIMIT_SIZE-1);
		String startDate = saleTeacherService.getFansStartDate(map);

		int totalNum=saleTeacherService.getFansCount(userID);

		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userID", userID);
		List<Fans> fans = saleTeacherService.getFans(map);
		if(fans.size() == 0){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setTotalNum(0);
			rv.setStartDate(null);
			rv.setData(new ArrayList<FansGroup>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		List<FansGroup> fansGroupList = new ArrayList<FansGroup>();
		for(int i=0; i<fans.size(); i++){
			if(fansGroupList.size() == 0 || !fansGroupList.get(fansGroupList.size()-1).getSubtime().equals(fans.get(i).getSubtime())){
				FansGroup fg = new FansGroup();
				fg.setSubtime(fans.get(i).getSubtime());
				fg.setFans(new ArrayList<Fans>());
				fansGroupList.add(fg);
			}
			fansGroupList.get(fansGroupList.size()-1).getFans().add(fans.get(i));
		}

		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setTotalNum(totalNum);
		if(startDate == null){
			rv.setStartDate(null);
		}else{
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(sdf.parse(startDate));
			calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);
			rv.setStartDate(calendar.getTimeInMillis());
		}
		rv.setData(fansGroupList);

		return MobileAccountUtils.getGson().toJson(rv);
	}

	/*
	 * 讲师所有课程章节评论列表接口
	 */
	@RequestMapping("getCourseComments")
	@ResponseBody
	public String getCourseComments(String page, Long userID){
		ReturnValue rv = new ReturnValue();
		if(userID == null){
			rv.setCode(300);
			rv.setLevel("error");
			rv.setMsg("参数错误");
			return MobileAccountUtils.getGson().toJson(rv);
		}

		if(!MobileAccountUtils.isNumber(page) || Integer.parseInt(page) < 1){
			page = "1";
		}
		int pageNum = Integer.parseInt(page);
		int total=saleTeacherService.getCourseCommentsCount(userID);
		int totalPage=0;
		if((total%PAGE_SIZE)>0){
			totalPage=total/PAGE_SIZE + 1;
		}else{
			totalPage=total/PAGE_SIZE;
		}

		if(total <= 0 || pageNum > totalPage){
			rv.setCode(200);
			rv.setLevel("info");
			rv.setMsg("没有数据");
			rv.setPage(0);
			rv.setTotalPage(0);
			rv.setTotalNum(0);
			rv.setData(new ArrayList<UserQuestion>());			
			return MobileAccountUtils.getGson().toJson(rv);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (pageNum-1)*PAGE_SIZE);
		map.put("size", PAGE_SIZE);
		map.put("userID", userID);
		List<UserComment> userComments = saleTeacherService.getCourseComments(map);

		map.put("replyType", 3);
		map.put("type", 3);
		for(int i=0; i<userComments.size(); i++){
			map.put("objectID", userComments.get(i).getId());
			userComments.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
			userComments.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
		}
		
		rv.setCode(200);
		rv.setLevel("info");
		rv.setMsg("成功");
		rv.setPage(pageNum);
		rv.setTotalPage(totalPage);
		rv.setTotalNum(total);
		rv.setData(userComments);

		return MobileAccountUtils.getGson().toJson(rv);
	}
}