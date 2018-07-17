package com.opentext.rest.model;

public class GetAnnotations {
	private String license;
	private String appName;
	private String docID;
	private long pageNum;
	private long subPageNum;
	private String annotations;
	
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
	public String getAnnotations() {
		return annotations;
	}
	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}
     
}
