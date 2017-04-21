package com.hc360.mobileaccount.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.util.TextUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.hc360.hrmq.mo.TSmsMessage;
import com.hc360.jms.JMSProducer;
import com.hc360.jms.activemq.ActiveMQ;
import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.YzxAccount;
import com.hc360.mobileaccount.po.YzxCallreqAuthenBack;
import com.ucpaas.client.JsonReqClient;

public class MobileAccountUtils {

	public static String SMSURL = "http://openapi.m.hc360.com/openapi/v1/senMsg/";

	public static final int SECONDS_IN_DAY = 60 * 60 * 24;
	public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;
	private static Gson gson = null;

	
	/*
	 * 是数字返回true
	 */
	public static boolean isNumber(String n){
		Pattern pattern = Pattern.compile("[0-9]*");
		return !StringUtils.isEmpty(n) && pattern.matcher(n).matches();
	}
	
	
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 在初始化的无重复待选数组中随机产生一个数放入结果中， 
	 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 
	 * 然后从len-2里随机产生下一个随机数，如此类推 
	 * @param max  指定范围最大值 
	 * @param min  指定范围最小值 
	 * @param n  随机数个数 
	 * @return int[] 随机数结果集 
	 */  
	public static int[] getRandomNum(int min, int max, int n){  
		int len = max-min+1;  
	      
	    if(max < min || n > len){  
	        return null;  
	    }  
	      
	    //初始化给定范围的待选数组  
	    int[] source = new int[len];  
        for (int i = min; i < min+len; i++){  
         source[i-min] = i;  
        }  
         
        int[] result = new int[n];  
        Random rd = new Random();  
        int index = 0;  
        for (int i = 0; i < result.length; i++) {  
           //待选数组0到(len-2)随机一个下标  
           index = Math.abs(rd.nextInt() % len--);  
           //将随机到的数放入结果集  
           result[i] = source[index];  
           //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
           source[index] = source[len];  
        }  
        
        return result;  
	}  
	
	
	
	/**
	* 求Map<K,V>中Key(键)的最大值
	* @param map
	* @return
	*/
	public static Object getMaxKey(Map<Integer, Object> map) {
		if (map == null) return null;
		Set<Integer> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		return obj[obj.length-1];
	}

	/**
	* 求Map<K,V>中Value(值)的最大值
	* @param map
	* @return
	*/
	public static Object getMaxValue(Map<Object, Integer> map) {
		if (map == null) return null;
		Collection<Integer> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		return obj[obj.length-1];
	}
	
	
	/**
	 * 根据map的value从大到小排序
	 * @param oriMap
	 * @return
	 */
	public static Map<Long, Integer> sortMapByValue(Map<Long, Integer> oriMap) {  
	    Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();  
	    if (oriMap != null && !oriMap.isEmpty()) {  
	        List<Entry<Long,Integer>> entryList = new ArrayList<Map.Entry<Long, Integer>>(oriMap.entrySet());  
	        Collections.sort(entryList,  
	                new Comparator<Map.Entry<Long, Integer>>() {  
	                    public int compare(Entry<Long, Integer> entry1,  
	                            Entry<Long, Integer> entry2) {  
	                        int value1 = 0, value2 = 0;  
	                        try {  
	                            value1 = Integer.valueOf(entry1.getValue());  
	                            value2 = Integer.valueOf(entry2.getValue());  
	                        } catch (NumberFormatException e) {  
	                            value1 = 0;  
	                            value2 = 0;  
	                        }  
	                        return value2 - value1;  
	                    }  
	                });  
	        Iterator<Map.Entry<Long, Integer>> iter = entryList.iterator();  
	        Entry<Long, Integer> tmpEntry = null;  
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	    }  
	    return sortedMap;  
	}  
	
	
	
