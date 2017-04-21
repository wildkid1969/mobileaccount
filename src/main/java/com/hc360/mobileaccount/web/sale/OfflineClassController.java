package com.hc360.mobileaccount.web.sale;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.OfflineChapter;
import com.hc360.mobileaccount.po.OfflineClass;
import com.hc360.mobileaccount.po.OfflineRegisterUser;
import com.hc360.mobileaccount.po.OfflineTeacher;
import com.hc360.mobileaccount.po.OfflineTeacherNclass;
import com.hc360.mobileaccount.po.OfflineUserRegister;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.service.OfflineChapterService;
import com.hc360.mobileaccount.service.OfflineClassService;
import com.hc360.mobileaccount.service.OfflineRegisterUserService;
import com.hc360.mobileaccount.service.OfflineTeacherNclassService;
import com.hc360.mobileaccount.service.OfflineTeacherService;
import com.hc360.mobileaccount.service.OfflineUserRegisterService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.XssProtectUtils;

@Controller
@RequestMapping(value="offlineClass")
public class OfflineClassController {

	
	@Resource
	private OfflineClassService offlineClassService;
	
	@Resource
	private OfflineTeacherNclassService offlineTeacherNclassService;
	
	@Resource
	private OfflineTeacherService offlineTeacherService;
	
	@Resource
	private OfflineChapterService offlineChapterService;
	
	@Resource
	private OfflineRegisterUserService userService;
	
	@Resource
	private OfflineUserRegisterService registerService;
	
	
	private static final int PAGE_SIZE = 10;
	
	
	/**
	 * 课程列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getClassList")
	@ResponseBody
	public String getClassList(Integer page){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		OfflineClass clazz = new OfflineClass();
		int total = offlineClassService.getOfflineClassCount(clazz);
		
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
			
			clazz.setStart(startNum);
			clazz.setSize(PAGE_SIZE);
			List<OfflineClass> classList = offlineClassService.getOfflineClassList(clazz);
			
			msg.setCode(200);
			msg.setPage(page);
			msg.setTotalPage(totalPage);
			msg.setMsgBody(classList);
			
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 课程详情
	 * @param classid
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="getClass")
	@ResponseBody
	public String getClass(Long classid,String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		OfflineClass clazz = offlineClassService.getById(classid);
		
		if(clazz!=null){
			//查询讲师信息
			OfflineTeacherNclass tnc = new OfflineTeacherNclass();
			tnc.setClassid(classid);
			List<OfflineTeacherNclass> tncList = offlineTeacherNclassService.getOfflineTeacherNclassList(tnc);
			
			if(!tncList.isEmpty()){
				OfflineTeacher teacher = offlineTeacherService.getById(tncList.get(0).getTeacherid());
				
				if(teacher!=null){
					msg.setDataBody(teacher);
				}
			}
			
			//查询课程里的章节列表
			OfflineChapter chapter = new OfflineChapter();
			chapter.setClassid(classid);
			List<OfflineChapter> chapterList = offlineChapterService.getOfflineChapterList(chapter);
			clazz.setChapterList(chapterList);
			
			msg.setCode(200);
			msg.setMsgBody(clazz);
			
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback + "(" + MobileAccountUtils.getGson().toJson(msg) + ")";
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	/**
	 * 章节详情
	 * @param chapterid
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="getChapter")
	@ResponseBody
	public String getChapter(Long chapterid,String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		OfflineChapter chapter = offlineChapterService.getById(chapterid);
		
		if(chapter!=null){
			msg.setCode(200);
			msg.setMsgBody(chapter);
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback + "(" + MobileAccountUtils.getGson().toJson(msg) + ")";
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 用户报名
	 * @param user
	 * @return
	 */
	@RequestMapping(value="enroll")
	@ResponseBody
	public String enroll(OfflineRegisterUser user,Long classid,String location,String callback){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(300);
		
		if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPhone()) 
				|| StringUtils.isEmpty(location)  || classid==null){
			msg.setCode(100);
			
			if(!StringUtils.isEmpty(callback)){
				return callback + "(" + MobileAccountUtils.getGson().toJson(msg) + ")";
			}
			
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//XSS过滤
		user.setName(XssProtectUtils.cleanXSS(user.getName()));
		user.setPhone(XssProtectUtils.cleanXSS(user.getPhone()));
		
		//插入用户表
		OfflineRegisterUser reUser = userService.getByPhone(user.getPhone());
		if(reUser==null){
			userService.insert(user);
		}else{
			user.setUserid(reUser.getUserid());
		}
		
		//插入报名表
		OfflineUserRegister register = new OfflineUserRegister();
		register.setClassid(classid);
		register.setUserid(user.getUserid());
		List<OfflineUserRegister> registerList = registerService.getOfflineUserRegisterList(register);
		
		if(registerList.isEmpty()){
			msg.setCode(200);
			
			register.setLocation(XssProtectUtils.cleanXSS(location));
			registerService.insert(register);
		}
		
		
		
		if(!StringUtils.isEmpty(callback)){
			return callback + "(" + MobileAccountUtils.getGson().toJson(msg) + ")";
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
}
