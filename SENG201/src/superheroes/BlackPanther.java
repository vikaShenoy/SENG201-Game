package superheroes;

/**
 * @author vsh33 The BlackPanther class is a subclass of SuperHeroes.
 *         BlackPanther is one type the user can choose for their hero.
 */
public class BlackPanther extends SuperHeroes {
	/**
	 * Creates a new BlackPanther. Increases damage reduction in accordance with
	 * special ability.
	 * 
	 * @param name String - Name for the hero.
	 */
	public BlackPanther(String name) {
		super(name, "Black Panther", "Vibranium Suit");
		this.setDamageReduction(5);
	}
}
