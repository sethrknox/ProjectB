package game;

import fixtures.Room;

public class Player {
	private Room currentRoom;
	
	public Player() {}
	public Player(Room startingRoom) {
		this.currentRoom = startingRoom;
	}
	
	public void setCurrentRoom(Room r) {
		this.currentRoom = r;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
}
