package powerups;

/**
 * @author vsh33 The Aether class is a subclass of PowerUps. Aether is one
 *         powerup the user can apply on their hero.
 */

public class Aether extends PowerUps {
	/**
	 * Creates new Aether. Increases odds in dice game when used.
	 */
	public Aether() {
		super("Aether", "Increased odds in dice game", 200, 5);
	}
}
