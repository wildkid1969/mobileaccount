package com.hc360.mobileaccount.utils;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hc360.b2b.netWorker.GetUrl;
import com.hc360.mobileaccount.umeng.wsq.WsqMain;
import com.hc360.rsf.config.ConfigLoader;
import com.hc360.rsf.config.RsfListener;
import com.hc360.rsf.imgup.FileStorageService;

public class FileUtils {
	
	public static final String AUDIO_PATH = File.separator+"mobileaccount"+File.separator;
	
	/**
	 * 上传图片工具类
	 * 
	 * @param image
	 * @return
	 */
	public static String uploadImage(byte[] image) {
		ConfigLoader configLoader = RsfListener.getConfigLoader();
		FileStorageService userService = (FileStorageService) configLoader
				.getServiceProxyBean("clientUserServiceImpl");
		String resource = userService.write(image, "png");
		return GetUrl.getFastDFSUrl(resource);
	}

	 /**
	   * 读取源文件内容
	   * @param filename String 文件路径
	   * @return byte[] 文件内容
	   */
	public static byte[] readFile(String filename){
		if(filename==null || filename.equals("")){
		      throw new NullPointerException("无效的文件路径");
		}
		
	    File file =new File(filename);
	    
	    long len = file.length();
	    byte[] bytes = new byte[(int)len];

	    BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	    int r;
		try {
			r = bis.read(bytes);
			if (r != len){
		    	bis.close();
			    throw new IOException("读取文件不正确");
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bis!=null){
				 try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    

	    return bytes;

	}
	
	public static byte[] readByteFile(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
        	throw new NullPointerException("File is too large！");
        }  
        
        FileInputStream fis= new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  
        // 确保所有数据均被读取  
        if (offset != buffer.length) {  
        	fis.close();
        	throw new IOException("Could not completely read file "+ file.getName());  
        }  
        
        fis.close();  
        
        return buffer;  
    }  
	
	
	/**
	 * 上传音频文件
	 * @param request
	 * @param path
	 * @return 文件地址
	 */
	public static String uploadAudio(String path,MultipartFile uploadFile){

		String fileFormat = FileUtils.getFileFormat(uploadFile);

		if(StringUtils.isEmpty(fileFormat) ){
			fileFormat = "mp3";
		}
		
        String audioFile = System.currentTimeMillis()+"."+fileFormat;
        
        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        
        FileOutputStream fo = null;
        try {
        	byte[] bytes = uploadFile.getBytes();   
        	
        	File file = new File(directory,audioFile);
    		
    		fo = new FileOutputStream(file);
        	fo.write(bytes);
        	
        } catch (IOException e) {
           e.printStackTrace();
		   return  "error";
        }finally{
        	if(fo!=null){
        		 try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        
        return directory.getAbsolutePath()+File.separator+audioFile;
    }
	
	 /**
     * 是否是音频
     * @param format
     * @return
     */
    public static boolean isAudio(MultipartFile file){
    	String[] formatArr = {"mid","mp3","amr","aac","ogg","act","wma","wav","flac","ape"}; 
    	
    	String format = getFileFormat(file);
    	String contentType = file.getContentType();
    	
    	for(String s:formatArr){
    		if(!StringUtils.isEmpty(format) && s.contains(format)){
    			if(!StringUtils.isEmpty(contentType) 
    					&& (contentType.toLowerCase().contains("audio")||contentType.toLowerCase().contains("application/octet-stream"))){
    				return true;
        		}
    		}
    	}
    	
    	return false;
    }
    
    public static String getFileFormat(MultipartFile file){
    	String filename = file.getOriginalFilename();
    	//过滤非音频格式的文件
    	if(!StringUtils.isEmpty(filename) && filename.contains(".")){
    		String fileFormat = filename.substring(filename.lastIndexOf(".")+1);
    		if(!StringUtils.isEmpty(fileFormat)){
    			return fileFormat.toLowerCase();
    		}
    	}
		return null;
    }
    
    
    /**
     * 从url里获取文件
     * 
     * @param path 路径
     * @param filename 文件名
     * @param urlStr 链接
     */
    public static void getURLResource(String path,String filename,String urlStr){ 
    	InputStream in = null;
    	BufferedInputStream bis = null;
    	FileOutputStream fos = null;
    	BufferedOutputStream bos = null;
		try {
	        URL url = new URL(urlStr);   
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
		    //得到输入流  
		    in = conn.getInputStream();
//          InputStream inStream = url.openStream();
		    
		    File dir = new File(path);
		    if(!dir.exists()){
		    	dir.mkdirs();
		    }
		    
	        bis = new BufferedInputStream(in);
	        fos = new FileOutputStream(dir+File.separator+filename);
			bos = new BufferedOutputStream(fos);
			
			byte[] buf = new byte[bis.available()];
			int r = 0;
			
			while((r=bis.read(buf))!=-1){
				bos.write(buf,0,r);
			}
			
			bos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bos!=null){
					bos.close();
				}
				if(fos!=null){
					fos.close();
				}
				if(bis!=null){
					bis.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

    }
    
    /** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData); 
        
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }  
    
    
    /**
     * FIle转byte数组
     * @param file
     * @return
     */
    public static byte[] File2byte(File file){  
        byte[] buffer = null;  
        try{  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1){  
                bos.write(b, 0, n);  
            }  
            fis.close();
            bos.close();  
            buffer = bos.toByteArray();//此方法大文件可能会OutOfMemory
        }catch (FileNotFoundException e){  
            e.printStackTrace();  
        }catch (IOException e){  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
    
    
    /**
     * html内容转图片然后上传
     * @param phone
     * @param content
     * @return 返回图片链接
     */
    public static String htmlToImage(String phone,String content){
    	HtmlImageGenerator imageGenerator = new HtmlImageGenerator();  
        String head= "<!DOCTYPE html>"
                    +"<html><body style='width:300px;font:normal 16px/25px SimSun;border:6px solid #FFFFFF;padding:5px;'>";
        String foot = "</body></html>";
        
        //装载html
        String html = head+content+foot;
        imageGenerator.loadHtml(html);  
        
        //创建图片文件
        String filePath = AUDIO_PATH+"wsq";
        String fileName = "longfeed"+phone+".png";
        
        File directory = new File(filePath);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        
        //将html写入图片文件
        File file = new File(filePath,fileName);
        imageGenerator.saveAsImage(file);
        
        //上传图片得到链接
        String s = WsqMain.uploadImage(phone, "", filePath+File.separator+fileName);
        System.out.println(s);
        
        file.delete();
        
    	return s;
    }
    
    public static void main(String[] args) {
    	String content = "推销技巧一：厉兵秣马<br />兵法说，不打无准备之仗。做为销售来讲，道理也是一样的。很多刚出道的促销员通常都有一个误区，以为销售就是要能说会道，其实根本就不是那么一回事。记得那时候我们培训了将近一个月，从产品知识到故障分析，从企业历史到销售技巧，每一个环节都反复练习，直至倒背如流。那时候我们同事之间经常互相打趣说咱都成了机器人了。我记得当时为了调试出一个最佳音乐效果，一没有顾客在场，我就专心致志地一个键一个键的反复试验，持续了将近一个星期，终于得到了自己满意的效果。<br />每次轮到自己休息，我总喜欢到各个卖场去转转：一来调查一下市场，做到心中有数。现在的顾客总喜欢讹促销员，哪里哪里有多么便宜，哪里哪里又打多少折了，如果你不能清楚了解这些情况，面对顾客时将会非常被动。二来可以学习一下别的促销员的技巧，只有博采各家之长，你才能炼就不败金身!<br />五大销售技巧和话术<br />推销技巧二：关注细节<br />现在有很多介绍促销技巧的书，里面基本都会讲到促销员待客要主动热情。但在现实中，很多促销员不能领会到其中的精髓，以为热情就是要满面笑容，要言语主动。其实这也是错误的，什么事情都要有个度，过分的热情反而会产生消极的影响。<br />热情不是简单地通过外部表情就能表达出来的，关键还是要用心去做。所谓精诚所至，金石为开!随风潜入夜，润物细无声，真正的诚就是想顾客所想，用企业的产品满足他们的需求，使他们得到利益。<br />五大销售技巧和话术"
    			+"<br /><br />推销技巧二：厉兵秣马<br />兵法说，不打无准备之仗。做为销售来讲，道理也是一样的。很多刚出道的促销员通常都有一个误区，以为销售就是要能说会道，其实根本就不是那么一回事。记得那时候我们培训了将近一个月，从产品知识到故障分析，从企业历史到销售技巧，每一个环节都反复练习，直至倒背如流。那时候我们同事之间经常互相打趣说咱都成了机器人了。我记得当时为了调试出一个最佳音乐效果，一没有顾客在场，我就专心致志地一个键一个键的反复试验，持续了将近一个星期，终于得到了自己满意的效果。<br />每次轮到自己休息，我总喜欢到各个卖场去转转：一来调查一下市场，做到心中有数。现在的顾客总喜欢讹促销员，哪里哪里有多么便宜，哪里哪里又打多少折了，如果你不能清楚了解这些情况，面对顾客时将会非常被动。二来可以学习一下别的促销员的技巧，只有博采各家之长，你才能炼就不败金身!<br />五大销售技巧和话术<br />推销技巧二：关注细节<br />现在有很多介绍促销技巧的书，里面基本都会讲到促销员待客要主动热情。但在现实中，很多促销员不能领会到其中的精髓，以为热情就是要满面笑容，要言语主动。其实这也是错误的，什么事情都要有个度，过分的热情反而会产生消极的影响。<br />热情不是简单地通过外部表情就能表达出来的，关键还是要用心去做。所谓精诚所至，金石为开!随风潜入夜，润物细无声，真正的诚就是想顾客所想，用企业的产品满足他们的需求，使他们得到利益。<br />五大销售技巧和话术"
    	+"<br /><br />推销技巧三：厉兵秣马<br />兵法说，不打无准备之仗。做为销售来讲，道理也是一样的。很多刚出道的促销员通常都有一个误区，以为销售就是要能说会道，其实根本就不是那么一回事。记得那时候我们培训了将近一个月，从产品知识到故障分析，从企业历史到销售技巧，每一个环节都反复练习，直至倒背如流。那时候我们同事之间经常互相打趣说咱都成了机器人了。我记得当时为了调试出一个最佳音乐效果，一没有顾客在场，我就专心致志地一个键一个键的反复试验，持续了将近一个星期，终于得到了自己满意的效果。<br />每次轮到自己休息，我总喜欢到各个卖场去转转：一来调查一下市场，做到心中有数。现在的顾客总喜欢讹促销员，哪里哪里有多么便宜，哪里哪里又打多少折了，如果你不能清楚了解这些情况，面对顾客时将会非常被动。二来可以学习一下别的促销员的技巧，只有博采各家之长，你才能炼就不败金身!<br />五大销售技巧和话术<br />推销技巧二：关注细节<br />现在有很多介绍促销技巧的书，里面基本都会讲到促销员待客要主动热情。但在现实中，很多促销员不能领会到其中的精髓，以为热情就是要满面笑容，要言语主动。其实这也是错误的，什么事情都要有个度，过分的热情反而会产生消极的影响。<br />热情不是简单地通过外部表情就能表达出来的，关键还是要用心去做。所谓精诚所至，金石为开!随风潜入夜，润物细无声，真正的诚就是想顾客所想，用企业的产品满足他们的需求，使他们得到利益。<br />五大销售技巧和话术"
    	+"<br /><br />推销技巧四：厉兵秣马<br />兵法说，不打无准备之仗。做为销售来讲，道理也是一样的。很多刚出道的促销员通常都有一个误区，以为销售就是要能说会道，其实根本就不是那么一回事。记得那时候我们培训了将近一个月，从产品知识到故障分析，从企业历史到销售技巧，每一个环节都反复练习，直至倒背如流。那时候我们同事之间经常互相打趣说咱都成了机器人了。我记得当时为了调试出一个最佳音乐效果，一没有顾客在场，我就专心致志地一个键一个键的反复试验，持续了将近一个星期，终于得到了自己满意的效果。<br />每次轮到自己休息，我总喜欢到各个卖场去转转：一来调查一下市场，做到心中有数。现在的顾客总喜欢讹促销员，哪里哪里有多么便宜，哪里哪里又打多少折了，如果你不能清楚了解这些情况，面对顾客时将会非常被动。二来可以学习一下别的促销员的技巧，只有博采各家之长，你才能炼就不败金身!<br />五大销售技巧和话术<br />推销技巧二：关注细节<br />现在有很多介绍促销技巧的书，里面基本都会讲到促销员待客要主动热情。但在现实中，很多促销员不能领会到其中的精髓，以为热情就是要满面笑容，要言语主动。其实这也是错误的，什么事情都要有个度，过分的热情反而会产生消极的影响。<br />热情不是简单地通过外部表情就能表达出来的，关键还是要用心去做。所谓精诚所至，金石为开!随风潜入夜，润物细无声，真正的诚就是想顾客所想，用企业的产品满足他们的需求，使他们得到利益。<br />五大销售技巧和话术";
    	System.out.println(content.replace("<br />", "").length());
    	htmlToImage("15011122311",content);
	}
    
}
