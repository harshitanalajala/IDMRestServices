package com.opentext.rest.model;

public class GetQueueInfo {
	
	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private com.vignette.idm.server.QueueInfo queueInfo;
	
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
	public com.vignette.idm.server.QueueInfo getQueueInfo() {
		return queueInfo;
	}
	public void setQueueInfo(com.vignette.idm.server.QueueInfo queueInfo) {
		this.queueInfo = queueInfo;
	}
	

}
