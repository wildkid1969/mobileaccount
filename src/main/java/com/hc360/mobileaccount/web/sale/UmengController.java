package com.hc360.mobileaccount.web.sale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc360.mmt.memcached.MemcachedHelper;
import com.hc360.mmt.memcached.mo.user.ValidateKeyMO;
import com.hc360.mobileaccount.common.UmengConstant;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.po.PushManager;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.TeacherReplyInvited;
import com.hc360.mobileaccount.po.UmengMessage;
import com.hc360.mobileaccount.po.UserStudyLog;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.CourseChapterService;
import com.hc360.mobileaccount.service.PushManagerService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.service.UserStudyLogService;
import com.hc360.mobileaccount.umeng.push.PushMain;
import com.hc360.mobileaccount.umeng.wsq.WsqMain;
import com.hc360.mobileaccount.utils.DateUtils;
import com.hc360.mobileaccount.utils.FileUtils;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.PushUtils;
import com.hc360.mobileaccount.utils.RC4;
import com.hc360.mobileaccount.utils.XssProtectUtils;

@Controller
@RequestMapping(value="umeng")
public class UmengController {

	@Resource
	private AccountService accountService;
	
	@Resource
	private CourseChapterService courseChapterService;
	
	@Resource
	private UserStudyLogService userStudyLogService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	@Resource
	private PushManagerService pushManagerService;
	
	@Resource
	private PushUtils pushUtils;
	
	
	/**
	 * 获取友盟id
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "getUmengid")
	@ResponseBody
	public String getUmengid(String userid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		Account user = accountService.getAccountInfoById(userid);
		
		if(user!=null){
			if(StringUtils.isEmpty(user.getUmengid())){
				user.setUmengid("");
			}
			msg.setCode(200);
			msg.setMsgBody(user);
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	/**
	 * 绑定友盟id
	 * @param phone
	 * @param umengid
	 * @return
	 */
	@RequestMapping(value = "bindUmengid")
	@ResponseBody
	public String bindUmengid(String phone,String umengid){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(300);
		
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(umengid)){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		phone = RC4.decry_RC4(phone);
		
		Account account = accountService.getAccountInfo(phone);
		
		if(account!=null){
			Account user = new Account();
			user.setPhone(phone);
			user.setUmengid(umengid);
			accountService.updateAccount(user);
			
			msg.setCode(200);
		}
		
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 友盟绑定
	 * @param phone 用户手机
	 * @param ticker 通知栏提示文字
	 * @param token 设备唯一标识device_token
	 * @return
	 */
	@RequestMapping(value = "push/bind")
	@ResponseBody
	public String bindPushUMeng(String phone, String ticker, String token) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(token)){
			rm.setCode(100);
			return MobileAccountUtils.getGson().toJson(rm);
		}
		
		phone = RC4.decry_RC4(phone);
		
		PushClientInfo info = accountService.getPushCilentInfoByPhone(phone);
		
