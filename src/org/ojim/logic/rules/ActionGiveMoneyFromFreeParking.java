package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.GameState;

public class ActionGiveMoneyFromFreeParking implements Action {

	private final GameState state;
	private final FreeParking field;
	
	public ActionGiveMoneyFromFreeParking(GameState state, FreeParking field) {
		this.state = state;
		this.field = field;
	}
	
	@Override
	public void execute() {
		ActionGiveMoneyFromFreeParking.execute(this.state, this.field);
	}
	
	public static void execute(GameState state, FreeParking field) {
		Bank.exchangeMoney(field, state.getActivePlayer(), field.getMoneyInPot());
	}

}
