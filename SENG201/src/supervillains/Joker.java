package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The Joker class is a subclass of SuperVillains. Joker is
 *         one of the villains in the game the hero may fight.
 */
public class Joker extends SuperVillains {
	/**
	 * Creates a new Joker. Adds the list of games he can play vs heroes.
	 */
	public Joker() {
		super("Joker", "Do you want to see know how I got these scars?", 5);
		ArrayList<String> games = new ArrayList<String>();
		games.add("GN");
		this.setGames(games);
	}
}
