package game;
import java.util.ArrayList;
import gui.*;
import superheroes.*;
/**
 * This class is the manager for the whole application. The main method is called from here.
 * An instance of this class is passed between GUI screens, as info on the team and cities are stored here.
 * The relevant GUI frames are loaded and closed as the user moves through the game.
 * @author vsh33 dle70
 *
 */
public class GameEnvironmentGUI {

	/**
	 * The hero team which the user controls.
	 */
	Teams team;
	/**
	 * The city which the user is currently in.
	 */
	Cities currentCity;
	/**
	 * The list of cities the user must clear.
	 */
	ArrayList<Cities> cityList;
	/**
	 * The system time at which the user starts the game.
	 */
	long startTime;
	/**
	 * Gets the time the game was started. Used to calculate how long the user took to play the game.
	 * @return startTime long the time the game was started at. The game starts when the hero select screen finishes.
	 */
	public long getStartTime() {
		return startTime;
	}
	/**
	 * The time the game was started at. Set when the hero select screen is finished.
	 * @param startTime long the user finishes the hero select screen at.
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

	public Cities getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(Cities currentCity) {
		this.currentCity = currentCity;
	}

	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}

	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchHeroSelectScreen();
	}

	public void launchHeroSelectScreen() {
		HeroSelectScreen window = new HeroSelectScreen(this);
	}

	public void closeHeroSelectScreen(HeroSelectScreen window) {
		startTime = System.currentTimeMillis();
		window.closeWindow();
		launchEnterCityScreen();
	}

	public void launchEnterCityScreen() {
		EnterCityScreen enterWindow = new EnterCityScreen(this);
	}

	public void closeEnterCityScreen(EnterCityScreen window) {
		window.closeWindow();
		launchHomeBaseScreen();
	}

	public void launchHomeBaseScreen() {
		HomeBaseScreen baseWindow = new HomeBaseScreen(this);
	}
	/**
	 * When the home base screen is closed, a string is passed to this function, which determines 
	 * which screen to open next, corresponding to the 4 map locations. 
	 * @param frame the HomeBaseScreen window to close.
	 * @param nextScreen String the screen to open next.
	 */
	public void closeHomeBaseScreen(HomeBaseScreen frame, String nextScreen) {
		frame.closeWindow();
		switch (nextScreen) {
		case ("Den"):
			launchDenScreen();
			break;
		case ("Hospital"):
			launchHospitalScreen();
			break;
		case ("Shop"):
			launchShopScreen();
			break;
		case ("Lair"):
			launchLairScreen();
			frame.closeWindow();
			break;
		}
	}

	public void launchGuessNumberScreen() {
		GuessNumberScreen gnWindow = new GuessNumberScreen(this);
	}

	public void closeGuessNumberScreen(GuessNumberScreen gnWindow) {
		gnWindow.closeWindow();
		launchVillainsScreen();
	}

	public void launchDiceRollScreen() {
		DiceRollScreen drWindow = new DiceRollScreen(this);
	}

	public void closeDiceRollScreen(DiceRollScreen drWindow) {
		drWindow.closeWindow();
		launchVillainsScreen();
	}

	public void launchPaperScissorsRockScreen() {
		PaperScissorsRockScreen psrWindow = new PaperScissorsRockScreen(this);
	}

	public void closePaperScissorsRockScreen(PaperScissorsRockScreen psrWindow) {
		psrWindow.closeWindow();
		launchVillainsScreen();
	}

	public void launchLairScreen() {
		LairScreen lairWindow = new LairScreen(this);
	}

	public void closeLairScreen(LairScreen lairWindow, int next) {
		lairWindow.closeWindow();
		switch (next) {
		case (1):
			launchVillainsScreen();
			break;
		case (2):
			launchHomeBaseScreen();
			break;
		}
	}

	public void launchVillainsScreen() {
		VillainsScreen villainWindow = new VillainsScreen(this);
	}
	/**
	 * Closes the villain screen and launches a game, the next city, or finishes the game.
	 * @param frame The villainScreen window to close.
	 * @param nextScreen String The next screen to open. 
	 */
	public void closeVillainsScreen(VillainsScreen frame, String nextScreen) {
		frame.closeWindow();
		switch (nextScreen) {
		case ("PSR"):
			launchPaperScissorsRockScreen();
			break;
		case ("DR"):
			launchDiceRollScreen();
			break;
		case ("GN"):
			launchGuessNumberScreen();
			break;
		case ("Next"):
			launchEnterCityScreen();
			break;
		case ("Finish"):
			frame.closeWindow();
			break;
		}
	}

	public void launchDenScreen() {
		DenScreen denWindow = new DenScreen(this);
	}

	public void closeDenScreen(DenScreen denWindow) {
		denWindow.closeWindow();
		launchHomeBaseScreen();
	}

	public void launchHospitalScreen() {
		HospitalScreen hospitalWindow = new HospitalScreen(this);
	}

	public void closeHospitalScreen(HospitalScreen window) {
		window.closeWindow();
		launchHomeBaseScreen();
	}

	public void launchShopScreen() {
		ShopScreen shopWindow = new ShopScreen(this);
	}

	public void closeShopScreen(ShopScreen window) {
		window.closeWindow();
		launchHomeBaseScreen();
	}

	public static void main(String[] args) {
		GameEnvironmentGUI gameEnv = new GameEnvironmentGUI();
		gameEnv.launchSetupScreen();
	}
	/**
	 * Creates the hero chosen by the user. The user chooses name and type.
	 * The villain is added to the team created earlier.
	 * @param team The team the user is controlling.
	 * @param heroName The name of the hero to be created.
	 * @param type The type of the hero to be created. Each type has its own subclass.
	 */
	public void createHero(Teams team, String heroName, String type) {
		switch (type) {
		case "Black Panther":
			BlackPanther panther = new BlackPanther(heroName);
			team.addHero(panther);
			break;
		case "Captain Marvel":
			CaptainMarvel captain = new CaptainMarvel(heroName);
			team.addHero(captain);
			break;
		case "Doctor Strange":
			DoctorStrange strange = new DoctorStrange(heroName);
			team.addHero(strange);
			team.setClairvoyance(true);
			break;
		case "Ironman":
			IronMan ironman = new IronMan(heroName);
			team.addHero(ironman);
			team.setCheaperPrices(true);
			break;
		case "Spiderman":
			Spiderman spider = new Spiderman(heroName);
			team.addHero(spider);
			break;
		case "Wolverine":
			Wolverine wolv = new Wolverine(heroName);
			team.addHero(wolv);
		}
	}
	/**
	 * Generates cities. The number is chosen by the user in the setup screen.
	 * An ArrayList is constructed, and the cities added to it.
	 * @param cityNumber - the city number that gets passed into the villain selection switch
	 */
	public void createCities(int cityNumber) {
		ArrayList<Cities> citiesList = new ArrayList<Cities>();
		for (int i = 1; i <= team.getNumCities(); i++) {
			Cities newCity = new Cities(i);
			citiesList.add(newCity);
		}
		cityList = citiesList;
	}

	public ArrayList<Cities> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<Cities> cityList) {
		this.cityList = cityList;
	}
}
