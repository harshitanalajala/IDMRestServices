package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class AddEntry {
	
	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private List<NameValue> entryVariables;
	private Long entryID;
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getQueueServer() {
		return queueServer;
	}
	public void setQueueServer(String queueServer) {
		this.queueServer = queueServer;
	}
	public String getQueueServiceName() {
		return queueServiceName;
	}
	public void setQueueServiceName(String queueServiceName) {
		this.queueServiceName = queueServiceName;
	}
	public long getQueueID() {
		return queueID;
	}
	public void setQueueID(long queueID) {
		this.queueID = queueID;
	}
	public List<NameValue> getEntryVariables() {
		return entryVariables;
	}
	public void setEntryVariables(List<NameValue> entryVariables) {
		this.entryVariables = entryVariables;
	}
	public Long getEntryID() {
		return entryID;
	}
	public void setEntryID(Long entryID) {
		this.entryID = entryID;
	}
	
	

}
