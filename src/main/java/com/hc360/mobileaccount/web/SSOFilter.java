package com.hc360.mobileaccount.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hc360.mobileaccount.service.AccountService;

public class SSOFilter implements Filter {	
	
	//private static Logger logger=Logger.getLogger(SSOFilter.class);
		
	private String SECRETKEY;

	private String [] UNLOGINREQ;

	@Autowired
	private AccountService accountService;
	
	private ServletContext servletContext;
	private WebApplicationContext webApplicationContext;

	@Override
	public void init(FilterConfig config) throws ServletException {
		setSECRETKEY(config.getInitParameter("SECRET.KEY"));
		setUNLOGINREQ((config.getInitParameter("UNLOGINREQ")).split(";"));
		this.servletContext=config.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.servletContext);		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		accountService = webApplicationContext.getBean(AccountService.class);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Map<String, String[]> map = req.getParameterMap();
		Map<String, String> mmp = new HashMap<String, String>();
		String phone = "";
		//logger.info(req.getRequestURL());	
//		if(req.getRequestURL().indexOf("cms") > 0){
//			
//			boolean login = true;
//			
//			HCAdminUser sso = HCAdminHelper.findAdminTicket(req);
//			if(sso != null){
//				login = false;
//			}
//			
//			if(login){
//				redirectToLoginPage(req,res);
//			}
//		}
//		for(String s : this.UNLOGINREQ){
//			if(req.getRequestURL().indexOf(s) >= 0){
//				chain.doFilter(request, response);
//				return;
//			}
//		}
//		if (map != null && map.size() > 0) {
//			String sign2 = null;
//			String apptype = null;
//			for (Entry<String, String[]> entry : map.entrySet()) {
//				if (entry.getKey() != null && !entry.getKey().equals("sign")) {
//					mmp.put(entry.getKey(), map.get(entry.getKey())[0]);
//				}				
//				if (entry.getKey().equals("sign")) {
//					sign2 = map.get(entry.getKey())[0];
//				}else if (entry.getKey().equals("apptype")){
//					apptype = map.get(entry.getKey())[0];
//				}else if (entry.getKey().equals("phone")) {
//					try{
//					phone = map.get(entry.getKey())[0];
//					phone = RC4.decry_RC4(phone);
//					}catch(Exception e){}
//				}else if(entry.getKey().equals("deviceid")){
//					continue;
//				}
//			}
//			
//			AccountSecret am = accountService.getUserPwd(phone,apptype);
//			if (am != null) {
//				mmp.put("password", am.getSecret());
//			}
//			if(sign2 == null){				
//				byte[] b = new String("{\"msg\":\"sign must not be null\"}").getBytes("UTF-8");
//				res.setContentLength(b.length);
//				res.getOutputStream().write(b);
//				res.setCharacterEncoding("UTF-8");
//				res.setStatus(HttpServletResponse.SC_OK);
//				res.flushBuffer();
//				return;
//			}
//			Map<String, String> map1 = MobileAccountUtils.sortMapByKey(mmp);
//			String cc = MobileAccountUtils.mapToString(map1);
//			try {
//				String sign1 = MD5.md5Encode(cc + "key=" + this.getSECRETKEY());				
//				if (sign2 != null && !sign2.equals(sign1)) {					
//					byte[] b = new String("{\"msg\":\"sign error\"}").getBytes("UTF-8");
//					res.setContentLength(b.length);
//					res.getOutputStream().write(b);
//					res.setCharacterEncoding("UTF-8");
//					res.setStatus(HttpServletResponse.SC_OK);
//					res.flushBuffer();
//					return;
//				}
//			} catch (Exception e) {
//			}
//		}
		if (true) {
			// redirectToLoginPage((HttpServletRequest)request,(HttpServletResponse)response);
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

	/**
	 * 重定向到登录页面
	 * 
	 * @throws IOException
	 */
	private void redirectToLoginPage(HttpServletRequest req, HttpServletResponse res) throws IOException{
		StringBuffer sb = req.getRequestURL();
		String returnURL = "http://sso.hc360.com/internallogin?ReturnURL="+sb.toString();

		res.sendRedirect(returnURL);
	}

	public String getSECRETKEY() {
		return SECRETKEY;
	}

	public void setSECRETKEY(String sECRETKEY) {
		SECRETKEY = sECRETKEY;
	}

	public String [] getUNLOGINREQ() {
		return UNLOGINREQ;
	}

	public void setUNLOGINREQ(String [] uNLOGINREQ) {
		UNLOGINREQ = uNLOGINREQ;
	}

}
