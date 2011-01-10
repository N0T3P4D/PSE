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
	
	public int getBuiltLevel() {
		return this.builtLevel;
	}
	
	public void upgrade(int level) {
		this.builtLevel = level;
	}
	
	

}
