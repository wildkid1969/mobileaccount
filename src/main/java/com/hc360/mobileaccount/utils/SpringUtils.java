package com.hc360.mobileaccount.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * 获取spring信息的工具类
 * 
 * @author HC360
 *
 */
public final class SpringUtils extends ApplicationObjectSupport {
	private static ApplicationContext applicationContext = null;

	@Override
	protected void initApplicationContext(ApplicationContext context)
			throws BeansException {
		
		super.initApplicationContext(context);
		
		if (applicationContext == null) {
			applicationContext = context;
		}
	}

	/**
	 * 在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象
	 * @return
	 */
	public static ApplicationContext getAppContext() {
		return applicationContext;
	}

	/**
	 * 在普通类可以通过调用SpringUtils.getBean(beanName)获取bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getAppContext().getBean(name);
	}
}
