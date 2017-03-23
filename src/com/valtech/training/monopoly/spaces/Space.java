package com.valtech.training.monopoly.spaces;

public abstract class Space {
	
	private String name;

	private int position;

	public Space(String name, int position) {
		this.name = name;
		this.position = position;
	}
	public Space(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
