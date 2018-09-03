package healingitems;

/**
 * @author vsh33 PotionSmall is a subclass of HealingItems. PotionSmall is
 *         one of the consumable items in the game, which returns a hero's
 *         health over time.
 */
public class PotionSmall extends HealingItems {
	/**
	 * Creates a PotionSmall.
	 */
	public PotionSmall() {
		super("Small Potion", 50, 25, 3);
	}
}