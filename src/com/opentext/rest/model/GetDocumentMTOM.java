package com.opentext.rest.model;

import javax.activation.DataHandler;

public class GetDocumentMTOM {
	private String license;
	private String appName;
	private String docID;
	private String pages;
	private DataHandler content;  
	private String contentType;
	
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
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public DataHandler getContent() {
		return content;
	}
	public void setContent(DataHandler content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
    
}
