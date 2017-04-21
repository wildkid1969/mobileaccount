/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����9:48:06
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase;

import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.hc360.mobileaccount.po.User;

/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-29 ����9:48:06
 */
@Controller
@RequestMapping("/binding/*")
public class DataBindingController {
	
	@ResponseBody
	@RequestMapping("pathVariable/{cat1}/{cat2:[a-z0-9]+}")
	public String pathvar(@PathVariable String cat1, @PathVariable String cat2) {

		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"/bingding/pathVariable/{cat1}/{cat2:[a-z0-9]+}\")");
		sb.append("<br/>public String pathvar(@PathVariable String cat1, @PathVariable String cat2) {");
		sb.append("<br/>�õ��Ĳ���Ϊ��");
		sb.append("<br/>cat1 : ").append(cat1);
		sb.append("<br/>cat2 : ").append(cat2);
		return sb.toString();
	}

	@ResponseBody
	@RequestMapping("requestParameter")
	public String requestParameter(@RequestParam String cat1, String cat2) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"requestParameter\")");
		sb.append("<br/>public String requestParameter(@RequestParam String cat1, String cat2 ) {");
		sb.append("<br/>�õ��Ĳ���Ϊ��");
		sb.append("<br/>cat1 : ").append(cat1);
		sb.append("<br/>cat2 : ").append(cat2);
		return sb.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("requestHeader")
	public String requestHeader(@RequestHeader("Accept-Encoding") String acceptEncoding) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"requestHeader\")");
		sb.append("<br/>public String requestHeader(@RequestHeader(\"Accept-Encoding\") String acceptEncoding) {");
		sb.append("<br/>�õ���ͷ��ϢΪ��");
		sb.append("<br/>Accept-Encoding : ").append(acceptEncoding);
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "requestBody", method = RequestMethod.POST)
	public String requestBody(@RequestBody String user) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"requestBody\")");
		sb.append("<br/>public String requestBody(@RequestBody String requestBody) {");
		sb.append("<br/>�õ����������ݣ�");
		sb.append("<br/>Request Body : ").append(user);
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping("cookie")
	public String cookie(@CookieValue("JSESSIONID") String jsessionid, @CookieValue("cookie1") String cookie1) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"cookie\")");
		sb.append("<br/>public String cookie(@CookieValue(\"JSESSIONID\") String jsessionid, @CookieValue(\"cookie1\") String cookie1) {");
		sb.append("<br/>�õ���cookieΪ��");
		sb.append("<br/>JSESSIONID : ").append(jsessionid);
		sb.append("<br/>cookie1 : ").append(cookie1);
		return sb.toString();
	}

	@ResponseBody
	@RequestMapping("pojo")
	public String pojo(String name, User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"pojo\")");
		sb.append("<br/>public String pojo(String name, User user) {");
		sb.append("<br/>�õ��Ĳ���Ϊ��");
		sb.append("<br/>name : ").append(name);
		sb.append("<br/>user.name : ").append(user.getName());
		sb.append("<br/>user.age : ").append(user.getAge());
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping("arguments")
	public String arguments(HttpServletRequest request, HttpServletResponse reponse,
			HttpSession session, 
			WebRequest webRequest, 
			Locale local,
			InputStream inputStream,
			HttpEntity<String> httpEntity,
			Model model,
			RedirectAttributes redirectAttributes,
			User user,
			BindingResult bindingResult,
			SessionStatus sessionStatus,
			UriComponentsBuilder uriComponentsBuilder) {
		StringBuilder sb = new StringBuilder();
		sb.append("@RequestMapping(\"arguments\")");
		sb.append("<br/><p>public String arguments(HttpServletRequest request, HttpServletResponse reponse,</p><blockquote>	<p>		HttpSession session,&nbsp;	</p>	<p>		WebRequest webRequest,&nbsp;	</p>	<p>		Locale local,	</p>	<p>		InputStream inputStream,	</p>	<p>		HttpEntity&lt;String&gt; httpEntity,	</p>	<p>		Model model,	</p>	<p>		RedirectAttributes redirectAttributes,	</p>	<p>		User user,	</p>	<p>		BindingResult bindingResult,	</p>	<p>		SessionStatus sessionStatus,	</p>	<p>		UriComponentsBuilder uriComponentsBuilder) {	</p></blockquote><p>	<br /></p>");
		return sb.toString();
	}
}
