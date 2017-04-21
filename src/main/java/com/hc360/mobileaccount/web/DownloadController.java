package com.hc360.mobileaccount.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.utils.MobileAccountUtils;

@Controller
@RequestMapping("/download")
public class DownloadController {

	@RequestMapping(value = "file")
	@ResponseBody
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		String lm = request.getHeader("If-Modified-Since");
//
//		long lim = MobileAccountUtils.strGMTToLong(lm, null);
		/*
		 * 这里要进行时间的判断，暂时没有加上
		 * */

		String storeName = "kind.txt";
		String realName = "kind.txt";
		String contentType = "application/octet-stream";
		response.setHeader("user-agent", "3.2");
		response.setHeader("Last-Modified", MobileAccountUtils.longToGMTStr(new Date().getTime(), null));
		MobileAccountUtils.download(request, response, storeName, contentType, realName);

		return "3.2";
	}

}
