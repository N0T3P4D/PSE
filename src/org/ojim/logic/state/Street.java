package org.ojim.logic.state;

public class Street extends BuyableField {

	private int builtLevel;
	private int[] rentByLevel;
	
	public Street(String name, int position, int[] rentByLevel, int builtLevel) {
		super(name, position);
		this.builtLevel = builtLevel;
		this.rentByLevel = rentByLevel;
	}
	
	public Street(String name, int position, int[] rentByLevel) {
		super(name, position);
		this.builtLevel = 0;
		this.rentByLevel = rentByLevel;
	}

	//TODO: Noch benötigt?
	public int getBuiltLevel() {
		return this.builtLevel;
	}
	
	public int getNumberOfHotels() {
		return this.builtLevel / 5;
	}
	
	public int getNumberOfHouse() {
		return this.builtLevel % 5;
	}
	
	//xZise: Macht man nicht eher "+1 Upgrade" bzw. "-1 Upgrade"?
	public void upgrade(int level) {
		this.builtLevel = level;
	}
}
