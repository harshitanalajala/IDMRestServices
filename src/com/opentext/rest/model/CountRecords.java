package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class CountRecords {
	
	private String license;
	private String appName;
	private String condition;
	private List<NameValue> options;
	private long numRecords;
	
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public List<NameValue> getOptions() {
		return options;
	}
	public void setOptions(List<NameValue> options) {
		this.options = options;
	}
	public long getNumRecords() {
		return numRecords;
	}
	public void setNumRecords(long numRecords) {
		this.numRecords = numRecords;
	}
	
}
