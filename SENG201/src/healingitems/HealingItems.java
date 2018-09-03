
package healingitems;

/**
 * 
 * @author vsh33 HealingItems are the items that help heroes regain health
 *         lost in battle. This class contains attributes for healing items.
 */
public class HealingItems {
	/** 
	 * The name of the item.
	 */
	private String name;
	/**
	 * The amount of money the tea loses when they purchase this item in the shop.
	 */
	private int price;
	/**
	 * The amount of health the item regenerates for the hero.
	 */
	private int size;
	/**
	 * The time it takes for the item to be fully applied.
	 */
	private long time;

	/**
	 * Creates a HealingItem.
	 * 
	 * @param healingName String - Name of the items.
	 * @param healingPrice int - How much money the team needs to buy the item in shop.
	 * @param healingSize int - How much health the item restores.
	 * @param healingTime int - Total time it takes to apply the item.
	 */
	public HealingItems(String healingName, int healingPrice, int healingSize, long healingTime) {
		name = healingName;
		price = healingPrice;
		size = healingSize;
		time = healingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String healingName) {
		this.name = healingName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int healingPrice) {
		this.price = healingPrice;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int healingSize) {
		this.size = healingSize;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long healingTime) {
		this.time = healingTime;
	}

	/**
	 * Gives a formatted string with the item's info, to be displayed to the user.
	 * 
	 * @return String - item info with formatted item attributes for the user.
	 */
	public String getInfo() {
		String info = "<html><center>Name: " + name + "<BR>Size: " + size + "<BR>Time: " + time + "<BR>Price: " + price
				+ "</center></html>";
		return info;
	}

}