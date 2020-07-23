package com.tennis.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

	private String firstname;
	private String lastname;
	private String email;
	private long phone_num;
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(long phone_num) {
		this.phone_num = phone_num;
	}
	
	
	
	
	
	
	
	
	
}
