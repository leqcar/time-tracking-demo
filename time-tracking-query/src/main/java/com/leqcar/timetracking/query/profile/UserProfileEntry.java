package com.leqcar.timetracking.query.profile;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProfileEntry {

	@Id
	private String userIdentificationNumber;
	
	private String userName;
	
	private String firstName;
	
	private String lastName;

	public UserProfileEntry(String userIdentificationNumber, String userName, String firstName, String lastName) {
		this.userIdentificationNumber = userIdentificationNumber;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserProfileEntry() {
	}

	public String getUserIdentificationNumber() {
		return userIdentificationNumber;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}
}
