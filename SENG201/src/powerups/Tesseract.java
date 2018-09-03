package powerups;

/**
 * @author vsh33 The Tesseract class is a subclass of PowerUps. Tesseract
 *         is one powerup the user can apply on their hero.
 */

public class Tesseract extends PowerUps {
	/**
	 * Creates new Tesseract Reduces the damage the villain can do to the hero when
	 * used.
	 */
	public Tesseract() {
		super("Tesseract", "Villain damage reduction.", 100, 2);
	}
}
