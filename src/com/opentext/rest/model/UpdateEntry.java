package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.NameValue;

public class UpdateEntry {
	private String license;
	private String queueServer;
	private String queueServiceName;
	private long entryID;
	private List<NameValue> entryVariables;
	
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
	public long getEntryID() {
		return entryID;
	}
	public void setEntryID(long entryID) {
		this.entryID = entryID;
	}
	public List<NameValue> getEntryVariables() {
		return entryVariables;
	}
	public void setEntryVariables(List<NameValue> entryVariables) {
		this.entryVariables = entryVariables;
	}
}
