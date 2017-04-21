package com.hc360.mobileaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.po.Label;

@Service("labelService")
public interface LabelService{
	public List<Label> getAllLabels();
	public void insert(Label label);
	public Label getById(Long id);
	public Label getByName(String labelName);
	public List<Label> getSomeLablesByRandom(int size);
}
