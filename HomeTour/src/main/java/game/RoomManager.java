package game;

import fixtures.Door;
import fixtures.Inventory;
import fixtures.Item;
import fixtures.Room;

public class RoomManager {
	private Room startingRoom;
	private Room[] rooms;
	
	public RoomManager() {
		rooms = new Room[22];
	}
	
	public void init() {
	
//		Room temp1 = new Room("Room 1", "1 sd", "1 ld");
//		this.rooms[0] = temp1;
//		this.startingRoom = temp1;
//		
//		Room temp2 = new Room("Room 2", "2 sd", "2 ld");
//		this.rooms[1] = temp2;
//		
//		temp1.setExit("east", temp2);
//		temp2.setExit("west", temp1);
	
		// Ground floor/first floor
		Room frontEntrance = new Room("The Front Entrance",
				"the entryway of the mansion",
				"ld");
		this.rooms[0] = frontEntrance;
		Room den = new Room("The Den",
				"a small den",
				"ld");
		this.rooms[1] = den;
		Room musicRoom = new Room("The Music Room",
				"a music room",
				"ld");
		this.rooms[2] = musicRoom;
		Room livingRoom = new Room("The Living Room",
				"a large living room",
				"ld");
		this.rooms[3] = livingRoom;
		Room firstBath = new Room("The First Floor Bathroom",
				"a small bathroom",
				"ld");
		this.rooms[4] = firstBath;
		Room diningRoom = new Room("The Dining Room",
				"a large dining room",
				"ld");
		this.rooms[5] = diningRoom;
		Room kitchen = new Room("The Kitchen",
				"a kitchen",
				"ld");
		this.rooms[6] = kitchen;
		
		// Staircases
		Room upperStairs = new Room("Stairs To Second Floor",
				"stairs between the front entrance and second floor",
				"ld");
		this.rooms[7] = upperStairs;
		Room lowerStairs = new Room("Stairs To Cellar",
				"stairs between the kitchen and cellar",
				"ld");
		this.rooms[8] = lowerStairs;
		
		// Second floor/upstairs
		Room topOfStairs = new Room("Second Floor Entrance",
				"the entrance to the second floor",
				"ld");
		this.rooms[9] = topOfStairs;
		Room firstBedroom = new Room("The Children's Bedroom",
				"a small bedroom for children",
				"ld");
		this.rooms[10] = firstBedroom;
		Room firstStudy = new Room("The Children's Study",
				"a small study",
				"ld");
		this.rooms[11] = firstStudy;
		Room secondBedroom = new Room("The Master Bedroom",
				"a large master bedroom",
				"ld");
		this.rooms[12] = secondBedroom;
		Room secondStudy = new Room("The Parent's Study",
				"a home office",
				"ld");
		this.rooms[13] = secondStudy;
		Room secondBathroom = new Room("The Master Bedroom's Bathroom",
				"the master bedroom's bathroom",
				"ld");
		this.rooms[14] = secondBathroom;
		Room library = new Room("The Library",
				"a modest library",
				"ld");
		this.rooms[15] = library;
		Room nook = new Room("The Nook",
				"a small nook within the library",
				"ld");
		this.rooms[16] = nook;
		Room balcony = new Room("The Balcony",
				"a balcony above the backyard",
				"ld");
		this.rooms[17] = balcony;
		
		// Cellar/downstairs
		Room bottomOfStairs = new Room("The Cellar Entrance",
				"the entrance to the cellar",
				"ld");
		this.rooms[18] = bottomOfStairs;
		Room cellar = new Room("The Cellar",
				"the dark cellar",
				"ld");
		this.rooms[19] = cellar;
		
		// Outside
		Room outside = new Room("The Outside",
				"outside",
				"ld");
		this.rooms[20] = outside;
		
		// Populate first floor exits
		frontEntrance.setExit("north", upperStairs);
		frontEntrance.setExit("east", den);
		//frontEntrance.setExit("south", outside);
		frontEntrance.setExit("west", musicRoom);
		
		den.setExit("north", kitchen);
		den.setExit("west", frontEntrance);
		
		musicRoom.setExit("north", livingRoom);
		musicRoom.setExit("east", frontEntrance);
		
		livingRoom.setExit("west", firstBath);
		livingRoom.setExit("east", diningRoom);
		livingRoom.setExit("south", musicRoom);
		
		firstBath.setExit("east", livingRoom);
		
		diningRoom.setExit("west", livingRoom);
		diningRoom.setExit("east", kitchen);
		
		kitchen.setExit("east", lowerStairs);
		kitchen.setExit("south", den);
		kitchen.setExit("west", diningRoom);
		
		// Populate staircase exits
		upperStairs.setExit("north", topOfStairs);
		upperStairs.setExit("south", frontEntrance);
		
		lowerStairs.setExit("west", kitchen);
		lowerStairs.setExit("east", bottomOfStairs);
		
		// Populate second floor exits
		topOfStairs.setExit("north", library);
		topOfStairs.setExit("east", firstBedroom);
		topOfStairs.setExit("south", upperStairs);
		topOfStairs.setExit("west", secondBedroom);
		
		firstBedroom.setExit("south", firstStudy);
		firstBedroom.setExit("west", topOfStairs);
		
		firstStudy.setExit("north", firstBedroom);
		
		secondBedroom.setExit("north", secondBathroom);
		secondBedroom.setExit("east", topOfStairs);
		secondBedroom.setExit("south", secondStudy);
		
		secondStudy.setExit("north", secondBedroom);
		
		secondBathroom.setExit("south", secondBedroom);
		
		library.setExit("north", balcony);
		library.setExit("east", nook);
		library.setExit("south", topOfStairs);
		
		nook.setExit("west", library);
		
		balcony.setExit("south", library);
		
		// Populate basement
		bottomOfStairs.setExit("south", cellar);
		bottomOfStairs.setExit("west", lowerStairs);
		
		cellar.setExit("north", bottomOfStairs);
		
		// Populate outside
		outside.setExit("north", frontEntrance);
		
		
		
		// Add doors between every pair of rooms
		// 
		Door entranceOutside = new Door();
		Door entranceMusic = new Door();
		Door entranceDen = new Door();
		
		Door livingMusic = new Door();
		Door livingBath = new Door();
		Door livingDining = new Door();
		
		Door kitchenDining = new Door();
		Door kitchenDen = new Door();
		
		Door topOfStairsLibrary = new Door();
		Door topOfStairsFirstBed = new Door();
		Door topOfStairsSecondBed = new Door();
		
		Door firstBedStudy = new Door();
		
		Door secondBedStudy = new Door();
		Door secondBedBath = new Door();
		
		Door libraryNook = new Door();
		Door libraryBalcony = new Door();
		
		Door bottomOfStairsCellar = new Door("Cellar Door", "sd", "ld");
		
		// Populate first floor doors
		frontEntrance.setDoor("south", entranceOutside);
		frontEntrance.setDoor("east", entranceDen);
		//frontEntrance.setDoor("south", outside);
		frontEntrance.setDoor("west", entranceMusic);
		
		den.setDoor("north", kitchenDen);
		den.setDoor("west", entranceDen);
		
		musicRoom.setDoor("north", livingMusic);
		musicRoom.setDoor("east", entranceMusic);
		
		livingRoom.setDoor("west", livingBath);
		livingRoom.setDoor("east", livingDining);
		livingRoom.setDoor("south", livingMusic);
		
		firstBath.setDoor("east", livingBath);
		
		diningRoom.setDoor("west", livingDining);
		diningRoom.setDoor("east", kitchenDining);
		
		kitchen.setDoor("south", kitchenDen);
		kitchen.setDoor("west", kitchenDining);
		
		// Populate second floor doors
		topOfStairs.setDoor("north", topOfStairsLibrary);
		topOfStairs.setDoor("east", topOfStairsFirstBed);
		topOfStairs.setDoor("west", topOfStairsSecondBed);
		
		firstBedroom.setDoor("south", firstBedStudy);
		firstBedroom.setDoor("west", topOfStairsFirstBed);
		
		firstStudy.setDoor("north", firstBedStudy);
		
		secondBedroom.setDoor("north", secondBedBath);
		secondBedroom.setDoor("east", topOfStairsSecondBed);
		secondBedroom.setDoor("south", secondBedStudy);
		
		secondStudy.setDoor("north", secondBedStudy);
		
		secondBathroom.setDoor("south", secondBedBath);
		
		library.setDoor("north", libraryBalcony);
		library.setDoor("east", libraryNook);
		library.setDoor("south", topOfStairsLibrary);
		
		nook.setDoor("west", libraryNook);
		
		balcony.setDoor("south", libraryBalcony);
		
		// Populate basement doors
		bottomOfStairs.setDoor("south", bottomOfStairsCellar);
		
		cellar.setDoor("north", bottomOfStairsCellar);
		
		// Populate outside doors
		outside.setDoor("north", entranceOutside);
				
		this.startingRoom = outside;
		
		// Adding items
		Item key = new Item("Cellar Key", "The key to the cellar", "ld");
		Inventory temp = secondStudy.getInventory();
		temp.addItem(key);
		
		// Open some of the doors to start
		entranceDen.setStatus(true);
		kitchenDen.setStatus(true);
		topOfStairsLibrary.setStatus(true);
		
		// Lock the cellar door
		bottomOfStairsCellar.setLock(true);
	}
	
	public Room getStartingRoom() {
		return this.startingRoom;
	}
}
