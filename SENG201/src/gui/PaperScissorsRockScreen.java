package gui;
import java.awt.event.*;
import java.util.Random;
import superheroes.*;
import game.*;
import supervillains.SuperVillains;
import javax.swing.*;
/**
 * GUI screen for the paper-scissors-rock game the user plays against the villain.
 * The user chooses their move, and the villains is randomly generated.
 * The loser takes damage, and the user is returned to the VillainScreen.
 * @author dle70.
 *
 */
public class PaperScissorsRockScreen {
	/**
	 * The main frame
	 */
	private JFrame psrWindow;
	/**
	 * The game manager, holds important information on team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Hero team attribute, holds important info of the team
	 */
	private Teams team;
	/**
	 * Villain attribute, holds the villain playing against the hero in Paper Scissors Rock
	 */
	private SuperVillains villain;
	/**
	 * Hero attribute, holds the hero playing against the villain in Paper Scissors Rock
	 */
	private SuperHeroes combatHero;
	/**
	 * ActionListener used to delay the exiting of the game screen
	 */
	private ActionListener delay;
	/**
	 * Time attribute, the time delay of winning a game and closing the screen
	 */
	private Timer delayTimer;
	
	/**
	 * Creates a PaperScissorsRockScreen. 
	 * @param incomingGameEnv the game manager which holds info on team and city.
	 */
	public PaperScissorsRockScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		combatHero = team.getCombatHero();
		villain = gameEnv.getCurrentCity().getVillain();
		initialize();
		psrWindow.setLocationRelativeTo(null);
		psrWindow.setVisible(true);
	}

	public void closeWindow() {
		psrWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closePaperScissorsRockScreen(this);
	}

	private void initialize() {
		psrWindow = new JFrame();
		psrWindow.setTitle("Paper Scissors Rock");
		psrWindow.setBounds(100, 100, 569, 397);
		psrWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		psrWindow.getContentPane().setLayout(null);

		JLabel lblInitialMessage = new JLabel("New label");
		lblInitialMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblInitialMessage.setBounds(90, 12, 363, 87);
		psrWindow.getContentPane().add(lblInitialMessage);
		lblInitialMessage.setText(
				"<html><center>The battle is called <b>Paper Scissors Rock.</b><BR><BR>Throw the hand that defeats the other to win!</center></html>");

		JLabel lblHeroLabel = new JLabel("Hero's Hand:");
		lblHeroLabel.setBounds(103, 152, 97, 15);
		psrWindow.getContentPane().add(lblHeroLabel);

		JLabel lblVillainLabel = new JLabel("Villains Hand");
		lblVillainLabel.setBounds(359, 152, 94, 15);
		psrWindow.getContentPane().add(lblVillainLabel);

		JComboBox herosHandCombo = new JComboBox();
		herosHandCombo.setModel(new DefaultComboBoxModel(new String[] { "Paper", "Scissors", "Rock" }));
		herosHandCombo.setBounds(90, 179, 97, 31);
		psrWindow.getContentPane().add(herosHandCombo);

		herosHandCombo.setBounds(103, 179, 97, 31);
		psrWindow.getContentPane().add(herosHandCombo);

		JLabel lblVillainsHand = new JLabel("");
		lblVillainsHand.setBounds(280, 179, 230, 31);
		psrWindow.getContentPane().add(lblVillainsHand);
		lblVillainsHand.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(135, 260, 283, 15);
		psrWindow.getContentPane().add(lblMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		//Delay between displaying the game result and closing the screen.
		delay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delayTimer.stop();
				finishedWindow();
			}
		};
		/* Gets the selected move from the user's combobox selection. The villain's move is 
		 * randomly generated. The two are compared, and damage is applied to the loser. 
		 * In the case of ties, no one is damaged. The game returns to the VillainScreen. */
		JButton btnHand = new JButton("Throw Hand");
		btnHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random generator = new Random();
				int handNum = generator.nextInt(3);
				String heroHand = (String) herosHandCombo.getSelectedItem();
				String[] hands = new String[] { "Paper", "Scissors", "Rock" };
				String villainHand = hands[handNum];
				lblVillainsHand.setText(villainHand);

				if ((heroHand == "Scissors" && villainHand == "Paper") || (heroHand == "Paper" && villainHand == "Rock")
						|| (heroHand == "Rock" && villainHand == "Scissors")) {
					lblMessage.setText(combatHero.getName() + " wins!");
					villain.loseLife(combatHero);
					JOptionPane.showMessageDialog(null, villain.getName() + " lost life(s).");
				} else if ((villainHand == "Scissors" && heroHand == "Paper")
						|| (villainHand == "Paper" && heroHand == "Rock")
						|| (villainHand == "Rock" && heroHand == "Scissors")) {
					lblMessage.setText(villain.getName() + " wins!");
					int damage = combatHero.takeVillainDamage(villain);
					JOptionPane.showMessageDialog(null, combatHero.getName() + " took " + damage + " damage.");
				} else if ((heroHand == "Scissors" && villainHand == "Scissors")
						|| (heroHand == "Rock" && villainHand == "Rock")
						|| (heroHand == "Paper" && villainHand == "Paper")) {
					lblMessage.setText("Hero and Villain tie!");
				}
				btnHand.setVisible(false);
				delayTimer = new Timer(400, delay);
				delayTimer.start();
			}
		});
		btnHand.setBounds(221, 302, 130, 40);
		psrWindow.getContentPane().add(btnHand);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PaperScissorsRockScreen.class.getResource("/Images/Wallpaper3.jpg")));
		label.setBounds(-100, -62, 737, 427);
		psrWindow.getContentPane().add(label);
	}
}
