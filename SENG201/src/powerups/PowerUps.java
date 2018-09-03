package powerups;

/**
 * @author vsh33 PowerUps is a class that holds all the attributes of
 *         powerup items which is used in game to enhance gameplay. PowerUps are
 *         consumables that are used by user in game.
 */

public class PowerUps {
	/**
	 * The name of the power up.
	 */
	private String name;
	/**
	 * A description of what the power up does.
	 */
	private String effect;
	/**
	 * The amount of money the team loses when purchasing this item in the shop.
	 */
	private int price;
	/**
	 * The time it takes to apply this item in the power up den.
	 */
	private long time;

	/**
	 * Creates a PowerUp
	 * 
	 * @param powerName String- Name of powerup
	 * @param powerEffect String - Effect of powerup
	 * @param powerPrice int - price of powerup
	 * @param powerupTime int - application time of powerup
	 */

	public PowerUps(String powerName, String powerEffect, int powerPrice, long powerupTime) {
		name = powerName;
		effect = powerEffect;
		price = powerPrice;
		time = powerupTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String powerName) {
		this.name = powerName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int powerPrice) {
		this.price = powerPrice;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String powerEffect) {
		this.effect = powerEffect;
	}

	/**
	 * Gets the application time of powerup
	 * 
	 * @return long time - This returns the time it would take to use the power up
	 *         in the den
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Sets the power up application time
	 * 
	 * @param powerTime long - The time it would take to use the power up in the den
	 */
	public void setTime(long powerTime) {
		this.time = powerTime;
	}

	/**
	 * This method prints and formats out all the useful information of a powerup.
	 * 
	 * @return String info - The formatted information detailing the powerup's
	 *         attributes.
	 */
	public String getInfo() {
		String info = "<html><center> Name: " + name + "<BR>Effect: " + effect + "<BR>Price: " + price
				+ "</center></html>";
		return info;
	}
}