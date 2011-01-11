package org.ojim.logic.rules;

import org.ojim.logic.state.GameState;

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
