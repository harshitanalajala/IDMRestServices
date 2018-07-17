package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class insertContent {
	
	private byte[] content;
	private Integer numPages;
	private String license, appName, condition,contentType,docID;
	private List<NameValue> options,fields;
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
	 * @return the numPages
	 */
	public Integer getNumPages() {
		return numPages;
	}
	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}
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
	 * @return the fields
	 */
	public List<NameValue> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<NameValue> fields) {
		this.fields = fields;
	}
	

}
