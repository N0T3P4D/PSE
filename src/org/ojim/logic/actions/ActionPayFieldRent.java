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

import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.GameState;

public class ActionPayFieldRent implements Action {

	private BuyableField field;
	private GameState state;
	
	/**
	 * Erstellt eine Aktion die die Miete eines Feldes bezahlt.
	 * 
	 * @param state Spielzustand.
	 * @param fields Das Feld für das die Miete eingezogen wird.
	 */
	public ActionPayFieldRent(GameState state, BuyableField field) {
		this.state = state;
		this.field = field;
	}
	
	@Override
	public void execute() {
		ActionPayFieldRent.execute(state, field);
	}
	
	public static void execute(GameState state, BuyableField field) {
		field.payRent(state.getActivePlayer());
	}

}
