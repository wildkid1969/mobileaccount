package com.hc360.mobileaccount.web.sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.AccountYzx;
import com.hc360.mobileaccount.po.ExerciseAutoMatch;
import com.hc360.mobileaccount.po.ExerciseHangUpLog;
import com.hc360.mobileaccount.po.ExerciseResult;
import com.hc360.mobileaccount.po.ExerciseRoom;
import com.hc360.mobileaccount.po.MarketingCourse;
import com.hc360.mobileaccount.po.ReturnExerciseResult;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserConcern;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.ExerciseAutoMatchService;
import com.hc360.mobileaccount.service.ExerciseHangUpLogService;
import com.hc360.mobileaccount.service.ExerciseResultService;
import com.hc360.mobileaccount.service.ExerciseRoomService;
import com.hc360.mobileaccount.service.MarketingCourseService;
import com.hc360.mobileaccount.service.UserConcernService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;


@Controller
@RequestMapping(value="userExercise")
public class UserExerciseController {
	
	@Resource
	private ExerciseAutoMatchService exerciseAutoMatchService;
	
	@Resource
	private ExerciseRoomService exerciseRoomService;
	
	@Resource 
	private MarketingCourseService marketingCourseService;
	
	@Resource
	private ExerciseResultService exerciseResultService;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private UserConcernService userConcernService;
	
	@Resource
	private ExerciseHangUpLogService exerciseHangUpLogService;
	
	
	private static final int PAGE_SIZE=10;
	
	
	/**
	 * 对练房间里用户的状态：
	 * 0 准备匹配 1 匹配成功 2 准备开始对练 3 对练中 4 通话结束 5 评价中 6 对练结束
	 *
	 * 用户状态：
	 * 0 未在线 1 在线 2 进入对练课程 3 准备匹配 4 匹配成功 5 准备开始对练 6 对练中 7 通话结束 8 评价中
	 * 
	 */
	
	
	
