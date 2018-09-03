package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The Riddler class is a subclass of SuperVillains. Riddler
 *         is one of the villains in the game the hero may fight.
 *
 */
public class Riddler extends SuperVillains {
	/**
	 * Creates a new Riddler. Adds the list of games he can play vs heroes.
	 */
	public Riddler() {
		super("Riddler", "Can you defeat a mind such as mine?", 15);
		ArrayList<String> games = new ArrayList<String>();
		games.add("DR");
		this.setGames(games);
	}
}
