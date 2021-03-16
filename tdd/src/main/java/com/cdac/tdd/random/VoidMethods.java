package com.cdac.tdd.random;

public class VoidMethods {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Employee name cant be null");
		}
		this.name = name;
	}

}
