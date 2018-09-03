package gui;
import java.util.ArrayList;
import javax.swing.*;
import game.*;
import healingitems.*;
import superheroes.*;
import java.awt.event.*;
import java.awt.Color;
/**
 * GUI screen for a hospital where the user can apply healing items to their heroes.
 * @author dle70
 *
 */
public class HospitalScreen {
	/**
	 * The main frame
	 */
	private JFrame hospitalWindow;
	/**
	 * The Game Manager, holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Team attribute, holds the information of the team
	 */
	private Teams team;
	/**
	 * City attribute, holds the information on the current city
	 */
	private Cities city;
	/**
	 * Healing options, holds the list of healing items able to be used in the hospital
	 */
	private ArrayList healingOptions = new ArrayList<String>();
	/**
	 * Hero options, holds the list of heros able to be healed
	 */
	private ArrayList heroOptions = new ArrayList<String>();
	/**
	 * ActionLister used to apply the healing item in second intervals
	 */
	private ActionListener countdown;
	/**
	 * Timer for applying the healing item 
	 */
	private Timer clock;
	/**
	 * time left to apply the healing item
	 */
	private int timeRemaining;
	/**
	 * Hero attribute, holds the hero selected to apply the powerup on
	 */
	SuperHeroes selectedHero;
	/**
	 * Healing Item attribute, holds the healing item selected to apply it on the hero
	 */
	HealingItems selectedItem;
	/**
	 * Holds the maximum amount it can heal
	 */
	private int healCap;
	/**
	 * holds the amount of heal applied to the hero 
	 */
	private int healPerformed;
	
