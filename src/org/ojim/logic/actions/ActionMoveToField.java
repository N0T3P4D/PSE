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
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.fields.Field;

public class ActionMoveToField implements Action {

	private final Field[] fields;
	private final ServerLogic logic;
	private final boolean executePasses;
	private final boolean special;
	
	/**
	 * Erstellt eine Aktion die bei Ausführungs sich zum nächsten Feld.
	 * 
	 * @param state
	 *            Spielzustand.
	 * @param executePasses
	 *            Wenn wahr, wird die "passThrough"-Methode der betretenden
	 *            Felder ausgeführt.
	 * @param fields
	 *            Die Zielfelder.
	 */
	public ActionMoveToField(ServerLogic logic, boolean executePasses, boolean special,
			Field... fields) {
		if (fields.length == 0) {
			throw new IllegalArgumentException("There has to be at least one field.");
		}
		this.special = special;
		this.logic = logic;
		this.fields = fields;
		this.executePasses = executePasses;
	}

	@Override
	public void execute() {		
		// Das Field suchen, was am nächsten ist
		int playerPos = this.logic.getGameState().getActivePlayer().getPosition();

		Field next = this.fields[0];
		for (int i = 1; i < this.fields.length; i++) {
			/*
			 * Checks if the distance to the selected field is lower than this
			 * to the previous determined field.
			 */
			if ((this.fields[i].getPosition() - playerPos + GameState.FIELDS_AMOUNT)
					% GameState.FIELDS_AMOUNT < (next
					.getPosition() - playerPos + GameState.FIELDS_AMOUNT)
					% GameState.FIELDS_AMOUNT) {
				next = this.fields[i];
			}
		}

		// Zu diesem Feld dann gehen
		this.logic.movePlayerTo(next, this.logic.getGameState().getActivePlayer(), this.special, this.executePasses);
	}
}