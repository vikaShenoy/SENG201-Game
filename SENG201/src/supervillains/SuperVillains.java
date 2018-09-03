package supervillains;

import java.util.ArrayList;
import superheroes.SuperHeroes;

/**
 * @author vsh33 SuperVillains is the class which holds all attributes of
 *         a villain, and enables their modification. SuperVillains are the
 *         antagonists in the game, and must be defeated for the game to be
 *         completed.
 */
public class SuperVillains {
	/**
	 * Name of the villain.
	 */
	private String name;
	/**
	 * The taunt the villain cries out when the team enters their lair.
	 */
	private String taunt;
	/**
	 * The amount of health that is decremented when the hero loses
	 * a battle to this vilain.
	 */
	private int damage;
	/**
	 * The number of games the hero must win against the villain.
	 */
	private int lives = 3;
	/**
	 * The final villain in the game. Beating this villain finishes the game.
	 */
	private boolean boss = false;
	/** 
	 * The games the villain can play. This list is accessed
	 * when the user chooses to battle this villain. 
	 */
	private ArrayList<String> games;

	/**
	 * Creates a new SuperVillain.
	 * 
	 * @param villainName String - Name of the villain.
	 * @param villainTaunt String - Taunt the villain cries out.
	 * @param villainDamage String  - Damage the villain does to the hero in battle.
	 */
	public SuperVillains(String villainName, String villainTaunt, int villainDamage) {
		name = villainName;
		taunt = villainTaunt;
		damage = villainDamage;
	}

	public String getName() {
		return name;
	}

	public void setName(String villainName) {
		this.name = villainName;
	}

	public String getTaunt() {
		return taunt;
	}

	public void setTaunt(String villainTaunt) {
		this.taunt = villainTaunt;
	}

	/**
	 * Checks if the villain is the boss villain. The boss villain always resides in
	 * the final city, does the most damage, and has the most lives.
	 * 
	 * @return boolean boss - Whether the villain is boss or not.
	 */
	public boolean isBoss() {
		return boss;
	}

	/**
	 * Sets the villain to be the boss.
	 * 
	 * @param boss boolean - Whether the villain is a boss villain or not.
	 */
	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	/**
	 * Gets the list of names of the games the villain plays. These are used when a
	 * hero selects to battle a villain, to determine which game screen appears.
	 * 
	 * @return games - List of the villain's games.
	 */
	public ArrayList<String> getGames() {
		return games;
	}

	/**
	 * Sets a list of games the villain can potentially play.
	 * 
	 * @param games - List of the villain's games.
	 */
	public void setGames(ArrayList<String> games) {
		this.games = games;
	}

	/**
	 * Gets the damage the villain does. Damage is the amount of health the hero
	 * loses when they lose a battle to this villain.
	 * 
	 * @return int damage - The amount of damage the villain does.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the villain damage attribute. Damage is the amount of health the hero
	 * loses when they lose a battle to this villain.
	 * 
	 * @param villainDamage int - The amount of damage the villain does.
	 */
	public void setDamage(int villainDamage) {
		this.damage = villainDamage;
	}

	/**
	 * Returns the number of lives the villain has. If a villain has 0 lives they
	 * are removed from the game.
	 * 
	 * @return int lives - The remaining number of battles the villain can lose before
	 *         dieing.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Sets the number of lives the villain has. Boss villains will have more than
	 * other villains.
	 * 
	 * @param villainLives int - villainLives number of lives to give the villain.
	 */
	public void setLives(int villainLives) {
		this.lives = villainLives;
	}

	/**
	 * Decrements the villain's life, when they have just lose a battle. Takes one
	 * life, or two if the hero has power ups equipped.
	 * 
	 * @param hero SuperHeroes - The hero inflicting damage to the villain.
	 */
	public void loseLife(SuperHeroes hero) {
		if (hero.hasGauntletDamage() == true || hero.hasMarvelDamage() == true) {
			lives -= 2;
		} else {
			lives -= 1;
		}

		if (lives < 0) {
			lives = 0;
		}
	}
}