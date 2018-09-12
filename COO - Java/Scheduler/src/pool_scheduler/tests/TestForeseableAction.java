package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.actions.ForeseableAction;

public class TestForeseableAction {
	
	protected ForeseableAction action;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		action = new ForeseableAction(2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	
	@After
	public void tearDown() throws Exception {
		action = null;
	}
	
	@Test
	public void testForeseableAction() {
		assertTrue(action.getTime() == 0);
		assertTrue(action.getTimeToEnd() == 2);
	}
	
	@Test
	public void testIsReady() {
		assertTrue(action.isReady());
		assertFalse(action.isInProgress());
		assertFalse(action.isFinished());
	}
	
	@Test
	public void testDoStep() {
		action.doStep();
		assertFalse(action.isFinished());
		assertTrue(action.isInProgress());
		assertFalse(action.isReady());
		action.doStep();
		assertTrue(action.isFinished());
		assertFalse(action.isInProgress());
		assertFalse(action.isReady());
	}
	
	@Test
	public void testIsInProgress() {
		action.doStep();
		assertFalse(action.isFinished());
		assertTrue(action.isInProgress());
		assertFalse(action.isReady());
	}

	@Test
	public void testIsFinished() {
		action.doStep();
		action.doStep();
		assertTrue(action.isFinished());
		assertFalse(action.isInProgress());
		assertFalse(action.isReady());
	}

}
