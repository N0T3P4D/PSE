package org.ojim.logic.state;

public class InfrastructureField extends BuyableField {

	public InfrastructureField(String name, int position, int price) {
		super(name, position, price);
	}

	@Override
	public int getRent() {
		// Calculate here

		int ownerOwns = 0;
		for (BuyableField field : this.getFieldGroup().getFields()) {
			if (field instanceof InfrastructureField) {
				if (((InfrastructureField) field).getOwner().equals(
						this.getOwner())) {
					ownerOwns++;
				}
			}
		}
		// TODO: (xZise) Add dice dependent rent.
		

		return 0;
	}

}
