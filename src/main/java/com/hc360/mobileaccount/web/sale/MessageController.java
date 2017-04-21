package com.hc360.mobileaccount.web.sale;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserMessage;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.UserMessageService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping(value="msg")
public class MessageController {

	@Resource
	private UserMessageService userMessageService;
	
	@Resource
	private AccountService accountService;
	
	
	private static final int PAGE_SIZE = 10;
	
	
	
	/**
	 * 保存留言
	 * @param msg
	 * @return
	 */
	@RequestMapping(value="save")
	@ResponseBody
	public String save(UserMessage msg,String fromPhone,String toPhone){
		ReturnMsg r = new ReturnMsg();
		r.setCode(200);
		
		if(!StringUtils.isEmpty(fromPhone)){
			fromPhone = RC4.decry_RC4(fromPhone);
			Account fromuser = accountService.getAccountInfo(fromPhone);
			if(fromuser!=null){
				msg.setFromUserid(Long.valueOf(fromuser.getAccountid()));
			}
		}
		if(!StringUtils.isEmpty(toPhone)){
			toPhone = RC4.decry_RC4(toPhone);
			Account touser = accountService.getAccountInfo(toPhone);
			if(touser!=null){
				msg.setToUserid(Long.valueOf(touser.getAccountid()));
			}
		}
		
		if(msg.getFromUserid()==null || msg.getToUserid()==null || msg.getType() ==null){
			r.setCode(100);
			return MobileAccountUtils.getGson().toJson(r);
		}
		
		
		userMessageService.insert(msg);
		
		return MobileAccountUtils.getGson().toJson(r);
	}
	
	
	
	/**
	 * 查询留言
	 * @param userid
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping(value="get")
	@ResponseBody
	public String get(Long fromUserid,Long toUserid,String type,String types,Integer page){
		ReturnMsg r = new ReturnMsg();
		r.setCode(100);
		
		if((fromUserid==null&&toUserid==null) || (StringUtils.isEmpty(type)&&StringUtils.isEmpty(types))){
			return MobileAccountUtils.getGson().toJson(r);
		}
		
		
		UserMessage msg = new UserMessage();
		if(fromUserid!=null){
			msg.setFromUserid(fromUserid);
		}
		if(toUserid!=null){
			msg.setToUserid(toUserid);
		}
		
		if(!StringUtils.isEmpty(types)){
			msg.setTypes(types.split(","));
		}
		
		if(MobileAccountUtils.isNumber(type)){
			msg.setType(Integer.valueOf(type));
		}
		int total = userMessageService.getUserMessageCount(msg);
		
		
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
			
			msg.setStart(startNum);
			msg.setSize(PAGE_SIZE);
			
			List<UserMessage> msgList = userMessageService.getUserMessageList(msg);
			
			r.setCode(200);
			r.setMsgBody(msgList);
			r.setPage(page);
			r.setTotalPage(totalPage);
			r.setTotal(total);
		}else{
			r.setCode(300);
		}
		
		return MobileAccountUtils.getGson().toJson(r);
	}
	
	/**
	 * 查询两人聊天记录
	 * @param userid
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getChat")
	@ResponseBody
	public String getChatMsg(Long fromUserid,Long toUserid,String type,String types,Integer page){
		ReturnMsg r = new ReturnMsg();
		r.setCode(100);
		
		if(fromUserid==null || toUserid==null || (StringUtils.isEmpty(type)&&StringUtils.isEmpty(types))){
			return MobileAccountUtils.getGson().toJson(r);
		}
		
		UserMessage msg = new UserMessage();
		msg.setFromUserid(fromUserid);
		msg.setToUserid(toUserid);
		
		if(!StringUtils.isEmpty(types)){
			msg.setTypes(types.split(","));
		}
		
		if(MobileAccountUtils.isNumber(type)){
			msg.setType(Integer.valueOf(type));
		}
		int total = userMessageService.getUserChatMessageCount(msg);
		
		
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
			
			msg.setStart(startNum);
			msg.setSize(PAGE_SIZE);
			
			List<UserMessage> msgList = userMessageService.getUserChatMessageList(msg);
			
			Collections.sort(msgList, new Comparator<UserMessage>() {
				@Override
				public int compare(UserMessage o1, UserMessage o2) {
					return o1.getCreatetime().compareTo(o2.getCreatetime());
				}
			});
			
			r.setCode(200);
			r.setMsgBody(msgList);
			r.setPage(page);
			r.setTotalPage(totalPage);
			r.setTotal(total);
		}else{
			r.setCode(300);
		}
		
		return MobileAccountUtils.getGson().toJson(r);
	}
	
}
