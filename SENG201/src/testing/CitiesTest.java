package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import game.*;

/**
 * 
 * @author dle70
 * JUnit Tests for the Cities class
 *
 */

class CitiesTest {

	@Test
	void testGenerateLocations() {
		Cities test = new Cities(0);
		ArrayList<String> locations = test.generateLocations();
		int size = locations.size();
		boolean shop = locations.contains("Shop");
		boolean den = locations.contains("Den");
		boolean hospital = locations.contains("Hospital");
		boolean lair = locations.contains("Lair");
		
		assertEquals(4, size);
		assertEquals(true, shop);
		assertEquals(true, den);
		assertEquals(true, hospital);
		assertEquals(true, lair);

	}
	
	@Test
	void testGenerateVillain() {
		Cities testCity = new Cities(2);
		String name = testCity.getVillain().getName();
		assertEquals("Deathstroke", name);
	}

}
