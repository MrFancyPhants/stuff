package com.valtech.training.monopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.valtech.training.monopoly.spaces.Space;
import com.valtech.training.monopoly.spaces.SpaceHouse;
import com.valtech.training.monopoly.spaces.SpaceRailroad;
import com.valtech.training.monopoly.spaces.SpaceUtilities;
import com.valtech.training.monopoly.spaces.SpecialSpace;

public class Board {
	private int[] positions;
	public List<Space> listOfSpaces;

	public Board() {
		initialize();
	}

	private void initialize() {
		listOfSpaces = new ArrayList<Space>();
		positions = new int[40];
		for (int i = 0; i < 40; i++) {
			positions[i] = i;
			// SpaceHouse c = new SpaceHouse("House",45);

			switch (i) {
			case 0:
				listOfSpaces.add(new SpecialSpace("SpaceGo", i));
				break;
			case 4:
				listOfSpaces.add(new SpecialSpace("SpaceIncomeTax", i));
				break;
			case 12:
			case 28:
				listOfSpaces.add(new SpaceUtilities("SpaceUtilities"+(i+1), i, 2));
				break;
			case 38:
				listOfSpaces.add(new SpecialSpace("SpaceLuxuryTax", i));
				break;
			default:
				if (((i +1) % 10) == 6) {
					listOfSpaces.add(new SpaceRailroad("SpaceRailroad" + (i+1), i, 3));
				} else {
					listOfSpaces.add(new SpaceHouse("SpaceProperty" + (i + 1),i, 1));
				}
				break;
			}

		}
	}
	

	public List<Space> getListOfSpaces() {
		return listOfSpaces;
	}

	public int getSpaceNumber() {
		return listOfSpaces.size();
	}

	public int getSpacePosition(int n) {
		return Arrays.binarySearch(positions, n);
	}

}
