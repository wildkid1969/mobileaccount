/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����9:10:15
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-27 ����9:10:15
 */
@Controller
public class ShowcaseController {
	
	@RequestMapping("/")
	public String showcase() {
		return "showcase/index";
	}
}
