package com.valtech.training.monopoly;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void test() {
		Player p = new Player("Horse");
		p.setBalance(p.getBalance()-2000);
		
		assertEquals(-500, p.getBalance());
	}

}