		if(info==null){
			accountService.savePushClientInfo(phone, token, ticker);
		}else{
			info.setToken(token);
			
//			accountService.updatePushClientInfoTokenNull(info);
			
			if(!StringUtils.isEmpty(ticker)){
				info.setTicker(ticker);
			}
			accountService.updatePushClientInfo(info);
		}
		
		
		return MobileAccountUtils.getGson().toJson(rm);
	}
	
	
	/**
	 * 友盟取消绑定
	 * @param phone 用户手机
	 * @param ticker 通知栏提示文字
	 * @param token 设备唯一标识device_token
	 * @return
	 */
	@RequestMapping(value = "push/unbind")
	@ResponseBody
	public String unbindPushUMeng(String phone, String ticker) {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(200);
		
		if(StringUtils.isEmpty(phone)){
			rm.setCode(100);
			return MobileAccountUtils.getGson().toJson(rm);
		}
		
		phone = RC4.decry_RC4(phone);
		
		accountService.deletePushClientInfoByPhone(phone);		
		
		return MobileAccountUtils.getGson().toJson(rm);
	}
	
	
	
	/**
	 * 0 通用通知 1 自定义消息
	 * @throws Exception 
	 */
	@RequestMapping(value = "leaveMsg")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String leaveMsg(UmengMessage msg) throws Exception {
		ReturnMsg rm = new ReturnMsg();
		rm.setCode(300);
		
		if((StringUtils.isEmpty(msg.getToPhone())&&msg.getToUserid()==null) || msg.getType()==null 
				|| (!StringUtils.isEmpty(msg.getFromPhone())&&msg.getFromPhone().equals(msg.getToPhone())) 
				|| (msg.getFromUserid()!=null&&msg.getFromUserid().equals(msg.getToUserid()))){
			rm.setCode(100);
			return MobileAccountUtils.getGson().toJson(rm);
		}
		

		if(msg.getToUserid()!=null){
			Account user = accountService.getAccountInfoById(String.valueOf(msg.getToUserid()));
			if(user!=null){
				msg.setToPhone(user.getPhonenum());
			}
			
		}
		
		PushClientInfo info = accountService.getPushCilentInfoByPhone(msg.getToPhone());
		
		if(info!=null){
			
			PushMain androidPush = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
			PushMain iosPush = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
			
			int umengCode = -1;
			
			if(msg.getType()==0){
				if(info.getToken().length()==44){
					rm.setMessage("android");
					umengCode = androidPush.sendAndroidUnicastNotice(info.getToken(),msg.getTicker(), msg.getTitle(), msg.getText(),msg.getActivity(),null);
				}
				
				if(info.getToken().length()==64){
					rm.setMessage("IOS");
					umengCode = iosPush.sendIOSUnicastNotice(info.getToken(),msg.getTicker(), msg.getTitle(), msg.getText(),msg.getActivity(),null);
				}
			}
			
			if(msg.getType()==1){
				Map<String,String> map = new HashMap<String,String>();
				
				if(!StringUtils.isEmpty(msg.getExtraJsonStr())){
					map = JSONObject.parseObject(msg.getExtraJsonStr(),Map.class);
				}
				
				
				if(info.getToken().length()==44){
					rm.setMessage("android");
					umengCode = androidPush.sendAndroidUnicastMsg(info.getToken(),msg.getTicker(), msg.getTitle(), msg.getText(),msg.getActivity(), msg.getCustom(),map);
				}
				if(info.getToken().length()==64){
					rm.setMessage("IOS");
					umengCode = iosPush.sendIOSUnicastMsg(info.getToken(),msg.getTicker(), msg.getTitle(), msg.getText(),msg.getActivity(), msg.getCustom(),map);
				}
			}
			
			rm.setCode(umengCode);
			
		}
		
		
		return MobileAccountUtils.getGson().toJson(rm);
	}
	
	
	/**
	 * 给长时间未登录的用户推送相关视频
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 20 * * ?")
	public void pushNologinUsersChapters() throws Exception{
		List<Account> threeDayUserList = accountService.getNologinUserList(DateUtils.addDayStr(new Date(), -3));
		List<Account> sevenDayUserList = accountService.getNologinUserList(DateUtils.addDayStr(new Date(), -7));
		
		String msg1 = "小编根据你的观看历史，为你整理了同类视频，快去看看吧！";
		String msg2 = "已经有"+MobileAccountUtils.getRandomNum(50, 300, 1)[0]+"人在线时长超过你，请不要停止前进的步伐哟！";
		
		String msg3 = "这么热门的视频都没看过？赶紧去看看大家都在看什么！";
		String msg4 = "这个视频太火了！没看的赶紧去恶补一下！";
		
		PushMain androidPush = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		PushMain iosPush = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
		
		PushClientInfo info = null;
		
		UserStudyLog log = new UserStudyLog();
		List<UserStudyLog> logList = null;
		String objectid = "";
		Map<Long,Integer> chapMap = new HashMap<Long,Integer>();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("title","企业114");
		map.put("type","2");
		map.put("count","1");
		
		//3天未登录
		for(int i=0;i<threeDayUserList.size();i++){
			chapMap = recommendSimilarChapter(Long.valueOf(threeDayUserList.get(i).getAccountid()));
			if(!chapMap.isEmpty()){
				objectid = String.valueOf(chapMap.keySet().iterator().next());
				map.put("ticker","企业114"+msg1);
				map.put("text",msg1);
			}else{
				List<Long> chapterids = userStudyLogService.getPopChapterids(1);
				if(!chapterids.isEmpty()){
					objectid = String.valueOf(chapterids.get(0));
				}
				
				map.put("ticker","企业114"+msg3);
				map.put("text",msg3);
			}
			
			log.setUserid(Long.valueOf(threeDayUserList.get(i).getAccountid()));
			log.setChapterid(Long.valueOf(objectid));
			logList = userStudyLogService.getUserStudyLogList(log);
			if(logList.get(0).getChaptype()==1){
				map.put("activity","com.hc360.yellowpage.ui.VideoPlayActivity");
			}
			if(logList.get(0).getChaptype()==2){
				map.put("activity","com.hc360.yellowpage.ui.AudioPlayActivity");
			}
			
			map.put("objectid",objectid);
			info = accountService.getPushCilentInfoByPhone(threeDayUserList.get(i).getPhonenum());
			
			if(info.getToken().length()==44){
				androidPush.sendAndroidUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
			}
			if(info.getToken().length()==64){
				iosPush.sendIOSUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
			}
		}
		
		
		//7天未登录
		map.put("ticker","企业114"+msg2);
		map.put("text",msg2);
		for(int i=0;i<sevenDayUserList.size();i++){
			chapMap = recommendSimilarChapter(Long.valueOf(sevenDayUserList.get(i).getAccountid()));
			if(!chapMap.isEmpty()){
				objectid = String.valueOf(chapMap.keySet().iterator().next());
				map.put("ticker","企业114"+msg1);
				map.put("text",msg1);
			}else{
				List<Long> chapterids = userStudyLogService.getPopChapterids(1);
				if(!chapterids.isEmpty()){
					objectid = String.valueOf(chapterids.get(0));
				}
				map.put("ticker","企业114"+msg4);
				map.put("text",msg4);
			}
			
			log.setUserid(Long.valueOf(sevenDayUserList.get(i).getAccountid()));
			log.setChapterid(Long.valueOf(objectid));
			logList = userStudyLogService.getUserStudyLogList(log);
			if(logList.get(0).getChaptype()==1){
				map.put("activity","com.hc360.yellowpage.ui.VideoPlayActivity");
			}
			if(logList.get(0).getChaptype()==2){
				map.put("activity","com.hc360.yellowpage.ui.AudioPlayActivity");
			}
			
			map.put("objectid",objectid);
			info = accountService.getPushCilentInfoByPhone(sevenDayUserList.get(i).getPhonenum());

			if(info.getToken().length()==44){
				androidPush.sendAndroidUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
			}
			if(info.getToken().length()==64){
				iosPush.sendIOSUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
			}
		}
	}
	
	
	
	/**
	 * 给昨天没看完视频的用户推送通知继续完成学习进度
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 0 20 * * ?")
	public void pushExitChapterUsersChapters() throws Exception{
		UserStudyLog log = new UserStudyLog();
		log.setChaptype(1);
		log.setVideopoint(0l);
		log.setDateStr(DateUtils.addDayStr(new Date(), -1));
		List<UserStudyLog> logList = userStudyLogService.getUserStudyLogList(log);
		
		if(!logList.isEmpty()){
			PushMain androidPush = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
			PushMain iosPush = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
			
			PushClientInfo info = null;
			String msg = "还记得昨天没看完的视频吗？马上继续观看吧~";
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("ticker","企业114"+msg);
			map.put("title","企业114");
			map.put("text",msg);
			map.put("type","2");
			map.put("count","1");
			
			Account user = null;
			for(int i=0;i<logList.size();i++){
				user = accountService.getAccountInfoById(String.valueOf(logList.get(i).getUserid()));
				
				if(logList.get(i).getChaptype()==1){
					map.put("activity","com.hc360.yellowpage.ui.VideoPlayActivity");
				}
				if(logList.get(i).getChaptype()==2){
					map.put("activity","com.hc360.yellowpage.ui.AudioPlayActivity");
				}
				
				map.put("objectid",String.valueOf(logList.get(i).getChapterid()));
				info = accountService.getPushCilentInfoByPhone(user.getPhonenum());
				
				if(info.getToken().length()==44){
					androidPush.sendAndroidUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
				}
				if(info.getToken().length()==64){
					iosPush.sendIOSUnicastMsg(info.getToken(),null,null, null,null,"chapter",map);
				}
			}
		}
		
		
	}
	
	/**
	 * 9:00  您有XX条录音等待您评测 
	 * 12:00  您有XX条提问等待您回答 
	 * 
	 * 推送类型  1弹框通知（应用弹出提示框，可跳转指定页面）  2通知栏通知（章节推荐） 345原先的推送（企业活动/新闻/通知消息） 
	 * 6营销对练邀请  7用户对用户消息（留言/评论） 8 营销对练通知，不显示不保存（用户对练同意/拒绝/退出/结束对练）
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 9,12,15,20 * * ?")
	public void pushCommentCntToTeachers() throws Exception{

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", 1);//回复类型1话术评测2用户提问
		map.put("endTime", new Date());
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, -3);
		Date date = c.getTime();  
		map.put("beginTime", date);
		
		TeacherReplyInvited in = new TeacherReplyInvited();
		in.setIsReply(0);
		in.setType(1);
		
		String msg = "您有XX条录音等待您评测";
		String activity = "com.hc360.yellowpage.ui.SoundEvaluateActivity";
		
		int hour = DateUtils.getHour(new Date());
		if(hour==9){
			msg = "您有XX条录音等待您评测";
			activity = "com.hc360.yellowpage.ui.SoundEvaluateActivity";
			
			in.setType(1);//回复类型1话术评测2用户提问
			
			Calendar cc = Calendar.getInstance();
			cc.add(Calendar.HOUR, -15);
			Date d = cc.getTime();  
			map.put("beginTime", d);//昨天20点
			
		}else if(hour==12){
			msg = "您有XX条提问等待您回答";
			activity = "com.hc360.yellowpage.ui.QuestionEvaluateActivity";
			
			in.setType(2);

		}else if(hour==15){
			msg = "您有XX条录音等待您评测";
			activity = "com.hc360.yellowpage.ui.SoundEvaluateActivity";
			
			in.setType(1);
			
		}else if(hour==20){
			msg = "您有XX条提问等待您回答";
			activity = "com.hc360.yellowpage.ui.QuestionEvaluateActivity";
			
			in.setType(2);
			
		}
		
		List<TeacherReplyInvited> inList = saleTeacherService.getInvitedTeacherList(in);
		
		int count = 0;
		for(TeacherReplyInvited i:inList){
			map.put("userid", i.getUserid());
			count = saleTeacherService.getInvitedRepliesCount(map);
			if(count>0){
				pushUtils.umengPush(String.valueOf(i.getUserid()), msg.replace("XX", count+""), "1", activity, "0", "tips");
			}
		}
		
		
		
		
		
	}
	
	
	
	/**
	 * 根据产品运营后台编辑 定时推送给用户精品问答或社区帖子
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 0/2 * * ?")
	public void pushCustomContent() throws Exception{
		//获取当前小时
		Calendar can = Calendar.getInstance();
		Integer hour = can.get(Calendar.HOUR_OF_DAY);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		
		//先查出今天要推送的数据
		List<PushManager> pushList = pushManagerService.getPushManagerList(null);
		
		String hourStr = "";
		
		Iterator<PushManager> iterator = pushList.iterator();
        while(iterator.hasNext()){
        	PushManager p = iterator.next();
			hourStr = sdf.format(p.getPushtime());
			if(!hourStr.equals(String.valueOf(hour))){
				//移除不是当前小时的
				iterator.remove();
				if(pushList.isEmpty()){
					break;
				}
			}
			
		}
        
        PushMain androidPush = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		PushMain iosPush = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
		
		
		for(PushManager p:pushList){
			
			int umengCode = -1;
			
			if(!StringUtils.isEmpty(p.getToPhone())){//给特定用户推送
				
				PushClientInfo info = accountService.getPushCilentInfoByPhone(p.getToPhone());
				
				if(info!=null){
					if(info.getToken().length()==44){
						umengCode = androidPush.sendAndroidUnicastNotice(info.getToken(),p.getTicker(), p.getTitle(), p.getContent(),p.getAppActivity(),p.getObjectid()+"");
					}
					
					if(info.getToken().length()==64){
						umengCode = iosPush.sendIOSUnicastNotice(info.getToken(),p.getTicker(), p.getTitle(), p.getContent(),p.getAppActivity(),p.getObjectid()+"");
					}
				}
				
				System.out.println(umengCode);
			}else{//给全员推送
				List<PushClientInfo> clientInfoList = accountService.getPushCilentInfoList();
				
				for(PushClientInfo i:clientInfoList){
					if(i.getToken().length()==44){
						umengCode = androidPush.sendAndroidUnicastNotice(i.getToken(),p.getTicker(), p.getTitle(), p.getContent(),p.getAppActivity(),p.getObjectid()+"");
					}
					
					if(i.getToken().length()==64){
						umengCode = iosPush.sendIOSUnicastNotice(i.getToken(),p.getTicker(), p.getTitle(), p.getContent(),p.getAppActivity(),p.getObjectid()+"");
					}
				}
			}
			
		}
		
	}
	
	
	
	/**
	 * 根据用户学习过章节推荐相似章节
	 * @param userid
	 * @return
	 */
	public Map<Long,Integer> recommendSimilarChapter(Long userid){
		//用户学习过的章节(id,labelids)
		List<CourseChapter> studiedLabelidList = courseChapterService.getStudiedChapLabelList(userid);
		//用户学的章节里的标签(不重复)
		List<String> userLabelList = new ArrayList<String>();
		//用户学习过的章节id
		List<Long> userChapteridList = new ArrayList<Long>();
		
		for(CourseChapter cc:studiedLabelidList){
			userChapteridList.add(cc.getId());
			if(!StringUtils.isEmpty(cc.getLabelids())){
				for(String lid:cc.getLabelids().split(",")){
					if(!userLabelList.contains(lid) && !StringUtils.isEmpty(lid)){
						userLabelList.add(lid);
					}
				}
			}
		}
		
		
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		if(!userLabelList.isEmpty()){
			//所有章节
			List<CourseChapter> chapterList = courseChapterService.getAll();
			
			for(int i=0,s=chapterList.size();i<s;i++){
				//不包含学习过的章节
				if(!StringUtils.isEmpty(chapterList.get(i).getLabelids()) && !userChapteridList.contains(chapterList.get(i).getId())){
					int scount = 0;
					for(String lid:chapterList.get(i).getLabelids().split(",")){
						//包含一次记录一次
						if(userLabelList.contains(lid)){
							scount++;
						}
					}
					
					map.put(chapterList.get(i).getId(),scount);
				}
				
			}
			
		}
		
		return MobileAccountUtils.sortMapByValue(map);
		
	}
	
	
	/**
	 * 友盟微社区发帖子
	 * @param phone
	 * @param content
	 * @param imgStr
	 * @return
	 */
	@RequestMapping(value="pubFeed")
	@ResponseBody
	public String pubFeed(String phone,String title,String content,String imgStr,String key,String validCode,
			String callback,HttpServletResponse response){
		ReturnMsg msg = new ReturnMsg();
    	msg.setCode(300);
    	
    	response.setHeader("Access-Control-Allow-Origin", "*");
//    	response.setHeader("Access-Control-Allow-Origin", "http://168.mobile.hc360.com");
//		response.setHeader("Access-Control-Allow-Origin", "http://testwx.mdata.hc360.com");
		response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    	
    	if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(content) || content.replace("<br />", "").length()>2000 
    			|| StringUtils.isEmpty(key) || StringUtils.isEmpty(validCode)){
    		msg.setCode(100);
    		
    		if(!StringUtils.isEmpty(callback)){
        		return callback + "("+MobileAccountUtils.getGson().toJson(msg) +")";
        	}
    		
    		return MobileAccountUtils.getGson().toJson(msg);
    	}
    	
    	//后台校验验证码是否正确 
		String valiResult = MobileAccountUtils.doGet("http://sso.hc360.com/ValidJsonCode.jsp?ctoken="+key+"&validCode="+validCode,"gbk"); 
		boolean codeFlag = MemcachedHelper.remove(key,ValidateKeyMO.class); 
		
		System.out.println("valiResult:"+valiResult+";ctoken:"+key+";valicode:"+validCode);
		
		if(!valiResult.contains("msg:\"1\"") || !codeFlag){ 
			msg.setCode(400);
			
			if(!StringUtils.isEmpty(callback)){
        		return callback + "("+MobileAccountUtils.getGson().toJson(msg) +")";
        	}
			
			return MobileAccountUtils.getGson().toJson(msg);
		}
    	
		Account account = accountService.getAccountInfo(phone);
		
		if(account!=null){
			msg.setCode(200);
			WsqMain.pubFeed(phone,"",XssProtectUtils.cleanXSS(title),"点击查看原文",FileUtils.htmlToImage(phone,content));
		}
    	
    	if(!StringUtils.isEmpty(callback)){
    		return callback + "("+MobileAccountUtils.getGson().toJson(msg) +")";
    	}
    	
    	return MobileAccountUtils.getGson().toJson(msg);
	}
	
	public static void main(String[] args) throws Exception {
		PushMain push = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		String msg = "您有5条录音等待您评测";
		Map<String,String> map = new HashMap<String,String>();
		map.put("ticker","企业114");
		map.put("title","企业114");
		map.put("text",msg);
		map.put("type","1");
		map.put("objectid","");
		
		map.put("count","1");
		
		map.put("activity","com.hc360.yellowpage.ui.SoundEvaluateActivity");
		push.sendAndroidUnicastMsg("AoNYzQ8JKhAJVjJuQVteuldpSCVI04FqcQGA1vmMtyqO",null,null, msg,null,"tips",map);
		
		
		Calendar can = Calendar.getInstance();
		int hour = can.get(Calendar.HOUR_OF_DAY);
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String date = sdf.format(d);
		
		String date2 = new String(date);
		
		System.out.println(hour+","+date.equals(hour+""));
		System.out.println(date.equals(date2));
		System.out.println(String.valueOf(null));

	}
	
	
	
}
