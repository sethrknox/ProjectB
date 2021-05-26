package game;

import java.util.Scanner;

import fixtures.Item;
import fixtures.Room;
import fixtures.Inventory;

public class Main {

	public static void main(String[] args) {
		
		RoomManager rm = new RoomManager();
		rm.init();
		Player p = new Player(rm.getStartingRoom());
		boolean isRunning = true;
		Scanner s = new Scanner(System.in);
		
		printCommands();
		System.out.println("You are standing outside of an abandoned mansion. The gate behind you is closed. "
				+ "There is a large door in front of you to enter the building.");
		while(isRunning) {
			printRoom(p);
			
			System.out.println("Exits:");
			Room[] exits = p.getCurrentRoom().getExits();
			if (exits[0] != null) {
				System.out.println("north: " + exits[0].getShortDescription());
			}
			if (exits[1] != null) {
				System.out.println("east: " + exits[1].getShortDescription());
			}
			if (exits[2] != null) {
				System.out.println("south: " + exits[2].getShortDescription());
			}
			if (exits[3] != null) {
				System.out.println("west: " + exits[3].getShortDescription());
			}
			
			//System.out.println("PLAYER INV: " + p.getInventory().getItems());
			
			
			String[] input = collectInput(s);
//			System.out.println(input[0]);
//			System.out.println(input[1]);
			
			// Check if command had action and target
			if (input[0].equals("quit")) {
				isRunning = false;
			} else {
				parse(input, p);
			}
		}
		
		s.close();
	}
	
	private static void printRoom(Player p) {
		System.out.println("Location: " + p.getCurrentRoom().getName());
		System.out.println();
		System.out.println(p.getCurrentRoom().getLongDescription());
		System.out.println();
	}
	
	private static void printCommands() {
		System.out.println("Commands");
		System.out.println("go <direction>");
		System.out.println("open <direction> door");
		System.out.println("close <direction> door");
		System.out.println("inspect room");
		System.out.println("inspect inventory");
		System.out.println("inspect <item>");
		System.out.println("pickup <item>");
		System.out.println("drop <item>");
		System.out.println("use <item> on <object>");
		System.out.println("quit");
		System.out.println();
	}
	
	private static String[] collectInput(Scanner s) {
		
		String command = s.nextLine();
		String[] result = new String[2];
		// Find the space between the action and target
		int firstSpace = command.indexOf(" ");
		// No space found -> single word command
		if (firstSpace == -1) {
			result[0] = command;
			result[1] = "";
		} else {
			result[0] = command.substring(0, firstSpace);
			result[1] = command.substring(firstSpace + 1);
		}
		return result;
	}
	
	private static void parse(String[] command, Player p) {
		
		String action = command[0];
		String target = command[1];
		Room curRoom = p.getCurrentRoom();
		switch(action) {
		case "go": { // Command to move between rooms based on direction
			switch(target) {
			case "north", "east", "south", "west": {
				if (curRoom.getExit(target) != null) {
					// check if there is a door to handle, otherwise move player to next room
					if (curRoom.getDoor(target) != null) {
						if (curRoom.getDoor(target).getStatus()) {
							// When the player enters the mansion for the first time, the entrance door shuts behind them to lock them inside.
							if (curRoom.getName().equals("The Outside") &&
									curRoom.getExit(target).getName().equals("The Front Entrance")) {
								System.out.println("The door slams shut behind you and locks.");
							}
							p.setCurrentRoom(curRoom.getExit(target));
						} else {
							System.out.println("The door is closed");
						}
					} else {
						p.setCurrentRoom(curRoom.getExit(target));
					}
				} else {
					System.out.println("Cannot move in that direction");
				}
				break;
			}
			default: {
				System.out.println("Cannot move in that direction");
			}
			}
			break;
		}
		case "open", "close": { // Command to open doors between rooms
			// edit target down to just a direction without 'door' keyword
			int firstSpace = target.indexOf(" ");
			String dir = "";
			if (firstSpace != -1) {
				dir = target.substring(0, firstSpace);
			}
			 
			switch(dir) {
			case "north", "east", "south", "west": {
				if (curRoom.getDoor(dir) != null) {
					if (action.equals("open")) {
						if (curRoom.getDoor(dir).getStatus()) {
							System.out.println("The door is already open");
						} else if(curRoom.getDoor(dir).isLocked()) {
							System.out.println("The door is locked");
						} else {
							curRoom.getDoor(dir).setStatus(true);
							System.out.println("You opened the door");
						}
					}
					if (action.equals("close")) {
						if (!(curRoom.getDoor(dir).getStatus())) {
							System.out.println("The door is already closed");
						} else {
							curRoom.getDoor(dir).setStatus(false);
							System.out.println("You closed the door");
						}
					}
				} else {
					System.out.println("There is no door there");
				}
				break;
			}
			default: {
				System.out.println("Cannot open that");
			}
			}
			break;
		}
		case "inspect": { // Command to inspect room or inventory
			switch(target) {
			case "room": { // Inspecting the room
				System.out.println(curRoom.getInventory().getItems());
				break;
			}
			case "inventory": { // Inspect inventory
				System.out.println(p.getInventory().getItems());
				break;
			}
			default : { // Inspecting an item in inventory
				Item temp = p.getInventory().inspectItem(target);
				if (temp != null) {
					System.out.println(temp.getShortDescription());
				} else {
					System.out.println("That item is not in your inventory");
				}
				break;
			}
			}
			break;
		}
		case "pickup" : { // Command to pickup item in room
			Item temp = curRoom.getInventory().inspectItem(target);
			if (temp != null) {
				p.getInventory().addItem(temp);
				curRoom.getInventory().removeItem(temp);
				System.out.println("You picked up " + temp);
			} else {
				System.out.println("That item is not in this room");
			}
			break;
		}
		case "drop" : { // Command to drop item in room
			Item temp = p.getInventory().inspectItem(target);
			if (temp != null) {
				p.getInventory().removeItem(temp);
				curRoom.getInventory().addItem(temp);
				System.out.println("You dropped up " + temp);
			} else {
				System.out.println("That item is not in your inventory");
			}
			break;
		}
		case "use" : {
			String delimeter = " on ";
			int index = target.indexOf(delimeter);
			if (index != -1) {
				// Get the two objects being used and targeted
				String object1 = target.substring(0, index);
				String object2 = target.substring(index+4);
				
				switch(object1) {
				case "Cellar Key": {
					boolean b1 = (p.getInventory().inspectItem(object1) != null);
					boolean b2 = (curRoom.hasDoor(object2));
					boolean b3 = object2.equals("Cellar Door");
					
					// if player has key, key matches door, and door is in the room, then unlock it
					if (b1 && b2 && b3) {
						curRoom.openDoor(object2);
						System.out.println("You unlocked the door");
					} else if (!b1){
						System.out.println("You do not have that key");
					} else if (!b2) {
						System.out.println("That door is not in this room");
					} else if (!b3) {
						System.out.println("Your key does not fit that door");
					}
					break;
				}
				default: { // All other "use" cases that should not exist
					System.out.println("You cannot do that");
				}
				}
			} else {
				System.out.println("You cannot do that");
			}
			break;
		}
		default: { // All other commands are ignored
			System.out.println("Not a valid command.");
		}
		}
	}
	
}
