package game;

import java.util.Scanner;

import fixtures.Room;

public class Main {

	public static void main(String[] args) {
		
		RoomManager rm = new RoomManager();
		rm.init();
		Player p = new Player(rm.getStartingRoom());
		boolean isRunning = true;
		Scanner s = new Scanner(System.in);
		
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
		System.out.println(p.getCurrentRoom().getName());
		System.out.println();
		System.out.println(p.getCurrentRoom().getLongDescription());
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
					p.setCurrentRoom(curRoom.getExit(target));
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
		default: { // All other commands are ignored
			System.out.println("Not a valid command.");
		}
		}
	}
	
}
