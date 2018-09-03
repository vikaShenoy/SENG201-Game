package superheroes;

/**
 * @author vsh33 The DoctorStrange class is a subclass of SuperHeroes.
 *         DoctorStrange is one type the user can choose for their hero.
 */
public class DoctorStrange extends SuperHeroes {
	/**
	 * Creates a new DoctorStrange
	 * 
	 * @param name String - Name for the hero.
	 */
	public DoctorStrange(String name) {
		super(name, "Doctor Strange", "Clairvoyance");
	}
}
