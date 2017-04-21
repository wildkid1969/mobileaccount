package com.hc360.mobileaccount.po;

import java.util.ArrayList;
import java.util.List;

public class LinkBaseInfo {
	
	private List<Baseinfo> baseinfos = new ArrayList<Baseinfo>();
	
	private List<LinkInfo> linkinfos = new ArrayList<LinkInfo>();

	public List<Baseinfo> getBaseinfos() {
		return baseinfos;
	}

	public void setBaseinfos(List<Baseinfo> baseinfos) {
		this.baseinfos = baseinfos;
	}

	public List<LinkInfo> getLinkinfos() {
		return linkinfos;
	}

	public void setLinkinfos(List<LinkInfo> linkinfos) {
		this.linkinfos = linkinfos;
	}

}
