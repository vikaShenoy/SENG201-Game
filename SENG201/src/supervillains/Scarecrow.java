package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The Scarecrow class is a subclass of SuperVillains.
 *         Scarecrow is one of the villains in the game the hero may fight.
 *
 */
public class Scarecrow extends SuperVillains {
	/**
	 * Creates a Scarecrow. Adds the games he can play vs heroes.
	 */
	public Scarecrow() {
		super("Scarecrow", "You will suffer the greatest pain...", 15);
		ArrayList<String> games = new ArrayList<String>();
		games.add("PSR");
		games.add("DR");
		this.setGames(games);
	}
}
