package fixtures;

public class Room extends Fixture{
	Room[] exits;
	
	public Room(String name, String sd, String ld) {
		super(name, sd, ld);
		this.exits = new Room[4];
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
}
