
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����9:47:25
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-29 ����9:47:25   
 */
@Controller
public class RequestMappingController {
	
	
	/**
	 *
	 *<pre>
	 * &#064;RequestMapping(value = "/mapping/method1")
	 * �൱��: ��class�����϶���&#064;RequestMapping(value = "/mapping/*")
	 * ����method�ļ����϶���&#064;RequestMapping(value = "method1")
	 * </pre>
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-9-30 ����10:12:56
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mapping/method1")
	public String method1() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/method1\")";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/placeholder/{someText}")
	public String placeholder() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/placeholder/{someText}\")";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/mapping/regex/{someText:[0-9]+}")
	public String regex() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/regex/{someText:[0-9]+}\")";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/wildcard/**/*abc")
	public String wildcard() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/wildcard/**/*abc\")";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/method2", method = RequestMethod.GET)
	public String method2() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/method2\", method = RequestMethod.GET)";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/method3",
			method = RequestMethod.GET, params = "param1")
	public String method3() {
		return "����ӳ��Ϊ : @RequestMapping(value = \"/mapping/method3\", method = RequestMethod.GET, params = \"param1\")";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/method4",
			method = RequestMethod.GET, consumes = "text/plain")
	public String method4() {
		return "����ӳ��Ϊ (�ͻ��������Content-Type����Ϊtext/plain): @RequestMapping(value = \"/mapping/method4\", method = RequestMethod.GET, consumes = \"text/plain\")";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mapping/method5",
			method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Map<String, String> method5() {
		Map<String, String> map =new HashMap<String, String>();
		map.put("name", "����");
		map.put("age", "22");
		map.put("mapping", "����˷���Response��Content-Type����Ϊ:application/json��@RequestMapping(value = \"/mapping/method5\", method = RequestMethod.GET, produces = \"application/json;charset=UTF-8\")<br />");
		return map;
	}
	
}
