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

import org.ojim.logic.state.Player;
import org.ojim.logic.state.GameState;

public class ActionTransferMoneyToPlayers implements Action {

	private int amount;
	private final GameState state;
	
	public ActionTransferMoneyToPlayers(GameState state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToPlayers.execute(this.state, this.amount);
	}
	
	public static void execute(GameState state, int amount) {		
		// Get all players:
		Player activePlayer = state.getActivePlayer();
		Player[] players = state.getPlayers();
		
		// Lets get everybody the money
		for (Player player : players) {
			// If the player is not you
			if (!player.equals(activePlayer)) {
				player.transferMoney(amount);
			}
		}
		
		// Pay money
		activePlayer.transferMoney(-amount * players.length);
	}

}
