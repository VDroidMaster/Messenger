package com.vdroidmaster.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private String profileName;
	private String firstName;
	private String lastName;

	public Profile() {
	}

	public Profile(String profileName, String firstName, String lastName) {
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
