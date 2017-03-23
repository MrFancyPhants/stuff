package com.valtech.training.monopoly;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board b = new Board();
		
		assertEquals(39, b.getSpaceNumber());
	}

}
