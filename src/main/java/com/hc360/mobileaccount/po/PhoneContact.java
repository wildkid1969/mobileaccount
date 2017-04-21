package com.hc360.mobileaccount.po;

import java.util.List;

public class PhoneContact {
	
	private List<Number> numbers;

	private String name;
		
	public List<Number> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Number> numbers) {
		this.numbers = numbers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
