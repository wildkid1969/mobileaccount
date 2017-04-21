
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo 
 * Author: dixingxing
 * Createdate: ����4:13:38
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.po;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * 
 * @project spring-mybatis-demo
 * @author dixingxing
 * @version 1.0
 * @date 2013-9-13 ����4:13:38   
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	private Long id;
	
	@Length(min = 1, max = 100)
	private String name;
	
	@NotNull
	@Min(1)
	private Integer age;
	
	@Length(min = 1, max = 10)
	private String zipCode;
	
	
	
	
	/**
	*/
	public User() {
		super();
	}

	/**
	* @param id
	* @param name
	* @param age
	* @param zipCode
	*/
	public User(Long id, String name, Integer age, String zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
