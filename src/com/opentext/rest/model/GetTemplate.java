/**
 * 
 */
package com.opentext.rest.model;

import java.util.List;
import com.vignette.idm.server.TemplateDef;
import com.vignette.idm.server.NameValue;

public class GetTemplate {
	private String license;
	private String appName;
	private String templateName;
	private List<NameValue> options;
	private TemplateDef templateDefs;
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * @return the options
	 */
	public List<NameValue> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<NameValue> options) {
		this.options = options;
	}
	/**
	 * @return the templateDefs
	 */
	public TemplateDef getTemplateDefs() {
		return templateDefs;
	}
	/**
	 * @param templateDefs the templateDefs to set
	 */
	public void setTemplateDefs(TemplateDef templateDefs) {
		this.templateDefs = templateDefs;
	}

}
