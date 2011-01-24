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
import org.ojim.logic.state.fields.BuyableField;

public class ActionPayFieldRent implements Action {

	private BuyableField field;
	private ServerLogic logic;
	
	/**
	 * Erstellt eine Aktion die die Miete eines Feldes bezahlt.
	 * 
	 * @param state Spielzustand.
	 * @param fields Das Feld für das die Miete eingezogen wird.
	 */
	public ActionPayFieldRent(ServerLogic logic, BuyableField field) {
		this.logic = logic;
		this.field = field;
	}
	
	@Override
	public void execute() {
		//TODO: (xZise) Inform other player -> payRent(Player) gets logic? Or has a field a reference to logic?
		this.field.payRent(logic.getGameState().getActivePlayer());
	}
}
