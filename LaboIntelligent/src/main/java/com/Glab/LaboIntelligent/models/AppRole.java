package com.Glab.LaboIntelligent.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppRole implements Serializable{
	@Id
	private String appRoleName;

	public String getAppRoleName() {
		return appRoleName;
	}

	public void setAppRoleName(String appRoleName) {
		this.appRoleName = appRoleName;
	}

	public AppRole() {
		super();
	}

	public AppRole(String appRoleName) {
		super();
		this.appRoleName = appRoleName;
	}
}
