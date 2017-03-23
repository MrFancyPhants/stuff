package com.valtech.training.monopoly;

import java.util.ArrayList;
import java.util.List;

import com.valtech.training.monopoly.spaces.Space;
import com.valtech.training.monopoly.spaces.SpaceProperty;

public class Player {
	private String token;
	public int position;
	private int balance;
	
	public List<SpaceProperty> ownedHouses;
	public List<Space> ownedUtilities;
	public List<Space> ownedRailroads;
	public int faceValue;
	private boolean hasAnotherTurn;

	public Player(String t) {
		token = t;
		position = 0;
		balance = 1500;
		ownedUtilities = new ArrayList<Space>();
		ownedRailroads = new ArrayList<Space>();
		ownedHouses = new ArrayList<SpaceProperty>();
	}

	public String getToken() {
		return token;
	}

	public int takeTurn(Cup d1) {
		d1.shake();
		faceValue = d1.getSideValue();

		System.out.println(token + " has thrown " + faceValue);
		position += faceValue;

		return faceValue;
	}

	public boolean hasAdditionalTurn(Cup c) {
		if (c.isDoubleValue())
			System.out.println(token + " has thrown a double");
		hasAnotherTurn=c.isDoubleValue();
		return hasAnotherTurn;
	}

	public boolean isHasAnotherTurn() {
		return hasAnotherTurn;
	}

	public void setHasAnotherTurn(boolean hasAnotherTurn) {
		this.hasAnotherTurn = hasAnotherTurn;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
