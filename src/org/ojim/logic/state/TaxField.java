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

import org.ojim.logic.ServerLogic;
import org.ojim.logic.accounting.Bank;
import org.ojim.logic.actions.ActionPayFieldRent;

public class TaxField extends Field implements Rentable {
	
	private final int amount;
	private final Bank bank;

	public TaxField(String name, int position, int amount, Bank bank) {
		super(name, position);
		this.bank = bank;
		this.amount = amount;
	}
	
	public TaxField(String name, int position, int amount, ServerLogic logic) {
		this(name, position, amount, logic.getGameState().getBank());
		this.setExecuteActions(new ActionPayFieldRent(logic, this));
	}

	@Override
	public void payRent(Player payer) {
		Bank.exchangeMoney(payer, this.bank, this.amount);
	}

}
