package com.hc360.mobileaccount.umeng.push;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hc360.mobileaccount.common.UmengConstant;
import com.hc360.mobileaccount.umeng.push.android.AndroidBroadcast;
import com.hc360.mobileaccount.umeng.push.android.AndroidCustomizedcast;
import com.hc360.mobileaccount.umeng.push.android.AndroidFilecast;
import com.hc360.mobileaccount.umeng.push.android.AndroidGroupcast;
import com.hc360.mobileaccount.umeng.push.android.AndroidUnicast;
import com.hc360.mobileaccount.umeng.push.ios.IOSBroadcast;
import com.hc360.mobileaccount.umeng.push.ios.IOSCustomizedcast;
import com.hc360.mobileaccount.umeng.push.ios.IOSFilecast;
import com.hc360.mobileaccount.umeng.push.ios.IOSGroupcast;
import com.hc360.mobileaccount.umeng.push.ios.IOSUnicast;

public class PushMain {
	private String appKey = "";
	private String appMasterSecret = "";
	private PushClient client = new PushClient();
	
	public PushMain(String appKey,String appMasterSecret){
		this.appKey = appKey;
		this.appMasterSecret = appMasterSecret;
	}
	
	public void sendAndroidBroadcast() throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(appKey,appMasterSecret);
		broadcast.setTicker("Android broadcast ticker");
		broadcast.setTitle("中文的title");
		broadcast.setText("Android broadcast text");
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		broadcast.setProductionMode();
		// Set customized fields
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}
	
	public void sendAndroidUnicast(String token,String title,String text,String cmid) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken( token);
		unicast.setTicker( "Message");
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.goActivityAfterOpen("com.hc360.yellowpage.ui.CustomerManagerDeatil");
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		unicast.setExtraField("cmid", cmid);
		client.send(unicast);
	}
	
	/**
	 * 发送message消息
	 * @param token
	 * @param ticker
	 * @param title
	 * @param text
	 * @param activity
	 * @param custom
	 * @param extraMap
	 * @throws Exception
	 */
	public int sendAndroidUnicastMsg(String token,String ticker,String title,String text,String activity,String custom,Map<String,String> extraMap) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken(token);
		unicast.setTicker(ticker);
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.goActivityAfterOpen(activity);
		unicast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
		unicast.setCustomField(custom);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		Set<String> key = extraMap.keySet();
		Iterator<String> it = key.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            unicast.setExtraField(s, extraMap.get(s));
        }
		
		return client.send(unicast);
	}
	
	
	/**
	 * 发送notice消息
	 * @param token
	 * @param ticker
	 * @param title
	 * @param text
	 * @param activity
	 * @throws Exception
	 */
	public int sendAndroidUnicastNotice(String token,String ticker,String title,String text,String activity,String id) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken( token);
		unicast.setTicker(ticker);
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.goActivityAfterOpen(activity);
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		if(!StringUtils.isEmpty(id)){
			unicast.setExtraField("id", id);
		}
		unicast.setProductionMode();
		return client.send(unicast);
	}
	
	
	
	public void sendActiontoAndroid(String token,String title,String text,String cmid) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken( token);
		unicast.setTicker("Android unicast ticker");
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.goActivityAfterOpen("com.hc360.yellowpage.ui.CustomerManDetail");
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		unicast.setExtraField("cmid", cmid);
		client.send(unicast);
	 
	}
	
	public void sendAndroidGroupcast() throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(appKey,appMasterSecret);
		/* 
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		testTag.put("tag", "test");
		TestTag.put("tag", "Test");
		tagArray.put(testTag);
		tagArray.put(TestTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		groupcast.setFilter(filterJson);
		groupcast.setTicker("Android groupcast ticker");
		groupcast.setTitle("中文的title");
		groupcast.setText("Android groupcast text");
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		groupcast.setProductionMode();
		client.send(groupcast);
	}
	
	public void sendAndroidCustomizedcast() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appKey,appMasterSecret);
		// Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "13381310302");
		customizedcast.setTicker("Android customizedcast ticker");
		customizedcast.setTitle("中文的title");
		customizedcast.setText("Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}
	
	public void sendAndroidCustomizedcastFile() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appKey,appMasterSecret);
		// Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		String fileId = client.uploadContents(appKey,appMasterSecret,"aa"+"\n"+"bb"+"\n"+"alias");
		customizedcast.setFileId(fileId, "alias_type");
		customizedcast.setTicker("Android customizedcast ticker");
		customizedcast.setTitle("中文的title");
		customizedcast.setText("Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}
	
	public void sendAndroidFilecast() throws Exception {
		AndroidFilecast filecast = new AndroidFilecast(appKey,appMasterSecret);
		// upload your device tokens, and use '\n' to split them if there are multiple tokens 
		String fileId = client.uploadContents(appKey,appMasterSecret,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setTicker("Android filecast ticker");
		filecast.setTitle("中文的title");
		filecast.setText("Android filecast text");
		filecast.goAppAfterOpen();
		filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		client.send(filecast);
	}
	
	
	
	//////////////////////////////////////////////IOS//////////////////////////////////////////////////////////
	
	/**
	 * 发送message消息
	 * @param token
	 * @param ticker
	 * @param title
	 * @param text
	 * @param activity
	 * @param custom
	 * @param extraMap
	 * @throws Exception
	 */
	public int sendIOSUnicastMsg(String token,String ticker,String title,String text,String activity,String custom,Map<String,String> extraMap) throws Exception {
		IOSUnicast unicast = new IOSUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken(token);
		unicast.setAlert(text);//提示信息
		unicast.setBadge(1);//个数标识
		unicast.setSound("default");
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setTestMode();
		// Set customized fields
		Set<String> key = extraMap.keySet();
		Iterator<String> it = key.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            unicast.setCustomizedField(s, extraMap.get(s));
        }
		
		return client.send(unicast);
	}
	
	
	/**
	 * 发送notice消息
	 * @param token
	 * @param ticker
	 * @param title
	 * @param text
	 * @param activity
	 * @throws Exception
	 */
	public int sendIOSUnicastNotice(String token,String ticker,String title,String text,String activity,String id) throws Exception {
		IOSUnicast unicast = new IOSUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken(token);
		unicast.setAlert(text);//提示信息
		unicast.setBadge(1);//个数标识
		unicast.setSound("default");
		
		if(!StringUtils.isEmpty(id)){
			unicast.setCustomizedField("id", id);
		}
		
		// Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setTestMode();
		return client.send(unicast);
	}
	
	
	public void sendIOSBroadcast() throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(appKey,appMasterSecret);

		broadcast.setAlert("IOS 广播测试");
		broadcast.setBadge(0);
		broadcast.setSound("default");
		// set 'production_mode' to 'true' if your app is under production mode
		broadcast.setTestMode();
		// Set customized fields
		broadcast.setCustomizedField("test", "helloworld");
		client.send(broadcast);
	}
	
	public void sendIOSUnicast(String token) throws Exception {
		IOSUnicast unicast = new IOSUnicast(appKey,appMasterSecret);
		// Set your device token
		unicast.setDeviceToken(token);
		unicast.setAlert("IOS 单播测试");//提示信息
		unicast.setBadge(0);//个数标识
		unicast.setSound("default");
		// set 'production_mode' to 'true' if your app is under production mode
		unicast.setTestMode();
		// Set customized fields
		unicast.setCustomizedField("test", "helloworld");
		client.send(unicast);
	}
	
	public void sendIOSGroupcast() throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(appKey,appMasterSecret);
		/* 
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"iostest"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		testTag.put("tag", "iostest");
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		// Set filter condition into rootJson
		groupcast.setFilter(filterJson);
		groupcast.setAlert("IOS 组播测试");
		groupcast.setBadge(0);
		groupcast.setSound("default");
		// set 'production_mode' to 'true' if your app is under production mode
		groupcast.setTestMode();
		client.send(groupcast);
	}
	
	public void sendIOSCustomizedcast() throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(appKey,appMasterSecret);
		// Set your alias and alias_type here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setAlert("IOS 个性化测试");
		customizedcast.setBadge(0);
		customizedcast.setSound("default");
		// set 'production_mode' to 'true' if your app is under production mode
		customizedcast.setTestMode();
		client.send(customizedcast);
	}
	
	public void sendIOSFilecast() throws Exception {
		IOSFilecast filecast = new IOSFilecast(appKey,appMasterSecret);
		// upload your device tokens, and use '\n' to split them if there are multiple tokens 
		String fileId = client.uploadContents(appKey,appMasterSecret,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setAlert("IOS 文件播测试");
		filecast.setBadge(0);
		filecast.setSound("default");
		// set 'production_mode' to 'true' if your app is under production mode
		filecast.setTestMode();
		client.send(filecast);
	}
	
	public static void main(String[] args) {
		// set your appKey and master secret here
		PushMain demo = new PushMain(UmengConstant.IOS_APP_KEY,UmengConstant.IOS_APP_MASTER_SECRET);
		try {
//			 demo.sendAndroidCustomizedcast();
			/* these methods are all available, just fill in some fields and do the test
			 * demo.sendAndroidCustomizedcastFile();
			 * demo.sendAndroidBroadcast();
			 * demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast();
			 * demo.sendAndroidFilecast();
			 * 
			 * demo.sendIOSBroadcast();
			 * demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast();
			 * demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
			demo.sendIOSUnicast("62668f1fb0f202d5a26cdba63dc050dcc19970a44382f184418f90ed81ad4422");
			String s = "AiCinMtR0UGfjKhefRY-H5TAMKzpOU4gzZ056bbFZH4B";
			System.out.println(s.length());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
