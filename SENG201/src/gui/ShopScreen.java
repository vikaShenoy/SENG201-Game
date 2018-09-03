package gui;
import healingitems.*;
import powerups.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Font;
import game.*;
import java.awt.Color;
/**
 * GUI screen for the game's shop. This is where the user can purchase maps, healing items, and power ups.
 * Information about the game's items can be displayed.
 * The team's money is displayed.
 * @author vsh33.
 *
 */
public class ShopScreen {
	/**
	 * The main frame
	 */
	private JFrame shopWindow;
	/**
	 * The Game Manager, holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds the important information of the team
	 */
	private Teams team;
	/**
	 * City attribute, holds important information on the current city
	 */
	private Cities city;
	/**
	 * Shop attribute, holds the current shop in the city
	 */
	private Shop shop;
	/**
	 * Power Up List, holds the list of powerups available for purchase at the shop
	 */
	private ArrayList<String> powerupList;
	/**
	 * Item List, holds the list of healing items available for purchase at the shop
	 */
	private ArrayList<String> itemList;
	/**
	 * Map List, holds the list of maps available for purchase at the shop
	 */
	private ArrayList<String> mapList;
	/** 
	 * Creates a ShopScreen. 
	 * @param incomingGameEnv the game manager, which holds info on the team and city.
	 */
	public ShopScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		city = gameEnv.getCurrentCity();
		team = gameEnv.getTeam();
		shop = city.getCityShop();
		initialize();
		shopWindow.setLocationRelativeTo(null);
		shopWindow.setVisible(true);	
	}

	public void closeWindow() {
		shopWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeShopScreen(this);
	}

	private void initialize() {
		shopWindow = new JFrame();
		shopWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		shopWindow.setBackground(Color.LIGHT_GRAY);
		shopWindow.setTitle("Shop");
		shopWindow.setBounds(100, 100, 768, 518);
		shopWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopWindow.getContentPane().setLayout(null);

		JLabel lblTeamMoney = new JLabel("New label");
		lblTeamMoney.setBounds(44, 44, 154, 17);
		shopWindow.getContentPane().add(lblTeamMoney);
		lblTeamMoney.setText("Money: $" + String.valueOf(team.getMoney()));
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(51, 403, 703, 17);
		shopWindow.getContentPane().add(lblError);
		if (team.isCheaperPrices() == true) {
			lblError.setText("<html><i>You have access to cheaper prices due to Iron Man's special ability.</i></html>");
		}

		JLabel lblTeamItems = new JLabel("New label");
		lblTeamItems.setBounds(600, 129, 154, 291);
		shopWindow.getContentPane().add(lblTeamItems);
		lblTeamItems.setText(team.getItemInfo());
		//Returns the team to the home base.
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(595, 431, 140, 45);
		shopWindow.getContentPane().add(btnReturn);

		JComboBox<String> comboBoxPowerups = new JComboBox();
		comboBoxPowerups.setBounds(44, 100, 220, 30);
		shopWindow.getContentPane().add(comboBoxPowerups);

		powerupList = shop.getPowerupNames();
		comboBoxPowerups.setModel(new DefaultComboBoxModel(powerupList.toArray()));

		JComboBox<String> comboBoxItems = new JComboBox();
		comboBoxItems.setBounds(44, 171, 220, 30);
		shopWindow.getContentPane().add(comboBoxItems);

		itemList = shop.getItemNames();
		comboBoxItems.setModel(new DefaultComboBoxModel(itemList.toArray()));

		JComboBox<String> comboBoxMaps = new JComboBox();
		comboBoxMaps.setBounds(44, 242, 220, 30);
		shopWindow.getContentPane().add(comboBoxMaps);

		mapList = shop.getMapNames();
		comboBoxMaps.setModel(new DefaultComboBoxModel(mapList.toArray()));
		/* Fetches the relevant item by checking the combobox index. If the team has enough money,
		 * the item is removed from the combobox, and is added to the team's inventory. If the team
		 * doesn't have enough money, or there is no item selected, an error message is displayed.*/
		JButton btnPurchaseItem = new JButton("Purchase item");
		btnPurchaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBoxItems.getSelectedIndex();
				if (index != -1) {
					int price = shop.getItems().get(index).getPrice();
					if (team.isCheaperPrices() == true) {
						price = (int) (price * 0.8);

					}
					if (team.getMoney() >= price) {
						team.alterMoney(price * -1);
						lblTeamMoney.setText("Money: $" + String.valueOf(team.getMoney()));
						HealingItems item = shop.getItems().get(index);
						team.addItem(item);
						shop.removeItem(item);
						itemList = shop.getItemNames();
						lblTeamItems.setText(team.getItemInfo());
						comboBoxItems.setModel(new DefaultComboBoxModel(itemList.toArray()));
						lblError.setText("<html><b>Item successfully purchased.</b></html>");
					} else {
						lblError.setText("<html><b>You do not have enough money to purchase this.</b></html>");
					}
				} else {
					lblError.setText("<html><b>Nothing selected.</b></html>");
				}
			}
		});
		btnPurchaseItem.setBounds(228, 431, 140, 45);
		shopWindow.getContentPane().add(btnPurchaseItem);
		/* Fetches the relevant power up by checking the combobox index. If the team has enough money,
		 * the power up is removed from the combobox, and is added to the team's inventory. If the team
		 * doesn't have enough money, or there is no power up selected, an error message is displayed.*/
		JButton btnPurchasePowerup = new JButton("Purchase powerup");
		btnPurchasePowerup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBoxPowerups.getSelectedIndex();
				if (index != -1) {
					int price = shop.getPowerUps().get(index).getPrice();
					if (team.isCheaperPrices() == true) {
						price = (int) (price * 0.8);

					}
					if (team.getMoney() >= price) {
						team.alterMoney(price * -1);
						lblTeamMoney.setText("Money: $" + String.valueOf(team.getMoney()));
						PowerUps item = shop.getPowerUps().get(index);
						team.addPowerUp(item);
						shop.removePowerUp(item);
						powerupList = shop.getPowerupNames();
						lblTeamItems.setText(team.getItemInfo());
						comboBoxPowerups.setModel(new DefaultComboBoxModel(powerupList.toArray()));
						lblError.setText("<html><b>Item successfully purchased.</b></html>");
					} else {
						lblError.setText("<html><b>You do not have enough money to purchase this.</b></html>");
					}
				} else {
					lblError.setText("<html>Nothing selected.</b></html>");
				}
			}
		});
		btnPurchasePowerup.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnPurchasePowerup.setBounds(44, 432, 140, 45);
		shopWindow.getContentPane().add(btnPurchasePowerup);
		/* Fetches the relevant map by checking the combobox index. If the team has enough money,
		 * the map is removed from the combobox, and is added to the team's inventory. If the team
		 * doesn't have enough money, or there is no map selected, an error message is displayed.*/
		JButton btnPurchaseMap = new JButton("Purchase map");
		btnPurchaseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBoxMaps.getSelectedIndex();
				if (index != -1) {
					int price = shop.getMaps().get(index).getPrice();
					if (team.isCheaperPrices() == true) {
						price = (int) (price * 0.8);

					}
					if (team.getMoney() >= price) {
						team.alterMoney(price * -1);
						lblTeamMoney.setText("Money: $" + String.valueOf(team.getMoney()));
						Maps item = shop.getMaps().get(index);
						team.addMap(item);
						shop.removeMap(item);
						mapList = shop.getMapNames();
						lblTeamItems.setText(team.getItemInfo());
						comboBoxMaps.setModel(new DefaultComboBoxModel(mapList.toArray()));
						lblError.setText("<html><b>Item successfully purchased.</b></html>");
					} else {
						lblError.setText("<html><b>You do not have enough money to purchase this.</b></html>");
					}
				} else {
					lblError.setText("<html><b>Nothing selected.</b></html>");
				}
			}
		});
		btnPurchaseMap.setBounds(404, 431, 140, 45);
		shopWindow.getContentPane().add(btnPurchaseMap);

		JLabel lblDisplayInfo = new JLabel("");
		lblDisplayInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayInfo.setBounds(290, 100, 263, 102);
		shopWindow.getContentPane().add(lblDisplayInfo);

		JLabel lblItemTag = new JLabel("<html><b>Items:</b></html>");
		lblItemTag.setBounds(81, 365, 55, 17);
		shopWindow.getContentPane().add(lblItemTag);

		JLabel lblPowerupTag = new JLabel("<html><b>Powerups:</b></html>");
		lblPowerupTag.setBounds(80, 303, 92, 17);
		shopWindow.getContentPane().add(lblPowerupTag);

		//Buttons for the 6 items in the game. When clicked, the item's information is displayed.
		JButton btnAether = new JButton("Aether");
		btnAether.addActionListener(new ActionListener() {
			PowerUps aeth = new Aether();

			public void actionPerformed(ActionEvent e) {
				if (powerupList.contains("Aether")) {
					lblDisplayInfo.setText(aeth.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}

			}
		});
		btnAether.setBounds(184, 294, 110, 35);
		shopWindow.getContentPane().add(btnAether);

		JButton btnTesseract = new JButton("Tesseract");
		btnTesseract.addActionListener(new ActionListener() {
			PowerUps tess = new Tesseract();

			public void actionPerformed(ActionEvent e) {
				if (powerupList.contains("Tesseract")) {
					lblDisplayInfo.setText(tess.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}
			}
		});
		btnTesseract.setBounds(335, 294, 110, 35);
		shopWindow.getContentPane().add(btnTesseract);

		JButton btnNewButton = new JButton("Gauntlet");
		btnNewButton.addActionListener(new ActionListener() {
			PowerUps gauntlet = new Gauntlet();

			public void actionPerformed(ActionEvent e) {
				if (powerupList.contains("Gauntlet")) {
					lblDisplayInfo.setText(gauntlet.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}
			}
		});
		btnNewButton.setBounds(482, 294, 110, 35);
		shopWindow.getContentPane().add(btnNewButton);

		JButton btnSmall = new JButton("Small potion");
		btnSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HealingItems small = new PotionSmall();
				if (itemList.contains("Small Potion")) {
					lblDisplayInfo.setText(small.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}
			}
		});
		btnSmall.setBounds(184, 356, 110, 35);
		shopWindow.getContentPane().add(btnSmall);

		JButton btnMedium = new JButton("Medium potion");
		btnMedium.addActionListener(new ActionListener() {
			HealingItems medium = new PotionMedium();

			public void actionPerformed(ActionEvent e) {
				if (itemList.contains("Medium Potion")) {
					lblDisplayInfo.setText(medium.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}

			}
		});
		btnMedium.setBounds(330, 356, 115, 35);
		shopWindow.getContentPane().add(btnMedium);

		JButton btnBean = new JButton("Senzu bean");
		btnBean.addActionListener(new ActionListener() {
			HealingItems bean = new SenzuBean();

			public void actionPerformed(ActionEvent e) {
				if (itemList.contains("Senzu Bean")) {
					lblDisplayInfo.setText(bean.getInfo());
				} else {
					lblDisplayInfo.setText("This shop does not contain that item.");
				}
			}
		});
		btnBean.setBounds(482, 356, 110, 35);
		shopWindow.getContentPane().add(btnBean);

		JLabel lblWelcomeMessage = new JLabel("New label");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setBounds(184, 18, 373, 43);
		shopWindow.getContentPane().add(lblWelcomeMessage);

		lblWelcomeMessage.setText("<html><center> You are in the shop in <b>" + city.getCityName()
				+ "</b>.<BR>Here you can purchase items, powerups, and maps.</center></html>");

		JLabel lblPowerTag = new JLabel("<html><u>Powerups:</u></html>");
		lblPowerTag.setBounds(44, 72, 92, 17);
		shopWindow.getContentPane().add(lblPowerTag);

		JLabel lblItemtag2 = new JLabel("<html><u>Healing items:</u></html>");
		lblItemtag2.setBounds(44, 142, 115, 17);
		shopWindow.getContentPane().add(lblItemtag2);

		JLabel lblMapTag2 = new JLabel("<html><u>City maps($50):</u></html>");
		lblMapTag2.setBounds(44, 213, 98, 17);
		shopWindow.getContentPane().add(lblMapTag2);
		
		JLabel picture = new JLabel("");
		picture.setIcon(new ImageIcon(DenScreen.class.getResource("/Images/shop.png")));
		picture.setBounds(606, 18, 130, 130);
		shopWindow.getContentPane().add(picture);
	}
}
