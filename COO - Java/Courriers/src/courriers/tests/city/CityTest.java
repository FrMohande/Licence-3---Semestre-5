package courriers.tests.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.city.City;
import courriers.city.Inhabitant;
import courriers.letters.SimpleLetter;
import courriers.letters.contents.TextContent;

public class CityTest {
	
	protected City city;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		city = new City("C1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		city = null;
	}
	
	@Test
	public void testCityString() {
		assertTrue(city.getName().compareTo("C1") == 0);
		assertEquals("C1", city.getName());
		assertTrue(city.getInhabitants().isEmpty());
		assertTrue(city.getPostbox().isEmpty());
	}


	@Test
	public void testAddInhabitants() {
		city.addInhabitants(new Inhabitant("H", city));

		assertEquals(1, city.getInhabitants().size());
		assertTrue(city.getInhabitants().get(0).getName().compareTo("H") == 0);
		
		city.addInhabitants(new Inhabitant("H2", city));
		
		assertEquals(2, city.getInhabitants().size());
		assertTrue(city.getInhabitants().get(0).getName().compareTo("H") == 0);
		assertTrue(city.getInhabitants().get(1).getName().compareTo("H2") == 0);
	}

	@Test
	public void testSendLetter() {
		
		city.sendLetter(new SimpleLetter(new TextContent("content")));
		
		assertEquals(1, city.getPostbox().getLetters().size());
		assertEquals("content", ((TextContent)(city.getPostbox().getLetters().peek().getContent())).getContentLetter());
		
		
	}

	@Test
	public void testDistributeLetters() {
		Inhabitant h1 = new Inhabitant("H", city);
		Inhabitant h2 = new Inhabitant("H2", city);
		city.addInhabitants(h1);
		city.addInhabitants(h2);
		
		SimpleLetter letter = new SimpleLetter(0, h1, h2, new TextContent("content"));
		city.sendLetter(letter);
		
		
		city.distributeLetters();

		assertTrue(city.getPostbox().isEmpty());
		
		
	}

}
