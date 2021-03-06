package items;

import java.util.Random;

import javafx.scene.image.Image;

/**
 * This enumerator contains constants for different types of consumable items.
 * 
 * @author Matthew Allwright
 */
public enum Consumable implements Item {
	
	/*
	 * 
	 * CONSTANTS
	 * 
	 */
	
	CROUTON("A Soggy Crouton", " C ", "Crouton.png", 10, 5),
	GOO("Some Weird Goo", " G ", "Goo.png", 7, 10),
	FISH("A Half-Eaten Fish", " F ", "Fish.png", 4, 20),
	BUGS("De Bugs", " B ", "Bugs.png", 20, 2);
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private Random			_random	= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final String	NAME;
	private final String	STRING_REPR;
	private final String	IMAGE_NAME;
	private final int		SPAWN_CHANCE;
	private final int		HEALTH_MOD;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new consumable.
	 * 
	 * @param name
	 *            The name of the consumable.
	 * @param stringRepr
	 *            The 3-character string used to draw the consumable in the text
	 *            version of the game.
	 * @param imageName
	 *            The name of the image used to draw the consumable in the GUI
	 *            version of the game.
	 * @param spawnChance
	 *            The spawn chance of the consumable. Must be 0-100.
	 * @param healthMod
	 *            The health modifier of the consumable.
	 */
	private Consumable(String name, String stringRepr, String imageName, int spawnChance, int healthMod) {
		
		this.NAME = name;
		this.STRING_REPR = stringRepr;
		this.IMAGE_NAME = imageName;
		this.SPAWN_CHANCE = spawnChance;
		this.HEALTH_MOD = healthMod;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return new Image(ITEM_SPRITE_FOLDER_PATH + this.IMAGE_NAME);
	}
	
	@Override
	public String getStringRepr() {
		return this.STRING_REPR;
	}
	
	@Override
	public String getName() {
		return this.NAME;
	}
	
	@Override
	public boolean tryToSpawn() {
		return (_random.nextInt(100) + 1 <= this.SPAWN_CHANCE);
	}
	
	/**
	 * Returns the consumable's health modifier.
	 * 
	 * @return The consumable's health modifier.
	 */
	public int getHealthMod() {
		return this.HEALTH_MOD;
	}
	
	@Override
	public String getFileString() {
		return this.STRING_REPR.replaceAll(" ", "");
	}
	
	@Override
	public int getSpawnChance() {
		return this.SPAWN_CHANCE;
	}
	
}
