package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Player;

public class ActionPayInFreeParking implements Action {

	private int amount;
	private final State state;
	
	public ActionPayInFreeParking(State state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionPayInFreeParking.execute(this.state, this.amount);
	}
	
	public static void execute(State state, int amount) {
		//TODO: Implement action!
	}

}
