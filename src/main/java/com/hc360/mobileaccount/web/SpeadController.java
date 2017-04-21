package com.hc360.mobileaccount.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.PaccountSpreadSign;
import com.hc360.mobileaccount.service.PaccountSpreadSignService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

/**
 * 推广相关接口
 * @author HC360
 *
 */
@Controller
@RequestMapping(value="spread")
public class SpeadController {
	
	@Resource
	private PaccountSpreadSignService paccountSpreadSignService;
	
	
	@RequestMapping(value="getUserSign")
	@ResponseBody
	public String getUserSign(HttpServletRequest request,String appid,String idfa){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = -1;
		
		if(!StringUtils.isEmpty(appid) && !StringUtils.isEmpty(idfa)){
			map.put("appid", appid);
			map.put("idfa", idfa);
			PaccountSpreadSign sign = paccountSpreadSignService.getSpreadSignByParam(map);
			
			if(sign==null){
				sign = new PaccountSpreadSign();
				sign.setAppid(Long.valueOf(appid));
				sign.setIdfa(idfa);
				sign.setCreatetime(new Date());
				
				paccountSpreadSignService.insert(sign);
				
				msg = 0;
			}else{
				msg = 1;
			}
			
			//{"3BC236CF-ECD8-4B16-B675-02A606EC1498":0}
			map.clear();
			map.put("\""+idfa+"\"", msg);
			
		}
		
		return MobileAccountUtils.getGson().toJson(map);
	}
}
