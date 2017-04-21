package com.hc360.mobileaccount.umeng.wsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hc360.mobileaccount.po.QuestionAndPost;
import com.hc360.mobileaccount.umeng.wsq.UmengHttpClient.HttpMethod;


public class WsqMain {
    public static final String APP_KEY = "5742ce0d67e58e523b0000b2";
    public static final String APP_SECRECT = "02434e251982d3e2d131533eab25499d";
    
    private static final String BASEURL = "https://rest.wsq.umeng.com";
//   private static final String BASEURL = "http://opentest.rest.api.wsq.umeng.com";
    
    private static final String GET_ACCESS_TOKEN_API = "/0/get_access_token";
    private static final String USER_INFO_UPDATE_API = "/0/user/update";
    private static final String USER_HEADIMG_API = "/0/user/icon";
    private static final String GET_TIMELINE_COMMUNITY_API = "/0/feed/community_timeline";
    private static final String GET_COMMENT_LIST_API = "/0/feed/comments";
    private static final String FEED_PUB_API = "/0/feed/update";
    private static final String UPLOAD_IMG = "/0/image_token";
    
    
//    private static final String api = "/0/image_token";
//    private static final String api2 = "/0/feed/favourites/create";
//    private static final String api4 = "/0/point/details";
//    private static final String api5 = "/0/point/op";
    
    
    public static String getAccessToken(String phone,String nickname){
        HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("source_uid", phone);
        objectHashMap.put("source", "others");
        objectHashMap.put("name_l", "default");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", nickname);
        map.put("gender", 1);
        objectHashMap.put("user_info", map);
        String token = UmengHttpClient.accessTokenRequest(objectHashMap, BASEURL + GET_ACCESS_TOKEN_API, APP_SECRECT);
        System.out.println(token);
        JSONObject jsonObject = JSONObject.parseObject(token);
        //拿到access_token之后要在 UmengHttpClient.ACCESS_TOKEN 里面设置一下
        UmengHttpClient.ACCESS_TOKEN = jsonObject.getString("access_token");

    	return jsonObject.getString("access_token");
    }
    
    public static String getTimelineCommunity(int page,int size){
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ak", APP_KEY);
        map.put("page", page);
        map.put("count", size);
        String result = UmengHttpClient.sentRequest(BASEURL+GET_TIMELINE_COMMUNITY_API, HttpMethod.GET, map);
        
        return result;
    }
    
