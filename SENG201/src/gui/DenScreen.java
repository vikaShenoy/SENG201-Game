package gui;
import java.util.*;
import powerups.*;
import superheroes.*;
import javax.swing.*;
import javax.swing.Timer;
import game.*;
import java.awt.event.*;
import java.awt.Color;
/**
 * GUI screen for the power up den in each city.
 * The power up den is where the user can choose power ups and a hero to apply them to.
 * @author vsh33.
 *
 */
public class DenScreen {
	/**
	 * The main frame
	 */
	private JFrame denWindow;
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
	 * PowerUp Options, holds the list of powerups available to use in the den
	 */
	private ArrayList powerupOptions = new ArrayList<String>();
	/**
	 * Hero Options, holds the list of heros to apply the powerup on
	 */
	private ArrayList heroOptions = new ArrayList<String>();
	/**
	 * ActionLister used to apply the powerup in second intervals
	 */
	private ActionListener countdown;
	/**
	 * Timer for the applying the powerup
	 */
	private Timer clock;
	/**
	 * time left to apply the powerup
	 */
	private int timeRemaining;
	/**
	 * Hero attribute, holds the hero selected to apply the powerup on
	 */
	SuperHeroes selectedHero;
	/**
	 * PowerUp attribute, holds the powerup selected to apply it on the hero
	 */
	PowerUps selectedItem;
	
	
	/**
	 * Creates a DenScreen.
	 * @param incomingGameEnv - the game manager, which holds info on the team and city.
	 */
	public DenScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		city = gameEnv.getCurrentCity();
		initialize();
		denWindow.setLocationRelativeTo(null);
		denWindow.setVisible(true);
	}

	public void closeWindow() {
		denWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeDenScreen(this);
	}

	private void initialize() {
		denWindow = new JFrame();
		denWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		denWindow.setBackground(Color.LIGHT_GRAY);
		denWindow.setTitle("Powerup Den");
		denWindow.setBounds(100, 100, 648, 558);
		denWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		denWindow.getContentPane().setLayout(null);

		JButton btnExit = new JButton("Return home");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnExit.setBounds(354, 471, 140, 45);
		denWindow.getContentPane().add(btnExit);

		JLabel lblWelcomeMessage = new JLabel("New label");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setBounds(161, 208, 322, 45);
		denWindow.getContentPane().add(lblWelcomeMessage);
		lblWelcomeMessage.setText("<html><center>You are in the powerup den in <b>" + city.getCityName()
				+ "</b>.<BR>Select a powerup and hero to apply it to.</center></html>");

		JLabel lblProgress = new JLabel("");
		lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgress.setBounds(113, 399, 426, 24);
		denWindow.getContentPane().add(lblProgress);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(360, 326, 190, 24);
		denWindow.getContentPane().add(progressBar);

		powerupOptions = generatePowerups();
		heroOptions = generateHeroNames();

		JComboBox comboBoxPower = new JComboBox();
		comboBoxPower.setModel(new DefaultComboBoxModel(powerupOptions.toArray()));
		comboBoxPower.setBounds(44, 284, 220, 30);
		denWindow.getContentPane().add(comboBoxPower);

		JComboBox comboBoxHero = new JComboBox();
		comboBoxHero.setModel(new DefaultComboBoxModel(heroOptions.toArray()));
		if (powerupOptions.size() > 0) {
			comboBoxHero.setSelectedIndex(0);
		}

		comboBoxHero.setBounds(44, 355, 220, 30);
		denWindow.getContentPane().add(comboBoxHero);

		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(55, 435, 526, 24);
		denWindow.getContentPane().add(lblMessage);

		JLabel lblClock = new JLabel("");
		lblClock.setBounds(370, 362, 190, 15);
		denWindow.getContentPane().add(lblClock);
		
		/* Changes the progress bar and timer based on how much time is left to power up.
		Stops the clock when the full power up time has elapsed, and applies the powerup to the selected hero.*/
		countdown = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeRemaining--;
				int time = (int) selectedItem.getTime();
				progressBar.setValue(time - timeRemaining);
				lblClock.setText("Time to fully powerup: " + String.valueOf(timeRemaining));
				if (timeRemaining == 0) {
					String message = selectedHero.usePowerUp(selectedItem);
					lblProgress.setText("<html><b>Complete.</b></html>");
					lblClock.setText("");
					lblMessage.setText(message);
					clock.stop();
				}
			}
		};
		
		/* Stores the hero and power up selected by the user. Fetches the item time, and uses it to set the progress bar value.
		The timer is started. */
		JButton btnUsePowerup = new JButton("Use Powerup");
		btnUsePowerup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (powerupOptions.size() == 0) {
					lblMessage.setText("No powerups available.");
				} else {
					selectedHero = team.getHeroList().get(comboBoxHero.getSelectedIndex());
					selectedItem = team.getPowerUpList().get(comboBoxPower.getSelectedIndex());
					timeRemaining = (int) selectedItem.getTime();

					clock = new Timer(1000, countdown);
					clock.start();
					
					progressBar.setValue(0);
					progressBar.setMaximum(timeRemaining);
					lblProgress.setText(selectedHero.getName() + " is powering up...");
					team.removePowerUp(selectedItem);
					powerupOptions = generatePowerups();
					comboBoxPower.setModel(new DefaultComboBoxModel(powerupOptions.toArray()));
					lblClock.setText("Time to fully powerup: " + String.valueOf(timeRemaining));
					lblMessage.setText(
							selectedItem.getName() + " is currently being applied to " + selectedHero.getName() + ".");
				}
			}
		});
		btnUsePowerup.setBounds(136, 471, 140, 45);
		denWindow.getContentPane().add(btnUsePowerup);

		JLabel lblPowerup = new JLabel("<html><u>Powerup:</u></html>");
		lblPowerup.setBounds(113, 255, 99, 17);
		denWindow.getContentPane().add(lblPowerup);

		JLabel lblHero = new JLabel("<html><u>Hero:</u></html>");
		lblHero.setBounds(124, 326, 55, 17);
		denWindow.getContentPane().add(lblHero);

		JLabel lblApplicationTime = new JLabel("<html><u>Power-up progress:</u></html>");
		lblApplicationTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicationTime.setBounds(383, 291, 143, 17);
		denWindow.getContentPane().add(lblApplicationTime);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DenScreen.class.getResource("/Images/Gauntlet4.jpeg")));
		lblNewLabel.setBounds(30, 20, 584, 173);
		denWindow.getContentPane().add(lblNewLabel);
	}

	/**
	 * Creates a list of power ups the team has, to be displayed in a combo box.
	 * @return the list of power ups the team has.
	 */
	public ArrayList<String> generatePowerups() {
		ArrayList<PowerUps> powerupList = team.getPowerUpList();
		ArrayList<String> options = new ArrayList<String>();
		for (PowerUps item : powerupList) {
			options.add(item.getName());
		}
		return options;
	}
	/**
	 * Creates a list of the names of heroes the team has.
	 * @return the list of hero names.s
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
