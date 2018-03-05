package items;

/**
 * This class represents the armour item clothes.
 * 
 * @author Rahmanta Satriana
 * @version 1.1.1
 */
public class Clothes extends Armour {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private static final String	name		= "This is just my clothes!";
	private static final String	stringRepr	= " CL";
	private static final int	price		= 0;
	private static final int	spawnChance	= 0;
	private static final int	defense		= 0;
	private static final char	type		= 'a';
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new cloth armour item.
	 */
	public Clothes() {
		super(name, stringRepr, price, spawnChance, defense, type);
	}
	
}