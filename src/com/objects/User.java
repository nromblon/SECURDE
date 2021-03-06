package com.objects;

public class User extends DBObject{
	
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String username;
	private String email;
	private String idNumber;
	private String privilege;
	private String userType;
	private boolean isLocked;
	
	public User(int id, String firstName, String lastName, String middleInitial, String username, String email,
			String idNumber, String privilege, boolean isLocked) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.username = username;
		this.email = email;
		this.idNumber = idNumber;
		this.privilege = privilege;
		this.isLocked = isLocked;
	}
	
	public User(int id, String idNumber, String userType) {
		super();
		this.id = id;
		this.idNumber = idNumber;
		this.userType = userType;
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
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getFullName() {
		return this.firstName +" "+ this.middleInitial +". "+ this.lastName;
	}

}
