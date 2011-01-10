package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Player;

public class ActionTransferMoneyToBank implements Action {

	private int amount;
	private final State state;
	
	public ActionTransferMoneyToBank(State state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToBank.execute(state, this.amount);
	}
	
	public static void execute(State state, int amount) {
		Player activePlayer = state.getActivePlayer();
		activePlayer.transferMoney(-amount);
	}

}
