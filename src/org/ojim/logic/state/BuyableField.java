package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.rules.Action;
import org.ojim.logic.rules.ActionPayFieldRent;
import org.ojim.logic.rules.FieldRule;

public class BuyableField extends Field {

	private FieldGroup fieldGroup;
	private int price;
	private Player owner;
	private boolean mortgaged;

	public BuyableField(String name, int position, int price, GameState state) {
		super(state);
		this.setRule(new FieldRule(name, position,
				new Action[] { new ActionPayFieldRent(state, this) },
				new Action[0]));
		this.price = price;
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
	 * 
	 * @param player
	 *            the player who got to the field.
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
