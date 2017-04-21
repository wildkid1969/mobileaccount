package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.Ad;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.Label;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.ReturnValue;
import com.hc360.mobileaccount.po.Teacher;
import com.hc360.mobileaccount.po.TeacherReplyInvited;
import com.hc360.mobileaccount.po.UserConcernObject;
import com.hc360.mobileaccount.po.UserNcourse;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserPraise;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.po.UserSubscribeTeacher;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.AdService;
import com.hc360.mobileaccount.service.CourseService;
import com.hc360.mobileaccount.service.LabelService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserConcernObjectService;
import com.hc360.mobileaccount.service.UserNcourseService;
import com.hc360.mobileaccount.service.UserNreplyService;
import com.hc360.mobileaccount.service.UserQuestionService;
import com.hc360.mobileaccount.service.UserStudyLogService;
import com.hc360.mobileaccount.utils.DateUtils;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.PushUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping(value="teacherGuide")
public class TeacherGuideController {

	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private UserStudyLogService userStudyLogService;
	
	@Resource
	private LabelService labelService;
	
	@Resource
	private AdService adService;
	
	@Resource
	private UserQuestionService userQuestionService;
	
	@Resource
	private UserNcourseService userNcourseService;
	
	@Resource 
	private AccountService accountService;
	
	@Resource
	private UserNreplyService userNreplyService;
	
	@Resource
	private UserConcernObjectService userConcernObjectService;
	
	@Resource
	private PushUtils pushUtils;
	
	
	
