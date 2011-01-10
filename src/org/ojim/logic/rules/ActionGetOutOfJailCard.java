package org.ojim.logic.rules;

import org.ojim.logic.state.GameState;

/**
 * Führt die „Komme aus den Gefängnis frei“-Karte aus.
 * 
 * @author Fabian Neundorf
 */
public class ActionGetOutOfJailCard implements Action {

	private GameState state;

	public ActionGetOutOfJailCard(GameState state) {
		this.state = state;
	}

	@Override
	public void execute() {
		ActionGetOutOfJailCard.execute(this.state);
	}

	public static void execute(GameState state) {
		// TODO: Implement action!
	}

}