	/**
	 * 获取对练课程列表
	 * 没返回课程里的步骤和评价信息
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getCourseList")
	@ResponseBody
	public String getCourseList(Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		
		//查找总数
		MarketingCourse course = new MarketingCourse();
		int total=marketingCourseService.getCourseCount(course);

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
			
			course.setStart(startNum);
			course.setSize(PAGE_SIZE);
			
			List<MarketingCourse> courseList = marketingCourseService.getCourseList(course);
			
			//参与对练的用户
			if(!courseList.isEmpty()){
				List<Account> userList = new ArrayList<Account>();
				ExerciseResult result = new ExerciseResult();
				result.setSize(10);
				for(int i=0;i<courseList.size();i++){
					result.setCourseid(courseList.get(i).getId());
					userList = exerciseResultService.getExerciseUserListByCourse(result);
					if(!userList.isEmpty()){
						courseList.get(i).setUserList(userList);
					}
				}
				msg.setCode(200);
			}else{
				msg.setCode(300);
			}
			
			
			msg.setPage(page);
			msg.setTotalPage(totalPage);
			msg.setMsgBody(courseList);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 获取对练课程详情
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value="getCourse")
	@ResponseBody
	public String getCourse(Long courseid){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(200);
		
		MarketingCourse course = marketingCourseService.getById(courseid);
		
		//参与对练的用户
		if(course!=null){
			ExerciseResult r = new ExerciseResult();
			r.setSize(10);
			r.setCourseid(courseid);
			List<Account> userList  = exerciseResultService.getExerciseUserListByCourse(r);
			
			if(!userList.isEmpty()){
				course.setUserList(userList);
			}
			
			result.setCourse(course);
		}else{
			result.setCode(100);
		}
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	/**
	 * 获取对练课程详情
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value="getNextCourse")
	@ResponseBody
	public String getNextCourse(Long courseid){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(200);
		
		MarketingCourse course = marketingCourseService.getNextById(courseid);
		
		//参与对练的用户
		if(course!=null){
			ExerciseResult r = new ExerciseResult();
			r.setSize(10);
			r.setCourseid(courseid);
			List<Account> userList  = exerciseResultService.getExerciseUserListByCourse(r);
			
			if(!userList.isEmpty()){
				course.setUserList(userList);
			}
			
			result.setCourse(course);
		}else{
			result.setCode(100);
		}
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	
	
	/**
	 * 把用户存入匹配表里
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="prepareMatch")
	@ResponseBody
	public String prepareExercise(Long userid,Long courseid) throws IOException{
		ReturnExerciseResult result = new ReturnExerciseResult();
		
		if(userid==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		ExerciseAutoMatch match = new ExerciseAutoMatch();
		match.setUserid(userid);
		List<ExerciseAutoMatch> matchList = exerciseAutoMatchService.getAutoMatchList(match);
		
		if(matchList.isEmpty()){
			if(courseid!=null){
				match.setCourseid(courseid);
			}
			exerciseAutoMatchService.insert(match);
		}else{
			if(courseid==null){
				courseid=0l;
			}
			match.setCourseid(courseid);
			match.setId(matchList.get(0).getId());
			exerciseAutoMatchService.update(match);
			
		}
		
		
		result.setCode(200);
		result.setUserid(userid);
		result.setState(2);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	/**
	 * 将用户从匹配队列里删除
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="deleteUserInMatch")
	@ResponseBody
	public String deleteUserInMatch(Long userid){
		ReturnExerciseResult result = new ReturnExerciseResult();
		
		exerciseAutoMatchService.deleteByUserid(userid);
		result.setCode(200);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
//////////////////////////////////自动匹配////////////////////////////////////////////////
	
	/**
	 * 自动匹配 并返回 可以对练的用户
	 * @param userid
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value="autoMatch")
	@ResponseBody
	public String autoMatch(Long userid,Long courseid){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(400);
		
		if(userid==null || courseid==null){
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		
		///////匹配前先查看该用户是否已被匹配
		ExerciseRoom r = new ExerciseRoom();
		r.setMyId(userid);
		r.setState(1);
		List<ExerciseRoom> rList = exerciseRoomService.getRoomList(r);
		
		if(!rList.isEmpty()){
			
			//从匹配队列里删除
			exerciseAutoMatchService.deleteByUserid(userid);
			
			//对练用户的id
			String partnerid = "";
			result.setCode(100);
			result.setCourseid(rList.get(0).getCourseid());
			result.setRoomid(rList.get(0).getRoomid());
			result.setRole(rList.get(0).getRole());
			result.setUserid(userid);
			
			if(rList.get(0).getRole()==1){//当前用户已被匹配做销售
				partnerid = String.valueOf(rList.get(0).getPartnerid());
			}
			if(rList.get(0).getRole()==2){//当前用户已被匹配做客户
				partnerid = String.valueOf(rList.get(0).getUserid());
			}
			
			exerciseAutoMatchService.deleteByUserid(Long.valueOf(partnerid));
			
			
			Account partner = accountService.getAccountInfoById(partnerid);
			if(partner!=null){
				String clientId = "";
				AccountYzx yzx = accountService.getYzx(partner.getPhonenum(),"114");
				if(yzx!=null){
					clientId = yzx.getClientNumber();
				}
				
				result.setPartnerId(Long.valueOf(partnerid));
				result.setNickname(partner.getNickname());
				result.setImgUrl(partner.getHeaderimg());
				result.setPartnerPhone(partner.getPhonenum());
				result.setClientNumber(clientId);
				
			}
			
			result.setState(4);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		//////////去匹配用户
		
		//随机获取角色 1销售 2客户
		int randRole = MobileAccountUtils.getRandomNum(1, 2, 1)[0];
		long saleUserId = 0l;
		long customerId = 0l;
		
		//1.去匹配队列里寻找学习同一课程的用户
		ExerciseAutoMatch match = new ExerciseAutoMatch();
		match.setCourseid(courseid);
		match.setMyId(userid);
		match.setSize(1);
		match.setRand(randRole);
		
		
		List<ExerciseAutoMatch> matchList = exerciseAutoMatchService.getAutoMathUserList(match);
		
		if(!matchList.isEmpty()){
			//匹配成功以后从自动匹配表里清除 
			exerciseAutoMatchService.deleteByUserid(userid);
			exerciseAutoMatchService.deleteByUserid(matchList.get(0).getUserid());
			
			result.setCode(200);
			result.setCourseid(courseid);
			result.setRole(randRole);
			result.setUserid(userid);
			result.setPartnerId(matchList.get(0).getUserid());
			result.setNickname(matchList.get(0).getNickname());
			result.setImgUrl(matchList.get(0).getHeaderimg());
			result.setPartnerPhone(matchList.get(0).getPhone());
			result.setState(4);
			
			String clientId = "";
			Account partner = accountService.getAccountInfoById(String.valueOf(matchList.get(0).getUserid()));
			AccountYzx yzx = accountService.getYzx(partner.getPhonenum(),"114");
			if(yzx!=null){
				clientId = yzx.getClientNumber();
			}
			
			result.setClientNumber(clientId);
			
			//匹配成功后将两人移入对练房间
			ExerciseRoom room = new ExerciseRoom();
			if(randRole==1){
				saleUserId = userid;
				customerId = matchList.get(0).getUserid();
			}
			if(randRole==2){
				saleUserId = matchList.get(0).getUserid();
				customerId = userid;
			}
			room.setCourseid(courseid);
			room.setUserid(saleUserId);
			room.setPartnerid(customerId);
			room.setState(1);
			exerciseRoomService.insert(room);
			
			result.setRoomid(room.getRoomid());
		}else{
			//2.去匹配队列里寻找学习不同课程的用户
			ExerciseAutoMatch match2 = new ExerciseAutoMatch();
			match2.setMyId(userid);
			match2.setSize(1);
			List<ExerciseAutoMatch> matchList2 = exerciseAutoMatchService.getAutoMathUserList(match2);
			
			if(!matchList2.isEmpty()){
				//匹配成功以后从自动匹配表里清除 并移入房间
				exerciseAutoMatchService.deleteByUserid(userid);
				exerciseAutoMatchService.deleteByUserid(matchList2.get(0).getUserid());
				
				result.setCode(300);
				result.setRole(randRole);
				result.setUserid(userid);
				result.setPartnerId(matchList2.get(0).getUserid());
				result.setNickname(matchList2.get(0).getNickname());
				result.setImgUrl(matchList2.get(0).getHeaderimg());
				result.setPartnerPhone(matchList2.get(0).getPhone());
				result.setCourseid(courseid);
				result.setState(4);
				
				String clientId = "";
				Account partner = accountService.getAccountInfoById(String.valueOf(matchList2.get(0).getUserid()));
				AccountYzx yzx = accountService.getYzx(partner.getPhonenum(),"114");
				if(yzx!=null){
					clientId = yzx.getClientNumber();
				}
				
				result.setClientNumber(clientId);
				
				ExerciseRoom room = new ExerciseRoom();
				if(randRole==1){
					saleUserId = userid;
					customerId = matchList2.get(0).getUserid();
				}
				if(randRole==2){
					saleUserId = matchList2.get(0).getUserid();
					customerId = userid;
				}
				room.setCourseid(courseid);
				room.setUserid(saleUserId);
				room.setPartnerid(customerId);
				room.setState(1);
				exerciseRoomService.insert(room);
				
				result.setRoomid(room.getRoomid());
			}
			
		}
		
		return MobileAccountUtils.getGson().toJson(result);
		
	}
	
	
	
	

//////////////////////////////////主动邀请////////////////////////////////////////////////
	
	
	/**
	 * 获取邀请用户列表
	 * @param courseid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getInviterList")
	@ResponseBody
	public String getInviterList(Long courseid,Long userid){
		ReturnMsg result = new ReturnMsg();
		result.setCode(200);
		
		if(courseid==null || userid==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		List<ExerciseAutoMatch> matchList = null;
		
		//1.查找学习同一课程的用户
		ExerciseAutoMatch match = new ExerciseAutoMatch();
		match.setCourseid(courseid);
		match.setMyId(userid);
		match.setSize(20);
		matchList = exerciseAutoMatchService.getAutoMathUserList(match);
		
		//2.查找学习不同课程的用户
		if(matchList.isEmpty()){
			match.setCourseid(null);
			match.setSize(20);
			matchList = exerciseAutoMatchService.getAutoMathUserList(match);
		}
		
		
		//3.查找互相关注的好友里的同事
		if(matchList.isEmpty() || matchList.size()<5){
			UserConcern con = new UserConcern();
			con.setUserid(userid);
			con.setSize(10);
			List<UserConcern> concernList = userConcernService.getFriendList(con);
			
			if(!concernList.isEmpty()){
				Account user = accountService.getAccountInfoById(String.valueOf(userid));
				String myCorp = user.getCorpname();
				
				Account friend = null;
				List<Account> workmateList = new ArrayList<Account>();
				
				if(!StringUtils.isEmpty(myCorp)){
					for(int i=0;i<concernList.size();i++){
						friend = accountService.getAccountInfoById(String.valueOf(concernList.get(i).getUserid()));
						
						if(friend!=null && myCorp.equals(friend.getCorpname())){
							workmateList.add(friend);
						}
					}
					
					if(!workmateList.isEmpty()){
						for(int i=0;i<workmateList.size();i++){
							ExerciseAutoMatch m = new ExerciseAutoMatch();
							m.setUserid(Long.valueOf(workmateList.get(i).getAccountid()));
							m.setNickname(workmateList.get(i).getNickname());
							m.setPhone(workmateList.get(i).getPhonenum());
							
							matchList.add(m);
						}
					}
				}
				
			}
		}
		
		if(matchList.isEmpty()){
			result.setCode(300);
		}
		
		result.setMsgBody(matchList);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	/**
	 * 主动邀请
	 * @param courseid 对练的课程id
	 * @param userid 当前用户做为销售
	 * @param partnerid 被邀请人做为客户
	 * @return
	 */
	@RequestMapping(value="invite")
	@ResponseBody
	public String invite(Long courseid,Long userid,Long partnerid){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(100);
		
		if(courseid==null || userid==null || partnerid==null){
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		///////匹配前先查看该用户是否已被匹配
		ExerciseRoom r = new ExerciseRoom();
		r.setMyId(userid);
		r.setState(1);
		List<ExerciseRoom> rList = exerciseRoomService.getRoomList(r);
		
		if(!rList.isEmpty()){
			
			//从匹配队列里删除
			exerciseAutoMatchService.deleteByUserid(userid);
			
			result.setCode(100);
			result.setRoomid(rList.get(0).getRoomid());
			result.setRole(rList.get(0).getRole());
			result.setUserid(userid);
			
			if(rList.get(0).getRole()==1){//当前用户已被匹配做销售
				partnerid = rList.get(0).getPartnerid();
			}
			if(rList.get(0).getRole()==2){//当前用户已被匹配做客户
				partnerid = rList.get(0).getUserid();
			}
			
			Account partner = accountService.getAccountInfoById(String.valueOf(partnerid));
			if(partner!=null){
				result.setPartnerId(Long.valueOf(partnerid));
				result.setNickname(partner.getNickname());
				result.setImgUrl(partner.getHeaderimg());
				result.setPartnerPhone(partner.getPhonenum());
			}
			
			result.setState(4);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		///////查看被邀请人是否已进入房间
		
		r.setMyId(partnerid);
		rList = exerciseRoomService.getRoomList(r);
		
		if(!rList.isEmpty()){
			result.setCode(300);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		
		////////两人都未对练，则将两人放入房间
		
		//邀请进去房间后从自动匹配表里清除
		exerciseAutoMatchService.deleteByUserid(userid);
		
		//移入房间
		ExerciseRoom room = new ExerciseRoom();
		room.setCourseid(courseid);
		room.setUserid(userid);
		room.setPartnerid(partnerid);
		room.setState(1);
		exerciseRoomService.insert(room);
		
		if(userid!=null && partnerid!=null){
			Account partner = accountService.getAccountInfoById(String.valueOf(partnerid));
			
			if(partner!=null){
				result.setCode(200);
				result.setUserid(userid);
				result.setPartnerId(partnerid);
				result.setNickname(partner.getNickname());
				result.setImgUrl(partner.getHeaderimg());
				result.setRole(1);
				result.setState(4);
				result.setRoomid(room.getRoomid());
			}
			
		}
	
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	
	
	
	
	
	/**
	 * 更新对练房间状态
	 * @param roomid
	 * @param state
	 * @return
	 */
	@RequestMapping(value="updateRoomState")
	@ResponseBody
	public String updateRoomState(Long roomid,Integer state){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(200);
		
		if(roomid==null || state==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		ExerciseRoom room = new ExerciseRoom();
		room.setRoomid(roomid);
		room.setState(state);
		exerciseRoomService.update(room);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	/**
	 * 更新对练房间状态
	 * @param roomid
	 * @param state
	 * @return
	 */
	@RequestMapping(value="getRoomInfo")
	@ResponseBody
	public String getRoomInfo(Long roomid){
		ReturnMsg result = new ReturnMsg();
		result.setCode(200);
		
		if(roomid==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		ExerciseRoom room = exerciseRoomService.getById(roomid);
		
		if(room!=null){
			result.setMsgBody(room);
		}else{
			result.setCode(300);
		}
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	
	/**
	 * 互换身份
	 * @param courseid 课程id
	 * @param saleUserId 互换后的销售用户id
	 * @param customerId 互换后的客户id
	 * @param afterRole 互换后当前用户的角色 1销售2客户
	 * @return
	 */
	@RequestMapping(value="changeRole")
	@ResponseBody
	public String changeRole(Long courseid,Long saleUserId,Long customerId,Integer afterRole){
		ReturnExerciseResult result = new ReturnExerciseResult();
		result.setCode(200);
		
		if(courseid==null || saleUserId==null || customerId==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		
		
		ExerciseRoom room = new ExerciseRoom();
		room.setCourseid(courseid);
		room.setUserid(saleUserId);
		room.setPartnerid(customerId);
		room.setState(1);
		
		List<ExerciseRoom> roomList = exerciseRoomService.getRoomList(room);
		
		if(roomList.isEmpty()){
			exerciseRoomService.insert(room);
			result.setRoomid(room.getRoomid());
		}else{
			result.setCode(300);
			result.setRoomid(roomList.get(0).getRoomid());
		}
		
		String clientId = "";
		if(afterRole==1){
			Account user = accountService.getAccountInfoById(String.valueOf(saleUserId));
			if(user!=null){
				AccountYzx yzx = accountService.getYzx(user.getPhonenum(),"114");
				if(yzx!=null){
					clientId = yzx.getClientNumber();
				}
			}
		}
		if(afterRole==2){
			Account user = accountService.getAccountInfoById(String.valueOf(customerId));
			if(user!=null){
				AccountYzx yzx = accountService.getYzx(user.getPhonenum(),"114");
				if(yzx!=null){
					clientId = yzx.getClientNumber();
				}
			}
		}
		
		
		
		result.setClientNumber(clientId);
		result.setRole(afterRole);
		result.setUserid(saleUserId);
		result.setPartnerId(customerId);
		result.setState(1);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	/**
	 * 保存对练结果
	 * @param result
	 * @return
	 */
	@RequestMapping(value="saveResult")
	@ResponseBody
	public String saveResult(ExerciseResult result){
		ReturnExerciseResult eResult = new ReturnExerciseResult();
		eResult.setCode(200);
		
		if(result.getRoomid()==null || result.getCourseid()==null || result.getUserid()==null 
				|| result.getPartnerid()==null || StringUtils.isEmpty(result.getEvaluate()) || result.getScore()==null){
			eResult.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		ExerciseResult r = exerciseResultService.getByRoomId(result.getRoomid());
		if(r!=null){
			exerciseResultService.update(result);
		}else{
			exerciseResultService.insert(result);
		}
		
		
		
		return MobileAccountUtils.getGson().toJson(eResult);
	}
	
	
	/**
	 * 根据房间id获取对练结果
	 * @param roomid
	 * @return
	 */
	@RequestMapping(value="getResult")
	@ResponseBody
	public String getResult(Long roomid,Integer roleType){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(roleType==null){
			roleType=1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roomid", roomid);
		map.put("roleType", roleType);
		ExerciseResult r = exerciseResultService.getResultAndPartnerByRoomId(map);
		if(r!=null){
			msg.setCode(200);
		}
		
		msg.setMsgBody(r);
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 获取推荐课程
	 * @param labelid
	 * @return
	 */
	@RequestMapping(value="getRecommendCourses")
	@ResponseBody
	public String getRecommendCourses(Long labelId){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("labelId", labelId);
		map.put("size", 10);
		List<MarketingCourse> mcList = marketingCourseService.getRecommendCourses(map);
		if(!mcList.isEmpty()){
			msg.setCode(200);
		}
		
		msg.setMsgBody(mcList);
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 挂断电话的投诉记录
	 * 
	 * @param userid
	 * @param roomid
	 * @return
	 */
	@RequestMapping(value="hangupLog")
	@ResponseBody
	public String hangupLog(Long userid,Long roomid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(userid==null || roomid==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		ExerciseHangUpLog log = exerciseHangUpLogService.getByRoomid(roomid);
		
		if(log==null){
			msg.setCode(200);
			
			log = new ExerciseHangUpLog();
			log.setRoomid(roomid);
			log.setUserid(userid);
			log.setType(0);//默认都是投诉
			
			exerciseHangUpLogService.insert(log);
		}else{
			msg.setCode(300);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	@RequestMapping(value="editCourse")
	public ModelAndView editMarketingCourse(Long courseid){
		return new ModelAndView("editmarketingcourse","courseid",courseid);
	}
	
	@RequestMapping(value="saveCourse")
	public ModelAndView saveMarketingCourse(MarketingCourse c){
		ModelAndView mv = new ModelAndView("editmarketingcourse");
		
		if(c.getId()==null){
			mv.addObject("msg", "插入成功！");
			marketingCourseService.insert(c);
		}else{
			mv.addObject("msg", "保存成功！");
			marketingCourseService.update(c);
		}
		
		return mv;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(MobileAccountUtils.getRandomNum(1, 2, 1)[0]);
		System.out.println(RC4.encry_RC4_string("13167381770", RC4.KEY));
		System.out.println(RC4.encry_RC4_string("18519219161", RC4.KEY));
	}
}
