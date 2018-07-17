package com.opentext.rest.model;

import java.util.List;

public class GetSelectedQueueInfo {

	private String license;
	private String queueServer;
	private String queueServiceName;
	private String selectionID;
	private long offset;
	private int maxQueueInfos;
	private List<com.vignette.idm.server.QueueInfo> queueInfo;
	
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
	public String getSelectionID() {
		return selectionID;
	}
	public void setSelectionID(String selectionID) {
		this.selectionID = selectionID;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public int getMaxQueueInfos() {
		return maxQueueInfos;
	}
	public void setMaxQueueInfos(int maxQueueInfos) {
		this.maxQueueInfos = maxQueueInfos;
	}
	public List<com.vignette.idm.server.QueueInfo> getQueueInfo() {
		return queueInfo;
	}
	public void setQueueInfo(List<com.vignette.idm.server.QueueInfo> queueInfo) {
		this.queueInfo = queueInfo;
	}
	
	
	
}
