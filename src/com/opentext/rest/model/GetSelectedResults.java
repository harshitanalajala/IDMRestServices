package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.server.SearchResult;

public class GetSelectedResults {

	private String license;
	private String queueServer;
	private String queueServiceName;
	private String selectionID;
	private long offset;
	private int maxRows;
	private SearchResult values;
	
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
	public int getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}
	public SearchResult getValues() {
		return values;
	}
	public void setValues(SearchResult values) {
		this.values = values;
	}
	
	
}
