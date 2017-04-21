package com.hc360.mobileaccount.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PhoneAreaUtils {

	// 正则表达式,抽取手机归属地
	public static final String REGEX_GET_MOBILE = "(?is)(<tr[^>]+>[\\s]*<td[^>]+>[\\s]*卡号归属地[\\s]*</td>[\\s]*<td[^>]+>([^<]+)</td>[\\s]*</tr>)"; // 2:from
	// 正则表达式,审核要获取手机归属地的手机是否符合格式,可以只输入手机号码前7位
	public static final String REGEX_IS_MOBILE = "(?is)(^1[3|4|5|8][0-9]\\d{4,8}$)";
	
	public static String validateMobile(String mobile){  
        String returnString="";  
        if(mobile==null || mobile.trim().length()!=11){  
            return "-1";        //mobile参数为空或者手机号码长度不为11，错误！  
        }  
        if(mobile.trim().substring(0,3).equals("134") ||  mobile.trim().substring(0,3).equals("135") ||   
                mobile.trim().substring(0,3).equals("136") || mobile.trim().substring(0,3).equals("137")    
                || mobile.trim().substring(0,3).equals("138")  || mobile.trim().substring(0,3).equals("139") ||  mobile.trim().substring(0,3).equals("150") ||   
                mobile.trim().substring(0,3).equals("151") || mobile.trim().substring(0,3).equals("152")    
                || mobile.trim().substring(0,3).equals("157") || mobile.trim().substring(0,3).equals("158") || mobile.trim().substring(0,3).equals("159") 
                || mobile.trim().substring(0,3).equals("178") 
                 || mobile.trim().substring(0,3).equals("187") || mobile.trim().substring(0,3).equals("188")){  
            returnString="移动";   //中国移动  
        }  
        if(mobile.trim().substring(0,3).equals("130") ||  mobile.trim().substring(0,3).equals("131") ||   
                mobile.trim().substring(0,3).equals("132") || mobile.trim().substring(0,3).equals("156")   
                || mobile.trim().substring(0,3).equals("176") 
                || mobile.trim().substring(0,3).equals("185")  || mobile.trim().substring(0,3).equals("186")){  
            returnString="联通";   //中国联通  
        }  
        if(mobile.trim().substring(0,3).equals("133") ||  mobile.trim().substring(0,3).equals("153") ||   
        		mobile.trim().substring(0,3).equals("177") || mobile.trim().substring(0,3).equals("180") || mobile.trim().substring(0,3).equals("189")){  
            returnString="电信";   //中国电信  
        }  
        if(returnString.trim().equals("")){  
            returnString="其他";   //未知运营商  
        }  
        return returnString;  
    }  

	/**
	 * 获得手机号码归属地
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws Exception
	 */
	public static String getMobileFrom(String mobileNumber){
		if (!veriyMobile(mobileNumber)) {
			return mobileNumber;
		}

		String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + mobileNumber;
		String result = MobileAccountUtils.doGet(url, "gbk");

		return parseMobileFrom(result)+validateMobile(mobileNumber);

	}

	/**
	 * 从www.ip138.com返回的结果网页内容中获取手机号码归属地,结果为：省份 城市
	 * 
	 * @param htmlSource
	 * @return
	 */
	public static String parseMobileFrom(String htmlSource) {
		Pattern p = null;
		Matcher m = null;
		String result = null;

		p = Pattern.compile(REGEX_GET_MOBILE);
		m = p.matcher(htmlSource);

		while (m.find()) {
			if (m.start(2) > 0) {
				result = m.group(2);
				result = result.replaceAll("&nbsp;", " ");
			}
		}

		if (result == null) {
			Document doc = Jsoup.parse(htmlSource);
			Elements eles = doc.getElementsByClass("tdc2");
			int i = 0;
			for (Element el : eles) {
				if (i == 1) {
					result = el.text();
					result = result.replaceAll(" ", "");
					break;
				}
				i++;
			}
		}
		return result;

	}

	/**
	 * 验证手机号
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean veriyMobile(String mobileNumber) {
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile(REGEX_IS_MOBILE);
		m = p.matcher(mobileNumber);

		return m.matches();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getMobileFrom("13821639760"));
	}

}
