package items;

/**
 * This class represents the weapon item knife.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Knife extends Weapon {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "A Dull Butter Knife";
	private static final String	stringRepr	= " K ";
	private static final int	price		= 20;
	private static final int	spawnChance	= 3;
	private static final int	attack		= 10;
	private static final int	accuracy	= 95;
	private static final int	speed		= 90;
	private static final int	critchance	= 10;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new knife item.
	 */
	public Knife() {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance);
	}
	
	public Knife(int x, int y, int id) {
		super(name, stringRepr, price, spawnChance, attack, accuracy, speed, critchance, x, y, id);
	}
	
}
