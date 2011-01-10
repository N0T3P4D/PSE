package org.ojim.logic.rules;

public class Card {

	public final String text;
	private final Action[] actions;
	private final boolean hold;

	public Card(String text, boolean hold, Action... actions) {
		this.text = text;
		this.actions = actions;
		this.hold = hold;
	}

	/**
	 * Zieht eine Karte aus den Kartenstapel. Wenn die Karte in dein eigenen
	 * Stapel aufgenommen werden soll, wird diese Karte aufgenommen. Ansonsten
	 * wird {@link #execute()} ausgeführt.
	 */
	public void fetch() {
		if (hold) {
			//TODO: Move card to players card stack.
		} else {
			this.execute();
		}
	}

	/**
	 * Führt die Actions der Karte nach der Reihe aus.
	 */
	public void execute() {
		for (int i = 0; i < this.actions.length; i++) {
			this.actions[i].execute();
		}
	}
}
