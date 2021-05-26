package fixtures;

public class Door extends Fixture{
	
	private boolean isOpen;
	private boolean locked;
	
	public Door() {
		super("", "", "");
		this.isOpen = false;
		this.locked = false;
	}
	
	public Door(String name, String sd, String ld) {
		super(name, sd, ld);
		this.isOpen = false;
		this.locked = false;
	}

	public boolean getStatus() {
		return this.isOpen;
	}

	public void setStatus(boolean status) {
		this.isOpen = status;
	}
	
	public boolean isLocked() {
		return this.locked;
	}
	
	public void setLock(boolean b) {
		this.locked = b;
	}
}
