//IOException for use with CMD in Windows
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;



/**
 * 
 * @author Wylee McAndrews
 * @author Rahmanta Satriana
 * @author add name if modified
 *
 */
public class Enemy_Object {
	
<<<<<<< HEAD
/**		Public Variables	*/
=======
	/**		Public Variables	*/
>>>>>>> master
	
	// x/y position: Where the enemy is drawn on the screen (0,0 = topmost left)
	public int xPosition = 35;
	public int yPosition = 0;	
	
	/**		Private Variables	*/
	
<<<<<<< HEAD
	//Enemy Stats
	final private double HEALTH_POINTS = 10;
	final private double MANA_POINTS = 15;
	final private double ATTACK_POINTS = 5;
	final private double DEFENCE_POINTS = 5;
	final private double SPEED_POINTS = 5;
	final private double ACCURACY_POINTS = 70;
	final private double CRITICAL_HIT_POINTS = 16;
		
		
	private double healthPoints = 10;
	private double manaPoints = 15;
	private double attackPoints = 5;
	private double defencePoints = 5;
	private double speedPoints = 5;
	private double accuracyPoints = 70;
	private double criticalHitPoints = 16;	
	
	private int giveXP = 25;
	private int giveMoney = 100;
	
=======
>>>>>>> master
	private String enemyType;		//The type of enemy (Will be added via a constructor)
	
	// x/y padding: Converts x/y position into spaces/tabs
	private String xPadding = Utilities.multiplyString("  ", xPosition);
	private String yPadding = Utilities.multiplyString("\n", yPosition);
	
	private static String direction = "Left";		//The direction that the sprite is facing
	
	/**
	 * Object constructor:
	 * Define character type when a new Enemy_Object is created. (i.e Rat, Fish, etc)
	 * Modifiable with different characteristics (attacks, stats, etc)
	 * 
	 * @param enemy
	 * 			The type of enemy to display (Will affect sprite used & move type)
	 */
	public Enemy_Object(String enemy) 
	{
		enemyType = enemy;
	}
	
	
	/**
	 * Main class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	
	/**
	 * Prints the required sprite from a text file.
	 *  
	 * @param enemySprite
	 * 					The type of sprite to print.
	 * @throws FileNotFoundException 
	 */
	public void getSprite(String enemySprite)
	{
		//Update position on screen using newlines and spaces
		xPadding = Utilities.multiplyString("  ", xPosition);
		yPadding = Utilities.multiplyString("\n", yPosition);
		
		//Select the sprite frame to print based on the argument "enemySprite"
		//Uses 'enemyType' string to decide which folder to choose.
		switch(enemySprite) 
		{
			//Fight banner to be replaced with stats
			case("fight"):
				Utilities.printSprite("UI/Banner/fight", "", "");;
				break;
			
			case("stand"):
				Utilities.printSprite(enemyType + "/Stand/stand_" + direction, xPadding, yPadding);
				break;
			
			case("taunt1"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_One_" + direction, xPadding, yPadding);
				break;
				
			case("taunt2"):
				Utilities.printSprite(enemyType + "/Taunt/taunt_Two_" + direction, xPadding, yPadding);
				break;
		
			case("hurt"):
				Utilities.printSprite(enemyType + "/Hurt/hurt_" + direction, xPadding, yPadding);
				break;
			
			case("attack1"):
				Utilities.printSprite(enemyType + "/Attack/attack_One", xPadding, yPadding);
				break;
				
			case("attack2"):
				Utilities.printSprite(enemyType + "/Attack/attack_Two", xPadding, yPadding);
				break;
				
			case("attack3"):
				Utilities.printSprite(enemyType + "/Attack/attack_Three", xPadding, yPadding);
				break;
			
<<<<<<< HEAD
			case("dead"):
				Utilities.printSprite(enemyType + "/Dead/dead_" + direction, xPadding, yPadding);
				break;
			
=======
>>>>>>> master
			case("run_1"):
				Utilities.printSprite(enemyType + "/Run/run_One_" + direction, xPadding, yPadding);
				break;
			
			case("run_2"):
				Utilities.printSprite(enemyType + "/Run/run_Two_" + direction, xPadding, yPadding);
				break;
			
			default:
				System.out.println("Error: No move found.");
				break;
		}
<<<<<<< HEAD
	} //End of getSprite
=======
	} 
	
	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void enemyMove(Duck_Object player, String move)
	{
		
		move = move.toLowerCase();
		
		if (move.contains("taunt")) {
			taunt(player);
		}else if (move.contains("attack")) {
			attack(player);
		}
		
	}//End of enemyMove
	
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	public void swipe(Duck_Object player)
	{
		
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack1");
		player.getSprite("stand");
		Utilities.waitMilliseconds(300);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack2");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack3");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
	}//End of swipe
