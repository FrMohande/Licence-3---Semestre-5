package courriers.tests.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.city.BankAccount;

public class BankAccountTest {
	
	protected BankAccount bankAccount;
	protected static final double VAL12 = 12;
	protected static final double VAL7 = 7;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bankAccount = new BankAccount(null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		bankAccount = null;
	}
	
	@Test
	public void testBankAccount() {
		assertEquals(0, bankAccount.getAmount(), 0.001);
	}

	@Test
	public void testBankAccountDouble() {
		bankAccount = new BankAccount(null, VAL12);
		assertEquals(VAL12, bankAccount.getAmount(), 0.001);
	}
	
	@Test
	public void testCredit() {
		bankAccount.credit(VAL12);
		assertEquals(VAL12, bankAccount.getAmount(), 0.001);
		bankAccount.credit(VAL7);
		assertEquals(VAL12+VAL7, bankAccount.getAmount(), 0.001);
	}
	
	@Test
	public void testHasDebit() {
		assertFalse(bankAccount.hasDebit(VAL12));
		assertFalse(bankAccount.hasDebit(VAL7));
		bankAccount.credit(VAL7);
		assertFalse(bankAccount.hasDebit(VAL12));
		assertTrue(bankAccount.hasDebit(VAL7));
	}

	@Test
	public void testDebit() {
		assertFalse(bankAccount.debit(VAL12));
		assertEquals(0, bankAccount.getAmount(), 0.001);
		bankAccount.credit(VAL12);
		assertTrue(bankAccount.debit(VAL7));	
		assertEquals(VAL12-VAL7, bankAccount.getAmount(), 0.001);
		assertFalse(bankAccount.debit(VAL7));
		assertEquals(VAL12-VAL7, bankAccount.getAmount(), 0.001);
	}
}
