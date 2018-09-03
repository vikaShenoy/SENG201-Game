package game;

import java.util.ArrayList;

/**
 * @author vsh33 The Maps class gives the team directions of a related
 *         city. Price of the map is also stored.
 * 
 */
public class Maps {
	/**
	 * The associated city, of which the map shows locations.
	 */
	private String cityName;
	/**
	 * The price of purchasing the map.
	 */
	private int price;
	/**
	 * The locations of the city-map information.
	 */
	private ArrayList<String> locations;

	/**
	 * Creates a Map.
	 * 
	 * @param mapName String - Name of the city the map corresponds to.
	 * @param mapPrice int - How much the team needs to purchase the map in shop.
	 * @param mapLocations - A String List of the locations the four directions of the city leads to.
	 */
	public Maps(String mapName, int mapPrice, ArrayList<String> mapLocations) {
		cityName = mapName;
		price = mapPrice;
		locations = mapLocations;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
