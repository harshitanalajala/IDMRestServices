package com.opentext.rest.model;

import java.util.List;

public class GetSelectedEntries {
	
	private String license;
	private String queueServer;
	private String queueServiceName;
	private String selectionID;
	private long offset;
	private int maxEntries;
	private List<com.vignette.idm.server.QueueEntry> queueEntries;
	
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
	public int getMaxEntries() {
		return maxEntries;
	}
	public void setMaxEntries(int maxEntries) {
		this.maxEntries = maxEntries;
	}
	public List<com.vignette.idm.server.QueueEntry> getQueueEntries() {
		return queueEntries;
	}
	public void setQueueEntries(List<com.vignette.idm.server.QueueEntry> queueEntries) {
		this.queueEntries = queueEntries;
	}
	

}
