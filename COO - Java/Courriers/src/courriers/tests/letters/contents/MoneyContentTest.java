package courriers.tests.letters.contents;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.letters.contents.MoneyContent;

public class MoneyContentTest {
	
	protected MoneyContent moneyContent;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		moneyContent = new MoneyContent(10);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		moneyContent = null;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void MoneyContentWidthIllegalArgumentException(){
		new MoneyContent(-1);
	}
	
	@Test
	public void testMoneyContent() {
		assertEquals(10, moneyContent.getMoney(), 0.001);
	}

	public void testSetMoney() {
		moneyContent.setMoney(2.45);
		assertEquals(2.45, moneyContent.getMoney(), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMoneyException() {
		moneyContent.setMoney(-10);
	}
}
