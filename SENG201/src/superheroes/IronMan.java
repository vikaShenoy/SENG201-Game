package superheroes;

/**
 * @author vsh33 The IronMan class is a subclass of SuperHeroes. IronMan
 *         is one type the user can choose for their hero.
 */

public class IronMan extends SuperHeroes {
	/**
	 * Creates a new IronMan
	 * 
	 * @param name
	 *            Name for the hero.
	 */
	public IronMan(String name) {
		super(name, "Iron Man", "Billionare Engineer");
	}
}
