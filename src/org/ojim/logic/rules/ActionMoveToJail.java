package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Field;

public class ActionMoveToJail implements Action {

	private final State state;
	
	public ActionMoveToJail(State state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionMoveToJail.execute(state);
	}
	
	public static void execute(State state) {
		Field jail = null;
		// jail = state.getJail() ...?!
		ActionMoveToField.execute(state, jail);
	}
}
