package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserConcernObject;
import com.hc360.mobileaccount.po.UserNreply;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserConcernObjectService;
import com.hc360.mobileaccount.service.UserNreplyService;
import com.hc360.mobileaccount.service.UserQuestionService;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

@Controller
@RequestMapping(value="userQuestion")
public class UserQuestionController {
	
	@Resource 
	private AccountService accountService;
	
	@Resource
	private UserQuestionService userQuestionService;
	
	@Resource
	private UserNreplyService userNreplyService;
	
	@Resource
	private UserConcernObjectService userConcernObjectService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	
	private static final int PAGE_SIZE = 10;
	
	
	
	/**
	 * 删除我的问题
	 * @param questionid
	 * @return
	 */
	@RequestMapping(value="deleteMyQuestion")
	@ResponseBody
	public String deleteMyQuestion(String questionid,String userid,String sign){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(500);
		
		if(userid==null || questionid==null ||sign==null){
			msg.setCode(100); 
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//参数签名验证
		boolean signFlag = MD5.verify(questionid+userid, sign, MD5.KEY, "utf-8");
		
		if(signFlag){
			UserQuestion uq = userQuestionService.getById(Long.valueOf(questionid));
			
			if(uq!=null && String.valueOf(uq.getUserid()).equals(userid)){//是否是自己的问题
				int flag = userQuestionService.deleteById(Long.valueOf(questionid));
				
				if(flag==0){
					msg.setCode(200);
				}else{
					msg.setCode(300);
				}
			}else{
				msg.setCode(400);
			}
			
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	
	
	/**
	 * 关注
	 * type 1话术评测 2问题
	 * @param questionid
	 * @return
	 */
	@RequestMapping(value="concernObject")
	@ResponseBody
	public String concernObject(UserConcernObject obj){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(300);
		
		if(obj.getObjectid()==null || obj.getUserid()==null | obj.getType()==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		List<UserConcernObject> objList = userConcernObjectService.getUserConcernObjectList(obj);
		
		if(objList.isEmpty()){
			msg.setCode(200);
			userConcernObjectService.insert(obj);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	/**
	 * 取消关注
	 * type 1话术评测 2问题
	 * @param questionid
	 * @return
	 */
	@RequestMapping(value="unconcernObject")
	@ResponseBody
	public String unconcernObject(UserConcernObject obj){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(obj.getObjectid()==null || obj.getUserid()==null | obj.getType()==null){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		userConcernObjectService.delete(obj);
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	
	
	/**
	 * 获取我的关注列表
	 * @param userid
	 * @param type 1话术 2问题
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getMyConcernList")
	@ResponseBody
	public String getMyConcernList(Long userid,Integer type,Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(userid==null || type==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		UserConcernObject concernObj = new UserConcernObject();
		concernObj.setUserid(userid);
		concernObj.setType(type);
		
		int total = userConcernObjectService.getUserConcernObjectCount(concernObj);
		
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
			
			concernObj.setStart(startNum);
			concernObj.setSize(PAGE_SIZE);
			List<UserConcernObject> objList = userConcernObjectService.getUserConcernObjectList(concernObj);
			
			if(!objList.isEmpty()){
				List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>();
				UserQuestion uq = null;
				
				for(UserConcernObject o:objList){
					uq = userQuestionService.getById(o.getObjectid());
					userQuestionList.add(uq);
				}
				
				
				
				int userConcernCount = 0;
				int praiseCnt = 0;
				int myReplyCnt = 0;
				
				UserConcernObject obj = new UserConcernObject();
				obj.setType(2);
				
				UserNreply unr = new UserNreply();
				unr.setUserid(userid);
				unr.setReplyType(4);
				
				//处理集合
				for(int i=0; i<userQuestionList.size(); i++){
					//点赞数
					unr.setObjectid(userQuestionList.get(i).getId());
					praiseCnt = userNreplyService.getUserPraiseCount(unr);
					userQuestionList.get(i).setPraiseCnt(praiseCnt);
					
					//我的回复数
					myReplyCnt = userNreplyService.getUserReplyCount(unr);
					userQuestionList.get(i).setMyReplyCnt(myReplyCnt);
					
					//关注数
					obj.setObjectid(userQuestionList.get(i).getId());
					userConcernCount = userConcernObjectService.getUserConcernObjectCount(obj);
					userQuestionList.get(i).setConcernCnt(userConcernCount);
				}
				
				msg.setCode(200);
				msg.setMsgBody(userQuestionList);
				msg.setPage(page);
				msg.setTotalPage(totalPage);
			}
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
		
	}
	
	
	
	/**
	 * 获取我参与的问题列表
	 * @param userid
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getMyRelatedQuestionList")
	@ResponseBody
	public String getMyRelatedQuestionList(Long userid,Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(userid==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		List<Long> questionids = userNreplyService.getMyRelatedQuestionIdList(userid);
		List<Long> questionids2 = userQuestionService.getMyQuestionIdList(userid);
		
		//无重复交集
		questionids2.removeAll(questionids);
		questionids.addAll(questionids2);
		
		int startNum=0;
		int toNum = 0;
		int totalPage=0;
		
		int total = questionids.size();
		
		if(total>0){
			
			//把问题id集合倒序处理 (即时间倒序)
			Collections.sort(questionids, new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return o2.compareTo(o1);
				}
			});
			
			//把list分页
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
			
			toNum = startNum+PAGE_SIZE;
			
			if(toNum>total){
				toNum = total;
			}
			
			//subList返回的仅仅是个视图,大数据时慎用,会很耗内存
			questionids = questionids.subList(startNum, toNum);
			
			List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>();
			UserQuestion uq = null;
			
			for(Long id:questionids){
				uq = userQuestionService.getById(id);
				userQuestionList.add(uq);
			}
			
			int userConcernCount = 0;
			int praiseCnt = 0;
			int myReplyCnt = 0;
			
			UserConcernObject obj = new UserConcernObject();
			obj.setType(2);
			
			UserNreply unr = new UserNreply();
			unr.setUserid(userid);
			unr.setReplyType(4);
			
			//处理集合
			for(int i=0; i<userQuestionList.size(); i++){
				//点赞数
				unr.setObjectid(userQuestionList.get(i).getId());
				praiseCnt = userNreplyService.getUserPraiseCount(unr);
				userQuestionList.get(i).setPraiseCnt(praiseCnt);
				
				//我的回复数
				myReplyCnt = userNreplyService.getUserReplyCount(unr);
				userQuestionList.get(i).setMyReplyCnt(myReplyCnt);
				
				//关注数
				obj.setObjectid(userQuestionList.get(i).getId());
				userConcernCount = userConcernObjectService.getUserConcernObjectCount(obj);
				userQuestionList.get(i).setConcernCnt(userConcernCount);
			}
			
			msg.setCode(200);
			msg.setMsgBody(userQuestionList);
			msg.setPage(page);
			msg.setTotalPage(totalPage);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 我的回复列表
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getMyReplyList")
	@ResponseBody
	public String getMyReplyList(String userid,String objectid,Integer type){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(300); 
		
		if(userid==null || objectid==null ||type==null){
			msg.setCode(100); 
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("replyType", type);//回复类型:1话术评测 2营销对练 3章节(暂不用) 4用户提问
		map.put("objectID", objectid);
		map.put("userid", userid);
		List<UserNreply> myReplyList = saleTeacherService.getObjectReplys(map);
		
		if(!myReplyList.isEmpty()){
			msg.setCode(200); 
			msg.setMsgBody(myReplyList);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}

	
	
	public static void main(String[] args) {
		List<Long> i1 = new ArrayList<Long>();
		List<Long> i2 = new ArrayList<Long>();
		i1.add(1l);
		i1.add(2l);
		i1.add(3l);
		i1.add(4l);
		i2.add(1l);
		i2.add(2l);
		i2.add(3l);
		i2.add(5l);
		
		i2.removeAll(i1);
		i1.addAll(i2);
		
		Collections.sort(i1, new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o2.compareTo(o1);
			}
		});
		
		System.out.println(i1.size());
		System.out.println(i1.subList(0, 5));
		
		System.out.println(MD5.sign("145220131657", MD5.KEY, "utf-8"));
		
	}

}
