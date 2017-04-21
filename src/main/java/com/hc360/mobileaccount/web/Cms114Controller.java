package com.hc360.mobileaccount.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms")
public class Cms114Controller {
	@RequestMapping(value = "search_page", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/114data/search_page";
	}

	@RequestMapping(value = "search_result_page", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewResultPage(){
		return "/114data/search_result_page";
	}

	@RequestMapping(value = "info_read", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewInfoRead(){
		return "/114data/info_read";
	}

	@RequestMapping(value = "info_update", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewInfoUpdate(){
		return "/114data/info_update";
	}
	
	@RequestMapping(value = "info_insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewInfoInsert(){
		return "/114data/info_insert";
	}
}
