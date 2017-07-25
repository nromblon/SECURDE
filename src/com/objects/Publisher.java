package com.objects;

public class Publisher extends DBObject{
	private String name;
	public Publisher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
