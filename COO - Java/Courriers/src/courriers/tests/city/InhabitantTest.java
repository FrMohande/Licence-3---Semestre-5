package courriers.tests.city;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import courriers.city.City;
import courriers.city.Inhabitant;

public class InhabitantTest {
	
	Inhabitant h1;
	City c = new City("Lille");

	@Before
	public void setUp() throws Exception {
		h1 = new Inhabitant("toto", c);
	}

	@After
	public void tearDown() throws Exception {
		h1 = null;
	}

	@Test
	public void testInhabitantStringCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testInhabitantStringCityDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBankAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPostbox() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowLetters() {
		fail("Not yet implemented");
	}

}
