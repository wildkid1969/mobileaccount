package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.CourseNcomment;
import com.hc360.mobileaccount.po.Label;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.po.UserStudyLog;
import com.hc360.mobileaccount.po.Video;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.AudioService;
import com.hc360.mobileaccount.service.ChapterResourseService;
import com.hc360.mobileaccount.service.CourseChapterService;
import com.hc360.mobileaccount.service.CourseNcommentService;
import com.hc360.mobileaccount.service.LabelService;
import com.hc360.mobileaccount.service.UserNreplyService;
import com.hc360.mobileaccount.service.UserReplyService;
import com.hc360.mobileaccount.service.UserStudyLogService;
import com.hc360.mobileaccount.service.VideoService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping(value="userCourse")
public class UserCourseController {

	@Resource
	private CourseChapterService courseChapterService;
	
	@Resource
	private UserStudyLogService userStudyLogService;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private ChapterResourseService chapterResourseService;
	
	@Resource
	private UserReplyService userReplyService;
	
	@Resource
	private UserNreplyService userNreplyService;
	
	@Resource
	private CourseNcommentService courseNcommentService;
	
	@Resource
	private LabelService labelService;
	
	@Resource
	private VideoService videoService;
	
	@Resource
	private AudioService audioService;
	
	
	private static final int COMMENT_PAGE_SIZE = 10;
	
