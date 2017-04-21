package com.hc360.mobileaccount.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.PaccountGoodAdvise;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.service.PaccountGoodAdviseService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.XssProtectUtils;

@Controller
@RequestMapping(value="goodAdvise")
public class GoodAdviseController {
	
	@Resource
	private PaccountGoodAdviseService paccountGoodAdviseService;
	
	
	
	/**
	 * 保存用户提的意见
	 * @param request
	 * @param advise
	 * @return
	 */
	@RequestMapping(value="saveAdvice")
	@ResponseBody
	public String saveAdvise(HttpServletRequest request,PaccountGoodAdvise advise,String callback){
		ReturnMsg result = new ReturnMsg();
		result.setCode(1);
		
		if(advise!=null && !StringUtils.isEmpty(advise.getPhone()) && StringUtils.isNumeric(advise.getPhone())
				&& !StringUtils.isEmpty(advise.getContent())){
			if(!XssProtectUtils.isHaveXSS(advise.getContent())){
				advise.setCreatetime(new Date());
				advise.setIsCheck(0);
				advise.setRank(0);
				paccountGoodAdviseService.insert(advise);
				
				result.setCode(200);
				result.setMessage("保存成功！");
			}else{
				result.setCode(2);
				result.setMessage("内容不合法！");
			}
			
		}else{
			result.setMessage("参数错误！");
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback+"("+MobileAccountUtils.getGson().toJson(result)+")";
		}
		
		
		return MobileAccountUtils.getGson().toJson(result);
	}
	
}
