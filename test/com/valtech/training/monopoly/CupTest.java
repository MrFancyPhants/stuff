package com.valtech.training.monopoly;
import static org.junit.Assert.*;

import org.junit.Test;

import com.valtech.training.monopoly.*;

public class CupTest {


	
	@Test
	public void test() {
		Cup c = new Cup();
		assertEquals(0, c.getSideValue());
		
		c.shake();
		
		assertTrue(2<=c.getSideValue() && c.getSideValue()<=12);
		
	}

}
