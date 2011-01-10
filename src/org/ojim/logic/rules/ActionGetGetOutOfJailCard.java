package org.ojim.logic.rules;

import org.ojim.logic.State;

public class ActionGetGetOutOfJailCard implements Action {

	private State state;
	
	public ActionGetGetOutOfJailCard(State state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionGetGetOutOfJailCard.execute(this.state);
	}
	
	public static void execute(State state) {
		//TODO: Implement action!
	}

}
