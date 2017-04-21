package com.hc360.mobileaccount.web.sale;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserPraise;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.service.CourseChapterService;
import com.hc360.mobileaccount.service.UserPraiseService;
import com.hc360.mobileaccount.service.UserReplyService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

@Controller
@RequestMapping(value="userPraise")
public class UserPraiseController {

	
	@Resource
	private UserPraiseService userPraiseService;
	
	@Resource 
	private UserReplyService userReplyService;
	
	@Resource
	private CourseChapterService courseChapterService;
	
	/**
	 * 点赞接口
	 * @param praise
	 * @return
	 */
	@RequestMapping(value="clickALike")
	@ResponseBody
	public String clickALike(UserPraise praise){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(praise.getUserid()==null || praise.getObjectid()==null || praise.getType()==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		List<UserPraise> userPraiseList = userPraiseService.getUserPraisesByParam(praise);
		if(userPraiseList.isEmpty()){
			userPraiseService.insert(praise);
			
			if(praise.getType()==5){
				courseChapterService.addPraiseCnt(praise.getObjectid());	
			}else{
				userReplyService.addPraiseCnt(praise.getObjectid());
			}
			
			
		}else{
			msg.setCode(201);
			
			userPraiseService.deleteById(userPraiseList.get(0).getId());

			if(praise.getType()==5){
				courseChapterService.reducePraiseCnt(praise.getObjectid());
			}else{
				userReplyService.reducePraiseCnt(praise.getObjectid());	
			}
			
					
			
		}
		
		UserReply reply = userReplyService.getById(praise.getObjectid());
		if(reply!=null){
			msg.setBalance(reply.getPraiseCnt());
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
}
