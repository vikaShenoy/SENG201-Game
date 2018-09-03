package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.*;
import healingitems.*;
import powerups.*;
import superheroes.*;

/**
 * 
 * @author dle70
 * JUnit Tests for the Teams class
 */

class TeamsTest {

	@Test
	void testAddHero() {
		Teams test = new Teams("Avengers");
		SuperHeroes hero = new IronMan("Derrick");

		for (int counter = 0; counter < 4;) {
			test.addHero(hero);
			counter++;
		}
		assertEquals(4, test.getTeamSize());
	}

	@Test
	void testRemoveHero() {
		Teams test = new Teams("X-Force");
		SuperHeroes hero = new SuperHeroes("Vikas", "Spiderman", "Spider-sense");

		for (int counter = 0; counter < 2;) {
			test.addHero(hero);
			counter++;
		}
		test.removeHero(hero);

		assertEquals(1, test.getTeamSize());
	}

	
	@Test
	void testAddItem() {
		Teams test = new Teams("The Hand");
		HealingItems item = new PotionMedium();

		for (int counter = 0; counter < 2;) {
			test.addItem(item);
			counter++;
		}
		assertEquals(2, test.getItemList().size());
	}

	@Test
	void testRemoveItem() {
		Teams test = new Teams("X-Men");
		HealingItems item = new PotionSmall();

		for (int counter = 0; counter < 3;) {
			test.addItem(item);
			counter++;
		}
		test.removeItem(item);
		assertEquals(2, test.getItemList().size());
	}
	 

	@Test
	void testAddPowerUp() {
		Teams test = new Teams("Gorillaz");
		PowerUps power = new Tesseract();

		for (int counter = 0; counter < 3;) {
			test.addPowerUp(power);
			counter++;
		}
		assertEquals(3, test.getPowerUpList().size());
	}

	@Test
	void testRemovePowerUp() {
		Teams test = new Teams("Ninjas");
		PowerUps power = new Gauntlet();

		for (int counter = 0; counter < 4;) {
			test.addPowerUp(power);
			counter++;
		}
		test.removePowerUp(power);
		assertEquals(3, test.getPowerUpList().size());
	}

	@Test
	void testAddMaps() {
		Teams test = new Teams("Killers");
		Maps map = new Maps("New York", 100, null);

		for (int counter = 0; counter < 4;) {
			test.addMap(map);
			counter++;
		}
		assertEquals(4, test.getMapList().size());
	}

	@Test
	void testHasMaps() {
		Teams test = new Teams("Boobs");
		Maps map = new Maps("Gotham", 200, null);

		test.addMap(map);
		assertEquals(true, test.hasMap("Gotham"));
	}

	@Test
	void testAlterMoney() {
		Teams test = new Teams("Bankers");

		test.alterMoney(100);
		assertEquals(600, test.getMoney());
	}

	@Test
	void testContainsHero() {
		Teams test = new Teams("Gayos");
		SuperHeroes hero = new SuperHeroes("Derrick", "Iron Man", "Engineering Billionare");

		test.addHero(hero);
		assertEquals(true, test.containsHero("Derrick( Iron Man )"));
	}
}
