package com.valtech.training.monopoly;

public class Dice {

	protected int finalSide;
	public Dice(){
		roll();
	}
	public Dice(int n){
		setSideValue(n);
	}
	
	public int getSideValue(){
		return finalSide;
	}
	
	public void setSideValue(int n){
		this.finalSide=n;
	}
	
	protected void roll(){
		finalSide = 1 + (int)(Math.random() * 6); 
	}

	
}
