package game;

import java.util.*;
import supervillains.*;

/**
 * 
 * @author vsh33 Cities is a class that holds all the attributes of a city
 *         the user has to complete. Each city has four locations; villains
 *         lair, powerup den, hospital, shop. Cities class randomly assigns
 *         locations for the user to navigate through
 */

public class Cities {
	// Basic attributes
	/**
	 * The name of the city. 
	 */
	private String cityName;
	/**
	 * Cities are numbered 1 to 6. The city number is tied to the creation
	 * of a specific villain in the game.
	 */
	private int cityNumber;
	/**
	 * The villain that the user must beat to clear this city.
	 */
	private SuperVillains villain;
	/**
	 * A map of the city which shows where each direction leads.
	 */
	private Maps cityMap;
	/**
	 * The associated store in each city where the user can buy
	 * items.
	 */
	private Shop cityShop;

	// The location of where each direction in the city will lead.
	/**
	 * The location which the north button in the home base leads to.
	 */
	private String north;
	/**
	 * The location which the east button in the home base leads to.
	 */
	private String east;
	/**
	 * The location which the south button in the home base leads to.
	 */
	private String south;
	/**
	 * The location which the west button in the home base leads to.
	 */
	private String west;

	/**
	 * Creates a new City. villain - Name of the villain associated with the current
	 * city
	 * 
	 * @param cityNumber int - the number that determines the villain to a city
	 */
	public Cities(int cityNumber) {
		generateVillain(cityNumber);
		this.cityNumber = cityNumber;
		// Assigns a randomly generated location to a direction variable.
		ArrayList<String> locations = generateLocations();
		north = locations.get(0);
		east = locations.get(1);
		south = locations.get(2);
		west = locations.get(3);
		// Creates a map of the city, which can be purchased by th team.
		cityMap = new Maps(cityName, 50, locations);

	}

	/**
	 * Randomly generates a number and assigns it to an index in the Array. This
	 * index is the location which is given a direction value in the constructor
	 * 
	 * @return String[] locations - String of locations in a specific index order
	 */
	public ArrayList<String> generateLocations() {
		Random generator = new Random();
		ArrayList<String> locations = new ArrayList<String>();
		String[] places = new String[] { "Den", "Hospital", "Shop", "Lair" };

		int i = 0;
		while (i != 4) {
			int nextRandom = generator.nextInt(4);
			if (!locations.contains(places[nextRandom])) {
				locations.add(places[nextRandom]);
				i++;
			}
		}
		return locations;
	}

	public Shop getCityShop() {
		return cityShop;
	}

	public void setCityShop(Shop cityShop) {
		this.cityShop = cityShop;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(int cityNumber) {
		this.cityNumber = cityNumber;
	}

	public SuperVillains getVillain() {
		return villain;
	}

	public void setVillain(SuperVillains villain) {
		this.villain = villain;
	}

	public Maps getCityMap() {
		return cityMap;
	}

	public void setCityMap(Maps cityMap) {
		this.cityMap = cityMap;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	/**
	 * A switch that assigns the parameter to a specific villain. The Villain is
	 * hardcoded to the cityNumber
	 * 
	 * @param cityNumber int - the number associated with a villain (switch paramater)
	 */
	public void generateVillain(int cityNumber) {
		switch (cityNumber) {
		case (1):
			SuperVillains bane = new Bane();
			villain = bane;
			cityName = "The Pit";
			break;
		case (2):
			SuperVillains deathstroke = new Deathstroke();
			villain = deathstroke;
			cityName = "New York";
			break;
		case (3):
			SuperVillains lexLuthor = new LexLuthor();
			villain = lexLuthor;
			cityName = "Metropolis";
			break;
		case (4):
			SuperVillains riddler = new Riddler();
			villain = riddler;
			cityName = "Arkham City";
			break;
		case (5):
			SuperVillains scarecrow = new Scarecrow();
			villain = scarecrow;
			cityName = "Arkham Asylum";
			break;
		case (6):
			SuperVillains joker = new Joker();
			villain = joker;
			cityName = "Gotham";
			break;
		}
	}
}
