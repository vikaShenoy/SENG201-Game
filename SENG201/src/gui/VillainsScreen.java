package gui;
import java.util.*;
import javax.swing.*;
import game.*;
import superheroes.*;
import supervillains.*;
import java.awt.event.*;
import java.awt.Color;
/**
 * GUI screen for battling the villain. The user can check the health of their heroes.
 * The villain's information is displayed. The user chooses a hero to fight with, and then the battles begin.
 * This screen is returned to after battles. The screen displays end game message if neccesary, or progresses
 * the user to the next city.
 * @author dle70.
 */
public class VillainsScreen {
	/**
	 * The main frame
	 */
	private JFrame villainWindow;
	/**
	 * The game manager, holds important information on team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Hero team attribute, holds important info of the team
	 */
	private Teams team;
	/**
	 * City attribute, holds important information on the current city
	 */
	private Cities city;
	/**
	 * Villain attribute, holds the current villain fighting the hero
	 */
	private SuperVillains villain;
	/**
	 * Hero attribute, holds the current hero fighting the villain
	 */
	private SuperHeroes combatHero;
	/**
	 * Hero options, holds a list of heros able to fight the current villain
	 */
	private ArrayList heroOptions = new ArrayList<String>();
	
	/**
	 * Creates a VillainsScreen.
	 * @param incomingGameEnv game manager which holds info on the team and city.
	 */
	public VillainsScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		city = gameEnv.getCurrentCity();
		villain = city.getVillain();
		combatHero = team.getCombatHero();
		initialize();
		villainWindow.setLocationRelativeTo(null);
		villainWindow.setVisible(true);
	}

	public void closeWindow() {
		villainWindow.dispose();
	}

	public void finishedWindow(String nextScreen) {
		gameEnv.closeVillainsScreen(this, nextScreen);
	}

	private void initialize() {

		villainWindow = new JFrame(villain.getName() + "'s Lair");
		villainWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		villainWindow.setBackground(Color.LIGHT_GRAY);
		villainWindow.setBounds(100, 100, 521, 399);
		villainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		villainWindow.getContentPane().setLayout(null);

		heroOptions = generateHeroNames();

		JComboBox comboBoxHero = new JComboBox();
		comboBoxHero.setModel(new DefaultComboBoxModel(heroOptions.toArray()));
		comboBoxHero.setBounds(152, 232, 220, 30);
		villainWindow.getContentPane().add(comboBoxHero);
		
		JLabel lblHeroHealth = new JLabel("");
		lblHeroHealth.setBounds(397, 239, 90, 15);
		villainWindow.getContentPane().add(lblHeroHealth);

		JLabel lblMessage = new JLabel("<html><b>Choose your brave hero to fight!</b></html>");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(70, 150, 359, 77);
		villainWindow.getContentPane().add(lblMessage);
		
		JLabel lblWelcomeMessage = new JLabel("");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setBounds(102, 25, 288, 125);
		villainWindow.getContentPane().add(lblWelcomeMessage);
		lblWelcomeMessage.setText("<html><center>You have entered <b>" + city.getVillain().getName() + "</b>'s Lair. "
				+ "<BR><BR><b> " + villain.getName() + ": " + villain.getTaunt() + "</b><BR><BR>Lives remaining: "
				+ villain.getLives() + "</center></html>");
		
		JLabel lblhero = new JLabel("<html><u>Hero:</b></html>");
		lblhero.setHorizontalAlignment(SwingConstants.CENTER);
		lblhero.setBounds(85, 239, 55, 17);
		villainWindow.getContentPane().add(lblhero);
		
		JButton healthBtn = new JButton("Check health");
		healthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int health = team.getHeroList().get(comboBoxHero.getSelectedIndex()).getHealth();
				lblHeroHealth.setText("Health: " + health);
			}
		});
		healthBtn.setBounds(289, 308, 140, 45);
		villainWindow.getContentPane().add(healthBtn);
		/* If the game is over, this button quits the game. 
		 * If there are more cities, the button leads to an enter city screen.
		 * Otherwise, the relevant hero is set to combatHero, and a battle screen is opened for one of the villain's games.*/
		JButton btnBattle = new JButton("Battle");
		btnBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((villain.getLives() == 0 && villain.isBoss()) || team.getHeroList().size() == 0) {
					finishedWindow("Finish");
				} else if (villain.getLives() == 0) {
					finishedWindow("Next");
				} else {
					//Begin playing a minigame.
					String nextScreen = "";
					int size = villain.getGames().size();
					Random generator = new Random();
					int gameNum = generator.nextInt(size);
					nextScreen = villain.getGames().get(gameNum);
					healthBtn.setVisible(false);
					team.setCombatHero(team.getHeroList().get(comboBoxHero.getSelectedIndex()));
					finishedWindow(nextScreen);
				}
			}
		});
		btnBattle.setBounds(88, 308, 140, 45);
		villainWindow.getContentPane().add(btnBattle);
		
		JLabel lblVictoryPic = new JLabel("");
		lblVictoryPic.setBounds(122, 150, 280, 140);
		villainWindow.getContentPane().add(lblVictoryPic);
		
		//Hero just died in battle case.
		if (combatHero != null && combatHero.getHealth() == 0) {
			lblMessage.setText("<html><center>" + combatHero.getName() + " has perished at the hands of "
					+ villain.getName() + ".<BR>Select another hero to continue.</center></html>");
			team.removeHero(combatHero);
			heroOptions = generateHeroNames();
			comboBoxHero.setModel(new DefaultComboBoxModel(heroOptions.toArray()));
			//Team has no heroes left and the game is over.
			if (heroOptions.size() == 0) {
				long time = (System.currentTimeMillis() - gameEnv.getStartTime()) / 1000;
				lblVictoryPic.setIcon(new ImageIcon(VillainsScreen.class.getResource("/Images/Defeatb.jpg")));
				lblMessage.setVisible(false);
				lblhero.setVisible(false);
				comboBoxHero.setVisible(false);
				healthBtn.setVisible(false);
				lblWelcomeMessage.setText("<html><center>" + combatHero.getName()
						+ " has perished. Your team is out of heroes.<BR><BR>Game over.<BR><BR>Time: " + time
						+ "s.");
				btnBattle.setText("Quit");
			}
		}
		
		//Defeated the city's villain.
		//Won the game case.
		if (villain.getLives() == 0) {
			if (villain.isBoss()) {
				lblVictoryPic.setIcon(new ImageIcon(VillainsScreen.class.getResource("/Images/VictoryPic2b.jpg")));
				btnBattle.setText("Finish game");
				long timeTaken = (System.currentTimeMillis() - gameEnv.getStartTime()) / 1000;
				lblWelcomeMessage.setText("<html><center>You have defeated the final villain " + villain.getName()
						+ "!<BR><BR>Congratulations on completing this epic quest.<BR><BR>Team name: " + team.getName()
						+ "<BR><BR>Time taken: " + timeTaken + "s.</center></html>");
				lblMessage.setVisible(false);
				lblhero.setVisible(false);
				healthBtn.setVisible(false);
				comboBoxHero.setVisible(false);
				
				//More cities to complete case. Make the new city and iterate over cities list.
			} else {
				lblVictoryPic.setIcon(new ImageIcon(VillainsScreen.class.getResource("/Images/VictoryPic1b.jpg")));
				team.setNumCities(team.getNumCities() - 1);
				team.setMoney(team.getMoney() + 200);
				Cities nextCity = gameEnv.getCityList().get(team.getNumCities() - 1);
				Shop nextShop = new Shop(gameEnv);
				nextCity.setCityShop(nextShop);
				gameEnv.setCurrentCity(nextCity);
				btnBattle.setHorizontalAlignment(SwingConstants.CENTER);
				btnBattle.setText("Next city");
				lblWelcomeMessage.setText("<html><center>You have defeated <b>" + villain.getName()
						+ "</b> granting your team an extra $200. <BR><BR>Move on to the next city.</center></html>");
				lblMessage.setVisible(false);
				lblhero.setVisible(false);
				comboBoxHero.setVisible(false);
				healthBtn.setVisible(false);
			}
		}
		
	}
	/** 
	 * Creates an ArrayList of names of all the heroes in the team.
	 * This is used in the combobox, so the user can select a hero to battle with.
	 * @return options - List of hero's names in the team.
	 */
	public ArrayList<String> generateHeroNames() {
		ArrayList<SuperHeroes> heroList = team.getHeroList();
		ArrayList<String> options = new ArrayList<String>();
		for (SuperHeroes hero : heroList) {
			options.add(hero.getName());
		}
		return options;
	}
}
