package gui;
import javax.swing.*;
import java.awt.event.*;
import game.*;
import java.awt.Color;
/**
 * GUI screen which displays info about the next city the user will enter.
 * @author vsh33.
 *
 */
public class EnterCityScreen {
	/**
	 * The main frame
	 */
	private JFrame window;
	/**
	 * The Game Manager. holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds important information of the team
	 */
	private Teams team;
	
	
	/**
	 * Creates an EnterCityScreen.
	 * @param incomingGameEnv the game managers, which holds info on team and city.
	 */
	public EnterCityScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		initialize();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeEnterCityScreen(this);
	}

	private void initialize() {
		window = new JFrame();
		window.getContentPane().setBackground(Color.LIGHT_GRAY);
		window.setBackground(Color.LIGHT_GRAY);
		window.setTitle("Enter City");
		window.setBounds(100, 100, 687, 489);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JLabel lblTeam = new JLabel("<html><b>Team status:</b></html>");
		lblTeam.setBounds(62, 40, 133, 15);
		window.getContentPane().add(lblTeam);

		JLabel lblCitiesTag = new JLabel("<html><b>Cities remaining:</b></html>");
		lblCitiesTag.setBounds(62, 364, 133, 15);
		window.getContentPane().add(lblCitiesTag);
		
		//Calls finished window, closing the screen.
		JButton btnEnterCity = new JButton("Enter city");
		btnEnterCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnEnterCity.setBounds(453, 332, 117, 47);
		window.getContentPane().add(btnEnterCity);

		JLabel lblNextCityTag = new JLabel("<html><b>Next city:</b></html>");
		lblNextCityTag.setBounds(62, 391, 133, 15);
		window.getContentPane().add(lblNextCityTag);

		JLabel lblTeamStats = new JLabel("New label");
		lblTeamStats.setVerticalAlignment(SwingConstants.TOP);
		lblTeamStats.setBounds(207, 40, 178, 312);
		window.getContentPane().add(lblTeamStats);
		lblTeamStats.setText(team.getTeamInfo());

		JLabel lblCitiesNum = new JLabel("New label");
		lblCitiesNum.setBounds(230, 364, 70, 15);
		window.getContentPane().add(lblCitiesNum);
		lblCitiesNum.setText(String.valueOf(team.getNumCities()));

		JLabel lblNextCity = new JLabel("");
		lblNextCity.setBounds(230, 391, 218, 15);
		window.getContentPane().add(lblNextCity);

		Cities nextCity = gameEnv.getCityList().get(team.getNumCities() - 1);
		Shop nextShop = new Shop(gameEnv);
		nextCity.setCityShop(nextShop);
		gameEnv.setCurrentCity(nextCity);

		lblNextCity.setText(nextCity.getCityName());

		JLabel lblNextVillainTag = new JLabel("<html><b>Next villian: </b></html>");
		lblNextVillainTag.setBounds(62, 418, 133, 15);
		window.getContentPane().add(lblNextVillainTag);

		JLabel lblNextVillian = new JLabel("New label");
		lblNextVillian.setBounds(230, 418, 150, 15);
		window.getContentPane().add(lblNextVillian);
		lblNextVillian.setText(nextCity.getVillain().getName());
		
		//Sets the picture depending on which villain the city contains.
		JLabel lblPicVillain = new JLabel("");
		String picture = nextCity.getVillain().getName();
		switch (picture) {
		case("Bane"):
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/bane.png")));
			break;
		case("Deathstroke"): 
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/deathstroke.jpg")));
			break;
		case("Joker"):
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/JokerIcon.jpg")));
			break;
		case("Lex Luthor"):
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/lexluthor.jpg")));
			break;
		case("Riddler"):
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/riddler.jpg")));
			break;
		case("Scarecrow"):
			lblPicVillain.setIcon(new ImageIcon(EnterCityScreen.class.getResource("/Images/scarecrow.jpg")));
			break;
		}
		lblPicVillain.setBounds(392, 58, 256, 256);
		window.getContentPane().add(lblPicVillain);
	}
}