    public static String getCommentList(String feedId,int page,int size){
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ak", APP_KEY);
        map.put("feed_id", feedId);
        map.put("page", page);
        map.put("count", size);
        String result = UmengHttpClient.sentRequest(BASEURL+GET_COMMENT_LIST_API, HttpMethod.GET, map);
        
        return result;
    }
    
    
    //社区帖子统计
    public static void getTimelineCommunityResult(int page,int size){
    	String result = getTimelineCommunity(page,size);
        
        JSONObject json = JSONObject.parseObject(result);
        JSONArray jarr = JSONObject.parseArray(json.getString("results"));
        
        List<QuestionAndPost> qpList = new ArrayList<QuestionAndPost>();
        int comments = 0;
        int liked = 0;
        
        boolean vestFlag = false;
        
        String[] umengid = {"57ac5fe77019c91648a51e5b","57b18a9655c400620f74a5fe","57b19f7eb51b2d6bf04d109c","57b2a997ee78501d8705438c","57b2ab7dea77f79fe9314411","57e4902dd01463389d8c60e7","57e493117019c93a9d377d57","57d53397ea77f7ab9e9bd81b","57d523d7b51b2d267c15a292","57f9dfe27019c975a9d974b2","57b17ecbee78507cea759e35","57b19dc0ea77f76c803a4fa3","57b2ecb9ee7850173bc00341","57b2f4c955c400d35ed57627","57b5831c55c40056e2369f2f","57b5858c7019c97e388ee261","57b58849b51b2d167f3bbc73","57d9110fd014635662bf9f9a","57b6c953b51b2dacd76753ee","57b6d10055c40096df8dd87f","57b6d9caee78507005fbb450","57b6e2dad014637e0161fe98","57babd08ea77f74cd6a0a325","57bc0d5b7019c93dcaafa080","57bd3a9bea77f702bea3cc5f","57b18e73ee78507e6d9665c3","57b1925855c4006fea183229","57b19e997019c92467018b6b","57b26f667019c90b67f403c4","57b2e4cb55c400bc7324cb2d","57b42ed4ea77f703f030aec8","57b434317019c92beef2fce4","57b4356855c40001abb0bc82","57b43a9eea77f71901cc6855","57b43e3e7019c97af105cd57","57b58baeb51b2d187b8c6d73","57b58d0bea77f71bddb7a831","57b58e7555c4006425020992","57b58f5eb51b2d13782ccdf8","5805fe47d01463029ff16d01","57ac45dcea77f71d1e500462","57b1af89ee785028b1ec6dbf","57b1b26e55c400a87596b0b4","57b1b3fcb51b2d4bba780e67","57b1b4927019c9081aeb6348","57b1b530b51b2d8a3ef09db9","57b2ebedee7850173bc0025a","57b2ed71d01463b04a04376e","57b2ee99ea77f746106489bf","57b2ef1cea77f747db269b48","57b2efdc7019c971c420ab0e","57b2ce1aee78506b07fd67a9","57b2f683b51b2d4a87b6e29e","57b3bda9ee78502e80d2d74b","57b40117b51b2d79dae7a6c9","57f371b5ea77f716f0219686","57b4301b7019c903b8ce739b","57b43dacea77f728b8e2e87d","57bad0d055c400973b767b54","57bc212ad014633a26311b1e","57bd1226d01463559d0bb094","57bd1473b51b2d9e639c144c","57bd4f0255c400fc26a50136","57be5018ea77f773c98d2685","57beab25ee7850496ed93408","57bec32fea77f76f1d3160b4","57bec9d37019c919c227eb4b","57bec527d014632c91112de1","57d64cc455c40031ffab3909","57cfe296ea77f7023bfc5248","57b5915dee785014d6141012","57b40800ea77f796a8289956"}; 
        
        for(int i=0;i<jarr.size();i++){
        	String stats = jarr.getJSONObject(i).getString("stats");
        	
        	JSONObject statsJson = JSONObject.parseObject(stats);
        	comments = statsJson.getIntValue("comments");
        	liked = statsJson.getIntValue("liked");
        	
        	String feedId = jarr.getJSONObject(i).getString("id");
    		String content = jarr.getJSONObject(i).getString("content");
    		String createtime = jarr.getJSONObject(i).getString("create_time");
    		String name = JSONObject.parseObject(jarr.getJSONObject(i).getString("creator")).getString("name");
    		String userid = JSONObject.parseObject(jarr.getJSONObject(i).getString("creator")).getString("id");
    		
    		String headerimg = JSONObject.parseObject(JSONObject.parseObject(jarr.getJSONObject(i).getString("creator")).getString("icon_url")).getString("240");
    		String imgUrls = jarr.getJSONObject(i).getString("image_urls");
    		
//    		for(String uid:umengid){
//    			if(uid.contains(userid)){
//    				vestFlag = true;
//    			}
//    		}
    		
    		if(true){
    			if(content.length()>3){
        			content = content.substring(0,3)+"...";
        		}
//        		System.out.println(name+"$"+content+"$"+createtime);
    			
    			String c = getCommentList(feedId,1,100);
    			JSONObject cJson = JSONObject.parseObject(c);
    			int ctotal = cJson.getInteger("total");
    			if(ctotal>0){
					JSONArray commentArr = JSONObject.parseArray(cJson.getString("results"));
	    			for(int j=0;j<commentArr.size();j++){
	    				String ccontent = commentArr.getJSONObject(j).getString("content");
	    				String ccreate_time = commentArr.getJSONObject(j).getString("create_time");
	    				String replyId = JSONObject.parseObject(commentArr.getJSONObject(j).getString("creator")).getString("id");
	    				String replyName = JSONObject.parseObject(commentArr.getJSONObject(j).getString("creator")).getString("name");
	    				
	    				for(String uid:umengid){
	    	    			if(uid.contains(replyId)){
	    	    				vestFlag = true;
	    	    			}
	    	    		}
	    				if(!vestFlag){
	    					if(ccontent.length()>3){
		    					ccontent = ccontent.substring(0,3)+"...";
		            		}
		    				
		    				System.out.println(replyName+"$"+ccontent+"$"+ccreate_time);
	    				}
	    				
	    			}
				}
    			
        		
    		}
    		
    		vestFlag = false;
        }
    }
    
    
    //更新用户信息
    public static String updateUserInfo(String phone,String nickname,String imgUrl){
    	getAccessToken(phone,nickname);
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ak", APP_KEY);
        map.put("name", nickname);
        map.put("icon_url", imgUrl);
        String result = UmengHttpClient.sentRequest(BASEURL+USER_INFO_UPDATE_API, HttpMethod.PUT, map);
        String result2 = UmengHttpClient.sentRequest(BASEURL+USER_HEADIMG_API, HttpMethod.PUT, map);
        
        return result+"\n"+result2;
    }
    
