package org.ojim.logic.state.fields;

public class StationFieldGroup extends FieldGroup {

	public int[] rent;
	
	public StationFieldGroup(int[] rent) {
		super(FieldGroup.STATIONS);
		this.rent = rent;
	}
	
	public int getRent(int ownerOwns) {
		return this.rent[ownerOwns];
	}

}
