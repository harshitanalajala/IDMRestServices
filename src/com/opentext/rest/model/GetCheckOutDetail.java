package com.opentext.rest.model;

import com.vignette.idm.server.CheckOutDetail;

public class GetCheckOutDetail {
	private String license;
	private String appName;
	private String docID;
	
	CheckOutDetail checkOutDetail;
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
	 * @return the checkOutDetail
	 */
	public CheckOutDetail getCheckOutDetail() {
		return checkOutDetail;
	}
	/**
	 * @param checkOutDetail the checkOutDetail to set
	 */
	public void setCheckOutDetail(CheckOutDetail checkOutDetail) {
		this.checkOutDetail = checkOutDetail;
	}
	
	

}
