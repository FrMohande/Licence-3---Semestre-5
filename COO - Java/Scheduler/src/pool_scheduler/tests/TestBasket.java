package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.resources.Basket;

public class TestBasket {
	
	protected Basket basket;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		basket = new Basket("Basket 1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		basket = null;
	}
	
	@Test
	public void testDescription() {
		assertEquals("Basket 1", basket.description());
	}




}
