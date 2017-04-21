package com.hc360.mobileaccount.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.hc360.mobileaccount.po.ConfigUtil;
import com.hc360.mobileaccount.po.PhoneContact;
import com.hc360.mobileaccount.po.PhoneFriend;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping("/conf")
public class ConfigUtilsController {

	@Autowired
	private ConfigUtil conf;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "info")
	@ResponseBody
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return MobileAccountUtils.getGson().toJson(conf);
	}

	@RequestMapping(value = "uploadlist")
	@ResponseBody
	public String uploadContactList(@RequestBody String body, String userphone, String data) throws Exception {
		if (data != null) {
			String result = RC4.decry_RC4(data);
			List<PhoneContact> pc = MobileAccountUtils.getGson().fromJson(result, new TypeToken<List<PhoneContact>>() {
			}.getType());
//			int count = 0;
//			int bNum = 0;
			List<PhoneFriend> pf = new ArrayList<PhoneFriend>();
			for (PhoneContact p : pc) {
				StringBuffer listStr = new StringBuffer();
				for (com.hc360.mobileaccount.po.Number s : p.getNumbers()) {
					listStr.append(s.getNumber() + ",");
//					count++;
					PhoneFriend phf = new PhoneFriend();
					phf.setName(p.getName());
					phf.setPhone1(s.getNumber());
					phf.setPhone(userphone);
					pf.add(phf);
//					if (count == 30) {
//						count = 0;
//						String ccc = MobileAccountUtils.sendGet("http://openapi.m.hc360.com/openapi/v1/Corplib/linkinfo/",
//								RC4.encry_RC4_string(listStr.toString().trim(), "1bb762f7ce24ceee"));
//						listStr.delete(0,listStr.length()-1);
//						try{
//							ListUserInfo lui = MobileAccountUtils.getGson().fromJson(ccc, ListUserInfo.class);
//							if (lui != null) {
//								bNum += lui.getLinkinfos().size();
//							}
//						}catch(Exception e){}
//					}
					
					
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", pf);
			accountService.insertPhoneFirend(map);
//			if(bNum > 3){
//				System.out.println("企业用户");
//			}
		}
		return body;
	}
}
