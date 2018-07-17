package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class InsertRecord {

	private String license, appName;
	private List<NameValue> fields;
	
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
	public List<NameValue> getFields() {
		return fields;
	}
	public void setFields(List<NameValue> fields) {
		this.fields = fields;
	}
	
}
