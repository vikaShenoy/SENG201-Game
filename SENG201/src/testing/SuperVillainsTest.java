package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import superheroes.*;
import supervillains.*;

/**
 * 
 * @author dle70
 * JUnit Tests for the SuperVillains class
 *
 */

class SuperVillainsTest {

	/*@Test
	void testConstructor() {
		SuperVillains test = new Bane();

		String villainName = "Bane";
		String villainTaunt = "I was born in the dark, moulded by it...";
		int villainDamage = 30;

		assertEquals(villainName, test.getName());
		assertEquals(villainTaunt, test.getTaunt());
		assertEquals(villainDamage, test.getDamage());
	}*/

	@Test
	void testloseLife() {
		SuperVillains test = new Deathstroke();
		SuperHeroes hero = new BlackPanther("Derrick");
		int lives = 2;
		test.loseLife(hero);
		assertEquals(lives, test.getLives());
	}
}