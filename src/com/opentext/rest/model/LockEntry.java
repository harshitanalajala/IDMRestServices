package com.opentext.rest.model;

public class LockEntry {
	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private long entryID;
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
	public long getQueueID() {
		return queueID;
	}
	public void setQueueID(long queueID) {
		this.queueID = queueID;
	}
	public long getEntryID() {
		return entryID;
	}
	public void setEntryID(long entryID) {
		this.entryID = entryID;
	}
	public com.vignette.idm.server.QueueEntry getQueueEntry() {
		return queueEntry;
	}
	public void setQueueEntry(com.vignette.idm.server.QueueEntry queueEntry) {
		this.queueEntry = queueEntry;
	}
}
