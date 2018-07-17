package com.opentext.rest.model;

import java.util.List;

public class LockNextEntry {
	
	private String license;
	private String queueServer;
	private String queueServiceName;
	private List<Long> queueIDs;
	private com.vignette.idm.server.QueueEntry queueEntry;
	
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
	public List<Long> getQueueIDs() {
		return queueIDs;
	}
	public void setQueueIDs(List<Long> queueIDs) {
		this.queueIDs = queueIDs;
	}
	public com.vignette.idm.server.QueueEntry getQueueEntry() {
		return queueEntry;
	}
	public void setQueueEntry(com.vignette.idm.server.QueueEntry queueEntry) {
		this.queueEntry = queueEntry;
	}
	
	

}
