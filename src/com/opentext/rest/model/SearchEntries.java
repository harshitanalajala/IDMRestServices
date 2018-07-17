package com.opentext.rest.model;

import java.util.List;

import javax.naming.directory.SearchResult;

public class SearchEntries {

	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private List<String> varNames;
	private String condition;
	private String orderBy;
	private long offset;
	private int maxEntries;
	private com.vignette.idm.server.SearchResult variables;
	
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
	public com.vignette.idm.server.SearchResult getVariables() {
		return variables;
	}
	public void setVariables(com.vignette.idm.server.SearchResult variables) {
		this.variables = variables;
	}
	
	
}
