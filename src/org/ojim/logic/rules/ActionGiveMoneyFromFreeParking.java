package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.GameState;

public class ActionGiveMoneyFromFreeParking implements Action {

	private final GameState state;
	
	public ActionGiveMoneyFromFreeParking(GameState state) {
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionGiveMoneyFromFreeParking.execute(this.state);
	}
	
	public static void execute(GameState state) {
		FreeParking field = null;
		Bank.exchangeMoney(field, state.getActivePlayer(), field.getMoneyInPot());
	}

}
