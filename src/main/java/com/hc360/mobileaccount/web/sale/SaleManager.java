package com.hc360.mobileaccount.web.sale;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hc360.mobileaccount.po.Ad;
import com.hc360.mobileaccount.po.Audio;
import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.CourseChapter;
import com.hc360.mobileaccount.po.Picture;
import com.hc360.mobileaccount.po.Video;
import com.hc360.mobileaccount.service.AdService;
import com.hc360.mobileaccount.service.SaleTeacherService;

/**
 * 后台管理
 * @author HC360
 *
 */
@Controller
@RequestMapping(value="saleadmin")
public class SaleManager {

	@Resource
	private AdService adService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	
	
	
	/**
	 * 获取广告列表
	 * @return
	 */
	@RequestMapping(value="getAdList")
	public ModelAndView getAdList(Ad ad){
		ModelAndView mv = new ModelAndView("adlist");
		List<Ad> adList = adService.getAdList(ad);
		
		mv.addObject("adList", adList);
		
		return mv;
	}
	
	
	/**
	 * 编辑广告时获取信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="editAd")
	public ModelAndView editAd(Long id){
		ModelAndView mv = new ModelAndView("editad");
		Ad ad = adService.getById(id);
		
		mv.addObject("ad", ad);
		
		return mv;
	}
	
	
	/**
	 * 更新广告
	 * @param ad
	 * @return
	 */
	@RequestMapping(value="saveAd")
	public ModelAndView saveAd(Ad ad){
		ModelAndView mv = new ModelAndView("editad");
		adService.update(ad);
		
		Ad a = adService.getById(ad.getId());
		
		mv.addObject("ad", a);
		mv.addObject("msg", "更新成功!");
		
		return mv;
	}
	
	
	/**
	 * 去插入广告的页面
	 * @param ad
	 * @return
	 */
	@RequestMapping(value="toInsertAd",method=RequestMethod.GET)
	public ModelAndView toinsertAd(Ad ad){
		return new ModelAndView("editad","ad",new Ad());
	}
	
	/**
	 * 插入广告
	 * @param ad
	 * @return
	 */
	@RequestMapping(value="insertAd",method=RequestMethod.POST)
	public ModelAndView insertAd(Ad ad){
		ModelAndView mv = new ModelAndView("editad");
		adService.insert(ad);
		
		Ad a = adService.getById(ad.getId());
		
		mv.addObject("ad", a);
		mv.addObject("msg", "插入成功!");
		
		return mv;
	}
	
	
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteAd")
	public ModelAndView deleteAd(Long id){
		ModelAndView mv = new ModelAndView("editad");
		adService.delete(id);
		
		mv.addObject("ad", new Ad());
		mv.addObject("msg", "删除成功!");
		
		return mv;
	}
	
	/**
	 * 删除广告
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="deleteAd2")
	public void deleteAd2(Long id,HttpServletResponse response) throws IOException{
		adService.delete(id);
		response.sendRedirect("getAdList");
		
	}
	
	
	
	
	
	/**
	 * 添加课程
	 * @param course
	 * @return
	 */
	@RequestMapping(value="toAddCourse")
	public ModelAndView toaddCourse(Course course){
		return new ModelAndView("editcourse");
	}
	@RequestMapping(value="addCourse")
	public ModelAndView addCourse(Course course){
		// 添加课程
		saleTeacherService.addCourse(course);
		
		return new ModelAndView("editcourse","msg","添加成功！");
	}
	
	
	/**
	 * 添加章节及资源
	 * @param course
	 * @return
	 */
	@RequestMapping(value="toAddChapter")
	public ModelAndView toaddChapter(Course course){
		return new ModelAndView("editchapter");
	}
	@RequestMapping(value="addChapter")
	public ModelAndView addChapter(CourseChapter chapter,Audio audio,String vname,Integer vtimeLength,String vimgUrl,String sdUrl,String videofrom){
		// 添加课程
		if(chapter.getId()==null){
			saleTeacherService.addCourseChapter(chapter);
		}
		
		// 保存课件资源（视频、音频、图片）
		if(chapter.getType()==1){
			Video video = new Video();
			video.setName(vname);
			video.setTimeLength(vtimeLength);
			video.setImgUrl(vimgUrl);
			video.setSdUrl(sdUrl);
			video.setVideofrom(videofrom);
			saleTeacherService.addVideo(video);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", chapter.getId());
			map.put("resourceid", video.getId());
			map.put("type", 1);
			saleTeacherService.addResourse(map);
		}

		if(chapter.getType()==2){
			
			saleTeacherService.addAudio(audio);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", chapter.getId());
			map.put("resourceid", audio.getId());
			map.put("type", 2);
			saleTeacherService.addResourse(map);
		}
		
		if(chapter.getType()==3){
			Picture pic = new Picture();
			pic.setUrl(audio.getUrl());//url属性通用
			saleTeacherService.addPicture(pic);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("chapterid", chapter.getId());
			map.put("resourceid", pic.getId());
			map.put("type", 3);
			saleTeacherService.addResourse(map);
		}

		
		
		return new ModelAndView("editchapter","msg","添加成功！");
	}
}
