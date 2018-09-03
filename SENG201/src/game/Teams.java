package game;

import java.util.ArrayList;
import healingitems.*;
import powerups.*;
import superheroes.*;

/**
 * @author vsh33 The Teams class holds all the heroes and the inventory of
 *         the team: items, power ups, money, maps, and cities left to clear.
 */
public class Teams {
	// Basic attributes
	/**
	 * The name the user chooses for their team.
	 */
	private String name;
	/**
	 * The number of heroes in the team list.
	 */
	private int teamSize;
	/**
	 * The number of cities the hero has left to clear.
	 */
	private int numCities;
	/**
	 * The list of heroes in the team.
	 */
	private ArrayList<SuperHeroes> heroList;
	/**
	 * The list of items in the team inventory.
	 */
	private ArrayList<HealingItems> itemList;
	/**
	 * The list of power ups in the team inventory.
	 */
	private ArrayList<PowerUps> powerUpList;
	/**
	 * The list of maps in the team inventory.
	 */
	private ArrayList<Maps> mapList;
	/**
	 * The money the team has left. Can be used to purchase items in shops. 
	 * Money is granted when a villain is defeated.
	 */
	private int money = 500;

	private SuperHeroes combatHero;
	// Iron man ability, applies to the whole team.
	private boolean cheaperPrices = false;
	// Doctor Strange ability, applies to the whole team.
	private boolean clairvoyance = false;

	/**
	 * Creates a team. Initialises array lists for items to empty.
	 * 
	 * @param teamName String - The name the user chooses for the team.
	 */
	public Teams(String teamName) {
		heroList = new ArrayList<SuperHeroes>();
		itemList = new ArrayList<HealingItems>();
		powerUpList = new ArrayList<PowerUps>();
		mapList = new ArrayList<Maps>();
		name = teamName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<HealingItems> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<HealingItems> itemList) {
		this.itemList = itemList;
	}

	public ArrayList<Maps> getMapList() {
		return mapList;
	}

	public void setMapList(ArrayList<Maps> mapList) {
		this.mapList = mapList;
	}

	public ArrayList<PowerUps> getPowerUpList() {
		return powerUpList;
	}

	public void setPowerUpList(ArrayList<PowerUps> powerUpList) {
		this.powerUpList = powerUpList;
	}

	public ArrayList<SuperHeroes> getHeroList() {
		return heroList;
	}

	public void setHeroList(ArrayList<SuperHeroes> heroList) {
		this.heroList = heroList;
	}

	/**
	 * Gets the hero who is currently fighting. Combat hero is selected by the user
	 * when they battle the villain.
	 * 
	 * @return SuperHeroes SuperHero - hero who was last fighting.
	 */
	public SuperHeroes getCombatHero() {
		return combatHero;
	}

	/**
	 * Sets the combat hero.
	 * 
	 * @param combatHero SuperHeroes - the hero chosen to fight the villain.
	 */
	public void setCombatHero(SuperHeroes combatHero) {
		this.combatHero = combatHero;
	}

	/**
	 * Special ability which is set when Doctor Strange is added to the team.
	 * Enables the user to have access to maps all the time.
	 * 
	 * @param clairvoyance boolean - sets clairvoyance to true or false.
	 */
	public void setClairvoyance(boolean clairvoyance) {
		this.clairvoyance = clairvoyance;
	}

	/**
	 * Special ability which gives access to the map at all times.
	 * 
	 * @return boolean clairvoyance - whether the team is clairvoyant.
	 */
	public boolean isClairvoyance() {
		return clairvoyance;
	}

	/**
	 * Checks if the team has access to cheaper shop prices. Cheaper shop prices
	 * lower the cost of items.
	 * 
	 * @return boolean cheaperPrices - whether the team has cheaper prices.
	 */
	public boolean isCheaperPrices() {
		return cheaperPrices;
	}

	/**
	 * Sets whether the team has cheaper prices. Cheaper prices decreases the cost
	 * of items in the shop.
	 * 
	 * @param cheaperPrices boolean - sets cheaperPrices to true or false.
	 */
	public void setCheaperPrices(boolean cheaperPrices) {
		this.cheaperPrices = cheaperPrices;
	}

	/**
	 * Gets the number of cities left for the team to complete. If numCities is 1,
	 * when the team beats the villain, the game is won.
	 * 
	 * @return int numCities - number of cities left to clear.
	 */
	public int getNumCities() {
		return numCities;
	}

	/**
	 * Sets the number of cities.
	 * 
	 * @param numCities int - number of cities the user wants to explore.
	 */
	public void setNumCities(int numCities) {
		this.numCities = numCities;
	}

	/**
	 * Adds a new hero to the team. The team size is capped at 3. Teamsize variable
	 * is incremented.
	 * 
	 * @param hero SuperHeroes - new hero to be added to team.
	 */
	public void addHero(SuperHeroes hero) {
		if (heroList.size() >= 3) {
		} else {
			heroList.add(hero);
		}
		teamSize++;
	}

