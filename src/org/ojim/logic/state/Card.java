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

package org.ojim.logic.state;

import org.ojim.logic.actions.Action;

public class Card {

	public final String text;
	private final Action[] actions;
	private final boolean hold;
	private final GameState state;
	
	public Card(String text, GameState state, boolean hold, Action... actions) {
		this.text = text;
		this.actions = actions;
		this.hold = hold;
		this.state = state;
	}

	/**
	 * Zieht eine Karte aus den Kartenstapel. Wenn die Karte in dein eigenen
	 * Stapel aufgenommen werden soll, wird diese Karte aufgenommen. Ansonsten
	 * wird {@link #execute()} ausgeführt.
	 */
	public void fetch() {
		if (hold) {
			//TODO: Remove card from active stack.
			
			// Add this card to the players card stack:
			this.state.getActivePlayer().addCard(this);
		} else {
			this.execute();
		}
	}
	
	public Action[] getActions() {
		return this.actions;
	}

	/**
	 * Führt die Actions der Karte nach der Reihe aus.
	 */
	public void execute() {
		if (hold) {
			this.state.getActivePlayer().removeCard(this);
		}
		for (int i = 0; i < this.actions.length; i++) {
			this.actions[i].execute();
		}
	}
}
