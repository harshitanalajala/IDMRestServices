package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class LogEvent {
	
	private String license;
	private String category;
	private String name;
	private List<NameValue> fields;
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NameValue> getFields() {
		return fields;
	}
	public void setFields(List<NameValue> fields) {
		this.fields = fields;
	}
	
}
