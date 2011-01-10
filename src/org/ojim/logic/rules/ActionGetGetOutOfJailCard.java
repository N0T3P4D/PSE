package org.ojim.logic.rules;

import org.ojim.logic.state.GameState;

public class ActionGetGetOutOfJailCard implements Action {

	private GameState state;
	
	public ActionGetGetOutOfJailCard(GameState state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionGetGetOutOfJailCard.execute(this.state);
	}
	
	public static void execute(GameState state) {
		//TODO: Implement action!
	}

}
