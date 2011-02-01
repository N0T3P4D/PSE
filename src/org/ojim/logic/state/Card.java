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

package org.ojim.logic.state;

import org.ojim.logic.actions.Action;

public class Card {

	private static final Action[] EMPTY_ACTIONS = new Action[0];
	
	public final String text;
	private final Action[] preActions, acceptActions, declineActions, holdingActions;
	private final CardStack stack;
	private ServerPlayer fetcher;
	
	public Card(String text, CardStack stack, Action[] preActions, Action[] acceptActions, Action[] declineActions, Action[] holdingActions) {
		this.text = text;
		this.preActions = preActions;
		this.acceptActions = acceptActions;
		this.declineActions = declineActions;
		this.holdingActions = holdingActions;
		this.stack = stack;
	}
	
	public static Card newNormalCard(String text, CardStack stack, Action... preActions) {
		if (preActions.length < 1) {
			throw new IllegalArgumentException();
		}
		return new Card(text, stack, preActions, EMPTY_ACTIONS, EMPTY_ACTIONS, EMPTY_ACTIONS);
	}

	/**
	 * Zieht eine Karte aus den Kartenstapel. Wenn die Karte in dein eigenen
	 * Stapel aufgenommen werden soll, wird diese Karte aufgenommen.
	 * 
	 * @param player Lässt den Spieler
	 * @return <code>true</code> if a reaction is required.
	 */
	public boolean fetch(ServerPlayer player) {
		this.fetcher = player;
		executeActions(this.preActions);
		if (this.acceptActions.length > 0) {
			return true;
		} else {
			this.postFetch();
			return false;
		}
	}
	
	private void postFetch() {
		if (this.holdingActions.length > 0) {
			this.stack.remove();
			this.fetcher.addCard(this);			
		} else {
			this.stack.step();
		}
	}
	
	public void accept() {
		executeActions(this.acceptActions);
		this.postFetch();
	}
	
	public void decline() {
		executeActions(this.declineActions);
		this.postFetch();
	}
	
	public void file() {
		executeActions(this.holdingActions);
	}
	
	public static void executeActions(Action[] actions) {
		for (int i = 0; i < actions.length; i++) {
			actions[i].execute();
		}
	}
}