	/**
	 * true 同一天 false 不是同一天
	 * */
	public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
		final long interval = ms1 - ms2;
		return interval < MILLIS_IN_DAY && interval > -1L * MILLIS_IN_DAY && toDay(ms1) == toDay(ms2);
	}

	// 求经纬度之间的距离
	public static double getDistance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378.137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public static int GetDateToInt() {
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyyMMdd");
		String date = format1.format(new Date());
		return Integer.valueOf(date);
	}

	public static String getDateNow() {
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format1.format(new Date());
		return date;
	}

	public static String getReturnCallback(Object o, String callback) {
		if (o != null) {
			if (callback != null) {
				return callback + "(" + getGson().toJson(o) + ")";
			} else {
				return getGson().toJson(o);
			}
		} else {
			return "";
		}
	}

	public static String GetDateToString(String source) {
		java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			date = format1.parse(source);
			return format2.format(date);
		} catch (ParseException e) {
			return null;
		}

	}

	public static String levelGrade(int num) {
		if (num >= 10) {
			return "5";
		} else if (num >= 7 && num <= 9) {
			return "4";
		} else if (num >= 4 && num <= 6) {
			return "3";
		} else if (num >= 1 && num <= 3) {
			return "2";
		} else {
			return "1";
		}
	}

	public static String getYears(String nx) {

		if (TextUtils.isEmpty(nx)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date nowDate = new Date();
		String nowDateStr = df.format(nowDate);
		Date d1;
		Date d2;
		try {

			d1 = (Date) df.parse(nx);
			d2 = (Date) df.parse(nowDateStr);

			long diff = d2.getTime() - d1.getTime();
			long years = diff / (1000 * 60 * 60 * 24);

			return years / 365 + "";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// 发送营销短信
	public static String sendSms(String phone, String called, String appType) {
		try {
			// 要根据appType 设置短信回复内容 appType 为 114 、168
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			String name = "企业114";
			if (appType != null && appType.equals("168")) {
				name = "168电话";
			}

			if (phone == null || phone.isEmpty() || phone.equals("null") || !isPhoneNumber(phone)) {
				phone = "有客户";
			}

			params.add(new NameValuePair("sn", "DXX-HCW-010-00055"));
			params.add(new NameValuePair("pwd", MD5.md5Encode("DXX-HCW-010-00055372048").toUpperCase()));
			params.add(new NameValuePair("mobile", called));
			params.add(new NameValuePair("content", URLEncoder.encode(phone + "通过手机APP［" + name
					+ "］，已向您去电洽谈生意。获取更多生意电话  http://t.cn/RLYyVEk 回复TD退订【慧聪网】", "utf-8")));
			params.add(new NameValuePair("ext", ""));
			params.add(new NameValuePair("stime", ""));
			params.add(new NameValuePair("rrid", ""));
			params.add(new NameValuePair("msgfmt", ""));

			return MobileAccountUtils.request("http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend",
					params.toArray(new NameValuePair[params.size()]));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// 获取营销短息回复内容
	public static String getSmsReply() {
		String url = "http://sdk2.zucp.net:8060/webservice.asmx/mo";

		try {
			String xml = MobileAccountUtils.doGet(
					url + "?sn=DXX-HCW-010-00055&pwd=" + MD5.md5Encode("DXX-HCW-010-00055372048").toUpperCase(), "gbk");
			return xml.replaceAll("<[^>]*>", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @param zj
	 *            注册资金
	 * @param nx
	 *            年限
	 * */
	public static String levelGrade(String zj, String nx) {
		if (zj == null || zj.isEmpty()) {
			zj = "0";
		}
		Integer izj = 0;
		try {
			izj = Integer.valueOf(zj);
		} catch (Exception e) {
		}
		double q = 0;
		if (izj >= 500) {
			q = 5;
		} else if (izj >= 200) {
			q = 4;
		} else if (izj >= 50) {
			q = 3;
		} else if (izj >= 10) {
			q = 2;
		} else {
			q = 1;
		}

		q = q * 0.4;

		String snx = getYears(nx);
		int inx = Integer.valueOf(snx);
		double a = 0;
		if (inx >= 8) {
			a = 5;
		} else if (inx >= 5) {
			a = 4;
		} else if (inx >= 3) {
			a = 3;
		} else if (inx >= 1) {
			a = 2;
		} else {
			a = 1;
		}

		a = a * 0.6;

		double c = q + a;
		if (c == 5) {
			return "5";
		} else if (c >= 4) {
			return "4";
		} else if (c >= 3) {
			return "3";
		} else if (c >= 2) {
			return "2";
		} else {
			return "1";
		}
	}

	private static long toDay(long millis) {
		return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
	}

	public static String getSingForLei(String... args) {
		String tmpStr = null;
		String[] arr = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			arr[i] = args[i];
		}
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		try {
			tmpStr = MD5.md5Encode(content.toString() + "key=yidonghulianshiyebuwanwansui");
		} catch (Exception e) {
			return null;
		}
		return tmpStr;
	}

	/**
	 * 获取当前时间秒数
	 **/
	public static String getNowtime() {
		return String.valueOf(new Date().getTime());
	}

	public static long getTime() {
		return new Date().getTime();
	}

	/*
	 * @intro 生成短信验证码
	 */
	public static String getSmsCheckCode(int len) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++)
			result = result * 10 + array[i];
		return String.valueOf(result);
	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		String cookieValue = null;

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}

		String cookieStr = request.getHeader("cookie");
		String[] cookiess = cookieStr.split(";");
		for (int i = 0; i < cookiess.length; i++) {
			String cookie = cookiess[i].trim();
			if (cookie.startsWith(name + "=")) {// 判断的是cookie的名称
				cookieValue = cookie.substring(name.length() + 1);
				break;
			}
		}
		return cookieValue;
	}

	public static String doGet(String getURL, String charset) {

		StringBuffer buf = new StringBuffer();
		try {
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));// 设置编码,否则中文乱码
			String lines;
			while ((lines = reader.readLine()) != null) {
				buf.append(lines);
			}
			connection.disconnect();
			reader.close();
		} catch (Exception e) {
		}
		return buf.toString();
	}

	/*
	 * @ 判断是否是手机号
	 */
	public static boolean isPhoneNumber(String phoneNum) {
		return phoneNum.matches("^(13|14|15|17|18)\\d{9}$");
	}

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	/**
	 * 将map中的k-v 按照 k1=v1k2=v2输出字符串
	 */
	public static String mapToString(Map<String, String> map) {
		StringBuffer bf = new StringBuffer();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				bf.append(entry.getKey() + "=" + entry.getValue());
			}
		}
		return bf.toString();
	}

	/**
	 * 容大友信
	 * */
	public static String getKey(String UserName, String Password, String Timestemp) {
		String key = "";
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(UserName.getBytes());
			mdTemp.update(Password.getBytes());
			mdTemp.update(Timestemp.getBytes());
			key = bytesToHexString(mdTemp.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	public static String bytesToHexString(byte[] src) {
		String resultString = "";
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		resultString = stringBuilder.toString();
		stringBuilder = null;
		return resultString;
	}

	public static void download(HttpServletRequest request, HttpServletResponse response, String storeName,
			String contentType, String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = "D:\\";
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	public static Date strToDate(String pat, String str) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		if (pat != null) {
			pattern = pat;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long strGMTToLong(String strGMT, String pat) {

		String pattern = "EEE, dd MMM yyyy HH:mm:ss z";

		if (pat != null) {
			pattern = pat;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);

		try {
			Date date = formatter.parse(strGMT);
			return date.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	public static String longToGMTStr(long time, String format) {
		String pattern = "EEE, dd MMM yyyy HH:mm:ss z";
		if (format != null) {
			pattern = format;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);

		return formatter.format(new Date(time));
	}

	public static String getOrderNo(String uid) {
		Random rand = new Random();
		Integer nRandom = rand.nextInt(9000) + 1000;// 动态生成订单号
		int length = uid.length();
		String subUid = uid.substring(length - 2, length);
		Date d = new Date();

		SimpleDateFormat formatDate = new SimpleDateFormat("yyMMddHHmmss");
		String dateNowStr = formatDate.format(d);
		String Orderid = "yd" + subUid + dateNowStr + nRandom.toString();
		return Orderid;
	}

	public static String getDateMMDDHHMMSS() {
		SimpleDateFormat formatDate = new SimpleDateFormat("MMddHHmmss");
		return formatDate.format(new Date());
	}

	/**
	 * 获取n以内的随机数
	 * */
	public static int getRandom(int n) {
		return (int) (Math.random() * n)+1;
	}

	public static String getXMLValue(String xmlstr, String key) {
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlstr);
			return doc.getRootElement().element(key).getTextTrim();
		} catch (Exception e) {
			return null;
		}

	}

	public static String getCallBackYzxXml(String code, String reason) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<response>");
		sb.append("<retcode>" + code + "</retcode>");
		if (reason != null) {
			sb.append("<reason>" + reason + "</reason>");
		}
		sb.append("</response>");
		return sb.toString();
	}
	
	/**
	 * 呼叫鉴权请求接口 里的 通知录音 返回xml
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getYzxCallbackXml(){
		YzxCallreqAuthenBack back = new YzxCallreqAuthenBack();
		back.setRetcode(0);
		back.setRecord("1");
		
		Field[] f = YzxCallreqAuthenBack.class.getDeclaredFields();
		//创建文档并设置文档的根元素节点  
        Element root = DocumentHelper.createElement("response");  
        Document document = DocumentHelper.createDocument(root);  
        
        for(Field s:f){
        	String name = s.getName();
        	String value = null;
			try {
				value = String.valueOf(s.get(back));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        	System.out.println(value);
        	
        	// 添加子节点:add之后就返回这个元素  
            Element child = root.addElement(name);  
            //元素的节点的值  
            if(value!=null){
            	child.setText(value); 
            }
        }
        
     // 输出到控制台  
        XMLWriter xmlWriter = new XMLWriter();  
        try {
			xmlWriter.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		} 

        return document.toString(); 
	}
	
	public static String getYzxAuthenCallBackXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<response>");
		sb.append("<retcode>0</retcode>");
		sb.append("<record>1</record>");
		sb.append("</response>");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = getYzxAuthenCallBackXml();
		System.out.println(s);
	}
	

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + param;

			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setReadTimeout(10000);
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
//			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String request(String url, NameValuePair[] params) {

		String result = null;

		HttpClient client = new HttpClient();

		PostMethod postMethod = new PostMethod(url);

		postMethod.setRequestBody(params);

		int statusCode = 0;
		try {
			statusCode = client.executeMethod(postMethod);
		} catch (HttpException e) {
		} catch (IOException e) {
		}

		try {
			if (statusCode == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
				return result;
			} else {
			}
		} catch (Exception e) {
		}
		postMethod.releaseConnection();
		return result;

	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			conn.setReadTimeout(10000);
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static YzxAccount getYzxAccountHc() {

		JsonReqClient jsonReqClient = new JsonReqClient();
		try {
			String result = jsonReqClient.findAccoutInfo(YZXConstant.ACCOUNT_SID, YZXConstant.ACCOUNT_SID);
			PReturn pr = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
			if (pr != null) {
				return pr.getResp().getAccount();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static void doExecutorsService(Runnable r) throws Exception {
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(r);
		Thread.sleep(1000);
		exe.shutdown();
	}
	
	public static String dateToTimestamp(String d){
		Pattern pattern = Pattern.compile("[0-9]*");
		if(StringUtils.isEmpty(d)){
			return String.valueOf(new Date().getTime());
		}else if(pattern.matcher(d).matches()){
			return d;
		}

		String tt = null;
		SimpleDateFormat sdf = null;
		try {
			switch(d.length()){
				case 17: // 2015年12月30日 22:50
					sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
					tt = String.valueOf(sdf.parse(d).getTime());
					break;
				case 20: // 2016年01月11日 13:59:16
					sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					tt = String.valueOf(sdf.parse(d).getTime());
					break;
				case 16: // 2016-04-28 12:00
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					tt = String.valueOf(sdf.parse(d).getTime());
					break;
				default:
					tt = String.valueOf(new Date().getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
			tt = String.valueOf(new Date().getTime());
		}

		return tt;
	}

	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(inStr==null){
			return null;
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString().toLowerCase();
	}

	/**
	 * 2016.5.27 新的发短信接口 只需传动态内容
	 * @param phone
	 * @param msg 动态内容
	 * @param templateId 模板id
	 * @param level 通道代码
	 * @return
	 */
	public static String sendShortMsg(String phone,String msg,int templateId,int level) {
		JMSProducer jmsP = null;
		try {
			jmsP = ActiveMQ.createProducer("ydhl-mobileaccount-account","HC.SMS", ActiveMQDestination.QUEUE_TYPE);
			
			TSmsMessage tSmsMsg= new TSmsMessage();
			tSmsMsg.setContents(new String[]{msg}); //12345为验证码信息
			tSmsMsg.setMobile(phone); //通知的手机号码
			tSmsMsg.setLevel(level);   //短信通道
			tSmsMsg.setSysName("ydhl-mobileaccount-account"); //发送端应用标识
			tSmsMsg.setTemplateId(templateId);   //发送使用模板id
			
			jmsP.synchSend(tSmsMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	
	/** 
	   * 功能：检测当前URL是否可连接或是否有效, 
	   * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用 
	   * @param urlStr 指定URL网络地址 
	   * @return URL 
	   */  
	public static boolean isValideUrl(String urlStr) {  
	   if (urlStr == null || urlStr.length() <= 0) {                         
	    return false;                   
	   }  
	   
	   URL url;  
	   HttpURLConnection con;  
	   int respCode = -1; 
	   int errCount = 0;  
	   
	   for(int i=0;i<5;i++) {  
			try{  
				url = new URL(urlStr);  
			    con = (HttpURLConnection) url.openConnection();  
			    respCode = con.getResponseCode();  
			    
			    if(respCode != 200) {  
			    	errCount++;
			    }  
			}catch (Exception ex) {  
			    errCount++;   
			    continue;  
			}  
	   }  
	   
	   if(errCount==5){
		   return false;  
	   }else{
		   return true;  
	   }
	}  
}

class MapKeyComparator implements Comparator<String> {
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}
}
