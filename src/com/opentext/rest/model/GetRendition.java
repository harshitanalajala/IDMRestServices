package com.opentext.rest.model;

import java.util.List;

public class GetRendition{
	private String license;    
	private String appName;    
	private String docID;   
	private long pageNum;   
	private long subPageNum;   
	private String renditionType;   
	private List<com.vignette.idm.server.NameValue> options;    
	private byte[] rendition;   
	private Boolean hasNextSubPage;    
	private String contentType;   
	private String mimeType;
	
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
	public long getSubPageNum() {
		return subPageNum;
	}
	public void setSubPageNum(long subPageNum) {
		this.subPageNum = subPageNum;
	}
	public String getRenditionType() {
		return renditionType;
	}
	public void setRenditionType(String renditionType) {
		this.renditionType = renditionType;
	}
	public List<com.vignette.idm.server.NameValue> getOptions() {
		return options;
	}
	public void setOptions(List<com.vignette.idm.server.NameValue> options) {
		this.options = options;
	}
	public byte[] getRendition() {
		return rendition;
	}
	public void setRendition(byte[] rendition) {
		this.rendition = rendition;
	}
	public Boolean getHasNextSubPage() {
		return hasNextSubPage;
	}
	public void setHasNextSubPage(Boolean hasNextSubPage) {
		this.hasNextSubPage = hasNextSubPage;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
    
	}
