package com.hc360.mobileaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hc360.mobileaccount.dao.LabelMapper;
import com.hc360.mobileaccount.po.Label;
import com.hc360.mobileaccount.service.LabelService;

@Service("labelService")
public class LabelServiceImpl implements  LabelService{
	@Resource
	private LabelMapper labelMapper;

	@Override
	public List<Label> getAllLabels() {
		return labelMapper.getAllLabels();
	}

	@Override
	public void insert(Label label) {
		labelMapper.insert(label);
	}

	@Override
	public Label getByName(String labelName) {
		return labelMapper.getByName(labelName);
	}

	@Override
	public List<Label> getSomeLablesByRandom(int size) {
		return labelMapper.getSomeLablesByRandom(size);
	}

	@Override
	public Label getById(Long id) {
		return labelMapper.getById(id);
	}
	
}
