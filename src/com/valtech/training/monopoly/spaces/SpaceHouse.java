package com.valtech.training.monopoly.spaces;

public final class SpaceHouse extends SpaceProperty {

	public SpaceHouse(String name, int position,int group) {
		super(name, position, group);
		super.setPrice(40+(position*10));
		super.setRent(position*100);
		// TODO Auto-generated constructor stub
	}

}
