package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The Bane class is a subclass of SuperVillains. Bane is
 *         one of the villains in the game the hero may fight. Bane is the
 *         game's boss villain and is always present in the final city.
 */
public class Bane extends SuperVillains {
	/**
	 * Creates a new Bane. Sets Bane to boss. Adds the list of games Bane can play
	 * against the hero.
	 */
	public Bane() {
		super("Bane", "I was born in the dark, moulded by it...", 30);
		this.setBoss(true);
		this.setLives(4);
		ArrayList<String> games = new ArrayList<String>();
		games.add("PSR");
		games.add("GN");
		games.add("DR");
		this.setGames(games);
	}

}
