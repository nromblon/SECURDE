package com.objects;

public class Tag extends DBObject{
	
	private String name;

	public Tag(int id, String name) {
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
	
	public boolean isSameTag(Tag other) {
		return this.name.equals(other.getName());
	}

}
