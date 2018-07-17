package com.opentext.rest.model;

import java.util.List;

public class SelectEntries {

	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private List<String> varNames;
	private String condition;
	private String orderBy;
	private String selectionID;
	private Long numSelected;
	
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
	public List<String> getVarNames() {
		return varNames;
	}
	public void setVarNames(List<String> varNames) {
		this.varNames = varNames;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSelectionID() {
		return selectionID;
	}
	public void setSelectionID(String selectionID) {
		this.selectionID = selectionID;
	}
	public Long getNumSelected() {
		return numSelected;
	}
	public void setNumSelected(Long numSelected) {
		this.numSelected = numSelected;
	}
	
	
}
