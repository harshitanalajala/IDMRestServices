package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.SearchResult;

public class SearchRecords {
	private String license, appName;
	private List<String> fieldNames;
	private String condition;
	private String orderBy;
	private long offset;
	private int maxRecords;
	private SearchResult records;
	
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
	public List<String> getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public int getMaxRecords() {
		return maxRecords;
	}
	public void setMaxRecords(int maxRecords) {
		this.maxRecords = maxRecords;
	}
	public SearchResult getRecords() {
		return records;
	}
	public void setRecords(SearchResult records) {
		this.records = records;
	}
	
	

}
