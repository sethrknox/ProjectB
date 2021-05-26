package game;

import java.util.ArrayList;
import java.util.List;

import fixtures.Inventory;
import fixtures.Item;
import fixtures.Room;

public class Player {
	private Room currentRoom;
	private Inventory inventory;
	
	public Player() {
		this.currentRoom = null;
		this.inventory = new Inventory();
	}
	public Player(Room startingRoom) {
		this.currentRoom = startingRoom;
		this.inventory = new Inventory();
	}
	
	public void setCurrentRoom(Room r) {
		this.currentRoom = r;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}