>>>>>>> master
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
<<<<<<< HEAD
	public boolean enemyMove(Duck_Object player) throws IOException, InterruptedException {
		
		Random random = new Random();
		int move = random.nextInt(2);
		
		if (move == 0) {
			attack(player);
		}else if (move == 1) {
			taunt(player);
		}
		boolean inBattle = finishBattle(player, move);
		
		return inBattle;
		
		
	}//End of enemyMove
	
=======
	public void run(int numTimes, int xDirection, int yDirection, Duck_Object player) throws IOException, InterruptedException 
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("run_1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("run_2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}
		
	}//End of run
>>>>>>> master
	
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
<<<<<<< HEAD
	public void swipe(Duck_Object player) throws IOException, InterruptedException {
		
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack1");
		player.getSprite("stand");
		Utilities.waitMilliseconds(300);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack2");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
		getSprite("fight");
		getSprite("attack3");
		player.getSprite("hurt");
		Utilities.waitMilliseconds(100);
		Utilities.clearConsole();
		
	}//End of swipe
	
	/**
	 * 
	 * @param numTimes
	 * @param xDirection
	 * @param yDirection
	 * @param enemy
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void run(int numTimes, int xDirection, int yDirection, Duck_Object player)
	{
		if (xDirection == 1) {
			direction = "Right";
		}else {
			direction = "Left";
		}
		
		for (int i = 0; i < numTimes; i++) {
			
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("run_1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("run_2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(20);
			xPosition += xDirection;
			yPosition += yDirection;
			Utilities.clearConsole();
		}
		
	}//End of run
	
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	public void attack(Duck_Object player)
	{
		
		Random rand = new Random();
		int accuracyChance = rand.nextInt(100) + 1;
		int criticalHitChance = rand.nextInt(100) +1;
		boolean landed = true;
		boolean critical = true;
		
		if (accuracyChance <= accuracyPoints) {
			landed = true;
		}
		else {
			landed = false;
		}
		
		if (criticalHitChance <= criticalHitPoints) {
			critical = true;
		}
		else {
			critical = false;
		}
		
		double damage;
		damage = (attackPoints * 2.5) - player.getDefence();
		double playerHealth = player.getHealth();
		if (critical) {
			damage = damage * 1.5;
		}
		if (landed) {
			double newHealth = playerHealth - damage;
			player.setHealth(Math.round(newHealth));
		}
		
		System.out.println("The enemy attacked you...");
		Utilities.waitMilliseconds(500);
		
		
		run(13, -1, 0, player);
		swipe(player);
		run(13, +1, 0, player);
		run(0, -1, 0, player);
		
		if (landed) {
			if (critical) {
				System.out.println("It's a critical hit!");
			}
			System.out.print("The enemy dealt ");
			System.out.print(Math.round(damage));
			System.out.println(" damage to you!");
		}
		
		else {
			System.out.println("The enemy missed!");
		}
		Utilities.waitMilliseconds(2000);
	}//End of attack
	
	/**
	 * 
	 * @param player
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Duck_Object player) throws IOException, InterruptedException
	{
		
		double playerAttack = player.getAttack();
		double playerDefence = player.getDefence();
		player.setAttack(playerAttack + 5);
		player.setDefence(playerDefence - 5);
		
		
=======
	public void attack(Duck_Object player) throws IOException, InterruptedException 
	{
		run(13, -1, 0, player);
		swipe(player);
		run(13, +1, 0, player);
		run(0, -1, 0, player);
	}//End of attack
	
	/**
	 * 
	 * @param player
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void taunt(Duck_Object player)
	{
>>>>>>> master
		getSprite("fight");
		getSprite("stand");
		player.getSprite("stand");
		Utilities.waitMilliseconds(400);
		Utilities.clearConsole();
		
		for(int i = 0; i <= 3; i++)
		{	
			getSprite("fight");
			getSprite("taunt1");
			player.getSprite("stand");
<<<<<<< HEAD
			System.out.println("The enemy taunted you...");
=======
>>>>>>> master
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt2");
			player.getSprite("stand");
<<<<<<< HEAD
			System.out.println("The enemy taunted you...");
			Utilities.waitMilliseconds(50	);
			Utilities.clearConsole();
		}
		System.out.println("Your attack has increased!");
		System.out.println("Your defence has decreased!");
		Utilities.waitMilliseconds(2000);
	}
	
	public void flinch(Duck_Object player) throws IOException, InterruptedException
	{	
		getSprite("fight");
		getSprite("stand");
		player.getSprite("stand");
		Utilities.waitMilliseconds(400);
		Utilities.clearConsole();
		
		for(int i = 0; i <= 3; i++)
		{	
			getSprite("fight");
			getSprite("taunt1");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50);
			Utilities.clearConsole();
			
			getSprite("fight");
			getSprite("taunt2");
			player.getSprite("stand");
			Utilities.waitMilliseconds(50	);
			Utilities.clearConsole();
		}
	}
	
	public void resetStats() {
		
		healthPoints = HEALTH_POINTS + 0;
		manaPoints = MANA_POINTS + 0;
		attackPoints = ATTACK_POINTS + 0;
		defencePoints = DEFENCE_POINTS + 0;
		speedPoints = SPEED_POINTS + 0;
		accuracyPoints = ACCURACY_POINTS + 0;
		criticalHitPoints = CRITICAL_HIT_POINTS + 0;
		
	}
	
	private boolean finishBattle(Duck_Object player, int move) throws FileNotFoundException {
		
		double playerHealth = player.getHealth();
		
		/*
		if (move == 2) {
			System.out.println("The enemy ran away from battle...");
			resetStats();
			player.resetStats();
			return false;
		}*/
		
		/*else*/ if (playerHealth <= 0) {
			Utilities.clearConsole();
			getSprite("stand");
			player.getSprite("dead");
			System.out.println("The enemy knocked you out!");
			Utilities.waitMilliseconds(1000);
			System.out.println("Your consciousness slowly fades from this world...");
			Utilities.waitMilliseconds(1000);
			//resetStats();
			//player.resetStats();
			System.out.println("The battle has ended.");
			Utilities.waitMilliseconds(1200);
			System.exit(0);
			return false;
		}
		
		else {
			return true;
		}
		
		
	}
	
	public double getDefence() {
		return defencePoints;
	}
	
	public double getCriticalHit() {
		return criticalHitPoints;
	}


	public double getAttack() {
		return attackPoints;
	}

	public double getHealth() {
		return healthPoints;
	}
	public double getMana() {
		return manaPoints;
	}
	
	public double getSpeed() {
		return speedPoints;
	}
	
	public double getAccuracy() {	
		return accuracyPoints;	
	}
	
	public int getXP() {	
		return giveXP;	
	}
	
	public int getMoney() {	
		return giveMoney;	
	}
	
	
	
	public void setDefence(double newValue) {
		defencePoints = newValue;
	}
	
	public void setCriticalHit(double newValue) {
		criticalHitPoints = newValue;	
	}

	public void setAttack(double newValue) {
		attackPoints = newValue;
	}

	public void setHealth(double newValue) {
		healthPoints = newValue;
	}

	public void setMana(double newValue) {
		manaPoints = newValue;
	}
	
	public void setSpeed(double newValue) {
		speedPoints = newValue;
	}
	
	public void setAccuracy(double newValue) {	
		accuracyPoints = newValue;	
=======
			Utilities.waitMilliseconds(50	);
			Utilities.clearConsole();
		}
>>>>>>> master
	}
	
}