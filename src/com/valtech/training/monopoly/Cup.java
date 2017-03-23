package com.valtech.training.monopoly;

import com.valtech.training.monopoly.Dice;

public class Cup {

	private Dice d1;

	private Dice d2 ;

	public Cup() {
		d1= new Dice();
		d2= new Dice();
	}

	public int getSideValue(){
		return d1.getSideValue()+d2.getSideValue();
	}
	
	public boolean isDoubleValue(){
		return d1.getSideValue()==d2.getSideValue();
	}
	
	public void shake() {
		d1.roll();
		d2.roll();
	}
}
