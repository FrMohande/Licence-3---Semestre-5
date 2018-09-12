package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.actions.TakeResourceAction;
import pool_scheduler.resources.Cubicle;
import pool_scheduler.resources.CubiclePool;
import pool_scheduler.resources.ResourcefulUser;

public class TestTakeResourceAction {
	
	protected TakeResourceAction<Cubicle> takeResourceAction;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CubiclePool cubicles = new CubiclePool(3);
		
		for(int i=0; i<3; i++){
			cubicles.addResource(new Cubicle("cubiclePool "+ i));
		}
		takeResourceAction = new TakeResourceAction<>(new ResourcefulUser<Cubicle>(), cubicles, "tt");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		takeResourceAction = null;
	}
	
	@Test
	public void testIsReady() {
		assertTrue(takeResourceAction.isReady());
		assertFalse(takeResourceAction.isInProgress());
		assertFalse(takeResourceAction.isFinished());
	}

	@Test
	public void testIsFinished() {
		takeResourceAction.doStep();
		assertFalse(takeResourceAction.isReady());
		assertTrue(takeResourceAction.isFinished());
		assertFalse(takeResourceAction.isInProgress());
	}
	
	@Test(expected = IllegalAccessError.class)
	public void testDoStepWidthIllegalAccessError() {
		takeResourceAction.doStep();
		takeResourceAction.doStep();
	}
	
	@Test
	public void testDoStep() {
		assertTrue(takeResourceAction.isReady());
		assertFalse(takeResourceAction.isInProgress());
		assertFalse(takeResourceAction.isFinished());
		
		takeResourceAction.doStep();

		assertFalse(takeResourceAction.isReady());
		assertTrue(takeResourceAction.isFinished());
		assertFalse(takeResourceAction.isInProgress());
	}
}
