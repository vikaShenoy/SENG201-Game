package superheroes;

import powerups.*;
import supervillains.SuperVillains;

/**
 * @author vsh33 SuperHeroes is a class that holds all the attributes of a
 *         hero, and enables the changing of these attributes. SuperHeroes are
 *         the characters controlled by the user in the game.
 */
public class SuperHeroes {
	// Basic attributes.
	/**
	 * Name of the hero.
	 */
	private String name;
	/**
	 * Type of the hero. These correspond to subclasses.
	 */
	private String type;
	/**
	 * The hero's special ability, described.
	 */
	private String ability;
	/**
	 * Hero health. Altered in battle. Can be replenished.
	 * When the hero reaches 0, it's removed from the team.
	 */
	private int health = 100;

	// Advanced attributes.
	/**
	 * Decrements the damage done by the villain before it's applied to hero health.
	 */
	private int damageReduction = 0;
	/**
	 * The rate at which the hero recovers health in hospital.
	 */
	private int recoveryRate = 1;
	/**
	 * Absorbs one attack from the villain.
	 */
	private boolean shield = false;
	/**
	 * Gives the hero a better chance to win dice roll, by capping the 
	 * villain's roll.
	 */
	private boolean increasedOdds = false;
	/**
	 * Takes two of the villain's lives when the battle is won, and takes double
	 * villain damage when the battle is lost.
	 */
	private boolean gauntletDamage = false;
	/**
	 * Deals two lives of damage to the villain.
	 */
	private boolean marvelDamage = false;

	/**
	 * Creates a new SuperHero.
	 * 
	 * @param heroName String - Name of the hero.
	 * @param heroType String - Type of the hero.
	 * @param heroAbility String - Special ability name.
	 */

	public SuperHeroes(String heroName, String heroType, String heroAbility) {
		name = heroName;
		type = heroType;
		ability = heroAbility;
	}

	public String getName() {
		return name + "( " + type + " )";
	}

	public void setName(String heroName) {
		this.name = heroName;
	}

	public String getType() {
		return type;
	}

	public void setType(String heroType) {
		this.type = heroType;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String heroAbility) {
		this.ability = heroAbility;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int heroHealth) {
		this.health = heroHealth;
	}

	public void hasShield() {
		this.shield = true;
	}

	/**
	 * Gets the recovery rate.
	 * 
	 * @return recoveryRate int - This is the scaling factor for how fast a hero heals
	 *         in hospital.
	 */
	public int getRecoveryRate() {
		return recoveryRate;
	}

	/**
	 * Sets the recovery rate.
	 * 
	 * @param recoveryRate int - Scaling factor for how fast the hero heals in hospital.
	 */
	public void setRecoveryRate(int recoveryRate) {
		this.recoveryRate = recoveryRate;
	}

	/**
	 * Checks if the hero has a shield equipped. Shield will absorb one villain
	 * attack.
	 * 
	 * @return boolean - Whether or not the hero has a shield.
	 */
	public boolean getShield() {
		return shield;
	}

	/**
	 * Sets the hero's shield to on or off. Shield will absorb one villain attack.
	 * 
	 * @param shield boolean - Whether the hero has shield or not.
	 */
	public void setShield(boolean shield) {
		this.shield = shield;
	}

	/**
	 * Checks if the hero has the increasedOdds power up. Increased odds caps the
	 * villain's roll at 4 in the dice roll game.
	 * 
	 * @return boolean - Whether or not the hero has increasedOdds.
	 */
	public boolean hasIncreasedOdds() {
		return increasedOdds;
	}

	/**
	 * Sets the increasedOdds power up on or off. Increased odds caps the villain's
	 * roll at 4 in the dice roll game.
	 * 
	 * @param increasedOdds boolean - Whether the hero has this power up or not.
	 */
	public void setIncreasedOdds(boolean increasedOdds) {
		this.increasedOdds = increasedOdds;
	}

	/**
	 * Checks if the hero has the gauntlet power up. Gauntlet makes the hero take
	 * and receive twice as much damage in combat.
	 * 
	 * @return boolean gauntleyDamage - True if the hero has gauntlet, false if not.
	 */
	public boolean hasGauntletDamage() {
		return gauntletDamage;
	}

