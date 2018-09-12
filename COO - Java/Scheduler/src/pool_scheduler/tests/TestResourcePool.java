package pool_scheduler.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.resources.Cubicle;
import pool_scheduler.resources.CubiclePool;
import pool_scheduler.resources.Resource;

public class TestResourcePool {
	
	protected CubiclePool resourcePool;
	protected int size = 3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		resourcePool = new CubiclePool(size);
		for(int i=0; i<3; i++){
			resourcePool.addResource(new Cubicle("cubiclePool "+ i));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		resourcePool = null;
	}
	
	@Test
	public void testAddResource() {
		assertTrue(resourcePool.getCapacity() == 3);
		assertTrue(resourcePool.getResources().size() == 3);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class) 
	public void testAddResourceWidthArrayIndexOutOfBoundsException() {
		resourcePool.addResource(new Cubicle("cubiclePool 42"));
	}
	
	@Test
	public void testProvideResource() {
		Resource r = resourcePool.provideResource(resourcePool.getFirstResource());
		assertNotNull(r);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testProvideResourceWidthIllegalArgumentException() {
		resourcePool.provideResource(new Cubicle("cubiclePool 100"));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testProvideResourceWidthNoSuchElementException() {
		Cubicle r = resourcePool.provideResource(resourcePool.getFirstResource());
		assertNotNull(r);
		resourcePool.provideResource(r);
	}
	
	@Test
	public void testFreeResource() {
		Cubicle r = resourcePool.provideResource(resourcePool.getFirstResource());
		assertNotNull(r);
		assertTrue(resourcePool.getUsedResources().size() == 1);
		resourcePool.freeResource(r);
		assertTrue(resourcePool.getUsedResources().size() == 0);
	}


	@Test
	public void testGetNTotalResource() {
		assertTrue(resourcePool.getNTotalResource() == 3);
	}

	@Test
	public void testGetNAvailableResource() {
		assertTrue(resourcePool.getNTotalResource() == 3);
	}

	@Test
	public void testGetNUsedResource() {
		assertTrue(resourcePool.getNUsedResource() == 0);
	}
}
