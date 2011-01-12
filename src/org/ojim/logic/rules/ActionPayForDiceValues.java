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

package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.GameState;

public class ActionPayForDiceValues implements Action {

	private final int factor;
	private final IMoneyPartner payee;
	private final GameState state;

	public ActionPayForDiceValues(GameState state, int factor,
			IMoneyPartner payee) {
		this.factor = factor;
		this.payee = payee;
		this.state = state;
	}

	@Override
	public void execute() {
		ActionPayForDiceValues.execute(this.state, this.payee, this.factor);
	}

	public static void execute(GameState state, IMoneyPartner payee, int factor) {
		Bank.exchangeMoney(state.getActivePlayer(), payee, factor
				* state.getDices().getResultSum());
	}

}
