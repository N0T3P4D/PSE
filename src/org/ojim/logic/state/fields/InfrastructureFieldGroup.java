package org.ojim.logic.state.fields;

public class InfrastructureFieldGroup extends FieldGroup {

	private int[] factors;

	public InfrastructureFieldGroup() {
		super(FieldGroup.INFRASTRUCTURE);
	}

	public void setFactors(int[] factors) {
		if (factors.length != this.getFields().length) {
			throw new IllegalArgumentException("The number of rents differs from the number of fields.");
		}
		this.factors = factors;
	}

	public int getFactor(int ownerOwns) {
		if (ownerOwns <= 0) {
			return 0;
		} else {
			return this.factors[ownerOwns - 1];
		}
	}
}
