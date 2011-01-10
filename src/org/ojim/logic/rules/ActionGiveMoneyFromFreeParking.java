package org.ojim.logic.rules;

import org.ojim.logic.State;

public class ActionGiveMoneyFromFreeParking implements Action {

	private final State state;
	
	public ActionGiveMoneyFromFreeParking(State state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionGiveMoneyFromFreeParking.execute(this.state);
	}
	
	public static void execute(State state) {
		//TODO: Implement action!
	}

}
