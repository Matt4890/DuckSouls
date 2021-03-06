package items;

/**
 * This class represents the consumable item goo.
 * 
 * @author Matthew Allwright
 * @version 1.1.1
 */
public class Goo extends Consumable {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected static final String	pathToImage	= "/Sprites/Items/Goo";
	
	private static final String	name		= "Some Weird Goo";
	private static final String	stringRepr	= " G ";
	private static final int	price		= 10;
	private static final int	spawnChance	= 15;
	private static final int	health		= 0;
	private static final int	mana		= 20;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new goo item.
	 */
	public Goo() {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana);
	}
	
	/**
	 * Creates a new goo item.
	 * @param x
	 *            The x co-ord of the item.
	 * @param y
	 *            The y co-ord of the item.
	 * @param id
	 *            The ID of the item.
	 */
	public Goo(int x, int y, int id) {
		super(name, pathToImage, stringRepr, price, spawnChance, health, mana, x, y, id);
	}
	
}
