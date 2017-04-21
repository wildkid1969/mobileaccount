/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����9:48:58
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hc360.mobileaccount.po.User;

/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-29 ����9:48:58
 */
@Controller
@RequestMapping("/validation")
public class ValidationController {

	/**
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-14 ����10:04:33
	 * @param user
	 *            ���������ʡ�ԣ�����ҳ����spring��#springFormInput("user.name", "") �ᱨ�쳣
	 */
	@RequestMapping(value = "/userForm", method = RequestMethod.GET)
	public void userForm(User user) {
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public String validateUser(Model model, @Valid User user,
		/** ������user���� */ BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "��֤û��ͨ��");
		} else {
			model.addAttribute("message", "��֤ͨ��");
		}
		return "/validation/userForm";
	}
}
