package map;

import java.awt.Point;
import java.util.Scanner;

import map.TextRoom.Entity;
import utils.Utilities;

public class TextLevel {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static Scanner	_scanner			= new Scanner(System.in);
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		DEFAULT_LEVEL_SIZE	= 3;
	private final int		DEFAULT_ROOM_SIZE	= 5;
	
	private TextRoom[][]	roomArray;
	private Point			currentRoomPoint;
	private int				levelWidth, levelHeight;
	private int				roomSize;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public TextLevel() {
		
		this.levelWidth = DEFAULT_LEVEL_SIZE;
		this.levelHeight = DEFAULT_LEVEL_SIZE;
		this.roomSize = DEFAULT_ROOM_SIZE;
		
		this.genRoomArray();
		
		this.currentRoomPoint = new Point(0, 0);
		this.roomAt(this.currentRoomPoint).placeEntity(new Point(this.roomSize / 2 + 1, this.roomSize / 2 + 1), Entity.PLAYER);
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	private void genRoomArray() {
		
		this.roomArray = new TextRoom[this.levelWidth][this.levelHeight];
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				this.roomArray[x][y] = new TextRoom(this.roomSize);
				
			}
		}
		
		this.placeAllConnectingDoors();
		
	}
	
	private void placeAllConnectingDoors() {
		
		Point[] doors;
		
		for (int y = 0; y < this.levelHeight; y++) {
			for (int x = 0; x < this.levelWidth; x++) {
				
				doors = new Point[4];
				
				try {
					if (this.roomArray[x][y - 1] != null) {
						doors[0] = new Point((this.roomSize + 2) / 2, 0);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x][y + 1] != null) {
						doors[1] = new Point((this.roomSize + 2) / 2, (this.roomSize + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x - 1][y] != null) {
						doors[2] = new Point(0, (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				try {
					if (this.roomArray[x + 1][y] != null) {
						doors[3] = new Point((this.roomSize + 1), (this.roomSize + 2) / 2);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
				this.roomArray[x][y].placeDoors(doors);
				
			}
		}
		
	}
	
	/**
	 * 
	 * @param direction
	 *            Must be one of: 'u', 'd', 'l', or 'r'.
	 */
	public void moveRoom_Direction(char direction) {
		
		Point newPlayerPoint = new Point(TextRoom.Entity.PLAYER.POS);
		
		this.roomAt(this.currentRoomPoint).removeEntity(TextRoom.Entity.PLAYER.POS);
		
		switch (direction) {
			
			case 'u':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x, this.currentRoomPoint.y - 1);
				newPlayerPoint.y = this.roomSize + 1;
				break;
			
			case 'd':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x, this.currentRoomPoint.y + 1);
				newPlayerPoint.y = 0;
				break;
			
			case 'l':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x - 1, this.currentRoomPoint.y);
				newPlayerPoint.x = this.roomSize + 1;
				break;
			
			case 'r':
				this.currentRoomPoint = new Point(this.currentRoomPoint.x + 1, this.currentRoomPoint.y);
				newPlayerPoint.x = 0;
				break;
			
		}
		
		this.roomAt(this.currentRoomPoint).placeEntity(newPlayerPoint, TextRoom.Entity.PLAYER);
		
	}
	
	private TextRoom roomAt(Point position) {
		return this.roomArray[position.x][position.y];
	}
	
	/**
	 * Runs through a loop, where it displays the room and asks for input
	 * repeatedly.
	 */
	public void moveLoop() {
		
		String input;
		
		/*
		 * Loop:
		 * 
		 * Draws the room and lets the user move.
		 * 
		 * Currently does not exit.
		 * 
		 */
		while (true) {
			
			// Draw the room and ask the user for input
			Utilities.clearConsole();
			this.roomAt(this.currentRoomPoint).draw();
			System.out.print("\nAction \t: ");
			input = _scanner.nextLine().toUpperCase();
			
			// Do the action inputed by the user
			switch (input) {
				
				// Moving...
				
				case "W":
				case "NORTH":
					if (TextRoom.Entity.PLAYER.POS.y == 0) {
						this.moveRoom_Direction('u');
					} else {
						this.roomAt(this.currentRoomPoint).moveEntity(Entity.PLAYER.POS,
								new Point(Entity.PLAYER.POS.x, Entity.PLAYER.POS.y - 1));
					}
					break;
				
				case "S":
				case "SOUTH":
					if (TextRoom.Entity.PLAYER.POS.y == this.roomSize + 1) {
						this.moveRoom_Direction('d');
					} else {
						this.roomAt(this.currentRoomPoint).moveEntity(Entity.PLAYER.POS,
								new Point(Entity.PLAYER.POS.x, Entity.PLAYER.POS.y + 1));
					}
					break;
				
				case "D":
				case "EAST":
					if (TextRoom.Entity.PLAYER.POS.x == this.roomSize + 1) {
						this.moveRoom_Direction('r');
					} else {
						this.roomAt(this.currentRoomPoint).moveEntity(Entity.PLAYER.POS,
								new Point(Entity.PLAYER.POS.x + 1, Entity.PLAYER.POS.y));
					}
					break;
				
				case "A":
				case "WEST":
					if (TextRoom.Entity.PLAYER.POS.x == 0) {
						this.moveRoom_Direction('l');
					} else {
						this.roomAt(this.currentRoomPoint).moveEntity(Entity.PLAYER.POS,
								new Point(Entity.PLAYER.POS.x - 1, Entity.PLAYER.POS.y));
					}
					break;
				
				// Room commands...
				
				case "SAVE":
					this.roomAt(this.currentRoomPoint).saveToTextFile();
					break;
				
				// Default
				
				default:
					System.out.println("...What?");
					Utilities.waitMilliseconds(300);
					break;
				
			}
			
		}
		
	}
	
	/**
	 * Cleans up the static resources
	 */
	public static void cleanup() {
		
		_scanner.close();
		
	}
	
}