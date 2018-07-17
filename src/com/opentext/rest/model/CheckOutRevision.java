package com.opentext.rest.model;

import java.util.List;

public class CheckOutRevision {
	private String license;
	private String appName;
	private String docID;
	private String comment;
	private List<com.vignette.idm.server.NameValue> options;
	private byte[] content;
	private String contentTyp;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return the contentTyp
	 */
	public String getContentTyp() {
		return contentTyp;
	}
	/**
	 * @param contentTyp the contentTyp to set
	 */
	public void setContentTyp(String contentTyp) {
		this.contentTyp = contentTyp;
	}
	
	

}
