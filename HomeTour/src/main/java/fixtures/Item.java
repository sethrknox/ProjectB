package fixtures;

public class Item extends Fixture {

	private boolean pickupable;
	
	public Item() {
		this.pickupable = true;
	}
	
	public Item(String name, String sd, String ld) {
		super(name, sd, ld);
		this.pickupable = true;
	}

	public boolean isPickupable() {
		return pickupable;
	}

	public void setPickupable(boolean pickupable) {
		this.pickupable = pickupable;
	}

	@Override
	public String toString() {
		return "Item [name=" + this.getName() + "]";
	}
	
	
}
