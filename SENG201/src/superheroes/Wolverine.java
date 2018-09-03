package superheroes;

/**
 * @author vsh33 The Wolverine class is a subclass of SuperHeroes.
 *         Wolverine is one type the user can choose for their hero.
 */
public class Wolverine extends SuperHeroes {
	/**
	 * Creates a new Wolverine. Sets an increased recovery rate for the hero due to
	 * special ability.
	 * 
	 * @param name String - Name for the hero.
	 */
	public Wolverine(String name) {
		super(name, "Wolverine", "Regeneration");
		this.setRecoveryRate(2);
	}
}
