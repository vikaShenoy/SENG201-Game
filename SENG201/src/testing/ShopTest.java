package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import game.*;
import powerups.*;
import healingitems.*;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author vsh33
 * JUnit Tests for the Shop Class
 */

class ShopTest {

	@Test
	void testGetHealingItemNames() {
		ArrayList<HealingItems> testItems = new ArrayList<HealingItems>();
		GameEnvironmentGUI testGameEnv = new GameEnvironmentGUI();
		ArrayList<Cities> testCities = new ArrayList<Cities>();
		testGameEnv.setCityList(testCities);
		Shop testShop = new Shop(testGameEnv);
		HealingItems small = new PotionSmall();
		HealingItems medium = new PotionMedium();
		HealingItems bean = new SenzuBean();
		testItems.add(small);
		testItems.add(medium);
		testItems.add(bean);
		testShop.setItems(testItems);
		ArrayList<String> testNames = testShop.getItemNames();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Small Potion");
		expected.add("Medium Potion");
		expected.add("Senzu Bean");
		assertEquals(expected, testNames);
	}
	
	@Test
	void testGetPowerupNames() {
		ArrayList<PowerUps> testItems = new ArrayList<PowerUps>();
		GameEnvironmentGUI testGameEnv = new GameEnvironmentGUI();
		ArrayList<Cities> testCities = new ArrayList<Cities>();
		testGameEnv.setCityList(testCities);
		Shop testShop = new Shop(testGameEnv);
		PowerUps tess = new Tesseract();
		PowerUps aether = new Aether();
		PowerUps gauntlet = new Gauntlet();
		testItems.add(tess);
		testItems.add(aether);
		testItems.add(gauntlet);
		testShop.setPowerUps(testItems);
		ArrayList<String> testNames = testShop.getPowerupNames();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Tesseract");
		expected.add("Aether");
		expected.add("Gauntlet");
		assertEquals(expected, testNames);
	}
	
	@Test
	void testGetMapNames() {
		GameEnvironmentGUI testGameEnv = new GameEnvironmentGUI();
		ArrayList<Cities> testCities = new ArrayList<Cities>();
		testGameEnv.setCityList(testCities);
		Shop testShop = new Shop(testGameEnv);
		ArrayList<String> testLocations = new ArrayList<String>();
		ArrayList<Maps> testMaps = new ArrayList<Maps>();
		testLocations.add("Den");
		testLocations.add("Lair");
		testLocations.add("Shop");
		Maps testMap1 = new Maps("Gotham", 50, testLocations);
		Maps testMap2 = new Maps("Arkham City", 50, testLocations);
		testMaps.add(testMap1);
		testMaps.add(testMap2);
		testShop.setMaps(testMaps);
		ArrayList<String> testNames = testShop.getMapNames();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Gotham");
		expected.add("Arkham City");
		assertEquals(expected, testNames);
	}
}
