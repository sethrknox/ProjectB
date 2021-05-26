package fixtures;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	 private List<Item> inventory;
	 
	 public Inventory() {
		 this.inventory = new ArrayList<Item>();
	 }
	 
	 public List<Item> getItems() {
			return this.inventory;
		}
		
		public void addItem(Item i) {
			this.inventory.add(i);
		}
		
		public void removeItem(Item i) {
			this.inventory.remove(i);
		}
		
		public Item inspectItem(String name) {
			for (Item i : this.inventory) {
				if (i.getName().equals(name)) {
					return i;
				}
			}
			return null;
		}
		
}
