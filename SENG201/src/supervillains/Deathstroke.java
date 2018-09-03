package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The Deathstroke class is a subclass of SuperVillains.
 *         Deathstroke is one of the villains in the game the hero may fight.
 *
 */
public class Deathstroke extends SuperVillains {
	/**
	 * Creates a new Deathstroke and creates the list of games he can play vs
	 * heroes.
	 */
	public Deathstroke() {
		super("Deathstroke", "The last thing you will see is my blade!", 25);
		ArrayList<String> games = new ArrayList<String>();
		games.add("PSR");
		this.setGames(games);
	}
}
