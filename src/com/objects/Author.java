package com.objects;

public class Author extends DBObject{
	private String firstName;
	private String lastName;
	
	public Author(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	public String getNameLastNameFirst() {
		return this.lastName + ", " + this.firstName;
	}
	
}
