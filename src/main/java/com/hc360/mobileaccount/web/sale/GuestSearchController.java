package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.utils.MobileAccountUtils;


@Controller
@RequestMapping(value="guestSearch")
public class GuestSearchController {

	
	
	
	@RequestMapping(value="getHotInquiryInfo")
	@ResponseBody
	public String getHotInquiryInfo(){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		String[] hotWords = new String[]{"彩钢板","净水机","变压器"};
		List<ReturnMsg> wordList = new ArrayList<ReturnMsg>();
		
		for(String w:hotWords){
			String url = "http://s.hc360.com/getmmtlast.cgi?c=求购信息&w="+w+"&sys=mobileaccount&bus=searchInquiryInfo"
					+ "&n=0&e=5&z=中国";
			String result = MobileAccountUtils.doGet(url, "gbk");
			
			if(!StringUtils.isEmpty(result)){
				JSONObject jsonObject = JSONObject.parseObject(result);
				String allNum = jsonObject.getString("searchResultfoAllNum");
				
				//最新求购数、人工核实数
				ReturnMsg word = new ReturnMsg();
				word.setDataBody(w);
				word.setTotal(Integer.valueOf(allNum));
				
				wordList.add(word);
			}
			
		}
		
		if(!wordList.isEmpty()){
			msg.setCode(200);
		}
		
		msg.setMsgBody(wordList);
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
}
