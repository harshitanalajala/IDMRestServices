package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.DMStatus;

public class CreateRevision {
	private String license;
	private String appName;
	private List<com.vignette.idm.server.NameValue> fields;
	private DMStatus status;
	private String contentType;
	private byte[] content;
	private List<com.vignette.idm.server.NameValue> options;
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
	 * @return the fields
	 */
	public List<com.vignette.idm.server.NameValue> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<com.vignette.idm.server.NameValue> fields) {
		this.fields = fields;
	}
	/**
	 * @return the status
	 */
	public DMStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(DMStatus status) {
		this.status = status;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
	/**
	 * @return the options
	 */
	public List<com.vignette.idm.server.NameValue> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<com.vignette.idm.server.NameValue> options) {
		this.options = options;
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
