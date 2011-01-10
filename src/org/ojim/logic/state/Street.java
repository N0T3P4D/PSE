package org.ojim.logic.state;

public class Street extends BuyableField {

	private int builtLevel;
	private int[] rentByLevel;
	
	public Street(String name, int position, int[] rentByLevel, int builtLevel, int price) {
		super(name, position, price);
		this.builtLevel = builtLevel;
		this.rentByLevel = rentByLevel;
	}
	
	public Street(String name, int position, int[] rentByLevel, int price) {
		super(name, position, price);
		this.builtLevel = 0;
		this.rentByLevel = rentByLevel;
	}
	
	public int getBuiltLevel() {
		return this.builtLevel;
	}
	
	public int getRent() {
		return getRent(this.builtLevel);
	}
	
	public int getRent(int level) {
		//If no buildings here and all streets of the Group belong to the same owner, the rent is doubled 
		if(level == 0) {
			for(BuyableField field : getFieldGroup().getFields()) {
				if(!this.getOwner().equals(field.getOwner())){
					//Not all the same owner: normal rent
					return rentByLevel[0];
				}
			}
			//else: double rent
			return rentByLevel[0] * 2;
		} else {
			return rentByLevel[level];
		}
	}
	
	public void upgrade(int level) {
		this.builtLevel = level;
	}
	
	

}
