package com.hc360.mobileaccount.web.sale;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hc360.mobileaccount.po.AppVersion;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.service.AppVersionService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

@Controller
@RequestMapping(value="appVersion")
public class AppVersionController {
	
	@Resource
	private AppVersionService appVersionService;
	
	
	private String channelDownLoadUrl = "http://app.hc360.com/download/android/114/HCYellowPage_signed_aligned_IMCHANNEL.apk";
	
	
	/**
	 * 获取最新的版本信息
	 * @return
	 */
	@RequestMapping(value="getNew")
	@ResponseBody
	public String getNewVersion(String channel){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		msg.setMsgBody("");
		
		if(!StringUtils.isEmpty(channel)){
			AppVersion v = new AppVersion();
			List<AppVersion> versionList = appVersionService.getVersionList(v);
			
			if(!versionList.isEmpty()){
				
				String[] arr = channel.split("\\?");
				if(arr.length>1){
					channel = arr[0];
				}
				
				boolean isValideUrl = MobileAccountUtils.isValideUrl(channelDownLoadUrl.replace("IMCHANNEL", channel));
				
				if(isValideUrl){
					versionList.get(0).setDownUrl(channelDownLoadUrl.replace("IMCHANNEL", channel)+"?versionCode="+versionList.get(0).getVersionCode());
				}else{
					versionList.get(0).setDownUrl(channelDownLoadUrl.replace("IMCHANNEL", "default")+"?versionCode="+versionList.get(0).getVersionCode());
				}
				
				boolean isValide = MobileAccountUtils.isValideUrl(versionList.get(0).getDownUrl());
				
				if(isValide){
					msg.setCode(200);
					msg.setMsgBody(versionList.get(0));
				}
			}
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	public static void main(String[] args) {
		String s = "dsfsqwe=123";
//		System.out.println(s.substring(0, s.lastIndexOf("?")));
		
		String[] sarr = s.split("\\?");
		System.out.println(sarr);
		System.out.println(sarr.length);
		if(sarr.length>=2){
			System.out.println(sarr[0]);
		}
		
		
	}
	

	
	///////////////////////////////////版本信息后台管理/////////////////////////////////////////////

	@RequestMapping(value="save")
	public ModelAndView save(AppVersion v){
		ModelAndView mv = new ModelAndView("editappversion");
		AppVersion version = appVersionService.getById(v.getVersionid());
		
		if(version==null){
			appVersionService.insert(v);
			
			mv.addObject("msg","保存成功！");
		}else{
			appVersionService.update(v);
			
			mv.addObject("msg","更新成功！");
		}
		
		version = appVersionService.getById(v.getVersionid());
		
		mv.addObject("version",version);
		
		return mv;
	}
	@RequestMapping(value="delete")
	public ModelAndView delete(Long versionid){
		appVersionService.delete(versionid);
		
		return null;
	}
	@RequestMapping(value="getById")
	public ModelAndView get(Long versionid){
		
		AppVersion version = appVersionService.getById(versionid);
		
		return new ModelAndView("editappversion","version",version);
	}

}
