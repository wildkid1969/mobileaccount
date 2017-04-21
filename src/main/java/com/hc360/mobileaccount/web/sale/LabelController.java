package com.hc360.mobileaccount.web.sale;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.Course;
import com.hc360.mobileaccount.po.Label;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.Teacher;
import com.hc360.mobileaccount.service.CourseService;
import com.hc360.mobileaccount.service.LabelService;
import com.hc360.mobileaccount.service.SaleTeacherService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;

@Controller
@RequestMapping(value="saleLabel")
public class LabelController {

	
	@Resource
	private LabelService labelService;
	
	@Resource
	private SaleTeacherService saleTeacherService;
	
	@Resource
	private CourseService courseService;
	
	
	@RequestMapping(value="index")
	@ResponseBody
	public String labelInex(Long labelId){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(100);
		
		if(labelId==null){
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		//标签基本信息
		Label label = labelService.getById(labelId);
		
		if(label!=null){
			
			//添加标签的老师集合
			List<Long> teacherIds = saleTeacherService.getTeacherIdsByLabelId(labelId);
			if(!teacherIds.isEmpty()){
				Teacher tea = null;
				List<Teacher> teaList = new ArrayList<Teacher>();
				for(Long id:teacherIds){
					tea = saleTeacherService.getById(id);
					teaList.add(tea);
				}
				
				label.setTeacherList(teaList);
			}
			
			//添加标签的课程集合
			List<Long> courseIds = courseService.getCourseIdsByLabelId(labelId);
			if(!courseIds.isEmpty()){
				List<Course> courseList = new ArrayList<Course>();
				Course course = null;
				for(Long id:courseIds){
					course = courseService.getCourseById(id);
					courseList.add(course);
				}
				
				label.setCourseList(courseList);
			}
			
			msg.setCode(200);
			msg.setMsgBody(label);
			
		}
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	/**
	 * 获取所有标签
	 * @param request
	 * @return
	 */
	@RequestMapping("getAllLabels")
	@ResponseBody
	public String getAllLabels(){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		List<Label> lableList = labelService.getAllLabels();
		
		if(lableList.isEmpty()){
			msg.setCode(100);
			return MobileAccountUtils.getGson().toJson(msg);
		}
		
		msg.setMsgBody(lableList);
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	
	/**
	 * 获取随机标签
	 * @param request
	 * @return
	 */
	@RequestMapping("getRandomLabels")
	@ResponseBody
	public String getSomeLabels(Integer size){
		
		if(size==null){
			size = 50;
		}
		
		List<Label> lableList = labelService.getSomeLablesByRandom(size);
		
		if(lableList.isEmpty() || lableList.size()<size){
			for(int i=0;i<10;i++){
				lableList = labelService.getSomeLablesByRandom(size);
				if(!lableList.isEmpty() && lableList.size()>=10){
					break;
				}
			}
		}
		
		
		return MobileAccountUtils.getGson().toJson(lableList);
	}
	
	
	
	@RequestMapping(value="saveLabel")
	@ResponseBody
	public String saveLabel(Label label){
		ReturnMsg msg = new ReturnMsg();
		msg.setCode(200);
		
		if(!StringUtils.isEmpty(label.getName())){
			Label la = labelService.getByName(label.getName());
			
			if(la==null){
				labelService.insert(label);
			}else{
				msg.setCode(100);
				msg.setMessage("此标签已存在");
			}
			
		}else{
			msg.setCode(300);
		}
		
		
		return MobileAccountUtils.getGson().toJson(msg);
	}
	
	public static void main(String[] args) {
		  for(int i=0; i< 10; i++){  
	            if(i==4){  
	                break;
	            }  
	            System.out.print(i+" ");  
	        }  
	}
}
