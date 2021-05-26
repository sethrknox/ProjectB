package fixtures;

import java.util.ArrayList;
import java.util.List;

public class Room extends Fixture{
	Room[] exits;
	Door[] doors;
	private Inventory inventory;
	
	public Room(String name, String sd, String ld) {
		super(name, sd, ld);
		this.exits = new Room[4];
		this.doors = new Door[4];
		this.inventory = new Inventory();
	}

	public Room[] getExits() {
		return this.exits;
	}
	
	public Room getExit(String direction) {
		switch(direction) {
		case "north": {
			return this.exits[0];
		}
		case "east": {
			return this.exits[1];
		}
		case "south": {
			return this.exits[2];
		}
		case "west": {
			return this.exits[3];
		}
		default: {
			return null;
		}
		}
	}
	
	public void setExit(String direction, Room r) {
		switch(direction) {
		case "north": {
			this.exits[0] = r;
			break;
		}
		case "east": {
			this.exits[1] = r;
			break;
		}
		case "south": {
			this.exits[2] = r;
			break;
		}
		case "west": {
			this.exits[3] = r;
			break;
		}
		default: {}
		}
	}
	
	public Door getDoor(String direction) {
		switch(direction) {
		case "north": {
			return this.doors[0];
		}
		case "east": {
			return this.doors[1];
		}
		case "south": {
			return this.doors[2];
		}
		case "west": {
			return this.doors[3];
		}
		default: {
			return null;
		}
		}
	}
	
	public void setDoor(String direction, Door d) {
		switch(direction) {
		case "north": {
			this.doors[0] = d;
			break;
		}
		case "east": {
			this.doors[1] = d;
			break;
		}
		case "south": {
			this.doors[2] = d;
			break;
		}
		case "west": {
			this.doors[3] = d;
			break;
		}
		default: {}
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean hasDoor(String door) {
		for (Door d : this.doors) {
			if (d != null) {
				if (d.getName().equals(door)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void openDoor(String door) {
		for (Door d : this.doors) {
			if (d != null && d.getName().equals(door)) {
				d.setLock(false);
			}
		}
	}
	
}
