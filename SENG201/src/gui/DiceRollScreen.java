package gui;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import game.*;
import superheroes.*;
import supervillains.*;

/**
 * GUI screen for playing a minigame vs the city villain.
 * Dice rolls are random, the winner is the one with the hgiher roll.
 * The loser takes damage.
 * @author dle70.
 *
 */
public class DiceRollScreen {
	/**
	 *  The main frame
	 */
	private JFrame drWindow;
	/**
	 * The Game Manager, holds all the important information on the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds important information on the team
	 */
	private Teams team;
	/**
	 * City attribute, holds important information on the current city
	 */
	private Cities city;
	/**
	 * Villain attribute, holds the current villain fighting the hero in Dice Roll Game
	 */
	private SuperVillains villain;
	/**
	 * Hero attribute, holds the current hero fighting the villain in Dice Roll Game
	 */
	private SuperHeroes combatHero;
	/**
	 * ActionListener used to delay the existing of the game screen
	 */
	private ActionListener delay;
	/**
	 * Time attribute, the time delay of winning a game and closing the screen
	 */
	private Timer delayTimer;
	
	
	/**
	 * Creates a DiceRollScreen.
	 * @param incomingGameEnv the game manager, which contains info on the team and city.
	 */
	public DiceRollScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		villain = gameEnv.getCurrentCity().getVillain();
		combatHero = team.getCombatHero();
		initialize();
		drWindow.setLocationRelativeTo(null);
		drWindow.setVisible(true);
	}

	public void closeWindow() {
		drWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeDiceRollScreen(this);
	}

	private void initialize() {
		drWindow = new JFrame();
		drWindow.setTitle("Dice Roll Battle");
		drWindow.setBounds(100, 100, 569, 397);
		drWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drWindow.getContentPane().setLayout(null);

		JLabel lblInitialMessage = new JLabel("New label");
		lblInitialMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblInitialMessage.setBounds(90, 6, 363, 87);
		drWindow.getContentPane().add(lblInitialMessage);
		lblInitialMessage.setText(
				"<html><center>The battle is called <b>Dice Roll.</b><BR><BR>Roll the higher number to win!</center></html>");

		JLabel lblHeroLabel = new JLabel("Hero's Roll:");
		lblHeroLabel.setBounds(103, 152, 97, 15);
		drWindow.getContentPane().add(lblHeroLabel);

		JLabel lblVillainLabel = new JLabel("Villains Roll:");
		lblVillainLabel.setBounds(359, 152, 94, 15);
		drWindow.getContentPane().add(lblVillainLabel);

		JLabel lblHerosRoll = new JLabel("");
		lblHerosRoll.setBounds(135, 179, 24, 31);
		drWindow.getContentPane().add(lblHerosRoll);

		JLabel lblVillainsRoll = new JLabel("");
		lblVillainsRoll.setBounds(394, 179, 24, 31);
		drWindow.getContentPane().add(lblVillainsRoll);

		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(135, 222, 283, 25);
		drWindow.getContentPane().add(lblMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		//Delay to close the screen automatically after the game result is displayed.
		delay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delayTimer.stop();
				finishedWindow();
			}
		};
		/* Random numbers between 1-6 are generated for the hero and villain.
		 * The winner is the one with the higher roll. The loser takes damage accordingly,
		 * and the screen closes, returning to the villain's screen.*/
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random generator = new Random();
				int heroRoll = generator.nextInt(6) + 1;
				int villainRoll = 0;
				if (combatHero.hasIncreasedOdds() == true) {
					villainRoll = generator.nextInt(3) + 1;
				} else {
					villainRoll = generator.nextInt(6) + 1;
				}
				lblHerosRoll.setText(Integer.toString(heroRoll));
				lblVillainsRoll.setText(Integer.toString(villainRoll));
				if (heroRoll > villainRoll) {
					lblMessage.setText(combatHero.getName() + " wins!");
					villain.loseLife(combatHero);
					JOptionPane.showMessageDialog(null, villain.getName() + " lost life(s).");
				} else if (villainRoll > heroRoll) {
					lblMessage.setText(villain.getName() + " wins!");
					int damage = combatHero.takeVillainDamage(villain);
					JOptionPane.showMessageDialog(null, combatHero.getName() + " took " + damage + " damage.");
				} else if (heroRoll == villainRoll) {
					lblMessage.setText("Tie! No damage taken/given.");
				}
				delayTimer = new Timer(400, delay);
				delayTimer.start();
				btnRoll.setVisible(false);
			}
		});
		btnRoll.setBounds(221, 302, 117, 40);
		drWindow.getContentPane().add(btnRoll);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DiceRollScreen.class.getResource("/Images/Wallpaper3.jpg")));
		lblNewLabel.setBounds(-135, -69, 780, 434);
		drWindow.getContentPane().add(lblNewLabel);
	}
}
