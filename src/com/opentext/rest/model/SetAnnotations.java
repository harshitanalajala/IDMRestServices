package com.opentext.rest.model;

import java.util.List;

public class SetAnnotations {
	private String license;
	private  String appName;
	private String docID;
	private String annotations;
	private List<com.vignette.idm.server.NameValue> options;
	
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
	public String getAnnotations() {
		return annotations;
	}
	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}
	public List<com.vignette.idm.server.NameValue> getOptions() {
		return options;
	}
	public void setOptions(List<com.vignette.idm.server.NameValue> options) {
		this.options = options;
	}
    
}
