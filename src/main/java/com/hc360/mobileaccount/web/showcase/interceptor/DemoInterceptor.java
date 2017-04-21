
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����11:23:04
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-10-14 ����11:23:04   
 */
public class DemoInterceptor implements HandlerInterceptor{

	
	/**
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-14 ����11:40:45
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	
	/**
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-14 ����11:40:45
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("addByInterceptor", "1");
		
	}

	
	/**
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-14 ����11:40:45
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	

}
