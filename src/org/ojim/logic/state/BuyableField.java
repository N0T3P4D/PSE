package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;

public class BuyableField extends Field {

	private FieldGroup fieldGroup;
	private int price;
	private Player owner;
	private boolean mortgaged;
	
	public BuyableField(String name, int position, int price) {
		super(name, position);
		this.price = price;
		// TODO Auto-generated constructor stub
	}
	
	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public int getRent() {
		return 42;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public boolean isMortgaged() {
		return this.mortgaged;
	}
	
	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
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
	
	public FieldGroup getFieldGroup() {
		return this.fieldGroup;
	}
	
	public Player getOwner() {
		return this.owner;
	}
}
