package com.valtech.training.monopoly.spaces;

import com.valtech.training.monopoly.Player;

public final class SpaceUtilities extends SpaceProperty {

	public SpaceUtilities(String name, int position, int group) {
		super(name, position, group);
		super.setPrice(150);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculateRent(Player p) {
		//int noOfUtils = p.ownedRailroads.size();
		
		if (p.ownedUtilities.size() == 1) {
			return 4 * p.faceValue;
		} 
		return 10 * p.faceValue;


	}

}
