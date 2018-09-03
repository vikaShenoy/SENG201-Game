package superheroes;

/**
 * @author vsh33 The CaptainMarvel class is a subclass of SuperHeroes.
 *         CaptainMarvel is one type the user can choose for their hero.
 */

public class CaptainMarvel extends SuperHeroes {
	/**
	 * Creates a new CaptainMarvel.
	 * 
	 * @param name String - Name for the hero.
	 */
	public CaptainMarvel(String name) {
		super(name, "Captain Marvel", "Superhuman Strength");
		this.setMarvelDamage(true);
	}
}
