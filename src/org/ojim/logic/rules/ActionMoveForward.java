package org.ojim.logic.rules;

import org.ojim.logic.State;

public class ActionMoveForward implements Action {

	private int steps;
	private final State state;
	
	public ActionMoveForward(State state, int steps) {
		this.steps = steps;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionMoveForward.execute(this.state, this.steps);
	}
	
	public static void execute(State state, int steps) {
		//TODO: Implement action!
	}

}
