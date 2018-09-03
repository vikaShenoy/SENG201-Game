package gui;
import superheroes.*;
import supervillains.*;
import game.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
/**
 * GUI screen which enables the user to play a number guessing game against the city villain.
 * The hero gets two chances to guess the villain number, with a hint in between.
 * Damage is applied to the loser of the battle.
 * @author dle70
 *
 */
public class GuessNumberScreen {
	/**
	 * Holds the villain guess in Guess Number game
	 */
	private int villainNum;
	/**
	 * Holds the hero guess in Guess Number game
	 */
	private int heroNum;
	/**
	 * the counter for guess, maximum 2 allowed
	 */
	private int guesses = 0;
	/**
	 * The main frame
	 */
	private JFrame gnWindow;
	/**
	 * The Game Manager, holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds the important information of the team
	 */
	private Teams team;
	/**
	 * Hero attribute, holds the selected hero to fight the villain in Guess Number Game
	 */
	private SuperHeroes combatHero;
	/**
	 * City attribute, holds important information on the current city
	 */
	private Cities city;
	/**
	 * Villain attribute, holds the villain to fight the hero in Guess Number game
	 */
	private SuperVillains villain;
	/**
	 * ActionListener used to delay the exiting of the game screen
	 */
	private ActionListener delay;
	/**
	 * Time attribute, the time delay of winning a game and closing the screen
	 */
	private Timer delayTimer;
	/**
	 * Creates a GuessNumberScreen. 
	 * @param incomingGameEnv the game managers, which holds info on team and city.
	 */
	public GuessNumberScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		combatHero = team.getCombatHero();
		villain = gameEnv.getCurrentCity().getVillain();
		initialize();
		gnWindow.setLocationRelativeTo(null);
		gnWindow.setVisible(true);
	}

	public void closeWindow() {
		gnWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeGuessNumberScreen(this);
	}

	private void initialize() {
		gnWindow = new JFrame();
		gnWindow.setTitle("Guess Number Battle");
		gnWindow.setBounds(100, 100, 569, 397);
		gnWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gnWindow.getContentPane().setLayout(null);

		JLabel lblInitialMessage = new JLabel("New label");
		lblInitialMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblInitialMessage.setBounds(90, 12, 363, 87);
		gnWindow.getContentPane().add(lblInitialMessage);
		lblInitialMessage.setText(
				"<html><center>The battle is called <b>Guess Number Between 1 - 10.</b>"
				+ "<BR><BR>Guess the higher number to win!</center></html>");

		JLabel lblHeroLabel = new JLabel("Hero's Guess:");
		lblHeroLabel.setBounds(90, 152, 110, 15);
		gnWindow.getContentPane().add(lblHeroLabel);

		JLabel lblVillainLabel = new JLabel("Villains Response:");
		lblVillainLabel.setBounds(328, 152, 137, 15);
		gnWindow.getContentPane().add(lblVillainLabel);

		JComboBox herosNumCombo = new JComboBox();
		herosNumCombo.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		herosNumCombo.setBounds(90, 179, 78, 31);
		gnWindow.getContentPane().add(herosNumCombo);

		herosNumCombo.setBounds(114, 179, 54, 31);
		gnWindow.getContentPane().add(herosNumCombo);

		JLabel lblVillainsAnswer = new JLabel("");
		lblVillainsAnswer.setBounds(271, 179, 230, 31);
		gnWindow.getContentPane().add(lblVillainsAnswer);
		lblVillainsAnswer.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMessage = new JLabel("");

		lblMessage.setBounds(135, 260, 283, 30);
		gnWindow.getContentPane().add(lblMessage);

		lblMessage.setBounds(135, 260, 283, 15);
		gnWindow.getContentPane().add(lblMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Generates a random integer for the villain's chosen number, between 1-10.
		Random generator = new Random();
		villainNum = generator.nextInt(9) + 1;
		//Delay to close the screen automatically after the game result is displayed.
		delay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delayTimer.stop();
				finishedWindow();
			}
		};
		/* The hero's guess is taken from the combobox. If first time, a hint is given 
		 * of higher/lower. If number is guessed correctly, hero wins, otherwise villain wins.
		 * Damage is applied and screen closes.*/
		JButton btnGuess = new JButton("Guess");
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guesses++;
				heroNum = herosNumCombo.getSelectedIndex() + 1;
				// lost
				if (guesses >= 2 && (heroNum < villainNum || heroNum > villainNum)) {
					lblMessage.setText("You lose!");
					lblVillainsAnswer.setText("Loser");
					int damage = combatHero.takeVillainDamage(villain);
					JOptionPane.showMessageDialog(null, combatHero.getName() + " took " + damage + " damage.");
					btnGuess.setVisible(false);
					delayTimer = new Timer(400, delay);
					delayTimer.start();
					// won:
				} else if (heroNum == villainNum) {
					lblMessage.setText("You win!");
					villain.loseLife(combatHero);
					JOptionPane.showMessageDialog(null, villain.getName() + " lost life(s).");
					btnGuess.setVisible(false);
					delayTimer = new Timer(400, delay);
					delayTimer.start();
				} else if (guesses < 2 && heroNum < villainNum) {
					lblVillainsAnswer.setText("My number is higher.");
				} else if (guesses < 2 && heroNum > villainNum) {
					lblVillainsAnswer.setText("My number is lower.");
				}
			}

		});
		btnGuess.setBounds(221, 302, 117, 40);
		gnWindow.getContentPane().add(btnGuess);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GuessNumberScreen.class.getResource("/Images/Wallpaper3.jpg")));
		label.setBounds(-135, -64, 741, 445);
		gnWindow.getContentPane().add(label);
	}
}
