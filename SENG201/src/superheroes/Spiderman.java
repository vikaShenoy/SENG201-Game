package superheroes;

/**
 * @author vsh33 The Spiderman class is a subclass of SuperHeroes.
 *         Spiderman is one type the user can choose for their hero.
 */
public class Spiderman extends SuperHeroes {
	/**
	 * Creates a new Spiderman. Gives the hero a shield due to special ability.
	 * 
	 * @param name String - Name for the hero.
	 */
	public Spiderman(String name) {
		super(name, "Spiderman", "Spider-sense");
		this.hasShield();
	}
}
