package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.Ad;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.Grade;
import com.hc360.mobileaccount.po.Label;
import com.hc360.mobileaccount.po.QuestionAndPost;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.po.UserConcern;
import com.hc360.mobileaccount.po.UserConcernObject;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.po.UserSignIn;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.AdService;
import com.hc360.mobileaccount.service.CourseService;
import com.hc360.mobileaccount.service.GradeService;
import com.hc360.mobileaccount.service.LabelService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.ScoreService;
import com.hc360.mobileaccount.service.UserConcernObjectService;
import com.hc360.mobileaccount.service.UserConcernService;
import com.hc360.mobileaccount.service.UserNcourseService;
import com.hc360.mobileaccount.service.UserNreplyService;
import com.hc360.mobileaccount.service.UserQuestionService;
import com.hc360.mobileaccount.service.UserSignInService;
import com.hc360.mobileaccount.service.UserStudyLogService;
import com.hc360.mobileaccount.service.UserTalkService;
import com.hc360.mobileaccount.umeng.wsq.WsqMain;
import com.hc360.mobileaccount.utils.DateUtils;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.PushUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping("saleUser")
public class SaleUserController {
	
	@Resource
	private LabelService labelService;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private GradeService gradeService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private UserSignInService userSignInService;
	
	@Resource
	private ScoreService scoreService;
	
	@Resource
	private UserStudyLogService userStudyLogService;
	
	@Resource
	private UserNcourseService userNcourseService;
	
	@Resource
	private UserTalkService userTalkService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	@Resource
	private UserQuestionService userQuestionService;
	
	
	@Resource
	private UserConcernService userConcernService;
	
	@Resource
	private UserNreplyService userNreplyService;
	
	@Resource
	private AdService adService;
	
	@Resource
	private UserConcernObjectService userConcernObjectService;
	
