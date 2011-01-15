/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.actions.ActionPayFieldRent;

public abstract class BuyableField extends Field {

	//TODO: Gehört in Field
	private FieldGroup fieldGroup;
	private int price;
	private Player owner;
	
	private boolean mortgaged;
	private int mortgagePrice;

	public BuyableField(String name, int position, int price, GameState state) {
		super(state, name, position);
		this.setExecuteActions(new ActionPayFieldRent(state, this));
		this.price = price;
	}
	
	public void buy(Player newOwner) {
		// Remove previous owner
		if (this.owner != null) {
			this.owner.removeField(this);
		}
		this.owner = newOwner;
		if (this.owner != null) {
			this.owner.addField(this);
		}
	}

	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public abstract int getRent();

	public int getPrice() {
		return this.price;
	}

	public boolean isMortgaged() {
		return this.mortgaged;
	}

	public int getMortgagePrice() {
		return this.mortgagePrice;
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
