package org.ojim.logic.rules;

import org.ojim.logic.state.Player;
import org.ojim.logic.state.GameState;

public class ActionPayInFreeParking implements Action {

	private int amount;
	private final GameState state;
	
	public ActionPayInFreeParking(GameState state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionPayInFreeParking.execute(this.state, this.amount);
	}
	
	public static void execute(GameState state, int amount) {
		//TODO: Implement action!
	}

}
