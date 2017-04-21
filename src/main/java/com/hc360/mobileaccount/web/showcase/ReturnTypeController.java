/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����9:48:20
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import com.hc360.mobileaccount.po.User;
import com.hc360.mobileaccount.web.showcase.pdf.PdfView;

/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-29 ����9:48:20
 */

@Controller
@RequestMapping("/showcase/returntype")
public class ReturnTypeController {

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1L, "Tom", 21, "100001"));
		users.add(new User(2L, "Jack", 22, "100002"));
		return users;
	}

	/**
	 * {@link VelocityLayoutViewResolver}�� {@link UrlBasedViewResolver} �����࣬ <li>
	 * ����������voidʱ������url�Զ�ƥ����ͼ����urlΪ:/showcase/velocityView/
	 * ��᳢����/WEB-INF/views/�������/showcase/velocityView.html <li>
	 * ����������Stringʱ�����ݷ���ֵ������ͼ
	 * �����������践��ֵ(void)�ͷ����ַ���/showcase/velocityView����Ч����һ��ġ�
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-12 ����3:05:05
	 */
	@RequestMapping("velocityView")
	public void velocityView(Model model) {
		model.addAttribute("viewType", "velocity");
	}

	/**
	 * servlet-context.xml�����ȶ����velocityViewResolver�����jspViewResolver��
	 * ���Ի��ȳ��Բ���jspView.html�ļ������������ٳ��Բ���jspView.jsp
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-12 ����3:03:54
	 */
	@RequestMapping("jspView")
	public void jspView(Model model) {
		model.addAttribute("viewType", "jsp");
	}
	
	@RequestMapping("redirect")
	public String redirect(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("attr1", "v1");
		
		// ��ӵ�URL�����У�������ת��ҳ�棺/showcase/jspView?attr2=v2
		redirectAttributes.addAttribute("attr2", "v2");
		
		// �������ض�����ҳ����ֱ��ʹ�ã���ҳ����ͨ��request.getAttribute�ɻ�ȡ����
		// Ҳ�����ض�����request��ͨ��RequestContextUtils.getInputFlashMap(request)��ȡ
		// Ĭ������session�д洢�ģ���ȡ�󼴱����Ҳ����˵ֻ�ܻ�ȡһ��
		redirectAttributes.addFlashAttribute("attr3", "v3");
		return "redirect:/showcase/returntype/jspView";
	}

	@ResponseBody
	@RequestMapping(value = "textView")
	public String text() {
		return "just text!";
	}

	@ResponseBody
	@RequestMapping(value = "jsonView")
	public List<User> jsonView() {
		return getUsers();
	}

	/**
	 * servlet-context.xml�������� {@link ResourceBundleViewResolver}��
	 * ���classpath:spring/views.properties�в���viewNameΪ"pdfView"����ͼ����{@link PdfView}
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-12 ����4:29:02
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pdfView")
	public View pdfView(Model model) {
		model.addAttribute("users", getUsers());
		return new PdfView();
	}
	
	/**
	 * �൱�ڵ���model.addAttribute("userList", list)
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-31 ����1:45:14
	 * @return
	 */
	@RequestMapping(value = "list")
	public List<User> list() {
		return getUsers();
	}
	
	/**
	 * �൱�ڵ���model.addAllAttributes(map)
	 * 
	 * @author dixingxing
	 * @version 1.0
	 * @date 2013-10-31 ����2:06:44
	 * @return
	 */
	@RequestMapping(value = "map")
	public Map<String, Object> map() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "text1");
		map.put("user1", new User(1L, "dixx", 21, "1000098"));
		return map;
	}
	
	
}
