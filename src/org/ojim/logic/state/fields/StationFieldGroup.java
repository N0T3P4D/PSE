package org.ojim.logic.state.fields;

public class StationFieldGroup extends FieldGroup {

	private int[] rent;
	
	public StationFieldGroup() {
		super(FieldGroup.STATIONS);
	}
	
	/**
	 * Sets the rent levels. The number of rents has to be the same as the number of fields.
	 * @param rent The rent levels.
	 * @throws IllegalArgumentException If not enough/to much rents are set.
	 */
	public void setRent(int[] rent) throws IllegalArgumentException {
		if (rent.length != this.getFields().length) {
			throw new IllegalArgumentException("The number of rents differs from the number of fields.");
		}
		this.rent = rent;
	}

	public int getRent(int ownerOwns) {
		return this.rent[ownerOwns];
	}

}
