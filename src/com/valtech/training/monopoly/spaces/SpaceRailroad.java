package com.valtech.training.monopoly.spaces;

import com.valtech.training.monopoly.Player;

public final class SpaceRailroad extends SpaceProperty {

	public SpaceRailroad(String name, int position,int group) {
		super(name, position, group);
		super.setPrice(200);
		super.setRent(25);

	}
	
	@Override
	public int calculateRent(Player p) {
		int noOfRailsroads = p.ownedRailroads.size();

		return (noOfRailsroads != 0) ? (int) (25 * Math.pow(2, noOfRailsroads)) : 0;
	}

}
