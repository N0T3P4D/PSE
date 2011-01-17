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

package org.ojim.logic;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.ServerGameState;

/**
 * Acts as logic, but this class informs also the players.
 * 
 * @author Fabian Neundorf
 */
public class ServerLogic extends Logic {

	public ServerLogic(ServerGameState state, GameRules rules) {
		super(state, rules);
	}
	
	public void getFreeParkingMoney(FreeParking field) {
		this.exchangeMoney(field, this.getGameState().getActivePlayer(), field.getMoneyInPot());
	}
	
	public void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee,
			int amount) {
		Bank.exchangeMoney(payer, payee, amount);
		
		//TODO: (xZise) Inform other players!
	}

}
