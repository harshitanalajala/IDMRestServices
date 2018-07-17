package com.opentext.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vignette.idm.server.NameValue;

@XmlRootElement
public class Login {

	private String userID;
	private String password;
	private List<NameValue> options;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<NameValue> getOptions() {
		return options;
	}

	public void setOptions(List<NameValue> options) {
		this.options = options;
	}

}
