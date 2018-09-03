package gui;
import javax.swing.*;
import game.*;
import healingitems.*;
import powerups.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;

/**
 * GUI screen for the home base of a city.
 * This is the central location the team starts in, and must return to between locations.
 * Team status can be viewed, maps can be used, and the four map locations can be travelled to.
 * @author vsh33
 */
public class HomeBaseScreen {
	/**
	 * The main frame
	 */
	private JFrame homeWindow;
	/**
	 * The Game Manager, holds all the information on the team and the city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * City attribute, holds the information of the current city
	 */
	private Cities city;
	/**
	 * Team attribute, holds the information of the team
	 */
	private Teams team;
	
	/**
	 * Creates a HomeBaseScreen.
	 * @param incomingGameEnv the game manager which stores information about the team and city.
	 */
	public HomeBaseScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		city = gameEnv.getCurrentCity();
		team = gameEnv.getTeam();
		initialize();
		homeWindow.setLocationRelativeTo(null);
		homeWindow.setVisible(true);
	}

	public void closeWindow() {
		homeWindow.dispose();
	}

	public void finishedWindow(String nextScreen) {
		gameEnv.closeHomeBaseScreen(this, nextScreen);
	}

	private void initialize() {
		homeWindow = new JFrame();
		homeWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		homeWindow.setBackground(Color.LIGHT_GRAY);
		homeWindow.setTitle("Home Base");
		homeWindow.setBounds(100, 100, 676, 586);
		homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeWindow.getContentPane().setLayout(null);

		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(76, 437, 533, 25);
		homeWindow.getContentPane().add(lblMessage);
		
		if (team.isClairvoyance()) {
			lblMessage.setText("<html><i>Your team has access to the map at all times due to Doctor Strange.</i></html>");
		}
		
		//Random events. The first 2 correspond to getting robbed, the third and fourth gift items.
		Random generator = new Random();
		Random itemTaker = new Random();
		int randomEvent = generator.nextInt(20);
		if (randomEvent == 0 && team.getItemList().size() > 0) {
			int itemSize = team.getItemList().size();
			int steal = itemTaker.nextInt(itemSize);
			lblMessage.setText(team.getItemList().get(steal).getName()
					+ " has been removed from your inventory.");
			team.removeItem(team.getItemList().get(steal));
			JOptionPane.showMessageDialog(homeWindow, "<html><b>You have been robbed!</b></html>");
			
		} else if (randomEvent == 1 && team.getPowerUpList().size() > 0) {
			int powerSize = team.getPowerUpList().size();
			int steal = itemTaker.nextInt(powerSize);
			lblMessage.setText(team.getPowerUpList().get(steal).getName()
					+ " has been removed from your inventory.");
			team.removePowerUp(team.getPowerUpList().get(steal));
			JOptionPane.showMessageDialog(homeWindow, "<html><b>You have been robbed.</b></html>");
		} else if (randomEvent == 2) {
			int giveItem = itemTaker.nextInt(3);
			if (giveItem == 0) {
				HealingItems small = new PotionSmall();
				team.addItem(small);
			} else if (giveItem == 1) {
				HealingItems medium = new PotionMedium();
				team.addItem(medium);
			} else {
				HealingItems bean = new SenzuBean();
				team.addItem(bean);
			}
			lblMessage.setText("<html><i>Random item added to inventory.</i></html>");
			JOptionPane.showMessageDialog(homeWindow, "<html><b>You have been gifted a random item.</b></html>");
		} else if (randomEvent == 3) {
			int giveItem = itemTaker.nextInt(3);
			if (giveItem == 0) {
				PowerUps tess = new Tesseract();
				team.addPowerUp(tess);
			} else if (giveItem == 1) {
				PowerUps aeth = new Aether();
				team.addPowerUp(aeth);
			} else {
				PowerUps gauntlet = new Gauntlet();
				team.addPowerUp(gauntlet);
			}
			lblMessage.setText("<html><i>Random item added to inventory.</i></html>");
			JOptionPane.showMessageDialog(homeWindow, "<html><b>You have been gifted a random powerup.</b></html>");
		}

		JLabel lblWelcomeMsg = new JLabel("New label");
		lblWelcomeMsg.setBounds(46, 166, 340, 15);
		homeWindow.getContentPane().add(lblWelcomeMsg);
		lblWelcomeMsg.setText("<html>You are in your home base in <b>" + city.getCityName() + "</b>.</html>");

		JLabel lblInstructions = new JLabel("<html><u>Choose a direction to travel in:</u></html>");
		lblInstructions.setBounds(46, 193, 294, 15);
		homeWindow.getContentPane().add(lblInstructions);

		JLabel lblTeamStats = new JLabel("<html><b>Team status:</b></html>");
		lblTeamStats.setBounds(411, 146, 251, 329);
		homeWindow.getContentPane().add(lblTeamStats);

		JButton btnViewTeam = new JButton("View team status");
		btnViewTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTeamStats.setText(team.getTeamInfo());
			}
		});
		btnViewTeam.setBounds(124, 476, 180, 60);
		homeWindow.getContentPane().add(btnViewTeam);

		JButton btnSouth = new JButton("South");
		btnSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextLocation = city.getSouth();
				finishedWindow(nextLocation);
			}
		});
		btnSouth.setBounds(147, 375, 80, 50);
		homeWindow.getContentPane().add(btnSouth);

		JButton btnWest = new JButton("West");
		btnWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextLocation = city.getWest();
				finishedWindow(nextLocation);
			}
		});
		btnWest.setBounds(61, 302, 80, 50);
		homeWindow.getContentPane().add(btnWest);

		JButton btnEast = new JButton("East");
		btnEast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextLocation = city.getEast();
				finishedWindow(nextLocation);
			}
		});
		btnEast.setBounds(238, 302, 80, 50);
		homeWindow.getContentPane().add(btnEast);

		JButton btnNorth = new JButton("North");
		btnNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextLocation = city.getNorth();
				finishedWindow(nextLocation);
			}
		});
		btnNorth.setBounds(147, 229, 80, 50);
		homeWindow.getContentPane().add(btnNorth);
		
		/*If the team has a map of the current city, the labels for the direction buttons
		are changed to their corresponding locations.*/
		JButton btnUseMap = new JButton("Use city map");
		btnUseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.hasMap(city.getCityName())) {
					// finishedWindow("Map");
					btnNorth.setText(city.getNorth());
					btnEast.setText(city.getEast());
					btnSouth.setText(city.getSouth());
					btnWest.setText(city.getWest());
				} else {
					lblMessage.setText("<html><b>You do not have a map of this city in your inventory.</b></html>");
				}
			}
		});
		btnUseMap.setBounds(365, 476, 180, 60);
		homeWindow.getContentPane().add(btnUseMap);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(HomeBaseScreen.class.getResource("/Images/Banner2.jpg")));
		lblBanner.setBounds(46, 22, 580, 120);
		homeWindow.getContentPane().add(lblBanner);
	}

}
