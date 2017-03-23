package com.valtech.training.monopoly.spaces;

import com.valtech.training.monopoly.Player;

public abstract class SpaceProperty extends Space{

	private int price;
	private Player owner;
	private int rent;
	private int group;
	
	public SpaceProperty(String name,int position, int group) {
		super(name,position);
		this.setGroup(group);
		// TODO Auto-generated constructor stub
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int calculateRent(Player p)  {
		return 0;
	}
}
