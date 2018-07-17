package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class DeleteRecords {
	
	private String license;
	private String appName;
	private String condition;
	private List<NameValue> options;
	private long numDeleted;
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the options
	 */
	public List<NameValue> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<NameValue> options) {
		this.options = options;
	}
	/**
	 * @return the numDeleted
	 */
	public long getNumDeleted() {
		return numDeleted;
	}
	/**
	 * @param numDeleted the numDeleted to set
	 */
	public void setNumDeleted(long numDeleted) {
		this.numDeleted = numDeleted;
	}

}
