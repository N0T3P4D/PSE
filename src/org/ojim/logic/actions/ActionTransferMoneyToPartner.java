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

package org.ojim.logic.actions;

import org.ojim.logic.ServerLogic;
import org.ojim.logic.accounting.IMoneyPartner;

/**
 * Action to transfer the money between the player and one instance of
 * <code>IMoneyPartner</code>.
 * 
 * @author Fabian Neundorf
 */
public class ActionTransferMoneyToPartner implements Action {

	private final int amount;
	private final IMoneyPartner partner;
	private final ServerLogic logic;

	/**
	 * Creates a new action to transfer money to/from another trading partner.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. If negative the
	 *            player will get the money from the partner.
	 * @param partner
	 *            The partner of this money exchange.
	 */
	public ActionTransferMoneyToPartner(ServerLogic logic, int amount,
			IMoneyPartner partner) {
		this.amount = amount;
		this.partner = partner;
		this.logic = logic;
	}

	@Override
	public void execute() {
		this.logic.exchangeMoney(logic.getGameState().getActivePlayer(), partner,
				amount);
	}
}
