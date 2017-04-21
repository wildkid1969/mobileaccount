package com.hc360.mobileaccount.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.hc360.mobileaccount.common.UmengConstant;
import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.PushClientInfo;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.umeng.push.PushMain;

@Component
public class PushUtils {

	@Resource
	private AccountService accountService;
	
	
	/**
	 * 友盟推送
	 * @param userid
	 * @param msg
	 * @param type
	 * @param activity
	 * @param objectid
	 * @param custom
	 */
	public void umengPush(String userid,String msg,String type,String activity,String objectid,String custom){
		PushMain androidPush = new PushMain(UmengConstant.Android_APP_KEY,UmengConstant.Android_APP_MASTER_SECRET);
		PushMain iosPush = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
		
		PushClientInfo info = null;
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("ticker","企业114"+msg);
		map.put("title","企业114");
		map.put("text",msg);
		map.put("type",type);
		map.put("count","1");
		map.put("activity",activity);
		map.put("objectid",objectid);
		
		Account user = accountService.getAccountInfoById(userid);
		if(user!=null){
			info = accountService.getPushCilentInfoByPhone(user.getPhonenum());
			try {
				if(!StringUtils.isEmpty(info.getToken())){
					if(info.getToken().length()==44){
						androidPush.sendAndroidUnicastMsg(info.getToken(),null,null, null,null,custom,map);
					}
					if(info.getToken().length()==64){
						iosPush.sendIOSUnicastMsg(info.getToken(),null,null, null,null,custom,map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
