package powerups;

/**
 * @author vsh33 The Gauntlet is a subclass of PowerUps. Gaunlet is one
 *         powerup the user can apply on their hero.
 */

public class Gauntlet extends PowerUps {
	/**
	 * Creates new Gauntlet. Deals or takes double damage when used.
	 */
	public Gauntlet() {
		super("Gauntlet", "Deal or take double damage.", 300, 10);
	}
}