    //发布帖子
    public static String pubFeed(String phone,String nickname,String title,String content,String imgStr){
    	getAccessToken(phone,nickname);
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ak", APP_KEY);
        map.put("img_str", imgStr);
        map.put("title", title);
        map.put("content", content);
        String result = UmengHttpClient.sentRequest(BASEURL+FEED_PUB_API, HttpMethod.POST, map);
        
        return result;
    }
    
    //上传图片
    public static String uploadImage(String phone,String nickname,String imgPath){
    	getAccessToken(phone,nickname);
    	HashMap<String,Object> map = new HashMap<String,Object>();
        String postResult = UmengHttpClient.sentRequest(BASEURL + UPLOAD_IMG, UmengHttpClient.HttpMethod.POST, map);
        System.out.println(postResult);

        // 好了,已经拿到image token了,可以上传图片了!
        JSONObject json = JSONObject.parseObject(postResult);

        String imgUrl = null;
        if (json != null) {
            System.out.println("imagetoken="+json.getString("token"));
            String imageToken = json.getString("token");
            HashMap<String, Object> imageParameter = new HashMap<String, Object>();
            String imageResult = UmengHttpClient.uploadImage(imageToken, imgPath, imageParameter);
//            System.out.println(imageResult);
            
            JSONObject imgJson = JSONObject.parseObject(imageResult);
            
            if(imgJson!=null){
            	imgUrl = imgJson.getString("url");
            	System.out.println("url:"+imgUrl);
            }
            
        }
        
        return imgUrl;
    }
    
    public static void main(String[] args) {
        
//      getTimelineCommunityResult(4,100);
    	
//    	getAccessToken("17710928707","");
//    	String r = updateUserInfo("17710928707","星星爱做梦","http://img008.hc360.cn/k1/M05/69/37/wKhQwFgSqUuEHLtVAAAAABHLISs059.png");
    	String r = pubFeed("15011122311","","Woohoo","【美味不用等】感谢您致电麻辣诱惑中关村津乐汇店！激情夏日不如吃小龙虾吧！地址:中关村大街15号中关村广场购物中心津乐汇2楼 订餐电话：010-59863088 附近停车场为：津乐汇地下停车场，鼎好地下停车 地铁：4号线中关村站出 公交：307路; 355路; 365路; 498路; 549路; 579路; 601路; 614路; 681路; 696路; 697路; 699路; 717路; 982路; 特18路; 特4路; 特6路; 运通105线; 运通110线 恭候您的光临！","http://img3.duitang.com/uploads/item/201607/05/20160705175849_Sd5fN.jpeg;http://c-54d19014ee785020801f83c4.image.alimmdn.com/2016-11-01/570f41897019c9083c086176/ecda3a52b56a8c1cc1d7ec86a26a89c4");
    	System.out.println(r);
    	
//    	uploadImage("15011122311","","d:/12.jpg");
    	
    	
     }

}