	/**
	 * 获取章节列表
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="getChapterList")
	@ResponseBody
	public String getChapterList(String courseId){
		
		List<CourseChapter> chapterList = courseChapterService.getChapterListByCourseId(courseId);
		
		if(!chapterList.isEmpty()){
			List<Account> userlist = null;
			UserStudyLog log = new UserStudyLog();
			for(int i=0,s=chapterList.size();i<s;i++){
				log.setChapterid(chapterList.get(i).getId());
				userlist = userStudyLogService.getChapterUserList(log);
				chapterList.get(i).setUserlist(userlist);
			}
			
		}
		
		return MobileAccountUtils.getGson().toJson(chapterList);
	}
	
	

	/**
	 * 获取章节详情
	 * @param chapterId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="getChapterDetails")
	@ResponseBody
	public String getChapterDetails(String chapterId,String userId){
		//获取课程基本信息
		CourseChapter chap = courseChapterService.getChapterById(chapterId);
		
		if(chap==null){
			return MobileAccountUtils.getGson().toJson(new CourseChapter());
		}
		
		//根据章节类型查询章节里的资源
		if(chap.getType()==1){
			Video video = chapterResourseService.getChapterVideo(chapterId);
			if(video!=null){
				chap.setVideo(video);
			}
			
		}
		if(chap.getType()==2){
			List<Audio> audioList = chapterResourseService.getChapterAudioList(chapterId);
			if(!audioList.isEmpty() && audioList.get(0)!=null){
				chap.setAudioList(audioList);
			}
			List<Picture> pictureList = chapterResourseService.getChapterPictureList(chapterId);
			if(!pictureList.isEmpty() && pictureList.get(0)!=null){
				chap.setPictureList(pictureList);
			}
			
		}
		//用户是否点赞
		int isPraise = 0;
		//用户观看视频进度 
		Long videopoint = 0l;
		if(!StringUtils.isEmpty(userId)){
			CourseChapter cc = new CourseChapter();
			cc.setId(Long.valueOf(chapterId));
			cc.setUserid(userId);
			cc.setType(5);//点赞对象类型
			isPraise = courseChapterService.getUserIsPraise(cc);
			
			UserStudyLog log = new UserStudyLog();
			List<UserStudyLog> logList = userStudyLogService.getUserStudyLogList(log);
			if(!logList.isEmpty()){
				videopoint = logList.get(0).getVideopoint();
			}
		}
		
		chap.setIsPraise(isPraise);
		chap.setVideopoint(videopoint);
		
		
		String labels = chap.getLabelids();
		if(StringUtils.isEmpty(labels)){//标签为空时随机找两个
			labels = "";
			List<Label> labelList = labelService.getAllLabels();
			if(!labelList.isEmpty() && labelList.size()>=2){
				int[] idArr = MobileAccountUtils.getRandomNum(0, labelList.size()-1, 2);
				labels+=labelList.get(idArr[0]).getId()+",";
				labels+=labelList.get(idArr[1]).getId();
			}
		}
		
		String[] labelIds = labels.split(",");
		
		//推荐课程
		List<String> chapterIds = new LinkedList<String>();
		List<String> chapterIdsTemp = null;
		List<CourseChapter> chapterList = new ArrayList<CourseChapter>();
		
		if(labelIds !=null && labelIds.length>0){
			Map<String,Object> map = new HashMap<String,Object>();
			for(String labelId:labelIds){
				//根据标签查找相关课程
				map.clear();
				map.put("type",1);
				map.put("labelId",labelId);
//				map.put("level",account.getLevel());
				map.put("size",5);
				chapterIdsTemp = courseChapterService.getRecommendChapterIdListByLabelId(map);
				if(!chapterIdsTemp.isEmpty()){
					for(String cid:chapterIdsTemp){
						if(!chapterIds.contains(cid) && chapterIds.size()<6){//只推荐6个
							chapterIds.add(cid);
						}
					}
				}
				
			}
		}
		
		
		if(!chapterIds.isEmpty()){
			CourseChapter chapter = null;
			
			for(String id:chapterIds){
				//章节基本信息、所属老师、课程标签、等级id、等级名称
				chapter = courseChapterService.getChapterById(id);
				chapterList.add(chapter);
			}
		}
		
		chap.setRecommendChapterList(chapterList);
		
		//评论列表
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chapterid",chapterId);
		map.put("start",0);
		map.put("size",COMMENT_PAGE_SIZE);
		List<UserReply> commentList = courseChapterService.getChapterCommentList(map);
		chap.setCommentList(commentList);
		
		
		return MobileAccountUtils.getGson().toJson(chap);
	}
	
	
	
	/**
	 * 获取评论列表
	 * @param page
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value="getCommentList")
	@ResponseBody
	public String getCommentList(Integer page,String chapterId){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		//查找图书总数
		int total=courseChapterService.getChapterCommentCount(chapterId);

		int startNum=0;
		int totalPage=0;
		List<UserReply> commentList = null;
		
		if(total>0){
			if((total%COMMENT_PAGE_SIZE)>0){
				totalPage=total/COMMENT_PAGE_SIZE + 1;
			}else{
				totalPage=total/COMMENT_PAGE_SIZE;
			}
			if(page==null||page<1||page>totalPage){
				page=1;
			}
			if(page==1){
				startNum=0;
			}else{
				startNum=(page-1)*COMMENT_PAGE_SIZE;
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid",chapterId);
			map.put("start",startNum);
			map.put("size",COMMENT_PAGE_SIZE);
			commentList = courseChapterService.getChapterCommentList(map);
			
			msg.setPage(page);
			msg.setTotalPage(totalPage);
			msg.setMsgBody(commentList);
		}else{
			msg.setCode(100);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 保存用户的评论
	 * @param reply
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="saveUserComment")
	@ResponseBody
	public String saveUserComment(UserReply reply,CourseNcomment comment){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(StringUtils.isEmpty(reply.getContent()) || comment.getUserid()==null || comment.getChapterid() == null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		if(!StringUtils.isEmpty(reply.getAudioUrl())){
			reply.setAudioUrl(RC4.decry_RC4(reply.getAudioUrl()));
		}
		
		int flag = courseNcommentService.saveUserComment(reply, comment);
			
		
		if(flag==0){//0成功 1失败 事物回滚
			msg.setCode(200);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	/**
	 * 视频音频播放次数保存
	 * @param mediaId
	 * @param type 1视频 2音频
	 * @return
	 */
	@RequestMapping(value="updateMediaPlayCnt")
	@ResponseBody
	public String updateMediaPlayCnt(String mediaId,Integer type){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(StringUtils.isEmpty(mediaId) || type==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		if(type==1){
			videoService.updateVideoPlayCnt(mediaId);
			msg.setCode(200);
		}
		
		if(type==2){
			audioService.updateAudioPlayCnt(mediaId);
			msg.setCode(200);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	
	
	
	/**
	 * 保存用户学习记录
	 * @param log
	 * @return
	 */
	@RequestMapping(value="saveStudyLog")
	@ResponseBody
	public String saveStudyLog(UserStudyLog log){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(log.getUserid()==null || log.getChapterid()==null || log.getChaptype()==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		int total = userStudyLogService.getUserStudyLogCount(log);
		if(total==0){
			log.setState(1);
			userStudyLogService.insert(log);
		}else{
			userStudyLogService.update(log);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
}
