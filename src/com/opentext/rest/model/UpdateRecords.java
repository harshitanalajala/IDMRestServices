package com.opentext.rest.model;
import java.util.List;

import com.vignette.idm.server.NameValue;

public class UpdateRecords {
	private String License;
	private String appName;
	private String condition;
	private List<NameValue> fields;
	private long numUpdated;
	
	public String getLicense() {
		return License;
	}
	public void setLicense(String license) {
		License = license;
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
	public List<NameValue> getFields() {
		return fields;
	}
	public void setFields(List<NameValue> fields) {
		this.fields = fields;
	}
	public long getNumUpdated() {
		return numUpdated;
	}
	public void setNumUpdated(long numUpdated) {
		this.numUpdated = numUpdated;
	}
	

}
