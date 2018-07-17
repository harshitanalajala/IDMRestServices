package com.opentext.rest.model;

import java.util.List;

import javax.naming.directory.SearchResult;

public class SearchEntryRange {

	private String license;
	private String queueServer;
	private String queueServiceName;
	private long queueID;
	private List<String> varNames;
	private String condition;
	private String orderBy;
	private int offset;
	private int rangeSize;
	private com.vignette.idm.server.SearchResult variables;
	private Long offset0;
	private Long totalNumMatched;
	
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
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getRangeSize() {
		return rangeSize;
	}
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}
	public com.vignette.idm.server.SearchResult getVariables() {
		return variables;
	}
	public void setVariables(com.vignette.idm.server.SearchResult variables) {
		this.variables = variables;
	}
	public Long getOffset0() {
		return offset0;
	}
	public void setOffset0(Long offset0) {
		this.offset0 = offset0;
	}
	public Long getTotalNumMatched() {
		return totalNumMatched;
	}
	public void setTotalNumMatched(Long totalNumMatched) {
		this.totalNumMatched = totalNumMatched;
	}
	
	
}
