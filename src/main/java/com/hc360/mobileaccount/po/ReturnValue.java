package com.hc360.mobileaccount.po;

import java.util.List;

public class ReturnValue {
	private Integer code;
	private String level;
	private String msg;
	private Object data;
	private Integer page;
	private Integer totalPage;
	private Integer totalNum;
	private Long startDate;
	
	private List<Ad> adList;
	private List<Teacher> teacherList;
	private List<Label> labelList;
	private List<Course> courseList;
	private List<UserQuestion> questionsList;

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public List<UserQuestion> getQuestionsList() {
		return questionsList;
	}
	public void setQuestionsList(List<UserQuestion> questionsList) {
		this.questionsList = questionsList;
	}
	public List<Label> getLabelList() {
		return labelList;
	}
	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	public List<Ad> getAdList() {
		return adList;
	}
	public void setAdList(List<Ad> adList) {
		this.adList = adList;
	}
}