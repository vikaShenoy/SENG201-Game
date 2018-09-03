package gui;
import javax.swing.*;
import java.awt.event.*;
import java.util.function.Function;
import java.awt.Font;
import game.*;
import superheroes.*;
import java.awt.Color;
/**
 * GUI screen for choosing which heroes the user wishes to have in their team.
 * Names and types are chosen for the heroes, and on click, these heroes are added to team.
 * Stats are viewable for each hero type.
 * @author vsh33.
 */
public class HeroSelectScreen {
	/**
	 * The main frame
	 */
	private JFrame window;
	/**
	 * The Game Manager, holds all the important information of the team and city
	 */
	private GameEnvironmentGUI gameEnv;
	/**
	 * Hero attribute, holds the first hero of the team
	 */
	private JTextField hero1Name;
	/**
	 * Hero attribute, holds the second hero of the team
	 */
	private JTextField hero2Name;
	/**
	 * Hero attribute, holds the third hero of the team
	 */
	private JTextField hero3Name;
	/**
	 * Holds the number of heros in the team
	 */
	private int numHeroes;
	
	/**
	 * Creates a HeroSelectScreen.
	 * @param incomingGameEnv the game manager which holds info on team and city.
	 */
	public HeroSelectScreen(GameEnvironmentGUI incomingGameEnv) {
		gameEnv = incomingGameEnv;
		numHeroes = gameEnv.getTeam().getTeamSize();
		initialize();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		gameEnv.closeHeroSelectScreen(this);
	}

