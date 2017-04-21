package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.Date;
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
import com.hc360.mobileaccount.po.ExerciseResult;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.ReturnTalkResult;
import com.hc360.mobileaccount.po.TeacherReplyInvited;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.service.ExerciseResultService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserReplyService;
import com.hc360.mobileaccount.service.UserTalkService;
import com.hc360.mobileaccount.utils.DateUtils;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping(value="userTalk")
public class UserTalkController {
	
	@Resource
	private UserTalkService userTalkService;

	@Resource
	private UserReplyService userReplyService;
	
	@Resource
	private ExerciseResultService exerciseResultService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	private static final int PAGE_SIZE = 10;
	
	@RequestMapping(value="index")
	@ResponseBody
	public String userTalkIndex(Long userid){
		ReturnTalkResult result = new ReturnTalkResult();
		result.setCode(200);
		
		if(userid==null){
			result.setCode(100);
			return MobileAccountUtils.getGson().toJson(result);
		}
		
		//我的评测数
		UserTalk talk = new UserTalk();
		talk.setUserid(userid);
		int myTalkCount = userTalkService.getUserTalkCount(talk);
		
		result.setMyTalkCount(myTalkCount);
		
		//我的对练记录数
		ExerciseResult eResult = new ExerciseResult();
		eResult.setUserid(userid);
		int myExerciseCount = exerciseResultService.getExerciseResultCount(eResult);
		
		result.setMyExerciseCount(myExerciseCount);
		
		//话术评测参与用户
		int userTalksCount = userTalkService.getUserTalkCount(null);
		List<Account> talkList = userTalkService.getTodayTalkUsers(DateUtils.dateToStr(new Date(),"yyyy-MM-dd"));
		
		result.setTalkUserCount(userTalksCount);
		result.setTalkUserList(talkList);
		
		//营销对练参与用户
		int userExerciseCount = exerciseResultService.getExerciseResultCount(null);
		List<Account> exerciseList = exerciseResultService.getTodayExerciseUsers(DateUtils.dateToStr(new Date(),"yyyy-MM-dd"));
		
		result.setExerciseUserCount(userExerciseCount);
		result.setExerciseUserList(exerciseList);
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
	
	
	/**
	 * 我的评测记录数
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getMyTalkCount")
	@ResponseBody
	public String getMyTalkCount(Long userid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(userid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//查找总数
		UserTalk talk = new UserTalk();
		talk.setUserid(userid);
		int total=userTalkService.getUserTalkCount(talk);
		
		msg.setMsgBody(total);

		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	/**
	 * 我的评测记录
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getMyTalkList")
	@ResponseBody
	public String getMyTalkList(Long userid,Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(userid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//查找总数
		UserTalk talk = new UserTalk();
		talk.setUserid(userid);
		int total=userTalkService.getUserTalkCount(talk);

		int startNum=0;
		int totalPage=0;
		List<UserTalk> talkList = null;
		
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
			
			talk.setStart(startNum);
			talk.setSize(PAGE_SIZE);
			talkList = userTalkService.getUserTalkList(talk);
			
			if(!talkList.isEmpty()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("replyType", 1);//回复类型
				map.put("type", 1);//点赞类型
				for(int i=0; i<talkList.size(); i++){
					map.put("objectID", talkList.get(i).getId());
					talkList.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
					talkList.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
				}
				
				msg.setMsgBody(talkList);
			}

			msg.setPage(page);
			msg.setTotalPage(totalPage);
		}else{
			msg.setCode(100);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	/**
	 * 我的对练记录
	 * @return
	 */
	@RequestMapping(value="getMyExerciseList")
	@ResponseBody
	public String getMyExerciseList(Long userid,Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(userid==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//查找图书总数
		int total=exerciseResultService.getExerciseResultUnionCount(String.valueOf(userid));

		int startNum=0;
		int totalPage=0;
		List<ExerciseResult> resultList = null;
		
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
			
			ExerciseResult result = new ExerciseResult();
			result.setUserid(userid);
			result.setStart(startNum);
			result.setSize(PAGE_SIZE);
			resultList = exerciseResultService.getExerciseResultList(result);
			
			if(!resultList.isEmpty()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("replyType", 2);//回复类型
				map.put("type", 2);//点赞类型
				for(int i=0; i<resultList.size(); i++){
					map.put("objectID", resultList.get(i).getId());
					resultList.get(i).setUserNreplys(saleTeacherService.getObjectReplys(map));
					resultList.get(i).setUserPraises(saleTeacherService.getUserPraises(map));
				}
				
				msg.setMsgBody(resultList);
			}
			
			msg.setPage(page);
			msg.setTotalPage(totalPage);
		}else{
			msg.setCode(100);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 保存我的录音
	 * @param talk
	 * @return
	 */
	@RequestMapping(value="saveMyTalk")
	@ResponseBody
	public String saveMyTalk(UserTalk talk){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(StringUtils.isEmpty(talk.getAudioUrl()) || talk.getUserid()==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		if(talk.getIsShowName()==null){
			talk.setIsShowName(1);
		}
		
		talk.setAudioUrl(RC4.decry_RC4(talk.getAudioUrl()));
		
		userTalkService.insert(talk);
		
		//批量插入老师的邀请
		List<Long> teacherids = saleTeacherService.getAllTeacherIds();
		if(!teacherids.isEmpty()){
			List<TeacherReplyInvited> inviteList = new ArrayList<TeacherReplyInvited>();
			for(Long tid:teacherids){
				TeacherReplyInvited tt = new TeacherReplyInvited();
				tt.setInvitedid(talk.getId());
				tt.setType(1);
				tt.setUserid(tid);
				
				inviteList.add(tt);
			}
			
			saleTeacherService.batchInsertTeacherReplyInvited(inviteList);
			
			//向所有老师推送消息
			
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 最新话术评测列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="userTalkList")
	@ResponseBody
	public String userTalkList(Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		UserTalk talk = new UserTalk();
		int total = userTalkService.getUserTalkCount(talk);
		
		int totalPage = 0;
		int startNum = 0;
		
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
			
			talk.setStart(startNum);
			talk.setSize(PAGE_SIZE);
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
				
				msg.setCode(200);
				msg.setMsgBody(talkList);
			}
			
			msg.setPage(page);
			msg.setTotalPage(totalPage);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	@RequestMapping(value="getTalk")
	@ResponseBody
	public String getTalk(Long talkid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		UserTalk talk = userTalkService.getById(talkid);
		if(talk!=null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("replyType", 1);//回复类型
			map.put("type", 1);//点赞类型
			map.put("objectID", talk.getId());
			talk.setUserNreplys(saleTeacherService.getObjectReplys(map));
			talk.setUserPraises(saleTeacherService.getUserPraises(map));
			
			msg.setCode(200);
			msg.setMsgBody(talk);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	@RequestMapping(value="upload")
	public ModelAndView upload(){
		return new ModelAndView("upload");
	}
	
}
