package com.opentext.rest.model;

import java.util.List;

public class GetRenditionDetails {
	private String license;
	private String appName;    
	private String docID;    
	private long pageNum;    
	private List<com.vignette.idm.server.RenditionDetail> renditions;
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getDocID() {
		return docID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	public long getPageNum() {
		return pageNum;
	}
	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}
	public List<com.vignette.idm.server.RenditionDetail> getRenditions() {
		return renditions;
	}
	public void setRenditions(List<com.vignette.idm.server.RenditionDetail> renditions) {
		this.renditions = renditions;
	}
	 
	}
