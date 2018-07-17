package com.opentext.rest.model;

public class PublishRevision {
	
	private String license;
	private String appName;
	private String docID;
	private Long numPages;
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
	 * @return the docID
	 */
	public String getDocID() {
		return docID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(String docID) {
		this.docID = docID;
	}
	/**
	 * @return the numPages
	 */
	public Long getNumPages() {
		return numPages;
	}
	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(Long numPages) {
		this.numPages = numPages;
	}
	
	

}
