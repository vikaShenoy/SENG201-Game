package supervillains;

import java.util.ArrayList;

/**
 * @author vsh33 The LexLuthor class is a subclass of SuperVillains.
 *         LexLuthor is one of the villains in the game the hero may fight.
 *
 */
public class LexLuthor extends SuperVillains {
	/**
	 * Creates a new LexLuthor. Adds the list of games he can play vs heroes.
	 */
	public LexLuthor() {
		super("Lex Luthor", "If a man won’t kill God, the Devil will do it!", 20);
		ArrayList<String> games = new ArrayList<String>();
		games.add("PSR");
		games.add("GN");
		games.add("DR");
		this.setGames(games);
	}
}
