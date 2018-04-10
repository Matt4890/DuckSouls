package entities;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Armour;
import items.Consumable;
import items.Item;
import items.Weapon;
import javafx.scene.image.Image;

public class Player extends Entity {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static final double					BASE_HEALTH			= 20;
	private static final double					BASE_ATTACK			= 6;
	private static final double					BASE_DEFENCE		= 5;
	private static final double					BASE_SPEED			= 5;
	private static final double					BASE_ACCURACY		= 90;
	private static final double					BASE_CRIT			= 16;
	private static final int					BASE_NEEDED_XP		= 20;
	
	private static final int					HEALTH_PER_LEVEL	= 2;
	private static final int					ATTACK_PER_LEVEL	= 1;
	private static final int					DEFENCE_PER_LEVEL	= 2;
	private static final int					SPEED_PER_LEVEL		= 1;
	private static final int					ACCURACY_PER_LEVEL	= 2;
	private static final int					CRIT_PER_LEVEL		= 2;
	private static final int					NEEDED_XP_PER_LEVEL	= 10;
	
	private static final String					STRING_REPR			= " @ ";
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Weapon								weapon				= Weapon.NONE;
	private Armour								armour				= Armour.NONE;
	private HashMap<Item, Integer>				inventory			= new HashMap<Item, Integer>();
	
	private int									level;
	private int									experience;
	private int									experienceForNextLevel;
	private int									score;
	private static Scanner						scanner				= new Scanner(System.in);	// Scanner to get user input
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Player(Point position) {
		
		super(STRING_REPR, position, BASE_HEALTH, BASE_ATTACK, BASE_DEFENCE, BASE_SPEED, BASE_ACCURACY, BASE_CRIT);
		
		this.experience = 0;
		this.level = 1;
		this.score = 0;
		this.experienceForNextLevel = BASE_NEEDED_XP;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	public Image getImage() {
		return new Image(ENTITY_SPRITE_FOLDER_PATH + "Duck/Duck-" + this.orientation.STR + ".png");
	}
	
	@Override
	public double sendAttack() {
		double damage;
		
		damage = super.sendAttack();
		if (damage != 0) {
			damage = damage + this.weapon.getDamage();
		}
		return damage;
	}
	
	@Override
	public double receiveAttack(double damage) {
		if (damage == 0) {
			return 0;
		}
		else {
			damage = damage - this.armour.getDefense();
			damage = super.receiveAttack(damage);
			return (damage);
		}
	}
	
	@Override
	public String choice(int a, boolean isGUI) {
		
		//Give option to player (text version)
		String moveCommand;
		boolean choose = true;
		int move = 0;
		if(!isGUI) {
			while (choose) {
				System.out.print("\nEnter a move: ");
				moveCommand = scanner.nextLine().toLowerCase();	
				switch (moveCommand) {
				case "attack":
					choose = false;
					move = 0;
					break;
				case "taunt":
					move = 1;
					choose = false;
					break;	
				case "item":
					move = 4;
					choose = false;
					break;
				case "run":
					move = 5;
					choose = false;
					break;
				default:
					System.out.println("That is not a command!");
				}
			}

		}
		
		String command = super.choice(move, isGUI);
		return command;
	}
	
	@Override
	public String useItem(boolean isGUI) {
		String item = "";
		System.out.println(this.inventory);
		if(!isGUI) {
			while (true) {
				try {
					System.out.print("\nWhich item do you want to use?");
					item = scanner.nextLine().toLowerCase();	
					switch (item) {
					case "crouton":
						if(this.inventory.get(Consumable.CROUTON) != null && this.inventory.get(Consumable.CROUTON) != 0) {
							return "Crouton";
						}
						else {
							throw new Exception("Bad");
						}
					case "goo":
						if(this.inventory.get(Consumable.GOO) != null && this.inventory.get(Consumable.GOO) != 0) {
							return "Goo";
						}
						else {
							throw new Exception("Bad");
						}
					case "fish":
						if(this.inventory.get(Consumable.FISH) != null && this.inventory.get(Consumable.FISH) != 0) {
							return "Fish";
						}
						else {
							throw new Exception("Bad");
						}
					case "bugs":
						if(this.inventory.get(Consumable.BUGS) != null && this.inventory.get(Consumable.BUGS) != 0) {
							return "Bugs";
						}
						else {
							throw new Exception("Bad");
						}
					default:
						throw new Exception("Bad");
					}
				} 
				catch (Exception e) {
					System.out.print("\nYou don't have that item!");
				}
			}
		}
		return "APE";
	}
	
	@Override
	public boolean run() {
		return true;
	}
	
	public void addExperiece(int xp) {
		
		this.experience += xp;
		
		while (this.experience >= this.experienceForNextLevel) {
			this.levelUp();
		}
		
	}
	
	/**
	 * When player levels up the stats are increased. It similarly has the same
	 * code as the setStatsForLevel method hence why it gets called here
	 */
	private void levelUp() {
		
		this.experience -= this.experienceForNextLevel;
		
		this.level++;
		this.health = BASE_HEALTH + (HEALTH_PER_LEVEL * (this.level - 1));
		this.setStatsForLevel();
		this.experienceForNextLevel = BASE_NEEDED_XP + (NEEDED_XP_PER_LEVEL * (this.level - 1));
		
	}
	
	/**
	 * This is used for after battle, to reset the stats back to what it should
	 * be after being altered by a move, e.g. taunt
	 * 
	 * health not included as you have to use items to heal yourself
	 */
	public void setStatsForLevel() {
		this.attack = BASE_ATTACK + (ATTACK_PER_LEVEL * (this.level - 1));
		this.defence = BASE_DEFENCE + (DEFENCE_PER_LEVEL * (this.level - 1));
		this.speed = BASE_SPEED + (SPEED_PER_LEVEL * (this.level - 1));
		this.accuracy = BASE_ACCURACY + (ACCURACY_PER_LEVEL * (this.level - 1));
		this.crit = BASE_CRIT + (CRIT_PER_LEVEL * (this.level - 1));
	}

	
	public void pickupItem(Item item) {
		
		// TODO: IDEA! Have the user press a button to pick up an item!
		// TODO: IDEA! When the user picks up a weapon/armour, drop the other one!
		
		// Equip weapon if it's better than the current weapon
		if (item instanceof Weapon && ((Weapon) item).getDamage() > this.weapon.getDamage()) {
			this.weapon = (Weapon) item;
		}
		
		// Equip armour if it's better than the current armour
		if (item instanceof Armour && ((Armour) item).getDefense() > this.armour.getDefense()) {
			this.armour = (Armour) item;
		}
		
		// Add the item to the inventory
		if (this.inventory.containsKey(item)) {
			this.inventory.put(item, this.inventory.get(item) + 1);
		} else {
			this.inventory.put(item, 1);
		}
		
	}
	
	public void setScore(int addScore) {
		this.score = this.score + addScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public HashMap<Item, Integer> getInventory() {
		return this.inventory;
	}
	
	public double itemUse(Consumable item) {
		double healthHealed = this.health + 0;
		this.inventory.put(item, this.inventory.get(item) - 1);
		this.health = this.health + item.getHealthMod();
		if(this.health > (BASE_HEALTH + (HEALTH_PER_LEVEL * (this.level - 1)))) {
			this.health = BASE_HEALTH + (HEALTH_PER_LEVEL * (this.level - 1));
		}
		healthHealed = this.health - healthHealed;
		return healthHealed;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public Armour getArmour() {
		return this.armour;
	}
	
	public int getExperience() {
		return this.experience;
	}

	@Override
	public int getLevel() {
		return this.level;
	}
	
}
