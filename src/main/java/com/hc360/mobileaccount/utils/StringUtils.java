package com.hc360.mobileaccount.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static String randString = "0123456789abcdefghijklmnopqrstuvwxyz";
	public static final String numberChar = "0123456789";
	
	/**
     * request参数转换成int
     * @param value 
     * @param defaultValue 默认值
     * @return
     */
    public static int param2Int(String value, int defaultValue) {
    	if(!StringUtils.isEmpty(value)){
			return Integer.parseInt(value);
		}
		return defaultValue;
    }
    
    /**
     * 返回String
     * @param value 
     * @param defaultValue 默认值
     * @return
     */
    public static String param2String(String value,String defaultValue) {
    	return StringUtils.isEmpty(value)?defaultValue:value;
    }
    
    /**
   	 * 参数转换成long
   	 * @param value
   	 * @param defaultValue 默认值
   	 * @return
   	 */
   	public static long param2Long(String value, long defaultValue) {
   		if(!StringUtils.isEmpty(value)){
   			return Long.parseLong(value);
   		}
   		return defaultValue;
   	}
   	
    private static boolean isEmpty(String str){
    	return str == null || str.length() == 0;
    }
	
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
	public static boolean isNumber(String s){
		return s.matches("\\d+");
	}

    /**
     * http://domain or http://domain/
     * return  http://domain/
     * @param domain
     * @return
     */
    public static String domainSplit(String domain){
        if(StringUtils.isEmpty(domain)) return "/";
        if(domain.trim().endsWith("/")){
            domain = domain.trim();
            domain = domain.substring(0,domain.length()-1);
        }
        domain = domain+"/";
        return  domain;
    }
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int strlength(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * 
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String tosubstring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > strlength(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			origin = "";
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	public static String ListtoString(List<String> list){
		String  s = "";
		for(int i = 0 ; i < list.size() ; i++){
			String L = list.get(i);
			s =s+","+L ;
		}
		if(list == null || list.size()==0){
			return s;
		}
		return s.substring(1);
	}
	
	public static String getSixLengthFilghNo(String str){
		String f_no = "";
		for(int i=0;i<str.length();i++){	
			if(Character.isDigit(str.charAt(i))){	
				 char s = str.charAt(i);
				 String[] sd = str.split(String.valueOf(s));
				  if(sd[0].length()==0){
					  return 0+str;
				  }else{
					  if(sd[0].length()==4){
						  f_no = sd[0]+0+String.valueOf(s);
							return f_no;
					  }else{
						  f_no = sd[0]+0+String.valueOf(s)+sd[1];
						   return f_no;
					  }
					   
				  }
				 
			 }
		}
		 return f_no;
	}
	
	public static int getWordCountRegex(String s) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;
	}  
  
	/** 
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1 
     *  
     * @param s 需要得到长度的字符串 
     * @return i得到的字符串长度 
     */  
    public static int length(String s) {  
        if (s == null)  
            return 0;  
        char[] c = s.toCharArray();  
        int len = 0;  
        for (int i = 0; i < c.length; i++) {  
            len++;  
            if (!isLetter(c[i])) {  
                len++;  
            }  
        }  
        return len;  
    }  
  
    /** 
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位 
     *  
     *  
     * @param  origin 原始字符串 
     * @param len 截取长度(一个汉字长度按2算的) 
     * @param c 后缀            
     * @return 返回的字符串 
     */  
    public static String substring(String origin, int len) {  
        if (origin == null || origin.equals("") || len < 1)  
            return "";  
        byte[] strByte = new byte[len];  
        if (len > length(origin)) {  
            return origin;  
        }  
        try {  
            System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);  
            int count = 0;  
            for (int i = 0; i < len; i++) {  
                int value = (int) strByte[i];  
                if (value < 0) {  
                    count++;  
                }  
            }  
            if (count % 2 != 0) {  
                len = (len == 1) ? ++len : --len;  
            }  
            return new String(strByte, 0, len, "GBK");  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    private static final String pinyinRegex = "^[A-Za-z]*$";
    /**
     * 判断是否全是拼音或者字母
     * @param str
     * @return
     */
    public static boolean isPinyin(String str) {
    	if (null == str) {
    		return false;
    	}
    	Pattern pattern = Pattern.compile(pinyinRegex);
    	Matcher matcher = pattern.matcher(str);
    	matcher.find();
    	return matcher.matches();
    }


    /**
     * 比较两个对象内容是否一致
     * @param str
     * @param str2
     * @return
     */
    public static boolean isEquals(Object str,Object str2){
        if(str == null && str2 == null)return true;
        if(str == null && str2 != null) return false;
        if(str2 == null && str != null) return  false;
        if(str.toString().equals(str2.toString())) return true;
        return false;
    }
    
    /**
     * 去除字符串前后的空格 和逗号
     * 添加人：fdxu
     * @return
     */
    public static String strTrim(String str){
    	if(str!=null&&!str.trim().equals("")){
    		if(str.trim().substring(0,1).equals(",")){
    			str = str.substring(1,str.length());
    		}
    		if(str.trim().substring(str.length()-1,str.length()).equals(",")){
    			str = str.substring(0,str.length()-1);
    		}
    	}
    	return str;
    }
    
    /**
     * right trim "trim"
     * @param str
     * @param trim
     * @return
     */
    public static String TrimRight(String str,String trim){
    	if(str!=null&&!str.trim().equals("")){
//    		if(str.trim().substring(0,1).equals(",")){
//    			str = str.substring(1,str.length());
//    		}
    		if(str.trim().substring(str.length()-trim.length(),str.length()).equals(trim)){
    			str = str.substring(0,str.length()-trim.length());
    		}
    	}
    	return str;
    }
    
    /**
     * 判定string的包含关系   str1是否包含str2
     * @return  -1  不包含
     * 					0  包含并且完全相同
     * 					1  包含
     */
    public static Integer checkStrInclude(String str1,String str2){
    	if(str1!=null&&!str1.equals("")&&str2!=null&&!str2.equals("")){
    		if(str1.equals(str2)){
    			return 0;
    		}else{
    			if(str1.indexOf(str2)<0){
    				return str1.indexOf(str2);//return 1;
    			}else{
    				return 1;
    			}
    		}
    	}else{
    		return null;
    	}
    }
    /**
     * 生成数字字母随机字符串
     * @param length 随机字符串长度 
     * @return 随机字符串 
     */
    public static String getRandomStr(int length) {
    	StringBuffer buf = new StringBuffer();
    	Random random = new Random();
    	for(int i = 0; i < length; i++) {
    		buf.append(randString.charAt(random.nextInt(randString.length())));
    	}
    	return buf.toString();
    }
    
    /** 
     * 返回一个定长的随机字符串(只包含数字) 
     * 
     * @param length 随机字符串长度 
     * @return 随机字符串 
     */ 
    public static String getRandomNumString(int length) { 
            StringBuffer sb = new StringBuffer(); 
            Random random = new Random(); 
            for (int i = 0; i < length; i++) { 
                    sb.append(numberChar.charAt(random.nextInt(numberChar.length()))); 
            } 
            return sb.toString(); 
    }
    
    /**
     * 验证userName 是否为中文英文或数字
     * @param userName
     * @return
     */
    public static boolean checkUserName(String userName) {
	    	int length = userName.length();
		    for(int i = 0; i < userName.length(); i++){
		        if(userName.charAt(i) > 127){
		            length++;
		        }
		    }
    	  String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5]|[_#.@])+";
    	  Pattern p = Pattern.compile(regex);
    	  Matcher m = p.matcher(userName);
    	  return m.matches() && length >= 3 && length <= 15 ;
    }
    
}
