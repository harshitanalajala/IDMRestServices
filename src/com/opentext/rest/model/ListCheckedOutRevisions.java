package com.opentext.rest.model;

import java.util.List;

public class ListCheckedOutRevisions {

	private String license;
	private String appName;
	private boolean all;
	private  List<String> docIDs;
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
	 * @return the all
	 */
	public boolean isAll() {
		return all;
	}
	/**
	 * @param all the all to set
	 */
	public void setAll(boolean all) {
		this.all = all;
	}
	/**
	 * @return the docIDs
	 */
	public List<String> getDocIDs() {
		return docIDs;
	}
	/**
	 * @param docIDs the docIDs to set
	 */
	public void setDocIDs(List<String> docIDs) {
		this.docIDs = docIDs;
	}
    
}
