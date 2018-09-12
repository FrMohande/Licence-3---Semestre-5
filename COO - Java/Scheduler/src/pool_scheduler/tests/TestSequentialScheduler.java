package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.actions.Action;
import pool_scheduler.actions.ForeseableAction;
import pool_scheduler.schedulers.SequentialScheduler;

public class TestSequentialScheduler {
	
	protected SequentialScheduler scheduler;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		scheduler = new SequentialScheduler();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		scheduler = null;
	}
	
	@Test
	public void testSequentialScheduler() {
		assertTrue(scheduler.getActions().isEmpty());
	}
	
	@Test(expected = IllegalArgumentException.class)  
	public void testAddActionIllegalArgumentException() {
		Action action = new ForeseableAction(2);
		action.doStep();
		action.doStep();
		scheduler.addAction(action);
	}
	
	@Test(expected = IllegalStateException.class)  
	public void testAddActionIllegalStateException() {
		scheduler.doStep();
		scheduler.addAction(new ForeseableAction(2));
	}
	
	@Test  
	public void testAddAction() {
		scheduler.addAction(new ForeseableAction(2));
		scheduler.addAction(new ForeseableAction(5));
		assertFalse(scheduler.getActions().isEmpty());
		assertTrue(scheduler.getActions().size() == 2);
	}
	
	@Test
	public void testIsReady() {
		scheduler.addAction(new ForeseableAction(10));
		assertTrue(scheduler.isReady());
		assertFalse(scheduler.isInProgress());
		assertFalse(scheduler.isFinished());
		
	}
	
	
	@Test
	public void testDoStep() {
		//action 1
		scheduler.addAction(new ForeseableAction(2));
		//action 2
		scheduler.addAction(new ForeseableAction(2));
		
		//action 1
		assertTrue(scheduler.getActions().get(0).isReady());
		assertFalse(scheduler.getActions().get(0).isInProgress());
		assertFalse(scheduler.getActions().get(0).isFinished());
		
		//action 2
		assertTrue(scheduler.getActions().get(1).isReady());
		assertFalse(scheduler.getActions().get(1).isInProgress());
		assertFalse(scheduler.getActions().get(1).isFinished());
		
		scheduler.doStep();
		
		//action 1
		assertFalse(scheduler.getActions().get(0).isReady());
		assertTrue(scheduler.getActions().get(0).isInProgress());
		assertFalse(scheduler.getActions().get(0).isFinished());
		
		//action 2
		assertTrue(scheduler.getActions().get(1).isReady());
		assertFalse(scheduler.getActions().get(1).isInProgress());
		assertFalse(scheduler.getActions().get(1).isFinished());
		
		scheduler.doStep();
		
		//action 2
		assertTrue(scheduler.getActions().get(0).isReady());
		assertFalse(scheduler.getActions().get(0).isInProgress());
		assertFalse(scheduler.getActions().get(0).isFinished());
		

		scheduler.doStep();
		
		//action 2
		assertFalse(scheduler.getActions().get(0).isReady());
		assertTrue(scheduler.getActions().get(0).isInProgress());
		assertFalse(scheduler.getActions().get(0).isFinished());
		
		scheduler.doStep();
		
		assertTrue(scheduler.getActions().isEmpty());
		
	}
	
	@Test
	public void testIsInProgress() {
		scheduler.addAction(new ForeseableAction(2));
		assertTrue(scheduler.isReady());
		assertFalse(scheduler.isInProgress());
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertFalse(scheduler.isReady());
		assertTrue(scheduler.isInProgress());
		assertFalse(scheduler.isFinished());
	}
	

	@Test
	public void testIsFinished() {
		scheduler.addAction(new ForeseableAction(2));
		assertTrue(scheduler.isReady());
		assertFalse(scheduler.isInProgress());
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertFalse(scheduler.isReady());
		assertTrue(scheduler.isInProgress());
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertFalse(scheduler.isReady());
		assertFalse(scheduler.isInProgress());
		assertTrue(scheduler.isFinished());
	}

}
