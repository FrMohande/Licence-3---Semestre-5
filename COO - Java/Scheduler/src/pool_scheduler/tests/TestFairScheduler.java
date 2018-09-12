package pool_scheduler.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pool_scheduler.actions.ForeseableAction;
import pool_scheduler.schedulers.FairScheduler;

public class TestFairScheduler {
	private FairScheduler scheduler;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		scheduler = new FairScheduler();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		scheduler = null;
	}

	@Test
	public void testDoStep() {
		scheduler.addAction(new ForeseableAction(2));
		scheduler.addAction(new ForeseableAction(1));
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		assertTrue(scheduler.isFinished());
	}

	@Test
	public void testNextAction() {
		ForeseableAction fa1 = new ForeseableAction(1);
		ForeseableAction fa2 = new ForeseableAction(1);
		ForeseableAction fa3 = new ForeseableAction(1);
		scheduler.addAction(fa1);
		scheduler.addAction(fa2);
		assertEquals(fa1, scheduler.getCurrentAction());
		scheduler.nextAction();
		assertEquals(fa2, scheduler.getCurrentAction());
		scheduler.nextAction();
		assertEquals(fa1, scheduler.getCurrentAction());
		scheduler.addAction(fa3);
		scheduler.nextAction();
		assertEquals(fa2, scheduler.getCurrentAction());
		scheduler.nextAction();
		assertEquals(fa3, scheduler.getCurrentAction());
	}

	@Test
	public void testRemove() {
		scheduler.addAction(new ForeseableAction(1));
		assertFalse(scheduler.isFinished());
		scheduler.remove();
		assertFalse(scheduler.isFinished());
		scheduler.doStep();
		/* Scheduler automatically remove action if it's finished during doStep()*/
		assertTrue(scheduler.isFinished());
	}

	@Test
	public void testGetCurrentAction() {
		ForeseableAction fa1 = new ForeseableAction(1);
		ForeseableAction fa2 = new ForeseableAction(1);
		ForeseableAction fa3 = new ForeseableAction(1);
		scheduler.addAction(fa1);
		scheduler.addAction(fa2);
		scheduler.addAction(fa3);
		assertEquals(fa1, scheduler.getCurrentAction());
		scheduler.nextAction();
		assertEquals(fa2, scheduler.getCurrentAction());
		scheduler.nextAction();
		assertEquals(fa3, scheduler.getCurrentAction());
	}

}