	/**
	 * Creates a HospitalScreen. 
	 * @param incomingGameEnv the game manager, which holds info on team and city.
	 */
	public HospitalScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		team = gameEnv.getTeam();
		city = gameEnv.getCurrentCity();
		initialize();
		hospitalWindow.setLocationRelativeTo(null);
		hospitalWindow.setVisible(true);
	}

	public void closeWindow() {
		hospitalWindow.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeHospitalScreen(this);
	}

	private void initialize() {
		hospitalWindow = new JFrame();
		hospitalWindow.getContentPane().setBackground(Color.LIGHT_GRAY);
		hospitalWindow.setBackground(Color.LIGHT_GRAY);
		hospitalWindow.setTitle("Hospital");
		hospitalWindow.setBounds(100, 100, 635, 574);
		hospitalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hospitalWindow.getContentPane().setLayout(null);

		JLabel lblProgress = new JLabel("");
		lblProgress.setBounds(101, 446, 463, 24);
		hospitalWindow.getContentPane().add(lblProgress);
		//Visual display for how long till the item is applied to the hero.
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(374, 327, 190, 24);
		hospitalWindow.getContentPane().add(progressBar);

		JLabel lblWelcomeMessage = new JLabel("New label");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setBounds(170, 211, 312, 43);
		hospitalWindow.getContentPane().add(lblWelcomeMessage);
		lblWelcomeMessage.setText("<html><center> You are in the hospital in <b>" + city.getCityName()
				+ "</b>.<BR>Here you can apply healing items.</center></html>");

		healingOptions = generateHealingItems();
		heroOptions = generateHeroNames();

		JComboBox comboBoxHealing = new JComboBox();
		comboBoxHealing.setModel(new DefaultComboBoxModel(healingOptions.toArray()));
		comboBoxHealing.setBounds(56, 285, 220, 30);
		hospitalWindow.getContentPane().add(comboBoxHealing);

		JComboBox comboBoxHero = new JComboBox();
		comboBoxHero.setModel(new DefaultComboBoxModel(heroOptions.toArray()));
		if (healingOptions.size() > 0) {
			comboBoxHero.setSelectedIndex(0);
		}
		comboBoxHero.setBounds(56, 357, 220, 30);
		hospitalWindow.getContentPane().add(comboBoxHero);

		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(101, 402, 463, 24);
		hospitalWindow.getContentPane().add(lblMessage);

		JLabel lblClock = new JLabel("");
		lblClock.setBounds(374, 372, 190, 15);
		hospitalWindow.getContentPane().add(lblClock);
		/* Action listener for counting down once a hero and item are selected.
		 * The clock and progress bar are updated, and 25 health is applied every tick.
		 * Timer is stopped when te time finishes.*/
		countdown = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeRemaining--;
				if (healPerformed < healCap) {
					selectedHero.alterHealth(25);
					healPerformed += 25;
				}
				int time = (int) selectedItem.getTime() / selectedHero.getRecoveryRate();
				progressBar.setValue(time - timeRemaining);
				lblProgress.setText(selectedHero.getName() + " is at " + selectedHero.getHealth() + "% health.");
				lblClock.setText("Time to fully heal: " + String.valueOf(timeRemaining));
				if (timeRemaining == 0) {
					lblMessage.setText("<html><b>Healing item successfully applied.</b></html>");
					lblClock.setText("");
					clock.stop();
				}
			}
		};

		/* The selected hero and item are found, and the total time is set by fetching the item's time.
		 * The timer is started. */
		JButton btnUseHealingItem = new JButton("Use Healing Item");
		btnUseHealingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (healingOptions.size() == 0) {
					lblMessage.setText("<html><b>No healing items available.</b></html>");
				} else {
					selectedHero = team.getHeroList().get(comboBoxHero.getSelectedIndex());
					selectedItem = team.getItemList().get(comboBoxHealing.getSelectedIndex());

					timeRemaining = (int) selectedItem.getTime() / selectedHero.getRecoveryRate();
					
					healCap = selectedItem.getSize();
					healPerformed = 0;
					clock = new Timer(1000, countdown);
					clock.start();

					progressBar.setMaximum(timeRemaining);
					progressBar.setValue(0);
					lblProgress.setText(selectedHero.getName() + " is at " + selectedHero.getHealth() + "% health.");
					team.removeItem(selectedItem);
					healingOptions = generateHealingItems();
					comboBoxHealing.setModel(new DefaultComboBoxModel(healingOptions.toArray()));
					lblClock.setText("Time to fully healed: " + String.valueOf(timeRemaining));
					lblMessage.setText(
							selectedItem.getName() + " is currently being applied to " + selectedHero.getName() + ".");

				}
			}
		});

		btnUseHealingItem.setBounds(170, 482, 160, 43);
		hospitalWindow.getContentPane().add(btnUseHealingItem);
		//Returns the user to home base.
		JButton btnExit = new JButton("Return Home");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnExit.setBounds(372, 481, 140, 45);
		hospitalWindow.getContentPane().add(btnExit);

		JLabel lblHealing = new JLabel("<html><u>Healing Item:</u></html>");
		lblHealing.setHorizontalAlignment(SwingConstants.LEFT);
		lblHealing.setBounds(56, 256, 99, 17);
		hospitalWindow.getContentPane().add(lblHealing);

		JLabel lblHeros = new JLabel("<html><u>Hero:</u></html>");
		lblHeros.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeros.setBounds(56, 327, 55, 17);
		hospitalWindow.getContentPane().add(lblHeros);

		JLabel lblApplicationTime = new JLabel("<html><u>Healing progress:</u></html>");
		lblApplicationTime.setBounds(409, 298, 143, 17);
		hospitalWindow.getContentPane().add(lblApplicationTime);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(HospitalScreen.class.getResource("/Images/Hospital.jpg")));
		label.setBounds(19, 18, 581, 178);
		hospitalWindow.getContentPane().add(label);
	}
	/**
	 * Generates a list of healing item string names for the combobox, which the user can select.
	 * @return options - list of item names.
	 */
	public ArrayList<String> generateHealingItems() {
		ArrayList<HealingItems> healingItemList = team.getItemList();
		ArrayList<String> options = new ArrayList<String>();
		for (HealingItems item : healingItemList) {
			options.add(item.getName());
		}
		return options;
	}
	/**
	 * Generate a list of hero names, for the combobox, which the user uses to choose
	 * which hero to heal.
	 * @return options - list of hero names.
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