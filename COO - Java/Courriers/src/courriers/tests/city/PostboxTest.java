package courriers.tests.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.city.Postbox;
import courriers.letters.Letter;
import courriers.letters.SimpleLetter;
import courriers.letters.contents.TextContent;

public class PostboxTest {
	
	Postbox box;

	@Before
	public void setUp() throws Exception {
		box = new Postbox();
	}

	@After
	public void tearDown() throws Exception {
		box = null;
	}

	@Test
	public void testAddLetter() {
		assertTrue(box.isEmpty());
		box.addLetter(new SimpleLetter(new TextContent("")));
		assertFalse(box.isEmpty());
		SimpleLetter l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("", l.getContent().getContentLetter());
		
		box.addLetter(new SimpleLetter(new TextContent("toto")));
		box.addLetter(new SimpleLetter(new TextContent("toto2")));
		l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("toto", l.getContent().getContentLetter());
		l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("toto2", l.getContent().getContentLetter());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(box.isEmpty());
		box.addLetter(new SimpleLetter(new TextContent("")));
		assertFalse(box.isEmpty());
		box.takeFirstLetter();
		assertTrue(box.isEmpty());
	}

	@Test
	public void testGetLetters() {
		Queue<Letter<?>> q = box.getLetters();
		assertTrue(q.isEmpty());

		box.addLetter(new SimpleLetter(new TextContent("")));
		q = box.getLetters();
		assertFalse(q.isEmpty());
		SimpleLetter l = (SimpleLetter)q.peek();
		assertEquals("", l.getContent().getContentLetter());
		
		
	}

	@Test
	public void testTakeFirstLetter() {
		
		box.addLetter(new SimpleLetter(new TextContent("")));
		SimpleLetter l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("", l.getContent().getContentLetter());
		
		box.addLetter(new SimpleLetter(new TextContent("toto")));
		box.addLetter(new SimpleLetter(new TextContent("toto2")));
		l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("toto", l.getContent().getContentLetter());
		l = (SimpleLetter)box.takeFirstLetter();
		assertEquals("toto2", l.getContent().getContentLetter());
	}

}
