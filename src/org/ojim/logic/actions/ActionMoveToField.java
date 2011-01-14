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

import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;

public class ActionMoveToField implements Action {

	private final Field[] fields;
	private final GameState state;
	private final boolean executePasses;

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
	public ActionMoveToField(GameState state, boolean executePasses,
			Field... fields) {
		this.state = state;
		this.fields = fields;
		this.executePasses = executePasses;
	}

	@Override
	public void execute() {
		ActionMoveToField.execute(this.state, this.executePasses, this.fields);
	}

	public static void execute(GameState state, boolean executePasses, Field... fields) {
		// Das Field suchen, was am nächsten ist
		int playerPos = state.getActivePlayer().getPosition();

		Field next = fields[0];
		for (int i = 1; i < fields.length; i++) {
			/*
			 * Checks if the distance to the selected field is lower than this
			 * to the previous determined field.
			 */
			if ((fields[i].getRule().getPosition() - playerPos + state
					.getNumberOfFields())
					% state.getNumberOfFields() < (next.getRule().getPosition()
					- playerPos + state.getNumberOfFields())
					% state.getNumberOfFields()) {
				next = fields[i];
			}
		}

		// Zu diesem Feld dann gehen
		/*
		 * movetofield(nächtesfeld)
		 */
	}

}