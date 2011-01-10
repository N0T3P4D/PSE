package org.ojim.logic.state;

public class Street extends BuyableField {

	private int buildLevel;
	
	//TODO: Noch benötigt?
	public int getBuildLevel() {
		return this.buildLevel;
	}
	
	public int getNumberOfHotels() {
		return this.buildLevel / 5;
	}
	
	public int getNumberOfHouse() {
		return this.buildLevel % 5;
	}
	
}