	/**
	 * Removes the specified hero from the team. Occurs when a hero dies in combat.
	 * 
	 * @param hero SuperHeroes - hero to be removed.
	 */
	public void removeHero(SuperHeroes hero) {
		if (heroList.contains(hero)) {
			heroList.remove(hero);
			teamSize--;
		}
	}

	/**
	 * Adds the specified healing item to the inventory. Occurs when a healing item
	 * is purchased or gifted.
	 * 
	 * @param item HealingItems - item to be added.
	 */
	public void addItem(HealingItems item) {
		itemList.add(item);
	}

	/**
	 * Adds the specified map to the inventory. Occurs when a healing item is used
	 * or robbed.
	 * 
	 * @param map Maps - item to be added.
	 */
	public void addMap(Maps map) {
		mapList.add(map);
	}

	/**
	 * Removes the specified healing item from the inventory. Occurs when a healing
	 * item is used or robbed.
	 * 
	 * @param item HealingItems - item to be removed.
	 */
	public void removeItem(HealingItems item) {
		if (itemList.contains(item)) {
			itemList.remove(item);
		}
	}

	/**
	 * Adds the specified powerup to the inventory. Occures when a powerup is
	 * purchased or gifted.
	 * 
	 * @param powerUp PowerUps - powerup to be removed.
	 */
	public void addPowerUp(PowerUps powerUp) {
		powerUpList.add(powerUp);
	}

	/**
	 * Removes the specified powerup from the inventory. Occures when a powerup is
	 * used or robbed.
	 * 
	 * @param powerUp PowerUps - powerup to be removed.
	 */
	public void removePowerUp(PowerUps powerUp) {
		if (powerUpList.contains(powerUp)) {
			powerUpList.remove(powerUp);
		}
	}

	/**
	 * Checks to see whether the team has the map of a specified city present. Used
	 * in the home base screen. If true, the map will be displayed. Clairvoyance
	 * being true will make this function always return true.
	 * 
	 * @param cityName String - Mapslist is checked to see if a map of this city has been purchased.
	 * @return boolean mapPresent - true if the map is present in mapslist.
	 */
	public boolean hasMap(String cityName) {
		boolean mapPresent = false;
		for (Maps map : mapList) {
			if (map.getCityName().equals(cityName)) {
				mapPresent = true;
			}
		}
		if (clairvoyance == true) {
			mapPresent = true;
		}
		return mapPresent;
	}

	/**
	 * Team money is altered by an amount.
	 * 
	 * @param amount int - the value by which to change team's money.
	 */
	public void alterMoney(int amount) {
		money += amount;
		if (money < 0) {
			money = 0;
		}
	}

	/**
	 * Team is checked to see whether the hero named is present.
	 * 
	 * @param name String - of the hero to check for.
	 * @return boolean flag - whether the hero is present.
	 */
	public boolean containsHero(String name) {
		boolean flag = false;
		for (SuperHeroes hero : heroList) {
			if (hero.getName().equals(name)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * Gives a formatted html string displaying the contents of the team's
	 * inventory. Used so the user can see items they possess.
	 * 
	 * @return String info - formatted html string of inventory contents.
	 */
	public String getItemInfo() {
		String info = "<html><center>";

		info += "<BR>Items in inventory:<BR>";
		for (HealingItems item : itemList) {
			info += item.getName();
			info += "<BR>";
		}
		info += "<BR>Power ups in inventory:<BR>";
		for (PowerUps pu : powerUpList) {
			info += pu.getName();
			info += "<BR>";
		}
		info += "<BR>Maps in inventory:<BR>";
		for (Maps map : mapList) {
			info += map.getCityName();
			info += "<BR>";
		}

		info += "</center></html>";
		return info;
	}

	/**
	 * Gives a formatted html string of info on the team. Includes heroes, items,
	 * and money.
	 * 
	 * @return String info - formatted html string containing team information.
	 */
	public String getTeamInfo() {
		String info = "";

		info += "<html><left><u>Heroes in team:</u> <BR>";
		for (SuperHeroes hero : heroList) {
			info += hero.getName();
			info += "<BR>Health: " + hero.getHealth() + "%";
			info += "<BR>";
		}
		info += "<BR><u>Items in inventory:</u><BR>";
		for (HealingItems item : itemList) {
			info += item.getName();
			info += "<BR>";
		}
		info += "<u>Power ups in inventory:</u><BR>";
		for (PowerUps pu : powerUpList) {
			info += pu.getName();
			info += "<BR>";
		}
		info += "<u>Maps in inventory:</u><BR>";
		for (Maps map : mapList) {
			info += map.getCityName();
			info += "<BR>";
		}
		info += "Team money = $<b>" + money + "</b><BR>";
		info += "</left></html>";
		return info;
	}
}