	private void initialize() {
		Teams team = gameEnv.getTeam();

		window = new JFrame();
		window.getContentPane().setBackground(Color.LIGHT_GRAY);
		window.setBackground(Color.LIGHT_GRAY);
		window.setTitle("Hero Select Screen");
		window.setBounds(100, 100, 891, 842);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JLabel lblInstruct = new JLabel("<html><u>Choose your heroes:</u></html>");
		lblInstruct.setBounds(33, 35, 497, 25);
		window.getContentPane().add(lblInstruct);

		JLabel lblHero1 = new JLabel("<html><b>Hero 1:</b></html>");
		lblHero1.setBounds(43, 101, 70, 15);
		window.getContentPane().add(lblHero1);

		hero1Name = new JTextField();
		hero1Name.setBounds(147, 99, 114, 26);
		window.getContentPane().add(hero1Name);
		hero1Name.setColumns(10);

		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] { "Black Panther", "Captain Marvel", "Doctor Strange",
				"Ironman", "Spiderman", "Wolverine" }));
		comboBox1.setBounds(290, 99, 131, 26);
		window.getContentPane().add(comboBox1);

		JLabel lblHero2 = new JLabel("<html><b>Hero 2:</b></html>");
		lblHero2.setBounds(43, 139, 70, 15);
		window.getContentPane().add(lblHero2);

		hero2Name = new JTextField();
		hero2Name.setBounds(147, 137, 114, 26);
		window.getContentPane().add(hero2Name);
		hero2Name.setColumns(10);

		JComboBox comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] { "Black Panther", "Captain Marvel", "Doctor Strange",
				"Ironman", "Spiderman", "Wolverine" }));
		comboBox2.setBounds(290, 137, 131, 25);
		window.getContentPane().add(comboBox2);

		JLabel lblHero3 = new JLabel("<html><b>Hero 3:</b></html>");
		lblHero3.setBounds(43, 180, 70, 15);
		window.getContentPane().add(lblHero3);

		hero3Name = new JTextField();
		hero3Name.setBounds(147, 178, 114, 26);
		window.getContentPane().add(hero3Name);
		hero3Name.setColumns(10);

		JComboBox comboBox3 = new JComboBox();
		comboBox3.setModel(new DefaultComboBoxModel(new String[] { "Black Panther", "Captain Marvel", "Doctor Strange",
				"Ironman", "Spiderman", "Wolverine" }));
		comboBox3.setBounds(290, 178, 131, 25);
		window.getContentPane().add(comboBox3);
		
		//Teamsize is checked, and the options for selecting multiple heroes are set accordingly.
		if (numHeroes < 2) {
			lblHero2.setVisible(false);
			hero2Name.setVisible(false);
			comboBox2.setVisible(false);
		}

		if (numHeroes < 3) {
			lblHero3.setVisible(false);
			hero3Name.setVisible(false);
			comboBox3.setVisible(false);
		}

		JLabel lblName = new JLabel("<html><i>Name:</i></html>");
		lblName.setBounds(147, 72, 70, 15);
		window.getContentPane().add(lblName);

		JLabel lblType = new JLabel("<html><i>Type:</i></html>");
		lblType.setBounds(293, 72, 70, 15);
		window.getContentPane().add(lblType);

		JLabel lblStats = new JLabel("<html><i>Stats:</i></html>");
		lblStats.setVerticalAlignment(SwingConstants.TOP);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setBounds(484, 72, 206, 192);
		window.getContentPane().add(lblStats);

		JLabel lblClickToDisplay = new JLabel("<html><u>Click to display stats:</u></html>");
		lblClickToDisplay.setBounds(30, 236, 231, 25);
		window.getContentPane().add(lblClickToDisplay);

		JButton btnBlackPanther = new JButton("Black Panther");
		btnBlackPanther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlackPanther BP = new BlackPanther("Black Panther");
				lblStats.setText(BP.getStats());
			}
		});
		btnBlackPanther.setBounds(91, 489, 138, 40);
		window.getContentPane().add(btnBlackPanther);

		JButton btnCaptainMarvel = new JButton("Captain Marvel");
		btnCaptainMarvel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaptainMarvel CM = new CaptainMarvel("Captain Marvel");
				lblStats.setText(CM.getStats());
			}
		});
		btnCaptainMarvel.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnCaptainMarvel.setBounds(359, 490, 138, 40);
		window.getContentPane().add(btnCaptainMarvel);

		JButton btnDoctorStrange = new JButton("Doctor Strange");
		btnDoctorStrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorStrange DS = new DoctorStrange("Doctor Strange");
				lblStats.setText(DS.getStats());
			}
		});
		btnDoctorStrange.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnDoctorStrange.setBounds(636, 490, 138, 40);
		window.getContentPane().add(btnDoctorStrange);

		JButton btnIronman = new JButton("Ironman");
		btnIronman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IronMan IM = new IronMan("Ironman");
				lblStats.setText(IM.getStats());
			}
		});
		btnIronman.setBounds(91, 751, 138, 40);
		window.getContentPane().add(btnIronman);

		JButton btnSpiderman = new JButton("Spiderman");
		btnSpiderman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spiderman S = new Spiderman("Spiderman");
				lblStats.setText(S.getStats());
			}
		});
		btnSpiderman.setBounds(636, 751, 138, 40);
		window.getContentPane().add(btnSpiderman);

		JButton btnWolverine = new JButton("Wolverine");
		btnWolverine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wolverine W = new Wolverine("Wolverine");
				lblStats.setText(W.getStats());
			}
		});
		btnWolverine.setBounds(359, 751, 138, 40);
		window.getContentPane().add(btnWolverine);
		
		/*Checks to make sure names are valid. Creates the heroes the user specifies by calling
		the createHero function. Calls finished window.*/
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean invalidNames = checkNames();
				if (!invalidNames) {
					int teamSize = team.getTeamSize();
					String heroName = hero1Name.getText();
					String type = (String) comboBox1.getSelectedItem();
					gameEnv.createHero(team, heroName, type);
					if (teamSize == 2) {
						String heroName2 = hero2Name.getText();
						String type2 = (String) comboBox2.getSelectedItem();
						gameEnv.createHero(team, heroName2, type2);
					} else if (teamSize == 3) {
						String heroName2 = hero2Name.getText();
						String type2 = (String) comboBox2.getSelectedItem();
						gameEnv.createHero(team, heroName2, type2);
						String heroName3 = hero3Name.getText();
						String type3 = (String) comboBox3.getSelectedItem();
						gameEnv.createHero(team, heroName3, type3);
					}
					finishedWindow();
				} else {
					lblInstruct.setText("Hero names must be different and non-empty. Try again.");
				}
			}
		});
		btnConfirm.setBounds(706, 25, 140, 45);
		window.getContentPane().add(btnConfirm);
		
		JLabel lblPicBP = new JLabel("");
		lblPicBP.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/BPIcon.png")));
		lblPicBP.setBounds(33, 273, 256, 256);
		window.getContentPane().add(lblPicBP);
		
		JLabel lblPicCM = new JLabel("");
		lblPicCM.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/CMIcon.png")));
		lblPicCM.setBounds(303, 273, 256, 256);
		window.getContentPane().add(lblPicCM);
		
		JLabel lblPicDoc = new JLabel("");
		lblPicDoc.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/DSIcon.png")));
		lblPicDoc.setBounds(571, 276, 256, 256);
		window.getContentPane().add(lblPicDoc);
		
		JLabel lblPicIM = new JLabel("");
		lblPicIM.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/IMIcon.png")));
		lblPicIM.setBounds(33, 535, 256, 256);
		window.getContentPane().add(lblPicIM);
		
		JLabel lblPicW = new JLabel("");
		lblPicW.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/WIcon.png")));
		lblPicW.setBounds(313, 541, 256, 256);
		window.getContentPane().add(lblPicW);
		
		JLabel lblPicS = new JLabel("");
		lblPicS.setIcon(new ImageIcon(HeroSelectScreen.class.getResource("/Images/SIcon.png")));
		lblPicS.setBounds(571, 535, 256, 256);
		window.getContentPane().add(lblPicS);
	}
	
	/**
	 * Makes sure the user has entered unique names for each hero.
	 * @return true if the names are invalid, false if not.
	 */
	public boolean checkNames() {
		boolean errorFlag = false;
		String name1 = hero1Name.getText();
		String name2 = hero2Name.getText();
		String name3 = hero3Name.getText();

		if (numHeroes == 2 & name1.equals(name2)) {
			errorFlag = true;
		} else if (numHeroes == 3 & (name1.equals(name2) || name1.equals(name3) || name2.equals(name3))) {
			errorFlag = true;
		} else if (numHeroes == 1 & name1.length() == 0) {
			errorFlag = true;
		} else if (numHeroes == 2 & (name1.length() == 0 || name2.length() == 0)) {
			errorFlag = true;
		} else if (numHeroes == 3 & (name1.length() == 0 || name2.length() == 0 || name3.length() == 0)) {
			errorFlag = true;
		}
		return errorFlag;
	}
}
