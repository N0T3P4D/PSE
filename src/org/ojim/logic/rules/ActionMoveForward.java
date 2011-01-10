package org.ojim.logic.rules;

import org.ojim.logic.state.GameState;

public class ActionMoveForward implements Action {

	private int steps;
	private final GameState state;
	
	public ActionMoveForward(GameState state, int steps) {
		this.steps = steps;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionMoveForward.execute(this.state, this.steps);
	}
	
	public static void execute(GameState state, int steps) {
		//TODO: Implement action!
	}

}
