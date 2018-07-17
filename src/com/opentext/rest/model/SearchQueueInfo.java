package com.opentext.rest.model;

import java.util.List;

import javax.naming.directory.SearchResult;

public class SearchQueueInfo {
	
	private String license;
	private String queueServer;
	private String queueServiceName;
	private List<String> propNames;
	private String condition;
	private String orderBy;
	private long offset;
	private int maxQueueInfos;
	private com.vignette.idm.server.SearchResult properties;
	
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
	public List<String> getPropNames() {
		return propNames;
	}
	public void setPropNames(List<String> propNames) {
		this.propNames = propNames;
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
	public int getMaxQueueInfos() {
		return maxQueueInfos;
	}
	public void setMaxQueueInfos(int maxQueueInfos) {
		this.maxQueueInfos = maxQueueInfos;
	}
	public com.vignette.idm.server.SearchResult getProperties() {
		return properties;
	}
	public void setProperties(com.vignette.idm.server.SearchResult properties) {
		this.properties = properties;
	}

	
}
