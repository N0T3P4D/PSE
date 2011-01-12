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

package org.ojim.logic;

import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.Street;

public class Logic {
	
	private GameState state;
	
	/**
	 * @deprecated "Use buyStreet() or buyStreet(Player) instead"
	 */
	public void buyStreet(BuyableField field, Player player) {
		
	}
	
	public void buyStreet(Player active) {
		Field field = this.state.getFieldAt(active.getPosition());
		if (field instanceof BuyableField) {
			((BuyableField) field).buy(active);
		}
	}
	
	public void buyStreet() {
		// Get active player
		Player active = this.state.getActivePlayer();
		this.buyStreet(active);
	}

	public void addDiceRoll(int[] diceValues) {
		
	}
	
	public void toggleMortgage(BuyableField field) {
		//TODO: Finish?
		field.setMortgaged(!field.isMortgaged());
	}
	
	public GameState getGameState() {
		return this.state;
	}
	
	public boolean upgrade(Street street, int level) {
		return street.upgrade(level);
	}
	
}
