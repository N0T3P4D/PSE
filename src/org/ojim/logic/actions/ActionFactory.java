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
import org.ojim.logic.state.fields.FreeParking;

/**
 * This class support some default action constructors.
 * 
 * @author Fabian Neundorf.
 */
public class ActionFactory {

	/**
	 * Creates a new action for a default transfer to a bank.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. To transfer to
	 *            player input a negative value.
	 * @return New created action.
	 */
	public static ActionTransferMoneyToPartner newTransferMoneyToBank(
			ServerLogic logic, int amount) {
		return new ActionTransferMoneyToPartner(logic, amount, logic
				.getGameState().getBank());
	}

	/**
	 * Creates a new action for a default transfer to the free parking pot.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. To transfer to
	 *            player input a negative value.
	 * @return New created action.
	 */
	public static ActionTransferMoneyToPartner newTransferMoneyToFreeParking(
			ServerLogic logic, int amount, FreeParking field) {
		return new ActionTransferMoneyToPartner(logic, amount, field);
	}
	
}