	private static final int PAGE_SIZE = 10;
	
	
	/**
	 * 名师指导首页
	 * @return
	 */
	@RequestMapping("index")
	@ResponseBody
	public String index(){
		ReturnValue rv = new ReturnValue();
		//广告位推荐
		Ad ad = new Ad();
		ad.setState(1);
		ad.setTypes("1,2,3".split(","));
		List<Ad> adList = adService.getAdList(ad);
		rv.setAdList(adList);
		
		
		//课程标签列表
		List<String> courseLabels = courseService.getCourseLabels();
		String labels = "";
		for(String s:courseLabels){
			labels +=s +",";
		}
		
		List<String> labelIds = new ArrayList<String>();
		List<Label> lableList = new ArrayList<Label>();
		
		if(!StringUtils.isEmpty(labels)){
			try{
				String[] labelArr = labels.split(",");
				
				for(String s:labelArr){
					if(!StringUtils.isEmpty(s) && !labelIds.contains(s)){
						labelIds.add(s);
					}
				}
				
				Label label = null;
				for(String id:labelIds){
					label = labelService.getById(Long.valueOf(id));
					if(label!=null){
						lableList.add(label);
					}
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		
		rv.setLabelList(lableList);
		
		
		//答疑解惑
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("size", 5);
		List<UserQuestion> userQuestions = userQuestionService.getUserQuestionList(map);

		if(!userQuestions.isEmpty()){
			map.put("replyType", 4);//回复类型
			map.put("type", 4);//点赞类型
			for(int i=0; i<userQuestions.size(); i++){
				map.put("objectID", userQuestions.get(i).getId());//questionid
				userQuestions.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
				userQuestions.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
			}
			
			rv.setQuestionsList(userQuestions);
			
		}
		return MobileAccountUtils.getGson().toJson(rv);
	}
	
	
	/**
	 * 答疑解惑分页列表
	 * 
	 * @param page 页码
	 * @param userid 当有用户id是即为我的提问列表
	 * @return
	 */
	@RequestMapping(value="getQuestionList")
	@ResponseBody
	public String getQuestionList(Integer page,Long userid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		Map<String,Object> map = new HashMap<String, Object>();
		if(userid!=null){
			map.put("userid",userid);
		}
		int total = userQuestionService.getUserQuestionCount(map);
		
		int startNum=0;
		int totalPage=0;
		
		if(total>0){
			if((total%PAGE_SIZE)>0){
				totalPage=total/PAGE_SIZE + 1;
			}else{
				totalPage=total/PAGE_SIZE;
			}
			if(page==null||page<1||page>totalPage){
				page=1;
			}
			if(page==1){
				startNum=0;
			}else{
				startNum=(page-1)*PAGE_SIZE;
			}
			
			map.put("start", startNum);
			map.put("size", PAGE_SIZE);
			List<UserQuestion> userQuestions = userQuestionService.getUserQuestionList(map);
			
			//单独取出置顶问题集合，防止翻页后无置顶问题(与普通问题一起处理,处理完放入置顶问题集合里,最后从总集合里清除掉临时集合)
			List<UserQuestion> topQuestionTmp = userQuestionService.getUserTopQuestionList(map);
			userQuestions.addAll(topQuestionTmp);
			
			List<UserQuestion> topQuestions = new ArrayList<UserQuestion>();
			List<Long> topQuestionIds = new ArrayList<Long>();
			
			if(!userQuestions.isEmpty()){
				map.put("replyType", 4);//回复类型
				map.put("type", 4);//点赞类型
				
				int userConcernCount = 0;
				int praiseCnt = 0;
				
				UserConcernObject concernObj = new UserConcernObject();
				concernObj.setType(2);
				
				UserNreply unr = new UserNreply();
				
				
				
				//处理集合
				for(int i=0; i<userQuestions.size(); i++){
					//回复集合和点赞集合
					map.put("objectID", userQuestions.get(i).getId());//questionid
					userQuestions.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));//getObjectSimpleReplys(map)
					userQuestions.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
					
					//点赞总数
					unr.setReplyType(4);
					unr.setObjectid(userQuestions.get(i).getId());
					praiseCnt = userNreplyService.getUserPraiseCount(unr);
					
					userQuestions.get(i).setPraiseCnt(praiseCnt);
					
					//关注数
					concernObj.setUserid(null);
					concernObj.setObjectid(userQuestions.get(i).getId());
					userConcernCount = userConcernObjectService.getUserConcernObjectCount(concernObj);
					userQuestions.get(i).setConcernCnt(userConcernCount);
					
					//当前用户是否关注了此问题
					userQuestions.get(i).setIsConcern(0);
					if(userid!=null){
						concernObj.setUserid(userid);
						userConcernCount = userConcernObjectService.getUserConcernObjectCount(concernObj);
						if(userConcernCount>0){
							userQuestions.get(i).setIsConcern(1);
						}
					}
					
					//取出置顶问题
					if(userQuestions.get(i).getIsTop()==1 && !topQuestionIds.contains(userQuestions.get(i).getId())){
						//获取一条热门回复
						unr.setReplyType(4);
						unr.setObjectid(userQuestions.get(i).getId());
						List<UserNreply> hotReplyList = userNreplyService.getHotUserReply(unr);
						if(!hotReplyList.isEmpty()){
							userQuestions.get(i).setUserHotReply(hotReplyList.get(0));
						}
						
						topQuestionIds.add(userQuestions.get(i).getId());
						topQuestions.add(userQuestions.get(i));
						
					}
				}
				
				userQuestions.removeAll(topQuestionTmp);
				
				msg.setCode(200);
				msg.setMsgBody(userQuestions);
				msg.setDataBody(topQuestions);
				msg.setPage(page);
				msg.setTotalPage(totalPage);
				
			}
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 获取问题详情
	 * @param questionid
	 * @return
	 */
	@RequestMapping(value="getQuestion")
	@ResponseBody
	public String getQuestion(Long questionid,Long userid,String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		UserQuestion q = userQuestionService.getById(questionid);
		
		if(q!=null){
			//赞数
			UserNreply unr = new UserNreply();
			unr.setReplyType(4);
			unr.setObjectid(q.getId());
			int praiseCount = userNreplyService.getUserPraiseCount(unr);
			
			q.setPraiseCnt(praiseCount);
			
			//(回复或点赞)
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("replyType", 4);//回复类型
			map.put("type", 4);//点赞类型
			map.put("objectID", q.getId());
			
			List<UserNreply> replyList = saleTeacherService.getObjectOrderReplys(map);
			
			//设置每条回复的子回复数
			//遍历每条回复当前用户是否点赞
			Map<String,Object> userMap = new HashMap<String,Object>();
			userMap.put("type", 7);//用户提问的回复
			userMap.put("userId", userid);
			List<UserPraise> userPraiseList = null;
			for(UserNreply r:replyList){
				sonReplyList.clear();
				getSonReplyList(questionid,r.getReplyid());
				r.setReplyCnt(sonReplyList.size());
				
				r.setIsPraise(0);
				
				if(userid!=null && sonReplyList.size()>0){
					userMap.put("objectID", r.getReplyid());
					userPraiseList = saleTeacherService.getUserPraises(userMap);
					
					if(!userPraiseList.isEmpty()){
						r.setIsPraise(1);
					}
				}
				
			}
			
			//赞的集合
			q.setUserPraises(saleTeacherService.getUserPraises(map));
			//回复的集合
			q.setUserNreplys(replyList);
			
			//关注数
			UserConcernObject concernObj = new UserConcernObject();
			concernObj.setObjectid(questionid);
			concernObj.setType(2);
			concernObj.setUserid(null);
			int userConcernCount = userConcernObjectService.getUserConcernObjectCount(concernObj);
			q.setConcernCnt(userConcernCount);
			
			//用户是否关注了该问题
			q.setIsConcern(0);
			if(userid!=null){
				concernObj.setUserid(userid);
				userConcernCount = userConcernObjectService.getUserConcernObjectCount(concernObj);
				
				if(userConcernCount>0){
					q.setIsConcern(1);
				}
			}
			
			msg.setCode(200);
			msg.setMsgBody(q);
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback+"("+MobileAccountUtils.getGson().toJson(msg)+")";
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	
	private List<UserNreply> sonReplyList = new ArrayList<UserNreply>();

	/**
	 * 获取问题的回复详情
	 * @param questionid
	 * @return
	 */
	@RequestMapping(value="getUserReplySonList")
	@ResponseBody
	public String getUserReplySonList(Long questionid,Long replyid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(300);
		
		if(questionid==null || replyid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//当前回复的详情
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("replyType", 4);//回复类型
		map.put("objectID", questionid);
		map.put("replyId", replyid);
		List<UserNreply> unrList = saleTeacherService.getObjectSonReplys(map);
		
		//该回复和所有子回复
		if(!unrList.isEmpty()){
			//该回复的点赞集合
			if(unrList.get(0).getPraiseCnt()>0){
				Map<String,Object> pmap = new HashMap<String,Object>();
				pmap.put("type", 7);//回复类型
				pmap.put("objectID", replyid);
				List<UserPraise> praiseList = saleTeacherService.getUserPraises(pmap);
				
				unrList.get(0).setPraiseList(praiseList);
			}
			
			
			//添加到总集合并排序
			sonReplyList.clear();
			sonReplyList.addAll(unrList);
			getSonReplyList(questionid,replyid);
			
			Collections.sort(sonReplyList,new Comparator<UserNreply>() {
				@Override
				public int compare(UserNreply o1, UserNreply o2) {//o1 小于o2，返回-1（负数），相等返回0，o1大于o2返回1（正数）
					return o1.getCreatetime().compareTo(o2.getCreatetime());
				}
				
			});
			
			
			msg.setCode(200);
			msg.setMsgBody(sonReplyList);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 删除用户的某一条回复及子回复
	 * @param questionid
	 * @param replyid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="deleteUserReply")
	@ResponseBody
	public String deleteUserReply(Long questionid,Long replyid,Long userid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200); 
		
		if(questionid==null || replyid==null){
			msg.setCode(100); 
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		UserNreply reply = new UserNreply();
		reply.setReplyid(replyid);
		reply.setUserid(userid);
		reply.setReplyType(4);
		sonReplyList.clear();
		sonReplyList.add(reply);
		
		//获取子回复
		getSonReplyList(questionid,replyid);
	
		//逐条删除回复
		UserNreply unr = new UserNreply();
		unr.setUserid(userid);
		unr.setReplyType(4);
		for(UserNreply r:sonReplyList){
			unr.setReplyid(r.getReplyid());
			userNreplyService.deleteUserReply(unr);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 查询子回复
	 */
	public void getSonReplyList(Long questionid,Long replyid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("replyType", 4);//回复类型
		map.put("objectID", questionid);
		map.put("linkId", replyid);
		List<UserNreply> unrList = saleTeacherService.getObjectSonReplys(map);
		
		if(!unrList.isEmpty()){
			sonReplyList.addAll(unrList);
			
			for(UserNreply rid:unrList){
				getSonReplyList(questionid,rid.getReplyid());
			}
			
		}
		
	}
	
	
	
	/**
	 * 老师的个人主页
	 * @param teacherid 老师的userid
	 * @param userid 当前用户的id
	 * @return
	 */
	@RequestMapping(value="teacherIndex")
	@ResponseBody
	public String teacherIndex(Long teacherid,Long userid){
		ReturnValue rv = new ReturnValue();
		rv.setCode(100);
		
		//老师详情
		Teacher tea = saleTeacherService.getByUserId(teacherid);
		
		if(tea!=null){
			//当前用户是否订阅该老师
			UserSubscribeTeacher t = new UserSubscribeTeacher();
			t.setUserid(userid);
			t.setTeacherid(teacherid);
			int flag = saleTeacherService.isSubscribeTeacher(t);
			
			tea.setIsSub(flag);//0未订阅 1已订阅
			
			rv.setCode(200);
			rv.setData(tea);
		}
		
		//最新动态
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid",teacherid);
		map.put("size", 10);
		List<Course> newlyCourseList = courseService.getTeacherNewlyCourseList(map);
		
		//整合今日参与
		if(!newlyCourseList.isEmpty()){
			List<Account> todayStudyUserList = null;
			
			for(int i=0;i<newlyCourseList.size();i++){
				map.clear();
				map.put("courseid", newlyCourseList.get(i).getId());
				map.put("dateStr", DateUtils.dateToStr(new Date(), "yyyy-MM-dd"));
				map.put("size",50);
				todayStudyUserList = userStudyLogService.getTodayStudyUserList(map);
				
				if(!todayStudyUserList.isEmpty()){
					newlyCourseList.get(i).setTodayStudyUserList(todayStudyUserList);
				}
				
			}
			
			rv.setCourseList(newlyCourseList);
			
		}
		
		
		//她的回复
		map.clear();
		map.put("userid", teacherid);
		map.put("isReply", 1);
		int total=saleTeacherService.getUserQuestionsCount(map);

		if(total > 0){
			map.clear();
			map.put("start", 1);
			map.put("size", PAGE_SIZE);
			map.put("userid", teacherid);
			List<UserQuestion> userQuestions = saleTeacherService.getUserQuestions(map);

			if(!userQuestions.isEmpty()){
				map.put("replyType", 4);//回复类型
				map.put("type", 4);//点赞类型
				for(int i=0; i<userQuestions.size(); i++){
					map.put("objectID", userQuestions.get(i).getId());//questionid
					userQuestions.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
					userQuestions.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
				}
				
				rv.setQuestionsList(userQuestions);
			}
		}

		
		
		
		return MobileAccountUtils.getGson().toJson(rv);
	}
	
	
	/**
	 * 根据标签id获取相关课程
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value="getCourseListByLabelId")
	@ResponseBody
	public String getCourseList(Long labelId){
		ReturnValue rv = new ReturnValue();
		
		List<Long> courseIds = courseService.getCourseIdsByLabelId(labelId);
		if(!courseIds.isEmpty()){
			List<Course> courseList = new ArrayList<Course>();
			Course course = null;
			for(Long id:courseIds){
				course = courseService.getCourseById(id);
				courseList.add(course);
			}
			
			rv.setCourseList(courseList);
		}
		
		return MobileAccountUtils.getGson().toJson(rv);
	}
	
	
	/**
	 * 用户提问
	 * @param uq
	 * @return
	 */
	@RequestMapping(value="userQuestion")
	@ResponseBody
	public String userQuestion(UserQuestion uq){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(uq.getUserid()!=null && (!StringUtils.isEmpty(uq.getTitle()) || !StringUtils.isEmpty(uq.getAudioUrl()))){
			msg.setCode(200);
			
			if(!StringUtils.isEmpty(uq.getAudioUrl())){
				uq.setAudioUrl(RC4.decry_RC4(uq.getAudioUrl()));
			}
			Long userid = uq.getUserid();
			//保存用户的提问
			userQuestionService.insert(uq);
			
			msg.setMsgBody(uq.getId());
			
			//批量插入老师的邀请
			List<Long> teacherUserids = saleTeacherService.getAllTeacherIds();
			if(!teacherUserids.isEmpty()){
				String umsg = "您有1条新的提问等待您回答";
				String activity = "com.hc360.yellowpage.ui.QuestionEvaluateActivity";
				List<TeacherReplyInvited> inviteList = new ArrayList<TeacherReplyInvited>();
				for(Long tuid:teacherUserids){
					if(!tuid.equals(userid)){
						TeacherReplyInvited tt = new TeacherReplyInvited();
						tt.setInvitedid(uq.getId());
						tt.setType(2);
						tt.setUserid(tuid);
						
						inviteList.add(tt);
						
						//向所有老师推送消息
						pushUtils.umengPush(String.valueOf(tuid), umsg, "1", activity, String.valueOf(uq.getId()), "question");
					}
					
				}
				
				saleTeacherService.batchInsertTeacherReplyInvited(inviteList);
				
			}
		}
		
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 订阅老师所有的课程
	 * @param userid
	 * @param teacherid 老师的userid
	 * @return
	 */
	@RequestMapping(value="subcribeCourse")
	@ResponseBody
	public String subcribeCourse(Long userid,Long teacherid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(userid==null || teacherid==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//订阅老师
		UserSubscribeTeacher t = new UserSubscribeTeacher();
		t.setUserid(userid);
		t.setTeacherid(teacherid);
		int flag = saleTeacherService.isSubscribeTeacher(t);
		
		if(flag==0){
			msg.setCode(200);
			
			saleTeacherService.insertIntoUserSubscribeTeacher(t);
			
			//查询老师所有的课程
			List<Long> courseids = saleTeacherService.getTeacherCourseIds(teacherid);
			//用户已订阅的所有课程
			List<Long> userNcourseids = userNcourseService.getUserNCourseIds(String.valueOf(userid));
			if(!courseids.isEmpty()){
				List<UserNcourse> ucList = new ArrayList<UserNcourse>();
				for(Long cid:courseids){
					if(!userNcourseids.contains(cid)){
						UserNcourse uc = new UserNcourse();
						uc.setUserid(userid);
						uc.setCourseid(cid);
						
						ucList.add(uc);
					}
					
				}
				
				if(!ucList.isEmpty()){
					//保存用户订阅的所有课程
					userNcourseService.subcribeTeacherAllCourses(ucList);
				}
				
			}
		}else{//取消订阅
			msg.setCode(300);
			
			saleTeacherService.unsubscribeTeacher(t);
			
			//查询老师所有的课程
			List<Long> courseids = saleTeacherService.getTeacherCourseIds(teacherid);
			if(!courseids.isEmpty()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userid", userid);
				map.put("courseidList", courseids);
				userNcourseService.unsubcribeTeacherAllCourses(map);
			}
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	
	
}
