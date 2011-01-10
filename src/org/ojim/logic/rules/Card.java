package org.ojim.logic.rules;

public class Card {
	
	public final String text;
	private final Action[] actions;
	
	public Card(String text, Action... actions) {
		this.text = text;
		this.actions = actions;
	}

	public void execute() {
		for (int i = 0; i < this.actions.length; i++) {
			this.actions[i].execute();
		}
	}
}
