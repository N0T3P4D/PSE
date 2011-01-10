package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;

public class BuyableField extends Field {

	private FieldGroup fieldGroup;
	
	public BuyableField(String name, int position) {
		super(name, position);
		// TODO Auto-generated constructor stub
	}
	
	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	private Player owner;
	
	public int getRent() {
		return 42;
	}
	
	/**
	 * Pays the rent from the player to the owner.
	 * @param player the player who got to the field.
	 */
	public void payRent(Player player) {
		if (!this.owner.equals(player)) {
			Bank.exchangeMoney(player, this.owner, this.getRent());
		}
	}
	
	public Player getOwner() {
		return this.owner;
	}
}
