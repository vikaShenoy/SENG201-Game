package gui;
import javax.swing.*;
import javax.xml.stream.events.Characters;

import game.*;
import java.awt.Color;
import java.awt.event.*;
/**
 * GUI screen for setting up the game.
 * User enters their team name, number of cities to clear, 
 * and number of heroes they will have. 
 * @author vsh33.
 *
 */
public class SetupScreen {
	/**
	 * The main frame
	 */
	private JFrame setupWindow;
	/**
	 * user name attribute, holds the users name
	 */
	private JTextField nameText;
	/**
	 * The Game Manager, holds all the important information of the team and city 
	 */
	private GameEnvironmentGUI gameEnv;
	
	/**
	 * Creates a SetupScreen. LookAndFeel is set to Nimbus theme.
	 * @param incomingGameEnv the game manager which holds info on the team and city.
	 */
	public SetupScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
		setupWindow.setLocationRelativeTo(null);
		setupWindow.setVisible(true);
	}

	public void closeWindow() {
		setupWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeSetupScreen(this);
	}

	private void initialize() {
		setupWindow = new JFrame();
		setupWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		setupWindow.setBackground(Color.LIGHT_GRAY);
		setupWindow.setTitle("Heroes and Villains Setup");
		setupWindow.setBounds(100, 100, 630, 406);
		setupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupWindow.getContentPane().setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 448, 0);
		setupWindow.getContentPane().add(label);

		JLabel lblWelcomeToHeroes = new JLabel("<html><b>Welcome to Marvel vs DC!</b></html>");
		lblWelcomeToHeroes.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToHeroes.setBounds(169, 142, 291, 35);
		setupWindow.getContentPane().add(lblWelcomeToHeroes);

		JLabel lblEnterTeamName = new JLabel("Enter team name:");
		lblEnterTeamName.setBounds(88, 195, 294, 24);
		setupWindow.getContentPane().add(lblEnterTeamName);

		JLabel lblHowManyCities = new JLabel("How many cities will you explore?");
		lblHowManyCities.setBounds(86, 231, 232, 24);
		setupWindow.getContentPane().add(lblHowManyCities);

		JLabel lblHowManyHeroes = new JLabel("How many heroes will be in your team?");
		lblHowManyHeroes.setBounds(88, 267, 255, 24);
		setupWindow.getContentPane().add(lblHowManyHeroes);

		nameText = new JTextField();
		nameText.setBounds(394, 189, 114, 30);
		setupWindow.getContentPane().add(nameText);
		nameText.setColumns(10);

		JComboBox citiesCombo = new JComboBox();
		citiesCombo.setModel(new DefaultComboBoxModel(new String[] { "3", "4", "5", "6" }));
		citiesCombo.setBounds(394, 231, 114, 24);
		setupWindow.getContentPane().add(citiesCombo);

		JComboBox teamSizeCombo = new JComboBox();
		teamSizeCombo.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
		teamSizeCombo.setBounds(394, 267, 114, 24);
		setupWindow.getContentPane().add(teamSizeCombo);
		
		/*Makes sure the name is between 2 and 10 Characters
		Creates a new team and passes the relevant info to it. Team is then saved to gameEnv.
		Window is then closed.*/
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().length() >= 2 && nameText.getText().length() <= 10) {
					Teams team = new Teams(nameText.getText());
					int numCities = Integer.parseInt(citiesCombo.getSelectedItem().toString());
					int numHeroes = Integer.parseInt(teamSizeCombo.getSelectedItem().toString());
					team.setNumCities(numCities);
					team.setTeamSize(numHeroes);
					gameEnv.setTeam(team);
					gameEnv.createCities(numCities);
					finishedWindow();
				} else {
					lblEnterTeamName.setText("Name must be 2-10 characters long: ");
				}
			}
		});
		confirmButton.setBounds(245, 318, 140, 45);
		setupWindow.getContentPane().add(confirmButton);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(SetupScreen.class.getResource("/Images/Banner2.jpg")));
		label_1.setBounds(21, 10, 580, 120);
		setupWindow.getContentPane().add(label_1);
		
	}
}
