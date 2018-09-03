package game;

import java.util.*;
import healingitems.*;
import powerups.*;

/**
 * @author vsh33 Shop is a class that holds all the attirbutes of a shop
 *         the user can navigate through Items and information is displayed in
 *         the shop for the hero to purchase and use
 * 
 */

public class Shop {
	/**
	 * The game manager.
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * A list of items which can be purchased at this shop.
	 */
	private ArrayList<HealingItems> items;
	/**
	 * A list of power ups which can be purchased at this shop.
	 */
	private ArrayList<PowerUps> powerUps;
	/**
	 * A list of maps which can be purchased at this shop.
	 */
	private ArrayList<Maps> maps;

	public Shop(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		items = new ArrayList<HealingItems>();
		powerUps = new ArrayList<PowerUps>();
		maps = new ArrayList<Maps>();
		// Creates a list of items, powerups and maps
		generateItems();
		generatePowerUps();
		generateMaps();
	}


	public GameEnvironmentGUI getGameEnv() {
		return gameEnv;
	}

	public void setGameEnv(GameEnvironmentGUI gameEnv) {
		this.gameEnv = gameEnv;
	}

	public ArrayList<HealingItems> getItems() {
		return items;
	}

	public void setItems(ArrayList<HealingItems> addItems) {
		items = addItems;
	}

	public ArrayList<PowerUps> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(ArrayList<PowerUps> addPowerUps) {
		powerUps = addPowerUps;
	}

	public ArrayList<Maps> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<Maps> addMaps) {
		maps = addMaps;
	}

	public void removeItem(HealingItems item) {
		items.remove(item);
	}

	public void removePowerUp(PowerUps powerup) {
		powerUps.remove(powerup);
	}

	public void removeMap(Maps map) {
		maps.remove(map);
	}

	/**
	 * Generates the healing items in the shop. The switch randomises the
	 * availability, of certain healing items where it can have a range of either
	 * none or all the items. Options are determined by random generator.
	 */
	public void generateItems() {
		Random generator = new Random();
		int nextRandom = generator.nextInt(4);
		HealingItems small = new PotionSmall();
		HealingItems medium = new PotionMedium();
		HealingItems bean = new SenzuBean();
		switch (nextRandom) {
		case (0):
			break;
		case (1):
			items.add(small);
			break;
		case (2):
			items.add(small);
			items.add(medium);
			break;
		case (3):
			items.add(small);
			items.add(medium);
			items.add(bean);
			break;
		}
	}

	/**
	 * Generates the powerup items in the shop. The switch randomises the
	 * availability, of certain healing items where it can have a range of either
	 * none or all the items. Options are determined by random generator.
	 */
	public void generatePowerUps() {
		Random generator = new Random();
		int nextRandom = generator.nextInt(4);
		PowerUps aether = new Aether();
		PowerUps tess = new Tesseract();
		PowerUps gauntlet = new Gauntlet();
		switch (nextRandom) {
		case (0):
			break;
		case (1):
			powerUps.add(aether);
			break;
		case (2):
			powerUps.add(aether);
			powerUps.add(tess);
			break;
		case (3):
			powerUps.add(aether);
			powerUps.add(tess);
			powerUps.add(gauntlet);
			break;
		}
	}

	/**
	 * Generates the number of maps able to be purchased by the user. Buy options
	 * vary from zero to six available maps.
	 */
	public void generateMaps() {
		Random generator = new Random();
		int nextRandom = generator.nextInt(6);
		ArrayList<Cities> cityList = gameEnv.getCityList();
		for (Cities city : cityList) {
			maps.add(city.getCityMap());
		}
	}

	/**
	 * Generates the powerup names into a list, where it can be displayed in the
	 * shop
	 * 
	 * @return names - list of names of all the powerups
	 */
	public ArrayList<String> getPowerupNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (PowerUps power : powerUps) {
			names.add(power.getName());
		}
		return names;
	}

	/**
	 * Generates the healing items names into a list, where it can be displayed in
	 * the shop
	 * 
	 * @return names - list of names of all the healing items
	 */
	public ArrayList<String> getItemNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (HealingItems item : items) {
			names.add(item.getName());
		}
		return names;
	}

	/**
	 * Generates the names of all the maps into a list, where it can be displayed in
	 * the shop
	 * 
	 * @return names - list of names of all the maps
	 */
	public ArrayList<String> getMapNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Maps item : maps) {
			names.add(item.getCityName());
		}
		return names;
	}
}
