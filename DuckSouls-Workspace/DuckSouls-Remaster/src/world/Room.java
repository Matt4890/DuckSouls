package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;
import tests.StatisticTests;
import tiles.GeneralTile;
import tiles.Tile;
import tiles.Wall;
import utils.Orientation;

public class Room {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	protected static Random		_random				= new Random();
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	protected int				internalWidth;
	protected int				internalHeight;
	protected int				level;
	protected int				enemySpawnChance	= 1;
	
	protected Tile[][]			tileArray;
	protected Item[][]			itemArray;
	
	protected Player			player;
	protected ArrayList<Enemy>	enemiesList			= new ArrayList<Enemy>();
	
	protected String			roomName;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a square room of set size with scattered items and enemies.
	 * 
	 * @param size
	 *            Width and height of the room.
	 * @param enemySpawnChance
	 *            The spawn chance of enemies for a level. Must be from 0 to 100.
	 */
	public Room(int size, int level, int enemySpawnChance) {
		
		StatisticTests.testIntStatRange("Enemy Spawn Chance", enemySpawnChance);
		
		this.internalWidth = size;
		this.internalHeight = size;
		this.level = level;
		this.enemySpawnChance = enemySpawnChance;
		
		this.genTileArray();
		this.scatterItems();
		this.scatterEnemies();
		
	}
	
	public Room(String fileName) {
		
		// TODO: Fill in file reading constructor.
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Generates and appends a tile array to the instance. Makes the tile array 2
	 * units larger in both the x and y direction from the internal size to
	 * accommodate walls. Also automatically fills the edges with the correct wall
	 * tiles.
	 */
	protected void genTileArray() {
		
		// Generate 2D arrays to fill...
		this.tileArray = new Tile[this.internalWidth + 2][this.internalHeight + 2];
		this.itemArray = new Item[this.internalWidth + 2][this.internalHeight + 2];
		
		// For each position...
		for (int x = 0; x < this.internalWidth + 2; x++) {
			for (int y = 0; y < this.internalHeight + 2; y++) {
				
				/*
				 * Put the appropriate wall tiles along the edges and floor tiles in the middle.
				 */
				
				if (x == 0 && y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_TL; // Top Left
					
				} else if (x == this.internalWidth + 1 && y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_TR; // Top Right
					
				} else if (x == 0 && y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_BL; // Bottom Left
					
				} else if (x == this.internalWidth + 1 && y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_BR; // Bottom Right
					
				} else if (x == 0) {
					
					this.tileArray[x][y] = Wall.WALL_L; // Left
					
				} else if (y == 0) {
					
					this.tileArray[x][y] = Wall.WALL_T; // Top
					
				} else if (x == this.internalWidth + 1) {
					
					this.tileArray[x][y] = Wall.WALL_R; // Right
					
				} else if (y == this.internalHeight + 1) {
					
					this.tileArray[x][y] = Wall.WALL_B; // Bottom
					
				} else {
					
					this.tileArray[x][y] = GeneralTile.FLOOR; // Centre Tiles
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Scatters items at random throughout the level.
	 */
	public void scatterItems() {
		
		// Random number container
		int itemTypeNumber;
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				Point currentPosition = new Point(x, y);
				
				// This is used to pick what type of item will be spawned.
				/*
				 * There are 3 types of item: Consumable, Weapon, and Armour. The random number
				 * is from 0 to 5 so that some types of item will be favored over others.
				 * 
				 * As it is configured right now, 3/6 times consumable will be chosen, 2/6 for
				 * weapon, and 1/6 for armour.
				 */
				itemTypeNumber = _random.nextInt(6);
				
				switch (itemTypeNumber) {
					
					// Consumables
					case 0:
					case 1:
					case 2:
						
						Consumable consumable = Consumable.values()[_random.nextInt(Consumable.values().length)];
						if (consumable.tryToSpawn()) {
							this.placeItem(currentPosition, consumable);
						}
						
						break;
					
					// Weapons
					case 3:
					case 4:
						
						Weapon weapon = Weapon.values()[_random.nextInt(Weapon.values().length)];
						if (weapon.tryToSpawn()) {
							this.placeItem(currentPosition, weapon);
						}
						
						break;
					
					// Armour
					case 5:
						
						Armour armour = Armour.values()[_random.nextInt(Armour.values().length)];
						if (armour.tryToSpawn()) {
							this.placeItem(currentPosition, armour);
						}
						
						break;
					
				}
				
			}
		}
		
	}
	
	/**
	 * Scatters enemies at random throughout the room.
	 */
	public void scatterEnemies() {
		
		// For each position...
		for (int x = 1; x < this.internalWidth + 1; x++) {
			for (int y = 1; y < this.internalHeight + 1; y++) {
				
				// If the spawn chance is high enough...
				if (_random.nextInt(100) < this.enemySpawnChance) {
					
					// Place the entity
					this.addEntity(new Enemy(new Point(x, y), this.level));
					
				}
				
			}
		}
		
	}
	
	public void moveEntity(Entity entity, Orientation orientation) {
		
		Point moveTo = Orientation.pointAtDirection(entity.getPosition(), orientation);
		
		if (this.tileAt(moveTo).getCanWalkOn()) {
			
			if (entity instanceof Player) {
				
				
				
			} else {
				
				entity.setOrientation(orientation);
				entity.setPosition(moveTo);
				
			}
			
		}
		
	}
	
	/**
	 * Gets the item at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The item at position.
	 */
	public Item itemAt(Point pos) {
		
		return this.itemArray[pos.x][pos.y];
		
	}
	
	/**
	 * Sets a position at a point in the item array to a specific item.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to change.
	 * @param item
	 *            The item to set the position to.
	 */
	public void placeItem(Point position, Item item) {
		
		this.itemArray[position.x][position.y] = item;
		
	}
	
	/**
	 * Removes an item at a point in the item array.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the entity to remove.
	 */
	public void removeItem(Point position) {
		
		this.itemArray[position.x][position.y] = null;
		
	}
	
	/**
	 * Adds an entity to the room.
	 * 
	 * @param entity
	 *            The entity to add.
	 */
	public void addEntity(Entity entity) {
		
		if (entity instanceof Player) {
			this.player = (Player) entity;
		} else {
			this.enemiesList.add((Enemy) entity);
		}
		
	}
	
	/**
	 * Removes an entity at a point in the entity array.
	 * 
	 * @param entity
	 *            The entity to remove.
	 */
	public void removeEntity(Entity entity) {
		
		if (entity instanceof Player) {
			this.player = null;
		} else if (this.enemiesList.contains(entity)){
			this.enemiesList.remove((Enemy) entity);
		}
		
	}
	
	/**
	 * Sets a tile at a point in the tile array to a specific tile.
	 * 
	 * @param position
	 *            A java.awt.Point specifying the tile to change.
	 * @param tile
	 *            The tile to set the position to.
	 */
	public void setTile(Point position, Tile tile) {
		
		this.tileArray[position.x][position.y] = tile;
		
	}
	
	/**
	 * Gets the tile at a specific point in a room.
	 * 
	 * @param pos
	 *            The position to look at.
	 * @return The tile at position.
	 */
	public Tile tileAt(Point pos) {
		
		return this.tileArray[pos.x][pos.y];
		
	}
	
}