	/**
	 * Sets the gauntletDamage power up on or off. Gauntlet makes the hero take and
	 * receive twice as much damage in combat.
	 * 
	 * @param gauntletDamage boolean -  whether the hero has the power up or not.
	 */
	public void setGauntletDamage(boolean gauntletDamage) {
		this.gauntletDamage = gauntletDamage;
	}

	/**
	 * Checks if the hero has the Marvel Damage power up. Marvel damage enables the
	 * hero to take two of the villain's lives instead of one, when the hero wins a
	 * battle.
	 * 
	 * @return boolean marvelDamage - True if the hero has Marvel Damage, false if not.
	 */
	public boolean hasMarvelDamage() {
		return marvelDamage;
	}

	/**
	 * Sets the Marvel Damage power up on or off. Marvel damage enables the hero to
	 * take two of the villain's lives instead of one, when the hero wins a battle.
	 * 
	 * @param marvelDamage boolean - whether the hero has the power up or not.
	 */
	public void setMarvelDamage(boolean marvelDamage) {
		this.marvelDamage = marvelDamage;
	}

	/**
	 * Returns the hero's damage reduction.
	 * 
	 * @return int damageReduction - The amount that villain damage is decremented bym
	 *         before it's taken from the hero's health.
	 */
	public int getDamageReduction() {
		return damageReduction;
	}

	/**
	 * Sets the hero's damage reduction.
	 * 
	 * @param damageReduction int
	 *            the amount that villain damage is decremented bym before it's
	 *            taken from the hero's health.
	 */
	public void setDamageReduction(int damageReduction) {
		this.damageReduction = damageReduction;
	}

	/**
	 * This method is called to apply one of the game's power up to the hero,
	 * altering their damage reduction, granting increased game odds, or granting
	 * extra damage.
	 * 
	 * @param powerUp PowerUps - This is the powerUp item to be applied to the hero.
	 * @return message - This returns a success string informing the user of which
	 *         attributes have changed.
	 */
	public String usePowerUp(PowerUps powerUp) {
		if (powerUp.getName() == "Tesseract") {
			damageReduction += 5;
			return ("Tesseract used. Damage reduction of " + name + " increased by 5.");
		} else if (powerUp.getName() == "Aether") {
			increasedOdds = true;
			return ("Aether used. " + name + " has increased odds in dice game.");
		} else {
			gauntletDamage = true;
			return ("Gauntlet used. " + name + " will deal/take damage in the next game played.");
		}
	}

	/**
	 * Alters the hero's health attribute. Used to increment hero health in the
	 * hospital. Health is forcibly kept within the bounds of 0 and 100.
	 * 
	 * @param amount int - The value of health to be added.
	 */
	public void alterHealth(int amount) {
		health += amount;
		if (health > 100) {
			health = 100;
		} else if (health < 0) {
			health = 0;
		}
	}

	/**
	 * Applies damage to the hero based on the villain in combat. Damage is taken
	 * from the villain and modified based on special abilities shield and gauntlet.
	 * 
	 * @param villain SuperVillains - The villain the hero is fighting. Damage is calculated based on the villain.
	 * @return int damage - The amount of health the hero lost.
	 */
	public int takeVillainDamage(SuperVillains villain) {
		int damage = villain.getDamage() - damageReduction;
		if (gauntletDamage == true) {
			damage = damage * 2;
		} else if (shield == true) {
			damage = 0;
			shield = false;
		}
		health -= damage;
		if (health <= 0) {
			health = 0;
		}
		return damage;
	}

	/**
	 * This method formats useful information about the hero, to be displayed.
	 * 
	 * @return stats - The formatted information detailing the hero's attributes.
	 */
	public String getStats() {
		String stats = "";
		String hasShield = "";
		if (shield) {
			hasShield = "Yes";
		} else {
			hasShield = "No";
		}
		stats += "<html><center><b> " + type + "<BR><BR>Health: " + health + "<BR><BR>Ability: " + ability
				+ "<BR><BR>Shield: " + hasShield;
		stats += "<BR><BR>Damage reduction: " + damageReduction + "<BR><BR>Recovery rate: </b></center></html>" + recoveryRate;
		return stats;
	}
}
