/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.GameState;

public class ActionGiveMoneyFromFreeParking implements Action {

	private final GameState state;
	private final FreeParking field;
	
	public ActionGiveMoneyFromFreeParking(GameState state, FreeParking field) {
		this.state = state;
		this.field = field;
	}
	
	@Override
	public void execute() {
		ActionGiveMoneyFromFreeParking.execute(this.state, this.field);
	}
	
	public static void execute(GameState state, FreeParking field) {
		Bank.exchangeMoney(field, state.getActivePlayer(), field.getMoneyInPot());
	}

}