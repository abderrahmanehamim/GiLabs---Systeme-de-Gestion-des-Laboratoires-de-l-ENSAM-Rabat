package com.Glab.LaboIntelligent.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class AppUser implements Serializable {
	@Id

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUser() {
		super();
	}
	
	public String Userstatus;


	public String getUserstatus() {
		return Userstatus;
	}

	public void setUserstatus(String userstatus) {
		Userstatus = userstatus;
	}

	private String userPassword;
	private String userPasswordId="thisWillBeChanged";
	private boolean status=false;

	/*
	 * @ManyToOne private Banque userBanque;
	 */
	// ici je sais pas si c'est necesssaire de determiner le type de casccade vue
	// que c'est ManyToMany
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name="users_roles")
	private Collection<AppRole> userRoles;

	public AppUser(String email, String userPassword, Collection<AppRole> userRoles) {
		super();
		this.email = email;
		this.userPassword = userPassword;
		this.userRoles = userRoles;
	}

	public Collection<AppRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<AppRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUserPasswordId() {
		return userPasswordId;
	}

	
	
	 public static String getRandomString() 
	    {  int i= 20;
	        String theAlphaNumericS;
	        StringBuilder builder;
	        
	        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"; 

	        //create the StringBuffer
	        builder = new StringBuilder(i); 

	        for (int m = 0; m < i; m++) { 

	            // generate numeric
	            int myindex 
	                = (int)(theAlphaNumericS.length() 
	                        * Math.random()); 

	            // add the characters
	            builder.append(theAlphaNumericS 
	                        .charAt(myindex)); 
	        } 

	        return builder.toString(); 
	    }

	
}
