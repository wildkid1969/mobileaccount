package com.hc360.mobileaccount.dao;

import java.util.List;

import com.hc360.mobileaccount.po.Label;

public interface LabelMapper{
	public List<Label> getAllLabels();
	public void insert(Label label);
	public Label getById(Long id);
	public Label getByName(String labelName);
	public List<Label> getSomeLablesByRandom(int size);
}
