package com.opentext.rest.model;

import com.vignette.idm.server.TraceSeverity;

public class LogError {
	
	private String license;
	private String message;
	private String reason;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
