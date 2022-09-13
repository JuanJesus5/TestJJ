package com.ApiRest.TestJJ;

public class Hospital {

	private String name;
	private int x;
	private int y;

	//Constructores
	public Hospital() {}

	public Hospital(String name, int x, int y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}

	//Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}