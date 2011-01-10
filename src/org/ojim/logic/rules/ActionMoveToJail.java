package org.ojim.logic.rules;

import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;

public class ActionMoveToJail implements Action {

	private final GameState state;
	
	public ActionMoveToJail(GameState state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionMoveToJail.execute(state);
	}
	
	public static void execute(GameState state) {
		Field jail = null;
		// jail = state.getJail() ...?!
		ActionMoveToField.execute(state, jail);
	}
}
