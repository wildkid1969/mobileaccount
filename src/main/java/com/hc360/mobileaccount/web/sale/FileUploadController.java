package com.hc360.mobileaccount.web.sale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.reflect.TypeToken;
import com.hc360.mobileaccount.common.YZXConstant;
import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.ExerciseResult;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.UserQuestion;
import com.hc360.mobileaccount.po.UserReply;
import com.hc360.mobileaccount.po.UserTalk;
import com.hc360.mobileaccount.service.AudioService;
import com.hc360.mobileaccount.service.ExerciseResultService;
import com.hc360.mobileaccount.service.UserQuestionService;
import com.hc360.mobileaccount.service.UserReplyService;
import com.hc360.mobileaccount.service.UserTalkService;
import com.hc360.mobileaccount.utils.FileUtils;
import com.hc360.mobileaccount.utils.MD5;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

	@Resource
	private UserTalkService userTalkService;

	@Resource
	private ExerciseResultService exerciseResultService;

	@Resource
	private UserReplyService userReplyService;

	@Resource
	private AudioService audioService;

	@Resource
	private UserQuestionService userQuestionService;

	/**
	 *  限制文件大小 30M
	 *  spring配置文件也限制了，注意修改
	 */
	private static final int FILE_SIZE = 31457280;

	/*
	 * 图片批量上传到图片服务器
	 * 
	 * @param imgsJson 使用BASE64编码的图片数组
	 */
	@RequestMapping("uploadImage")
	@ResponseBody
	public String uploadImage(String imgsJson,String callback) {
		List<String> imgsReturn = new ArrayList<String>();
		List<String> imgs = MobileAccountUtils.getGson().fromJson(imgsJson,
				new TypeToken<ArrayList<String>>() {
				}.getType());
		for (int i = 0; imgs != null && i < imgs.size(); i++) {
			byte[] buffer = new Base64().decode(imgs.get(i).substring(22));
			imgsReturn.add(FileUtils.uploadImage(buffer));
		}
		
		if(!StringUtils.isEmpty(callback)){
			return callback +"("+MobileAccountUtils.getGson().toJson(imgsReturn)+")";
		}

		return MobileAccountUtils.getGson().toJson(imgsReturn);
	}
	
	
	/**
	 * 上传单张图片
	 * @param imgStr 使用BASE64编码的图片
	 * @param callback
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadMyImage")
	@ResponseBody
	public String uploadMyImage(String imgStr,String callback) throws IOException {
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		String img = MobileAccountUtils.getGson().fromJson(imgStr,new TypeToken<String>() {}.getType());
		System.out.println("img:"+img);
		
		byte[] buffer = new Base64().decode(img.substring(22));
		String imgUrl = FileUtils.uploadImage(buffer);
		
		msg.setMsgBody(imgUrl);
		
		if(!StringUtils.isEmpty(callback)){
			return callback +"("+MobileAccountUtils.getGson().toJson(msg)+")";
		}
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	
	@RequestMapping(value = "uploadHeader", method = RequestMethod.POST)
	@ResponseBody
	public String uploadHeader(@RequestParam("uploadFile") MultipartFile file,String callback,HttpServletResponse response) throws IOException{
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		response.setHeader("Access-Control-Allow-Origin", "http://168.mobile.hc360.com");
		response.setHeader("Access-Control-Allow-Origin", "http://testwx.mdata.hc360.com");
		response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		
		String imgUrl = FileUtils.uploadImage(file.getBytes());
		
		msg.setMsgBody(imgUrl);
		
		if(!StringUtils.isEmpty(callback)){
			return callback +"("+MobileAccountUtils.getGson().toJson(msg)+")";
		}
		return MobileAccountUtils.getGson().toJson(msg);
	}



	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return RC4加密后的文件路径
	 * @throws java.io.IOException
	 */
	@RequestMapping(value = "uploadAudio", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAudio(@RequestParam("uploadFile") MultipartFile file,
			Integer type) {
		ReturnMsg msg = new ReturnMsg();

		// 限制上传文件大小
		if (file.getSize() > FILE_SIZE) {
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}

		// 语音类型：1 话术训练 2营销对练结果(暂使用云之讯录音) 3评论 4章节 5用户提问）

		String mark = "";
		if (type == 1) {
			mark = "talk";
		} else if (type == 2) {
			mark = "exercise";
		} else if (type == 3) {
			mark = "reply";
		} else if (type == 4) {
			mark = "chapter";
		} else if (type == 5) {
			mark = "question";
		}

		// 过滤非音频格式的文件
		if (FileUtils.isAudio(file)) {
			String fileUrl = FileUtils.uploadAudio(FileUtils.AUDIO_PATH + mark,file);

			// 上传成功返回加密后的文件路径
			msg.setCode(200);
			msg.setMsgBody(RC4.encry_RC4_string(fileUrl, RC4.KEY));
			return MobileAccountUtils.getGson().toJson(msg);
		} else {
			msg.setCode(300);
		}

		return MobileAccountUtils.getGson().toJson(msg);
	}

	/**
	 * 读取语音文件
	 * 
	 * 语音类型： 1 话术训练 2营销对练结果 3评论 4 章节 5 用户提问
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getAudioFile")
	public void getAudioFile(String userid, Long objectId, Integer type,
			String sign, HttpServletResponse response) {
		String filePath = "";

		boolean flag = MD5.verify(userid + objectId + type, sign, MD5.KEY,
				"utf-8");

		if (flag) {
			if (type == 1) {
				UserTalk talk = userTalkService.getById(objectId);
				if (talk != null) {
					filePath = talk.getAudioUrl();
				}
			} else if (type == 2) {
				ExerciseResult result = exerciseResultService
						.getByRoomId(objectId);
				if (result != null) {
					filePath = result.getAudioUrl();
				}
			} else if (type == 3) {
				UserReply reply = userReplyService.getById(objectId);
				if (reply != null) {
					filePath = reply.getAudioUrl();
				}
			} else if (type == 4) {
				Audio audio = audioService.getById(objectId);
				if (audio != null) {
					filePath = audio.getUrl();
				}
			} else if (type == 5) {
				UserQuestion qestion = userQuestionService.getById(objectId);
				if (qestion != null) {
					filePath = qestion.getAudioUrl();
				}
			}

			if (!StringUtils.isEmpty(filePath)) {

				try {
					byte[] b = FileUtils.readFile(filePath);
//					byte[] b = FileUtils.readByteFile(filePath);

					response.setStatus(HttpServletResponse.SC_OK);
					response.setContentType("audio/mpeg");
					response.setCharacterEncoding("UTF-8");
					response.setContentLength(b.length);
					response.getOutputStream().write(b);
					response.flushBuffer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	/**
	 * 下载对练结果里的云之讯录音链接 并更新上传务器后的地址
	 * 由于挂完电话立刻去下载经常文件不能播，所以改用定时任务，隔一段时间再去下载
	 */
	@RequestMapping(value = "downloadYzxAudio")
	@ResponseBody
	public String updateExercise(Long roomid) {
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);

		ExerciseResult result = exerciseResultService.getAudioUrlByRoomId(roomid);

		if (result != null) {
			String filePath = FileUtils.AUDIO_PATH + "yzxcall" + File.separator;
			String filename = result.getUserid() + "-"+ System.currentTimeMillis() + ".mp3";

			System.out.println("downUrl:" + result.getAudioUrl());

			if (!StringUtils.isEmpty(result.getAudioUrl())) {

				FileUtils.getURLResource(filePath, filename,result.getAudioUrl());

				result.setRoomid(result.getRoomid());
				result.setAudioUrl(filePath + filename);
				result.setIsDown(1);
				exerciseResultService.update(result);

				msg.setCode(200);
			}
		}

		return MobileAccountUtils.getGson().toJson(msg);

	}

	/**
	 * 定时下载云之讯的录音文件并清理没有云之讯录音链接的记录
	 */
	@RequestMapping(value="downAudios")
	@Scheduled(cron = "0 0 02,05 * * ?")
	public void downloadAudioFileByTime() {
		List<ExerciseResult> list = exerciseResultService.getAllNoDownloadResult();
		
	  	String filePath = FileUtils.AUDIO_PATH + "yzxcall" + File.separator;
		String filename = "";
    	for(int i=0;i<list.size();i++){
    		filename = list.get(i).getUserid() + "-"+ System.currentTimeMillis() + ".mp3";
    		
        	System.out.println("downUrl:"+list.get(i).getAudioUrl());
        	
        	if(!StringUtils.isEmpty(list.get(i).getAudioUrl())){
        		FileUtils.getURLResource(filePath,filename,list.get(i).getAudioUrl());
        		
        		list.get(i).setRoomid(list.get(i).getRoomid());
        		list.get(i).setAudioUrl(filePath+filename);
        		list.get(i).setIsDown(1);
        		exerciseResultService.update(list.get(i));
        	}else{
        		exerciseResultService.delete(list.get(i).getId());
        	}
    		
    	}

	}
	
	/**
	 * 批量清理未对练完成的记录
	 */
//	@Scheduled(cron = "0 0 02 * * ?")
	public void deleteGarageExercise() {
		List<ExerciseResult> list = exerciseResultService.getAllNoDownloadResult();
		for (int i = 0; i < list.size(); i++) {
			exerciseResultService.delete(list.get(i).getId());
		}
		
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MD5.md5Encode("2200822023032" + MD5.KEY));

		String filePath = "d:\\" + "yzxcall";
		String filename = "2019212008" + "-" + new Date().getTime() + ".wav";
		String callid="62347042296793uGxOn770152761";
		String downloadAudioUrl = "http://www.ucpaas.com/fileserver/record/9a5d6c8c8f9feb180deaf63d771f76d4_62347042296793uGxOn770152761_20160817?sig="
				+MD5.md5Encode(YZXConstant.ACCOUNT_SID+callid+YZXConstant.AUTH_TOKEN);
		System.out.println("downUrl:" + downloadAudioUrl);
		FileUtils.getURLResource(filePath, filename, downloadAudioUrl);
	}

}