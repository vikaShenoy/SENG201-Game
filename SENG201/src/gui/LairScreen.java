package gui;
import javax.swing.*;
import game.*;
import supervillains.*;
import java.awt.event.*;
import java.awt.Color;
/**
 * GUI screen for the entrance to the villain's lair. The user can either enter,
 * or return to home base and continue to explore the city.
 * @author dle70
 */
public class LairScreen {
	/**
	 * The main frame
	 */
	private JFrame lairWindow;
	/**
	 * The Game Manager, holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds the important information of the team
	 */
	private Teams team;
	/**
	 * City attribute, holds the important information of the current city
	 */
	private Cities city;
	/**
	 * Villain attribute, holds the current villain of the lair
	 */
	private SuperVillains villain;
	
	/**
	 * Creates a LairScreen.
	 * @param incomingGameEnv the game manager, which holds info on team and city.
	 */
	public LairScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		city = gameEnv.getCurrentCity();
		villain = city.getVillain();
		initialize();
		lairWindow.setLocationRelativeTo(null);
		lairWindow.setVisible(true);
	}

	public void closeWindow() {
		lairWindow.dispose();
	}

	public void finishedWindow(int next) {
		gameEnv.closeLairScreen(this, next);
	}

	private void initialize() {
		lairWindow = new JFrame();
		lairWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		lairWindow.setBackground(Color.LIGHT_GRAY);
		lairWindow.setTitle("Villain's Lair");
		lairWindow.setBounds(100, 100, 530, 454);
		lairWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lairWindow.getContentPane().setLayout(null);

		JLabel lblWelcomeMessage = new JLabel("New label");
		lblWelcomeMessage.setBounds(90, 215, 331, 110);
		lairWindow.getContentPane().add(lblWelcomeMessage);
		lblWelcomeMessage.setText("<html><center>You are at the entrance to the lair of <b>" + city.getVillain().getName()
				+ "</b>."
				+ "<BR><BR> Turn back or enter to begin combat. <BR><BR><i>The city cannot be cleared until the villain is defeated.</i></center></html>");

		if (villain.isBoss() == true) {
			lblWelcomeMessage.setText("<html><center>You are at the entrance to the lair of "
					+ city.getVillain().getName() + "."
					+ "<BR><BR> This is the game's final villain. <BR><BR><i>The game cannot be won until the villain is defeated.</i></center></html>");
		}
		//Closes the screen and opens the VillainScreen.
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(1);
			}
		});
		btnEnter.setBounds(90, 357, 140, 45);
		lairWindow.getContentPane().add(btnEnter);
		//Returns the user to home base.
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(2);
			}
		});

		btnLeave.setBounds(295, 357, 140, 45);
		lairWindow.getContentPane().add(btnLeave);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LairScreen.class.getResource("/Images/DCVillains2.jpeg")));
		lblNewLabel.setBounds(21, 18, 480, 175);
		lairWindow.getContentPane().add(lblNewLabel);
	}
}
