package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import pool_scheduler.resources.Cubicle;
import pool_scheduler.resources.ResourcefulUser;

public class TestResourcefulUser {
	
	protected ResourcefulUser<Cubicle> resourcefulUser;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		resourcefulUser = new ResourcefulUser<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		resourcefulUser = null;
	}
	
	@Test
	public void testSetResource() {
		assertNull(resourcefulUser.getResource());
		resourcefulUser.setResource(new Cubicle("Cubicle 1"));
		assertNotNull(resourcefulUser.getResource());
	}

	@Test
	public void testResetResource() {
		assertNull(resourcefulUser.getResource());
		resourcefulUser.setResource(new Cubicle("Cubicle 1"));
		assertNotNull(resourcefulUser.getResource());
		resourcefulUser.resetResource();
		assertNull(resourcefulUser.getResource());
	}

}
