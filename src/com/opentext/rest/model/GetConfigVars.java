package com.opentext.rest.model;

import java.util.List;

import com.vignette.idm.common.NameValue;

public class GetConfigVars {
	
	private String license;
	private String varNamePattern;
	private List<NameValue> vars;
		
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getVarNamePattern() {
		return varNamePattern;
	}
	public void setVarNamePattern(String varNamePattern) {
		this.varNamePattern = varNamePattern;
	}
	public List<NameValue> getVars() {
		return vars;
	}
	public void setVars(List<NameValue> vars) {
		this.vars = vars;
	}

}
