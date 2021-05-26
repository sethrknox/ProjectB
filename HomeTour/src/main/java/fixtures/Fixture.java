package fixtures;

public abstract class Fixture {
	private String name;
	private String shortDescription;
	private String longDescription;
	
	public Fixture() {}
	public Fixture(String name, String sd, String ld) {
		this.name = name;
		this.shortDescription = sd;
		this.longDescription = ld;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getShortDescription() {
		return this.shortDescription;
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
	@Override
	public String toString() {
		return "Fixture [name=" + name + ", shortDescription=" + shortDescription + ", longDescription="
				+ longDescription + "]";
	}
	
	
}
