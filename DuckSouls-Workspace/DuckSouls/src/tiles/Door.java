package tiles;

import utils.Orientation;

/**
 * This class represents all of the door for DuckSouls.
 * 
 * @author Matthew Allwright
 * @version 1.3
 */
public class Door extends Tile {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private String			key;											// TODO: Make keys an enum.
	private Orientation		orientation;									// TODO: Implement.
	private boolean			locked		= false;
	private static String	pathToImage	= "../Sprites/Tiles/Sewer/Door-";
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a new unlocked door.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 * @param orientation
	 *            The orientation the door is facing.
	 */
	public Door(boolean isGUI, Orientation orientation) {
		super(isGUI, " D ", true, pathToImage + orientation.str + ".png");
		this.orientation = orientation;
	}
	
	/**
	 * Creates a new locked door.
	 * 
	 * @param isGUI
	 *            Whether this is a GUI tile or not.
	 * @param orientation
	 *            The orientation the door is facing.
	 * @param key
	 *            The key that can be used to unlock the door.
	 */
	public Door(boolean isGUI, Orientation orientation, String key) {
		super(isGUI, " D ", false, pathToImage + orientation.str + ".png");
		this.orientation = orientation;
		this.locked = true;
		this.key = key;
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Checks to see if the door can be unlocked with a key. If it can, the door is
	 * then unlocked. If it can't, then the door remains locked.
	 * 
	 * @param key
	 *            The key to try on the door.
	 * @return whether the door was unlocked with the key or not.
	 */
	public boolean tryUnlock(String key) {
		
		// If it can be unlocked...
		if (key.equals(this.key)) {
			
			// Unlock the door
			this.locked = false;
			this.canWalkOn = true;
			return true;
			
		} else { // If it can't...
			
			// Don't
			return false;
			
		}
		
	}
	
	/**
	 * Returns whether the door is locked or not.
	 * 
	 * @return Whether the door is locked or not.
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Returns a copy of the key that this door is locked with.
	 * 
	 * @return A copy of the key that this door is locked with.
	 */
	public String getKey() {
		return new String(this.key);
	}
	
}