	@Resource
	private PushUtils pushUtils;
	
	
	
	
	/**
	 * 销售学习首页数据（普通用户版 已登录）
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="userIndex")
	@ResponseBody
	public String getUserInfo(String phone){
		ReturnMsg msg = new ReturnMsg();
		Account account = null;
		int isLogin=0;
		
		if(!StringUtils.isEmpty(phone)){
			isLogin=1;
			//获取用户基本信息
			phone = RC4.decry_RC4(phone);
			account = accountService.getAccountInfo(phone);
		}
		
		//广告位推荐
		Ad ad = new Ad();
		ad.setType(4);//广告类型 1课程 2老师 3 名师指导活动列表 4销售社区首页活动
		ad.setState(1);
		List<Ad> adList = adService.getAdList(ad);
		
		for(Ad a:adList){
			if(account!=null && !StringUtils.isEmpty(a.getGoUrl())){
				if(a.getGoUrl().contains("?")){
					a.setGoUrl(a.getGoUrl()+"&userid="+account.getAccountid());
				}else{
					a.setGoUrl(a.getGoUrl()+"?userid="+account.getAccountid());
				}
			}
		}
		
		msg.setAdList(adList);
		
		if(isLogin==1){
			
			if(account==null){
				msg.setCode(100);
				return MobileAccountUtils.getGson().toJson(msg);
			}
			
			if(account.getLevel()==null){
				List<Grade> gradeList = gradeService.getAllGradeList();
				account.setLevel(gradeList.get(0).getId());
			}
			
			//获取等级信息
//			Grade grade = gradeService.getGradeById(account.getLevel());
//			if(grade!=null){
//				account.setLevelTotal(String.valueOf(grade.getChapterCnt()));//等级包含的总章节数
//				account.setLevelName(grade.getName());
//			}else{
//				List<Grade> gradeList = gradeService.getAllGradeList();
//				if(!gradeList.isEmpty()){
//					account.setLevel(gradeList.get(0).getId());
//					account.setLevelTotal(String.valueOf(gradeList.get(0).getChapterCnt()));//等级包含的总章节数
//					account.setLevelName(gradeList.get(0).getName());
//				}
//			}
//			
//			Grade nextGrade = gradeService.getNextGradeById(account.getLevel());
//			if(nextGrade!=null){
//				account.setNextLevel(nextGrade.getName());//下一等级名称
//			}
			
			//获取用户积分信息
			Score score = scoreService.selectScore(phone);
			if(score!=null){
				account.setUserScore(score.getScorenum());
			}
			
			//获取用户本周签到信息
//			String monday = DateUtils.getMondayDateOfThisWeek("yyyy-MM-dd");
//			UserSignIn signIn = new UserSignIn();
//			signIn.setUserid(Long.valueOf(account.getAccountid()));
//			signIn.setSigntimeStr(monday);
//			List<UserSignIn> userSignInList = userSignInService.getUserSignInList(signIn);
//			
//			String userSignInWeeks = "";
//			if(!userSignInList.isEmpty()){
//				for(int i=0;i<userSignInList.size();i++){
//					userSignInWeeks += DateUtils.getWeekOfDate(userSignInList.get(i).getSigntime())+",";
//				}
//				userSignInWeeks = userSignInWeeks.substring(0,userSignInWeeks.length()-1);
//			}
//			
//			account.setUserSignInWeeks(userSignInWeeks);
			
			msg.setUserInfo(account);
		}
		

		
		//获取推荐的课程
		String labels = "";//标签id集合
		if(isLogin==1){
			labels = account.getLabelids();
		}
		
		//标签为空时随机找两个
		if(StringUtils.isEmpty(labels)){
			List<Label> labelList = labelService.getAllLabels();
			if(!labelList.isEmpty() && labelList.size()>=2){
				int[] idArr = MobileAccountUtils.getRandomNum(0, labelList.size()-1, 2);
				labels=labelList.get(idArr[0]).getId()+",";
				labels+=labelList.get(idArr[1]).getId();
			}
		}
		String[] labelIds = labels.split(",");
		
		List<Long> courseIds = new LinkedList<Long>();
		List<Long> courseIdsTemp = null;
		List<Course> courseList = new ArrayList<Course>();
		
		if(labelIds !=null && labelIds.length>0){
			Map<String,Object> map = new HashMap<String,Object>();
			for(String labelId:labelIds){
				//根据用户的兴趣标签查找相关课程
				map.clear();
				map.put("labelId", labelId);
				courseIdsTemp = courseService.getRecommendCourseIdListByLabelId(map);
				if(!courseIdsTemp.isEmpty()){
					for(Long cid:courseIdsTemp){
						if(!courseIds.contains(cid)){
							courseIds.add(cid);
						}
					}
				}
				
			}
		}
		
		//用户订阅的课程
		if(isLogin==1){
			List<Long> userNcourseids = userNcourseService.getUserNCourseIds(String.valueOf(account.getAccountid()));
			if(!userNcourseids.isEmpty()){
				for(Long cid:userNcourseids){
					if(!courseIds.contains(cid)){
						courseIds.add(cid);
					}
				}
			}
		}
		
		
		if(!courseIds.isEmpty()){
			Course course = null;
//			UserStudyLog log = new UserStudyLog();
//			int userFinishCnt = 0;
			
			for(Long id:courseIds){
				//课程基本信息、所属老师、课程标签、等级id、等级名称
				course = courseService.getCourseById(id);
				//用户进度
//				if(isLogin==1){
//					log.setUserid(Long.valueOf(account.getAccountid()));
//					log.setCourseid(course.getId());
//					log.setState(1);//完成状态
//					userFinishCnt = userStudyLogService.getUserStudyLogCount(log);
//				}
//				course.setUserFinishCnt(userFinishCnt);
				
				courseList.add(course);
			}
		}
		
		if(courseList.isEmpty()){
			List<Course> courses = courseService.getRandomCourses(50);
			courseList.addAll(courses);
		}
		
		
		
		msg.setCode(200);
		msg.setMsgBody(courseList);
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	
	/**
	 * 销售学习首页数据
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getIndexData")
	@ResponseBody
	public String getIndexData(String userid){
		ReturnMsg msg = new ReturnMsg();
		
		int isLogin = 0;
		
		if(!StringUtils.isEmpty(userid)){
			isLogin = 1;
		}
		
		//获取推荐的课程
		String labels = "";//标签id集合
		if(isLogin==1){
			Account account = accountService.getAccountInfoById(userid);
			labels = account.getLabelids();
		}
		
		//标签为空时随机找两个
		if(StringUtils.isEmpty(labels)){
			List<Label> labelList = labelService.getAllLabels();
			if(!labelList.isEmpty() && labelList.size()>=2){
				int[] idArr = MobileAccountUtils.getRandomNum(0, labelList.size()-1, 2);
				labels=labelList.get(idArr[0]).getId()+",";
				labels+=labelList.get(idArr[1]).getId();
			}
		}
		String[] labelIds = labels.split(",");
		
		List<Long> courseIds = new LinkedList<Long>();
		List<Long> courseIdsTemp = null;
		List<Course> courseList = new ArrayList<Course>();
		
		if(labelIds !=null && labelIds.length>0){
			Map<String,Object> map = new HashMap<String,Object>();
			for(String labelId:labelIds){
				//根据用户的兴趣标签查找相关课程
				map.clear();
				map.put("labelId", labelId);
				courseIdsTemp = courseService.getRecommendCourseIdListByLabelId(map);
				if(!courseIdsTemp.isEmpty()){
					for(Long cid:courseIdsTemp){
						if(!courseIds.contains(cid)){
							courseIds.add(cid);
						}
					}
				}
				
			}
		}
		
		//用户订阅的课程
		if(isLogin==1){
			List<Long> userNcourseids = userNcourseService.getUserNCourseIds(userid);
			if(!userNcourseids.isEmpty()){
				for(Long cid:userNcourseids){
					if(!courseIds.contains(cid)){
						courseIds.add(cid);
					}
				}
			}
		}
		
		
		if(!courseIds.isEmpty()){
			Course course = null;
			
			for(Long id:courseIds){
				//课程基本信息、所属老师、课程标签、等级id、等级名称
				course = courseService.getCourseById(id);
				
				courseList.add(course);
			}
			
		}
		
		if(courseList.isEmpty()){
			List<Course> courses = courseService.getRandomCourses(50);
			courseList.addAll(courses);
		}
		
		//添加课程的赞数和回复数
		Course course = null;
		for(Course c:courseList){
			course = courseService.getCoursePraiseCntAndReplyCntById(c.getId());
			
			c.setPraiseCnt(course.getPraiseCnt());
			c.setReplyCnt(course.getReplyCnt());
		}
		
		msg.setCode(200);
		msg.setDataBody(courseList);
		
		
	    List<QuestionAndPost> qpList = new ArrayList<QuestionAndPost>();
		
		//获取热门回复对应的问题列表
		UserNreply unr = new UserNreply();
		unr.setReplyType(4);
		unr.setSize(5);
		List<UserNreply> hotReplyList = userNreplyService.getHotUserReply(unr);
		
		List<UserQuestion> questionList = new ArrayList<UserQuestion>();
		UserQuestion question = null;
		int hotPraiseCount = 0;
		
		for(UserNreply r:hotReplyList){
			question = userQuestionService.getById(r.getObjectid());
			question.setUserHotReply(r);
			
			unr.setObjectid(r.getObjectid());
			hotPraiseCount = userNreplyService.getUserPraiseCount(unr);
			
			question.setPraiseCnt(hotPraiseCount);
			
			
			questionList.add(question);
		}
		
		//将热门问题添加至总集合
		for(UserQuestion uq:questionList){
			QuestionAndPost qp = new QuestionAndPost();
    		qp.setId(uq.getId()+"");
    		qp.setContent(uq.getTitle());
    		qp.setAudioUrl(uq.getAudioUrl());
    		qp.setTimeLength(uq.getTimeLength());
    		qp.setUserHotReply(uq.getUserHotReply());
    		
    		qp.setNickname(uq.getUser().getNickname());
    		qp.setHeaderimg(uq.getUser().getHeaderimg());
    		qp.setCreatetime(uq.getCreatetime());
    		qp.setReplyCnt(uq.getReplyCnt());
    		qp.setPraiseCnt(uq.getPraiseCnt());
    		
    		qp.setType(1);
    		
    		qpList.add(qp);
		}
		
		//获取友盟微社区的热门帖子
		try{
			int postCnt = 0;
			
			for(int p=1;p<=10;p++){//获取多页
				String wsqResult = WsqMain.getTimelineCommunity(p,100);
				
				JSONObject json = JSONObject.parseObject(wsqResult);
		        JSONArray jarr = JSONObject.parseArray(json.getString("results"));
		        
		        int replyCnt = 0;
		        int praiseCnt = 0;
		        
		        //将热门帖子添加至总集合
	        	for(int i=0;i<jarr.size();i++){
	            	String stats = jarr.getJSONObject(i).getString("stats");
	            	
	            	JSONObject statsJson = JSONObject.parseObject(stats);
	            	replyCnt = statsJson.getIntValue("comments");
	            	praiseCnt = statsJson.getIntValue("liked");
	            	
	            	if(praiseCnt>=5 && postCnt<15){
	            		String id = jarr.getJSONObject(i).getString("id");
	            		String content = jarr.getJSONObject(i).getString("content");
	            		String nickname = JSONObject.parseObject(jarr.getJSONObject(i).getString("creator")).getString("name");
	            		String headerimg = JSONObject.parseObject(JSONObject.parseObject(jarr.getJSONObject(i).getString("creator")).getString("icon_url")).getString("240");
	            		String createtime = jarr.getJSONObject(i).getString("create_time");
	            		String imgUrls = jarr.getJSONObject(i).getString("image_urls");
	            		
	            		QuestionAndPost qp = new QuestionAndPost();
	            		qp.setId(id);
	            		qp.setContent(content);
	            		qp.setNickname(nickname);
	            		qp.setHeaderimg(headerimg);
	            		qp.setCreatetime(createtime);
	            		qp.setImageUrls(imgUrls);
	            		qp.setReplyCnt(replyCnt);
	            		qp.setPraiseCnt(praiseCnt);
	            		qp.setType(2);
	            		
	            		qpList.add(qp);
	            		
	            		postCnt++;
	            	}
	        	}
	        	
	        	if(postCnt>=15){
	        		break;
	        	}
			}
		}catch(Exception e){
        	e.printStackTrace();
        }
		
        
        
        //按时间降序排列
        Collections.sort(qpList, new Comparator<QuestionAndPost>() {
			@Override
			public int compare(QuestionAndPost o1, QuestionAndPost o2) {
				return o2.getCreatetime().compareTo(o1.getCreatetime());
			}
		});
        
        msg.setMsgBody(qpList);
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 查看所有班级
	 * @return
	 */
	@RequestMapping(value="getAllGrades")
	@ResponseBody
	public String getAllGrades(){
		List<Grade> gradeList= gradeService.getAllGradeList();
		
		return MobileAccountUtils.getGson().toJson(gradeList);
	}
	
	
	/**
	 * 查看等级详情
	 * @param gradeId
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="getGradeDetails")
	@ResponseBody
	public String getGradeDetails(Integer gradeId,String accountId){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		Grade grade = gradeService.getGradeById(gradeId);
		
		if(grade==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//新完成的用户
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("levelId", gradeId);
		map.put("size", 5);
		List<Account> userList = accountService.getReachLevelUserByLevelId(map);
		if(!userList.isEmpty()){
			msg.setUserList(userList);
		}
		
		//当前用户达到的该级别需要的星星数
		Account account = accountService.getAccountInfoById(accountId);
		if(account!=null){
			int needStars = gradeService.getNeedStarsById(gradeId);
			int userNeedStars = needStars-account.getFinishStudyCnt();
			if(userNeedStars<0){
				userNeedStars = 0;
			}
			
			msg.setBalance(userNeedStars);
		}
		
		//该等级包含的课程
		List<Course> courseList = courseService.getCourseListOfGradeByGradeId(gradeId);
		if(!courseList.isEmpty()){
			grade.setCourseList(courseList);
		}
		
		msg.setMsgBody(grade);
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 用户的个人主页
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="userCenter")
	@ResponseBody
	public String userCenter(String phone){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		phone = RC4.decry_RC4(phone);
		
		//用户基本信息
		Account account = accountService.getAccountInfo(phone);
		
		if(account==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		msg.setUserInfo(account);
		
		//我的录音
		UserTalk talk = new UserTalk();
		talk.setUserid(Long.valueOf(account.getAccountid()));
		int total=userTalkService.getUserTalkCount(talk);

		talk.setStart(0);
		talk.setSize(10);
		List<UserTalk> talkList = userTalkService.getUserTalkList(talk);
		
		if(!talkList.isEmpty()){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("replyType", 1);//回复类型
			map.put("type", 1);//点赞类型
			for(int i=0; i<talkList.size(); i++){
				map.put("objectID", talkList.get(i).getId());
				talkList.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
				talkList.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
			}
			
			msg.setTotal(total);
			msg.setMsgBody(talkList);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 用户签到
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="userSignIn")
	@ResponseBody
	public String userSignIn(Long userid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(userid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		
		
		//用户签到
		UserSignIn in = new UserSignIn();
		in.setUserid(userid);
		in.setSigntimeStr(DateUtils.getCurrentTime("yyyy-MM-dd"));
		UserSignIn oldIn = userSignInService.getUserSignInByTime(in);
		if(oldIn==null){
			in.setSigntime(new Date());
			userSignInService.insert(in);
		}else{
			msg.setCode(300);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 关注/取消关注用户
	 * @param userid
	 * @param otherid
	 * @return
	 */
	@RequestMapping(value="concern")
	@ResponseBody
	public String cocern(Long userid,Long otherid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(userid==null || otherid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		UserConcern con = new UserConcern();
		con.setUserid(userid);
		con.setConcernUserid(otherid);
		List<UserConcern> conList = userConcernService.getUserConcernList(con);
		
		if(conList.isEmpty()){
			userConcernService.insert(con);
		}else{
			msg.setCode(201);
			userConcernService.delete(con);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 保存用户的评论
	 * @param reply
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="saveUserReply")
	@ResponseBody
	public String saveUserComment(UserReply reply,Integer type,Long objectid,Long linkid,Long userid,String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(StringUtils.isEmpty(reply.getContent()) || userid==null || type == null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		if(!StringUtils.isEmpty(reply.getAudioUrl())){
			reply.setAudioUrl(RC4.decry_RC4(reply.getAudioUrl()));
		}
		
		//type:评论类型0章节 1话术评测 2营销对练 3章节评论（暂不包含） 4 用户提问
		int flag = 1;
		
		Account user = accountService.getAccountInfoById(String.valueOf(userid));
		UserNreply nreply = new UserNreply();
		nreply.setObjectid(objectid);
		nreply.setUserid(userid);
		nreply.setReplyType(type);
		nreply.setUserType(user.getUserType());
		if(linkid!=null){
			nreply.setLinkid(linkid);
		}
		flag = userNreplyService.saveUserComment(reply,nreply);
		
		if(type==4){
			UserConcernObject obj = new UserConcernObject();
			obj.setObjectid(objectid);
			obj.setType(2);
			List<UserConcernObject> objList = userConcernObjectService.getUserConcernObjectList(obj);
			
			String umsg = "您关注的问题有新的回答";
			String activity = "com.hc360.yellowpage.ui.SolveQuestionDetail";
			
			//向所有关注的用户推送消息
			for(UserConcernObject o:objList){
				if(!String.valueOf(o.getUserid()).equals(String.valueOf(userid))){
					pushUtils.umengPush(String.valueOf(o.getUserid()), umsg, "1", activity, String.valueOf(o.getObjectid()), "question");
				}
			}
			
		}
		
		if(flag==0){//0成功 1失败 事物回滚
			msg.setCode(200);
			msg.setUserInfo(user);
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback + "(" +MobileAccountUtils.getGson().toJson(msg) + ")";
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	

}
