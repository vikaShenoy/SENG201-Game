package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import powerups.*;
import superheroes.*;
import supervillains.*;

/**
 * 
 * @author dle70
 * JUnit Tests for the SuperHeroes class
 */

class SuperHeroesTest {

	/*@Test
	void testConstructor() {
		SuperHeroes test = new BlackPanther("Derrick");

		String heroName = "Derrick" + "( " + test.getType() + " )";
		String heroType = "Black Panther";
		String heroAbility = "Vibranium Suit";

		assertEquals(heroName, test.getName());
		assertEquals(heroType, test.getType());
		assertEquals(heroAbility, test.getAbility());
	}*/

	@Test
	void testTesseract() {
		SuperHeroes test = new Spiderman("Derrick");
		PowerUps powerUp = new Tesseract();

		int initialDamage = test.getDamageReduction();
		test.usePowerUp(powerUp);
		assertEquals(initialDamage + 5, test.getDamageReduction());
	}

	@Test
	void testAether() {
		SuperHeroes test = new Wolverine("Vikas");
		PowerUps powerUp = new Aether();

		boolean initalOdds = true;
		test.usePowerUp(powerUp);
		assertEquals(initalOdds, test.hasIncreasedOdds());
	}

	@Test
	void testGaunlet() {
		SuperHeroes test = new CaptainMarvel("Derrick");
		PowerUps powerUp = new Gauntlet();

		boolean initalDamage = true;
		test.usePowerUp(powerUp);
		assertEquals(initalDamage, test.hasGauntletDamage());
	}

	/*@Test
	void testAlterHealth() {
		SuperHeroes test = new DoctorStrange("Vikas");

		int initalHealth = 75;
		assertEquals(initalHealth, test.alterHealth(initalHealth));
	}*/

	@Test
	void testTakeVillainDamage() {
		SuperHeroes test = new SuperHeroes("Derrick", "Wolverine", "Regeneration");
		SuperVillains villain = new LexLuthor();

		int afterDamageHealth = 80;
		test.takeVillainDamage(villain);
		
		assertEquals(afterDamageHealth, test.getHealth());
	}
}