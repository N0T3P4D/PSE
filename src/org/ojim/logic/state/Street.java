package org.ojim.logic.state;

public class Street extends BuyableField {

	private int builtLevel;
	private int[] rentByLevel;
	private int mortgagePrice;

	public Street(String name, int position, int[] rentByLevel, int builtLevel,
			int price, GameState state) {
		super(name, position, price, state);
		this.builtLevel = builtLevel;
		this.rentByLevel = rentByLevel;
	}

	public Street(String name, int position, int[] rentByLevel, int price,
			GameState state) {
		this(name, position, rentByLevel, 0, price, state);
	}

	// xZise: Please activate the UTF-8 encoding in Eclipse!
	// TODO: Noch benötigt?
	public int getBuiltLevel() {
		return this.builtLevel;
	}

	public int getRent() {
		return getRent(this.builtLevel);
	}

	public int getRent(int level) {
		// If no buildings here and all streets of the Group belong to the same
		// owner, the rent is doubled
		if (level == 0) {
			for (BuyableField field : getFieldGroup().getFields()) {
				if (!this.getOwner().equals(field.getOwner())) {
					// Not all the same owner: normal rent
					return rentByLevel[0];
				}
			}
			// else: double rent
			return rentByLevel[0] * 2;
		} else {
			return rentByLevel[level];
		}
	}

	public int getNumberOfHotels() {
		return this.builtLevel / 5;
	}

	public int getNumberOfHouse() {
		return this.builtLevel % 5;
	}

	// xZise: Macht man nicht eher "+1 Upgrade" bzw. "-1 Upgrade"?

	public boolean upgrade(int level) {
		int newLevel = this.builtLevel + level;
		if(newLevel < 0 || newLevel > 5) {
			return false;
		}
		this.builtLevel = newLevel;
		return true;
	}

	public int getMortgagePrice() {
		return this.mortgagePrice;
	}
}
