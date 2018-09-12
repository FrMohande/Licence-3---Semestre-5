package courriers.tests.letters.decorators;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.letters.SimpleLetter;
import courriers.letters.contents.TextContent;
import courriers.letters.decorators.RegisteredLetter;

public class RegisteredLetterTest {

	RegisteredLetter<?> l;
	
	@Before
	public void setUp() throws Exception {
		l = new RegisteredLetter<SimpleLetter>(new SimpleLetter(new TextContent("text")));
	}

	@After
	public void tearDown() throws Exception {
		l = null;
	}

	@Test
	public void testRegisteredLetter() {
		new RegisteredLetter<SimpleLetter>(new SimpleLetter(new TextContent("text")));
	}


	@Test
	public void testGetCost() {
		assertEquals(
				RegisteredLetter.EXTRA_COST + new SimpleLetter(new TextContent("")).getCost(),
				l.getCost(),
				0.001);
	}
}
