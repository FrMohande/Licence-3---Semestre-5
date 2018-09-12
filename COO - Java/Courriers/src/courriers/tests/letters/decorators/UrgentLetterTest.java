package courriers.tests.letters.decorators;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.letters.SimpleLetter;
import courriers.letters.contents.TextContent;
import courriers.letters.decorators.UrgentLetter;

public class UrgentLetterTest {
	
	UrgentLetter<?> l;

	@Before
	public void setUp() throws Exception {
		l = new UrgentLetter<SimpleLetter>(new SimpleLetter(new TextContent("")));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCost() {
		assertEquals(
				UrgentLetter.EXTRA_COST * new SimpleLetter(new TextContent("")).getCost(),
				l.getCost(),
				0.001);
	}

	@Test
	public void testUrgentLetter() {
		new UrgentLetter<SimpleLetter>(new SimpleLetter(new TextContent("")));
		
	}

}
