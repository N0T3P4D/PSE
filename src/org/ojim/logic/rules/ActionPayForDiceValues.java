package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.GameState;

public class ActionPayForDiceValues implements Action {

	private final int factor;
	private final IMoneyPartner payee;
	private final GameState state;

	public ActionPayForDiceValues(GameState state, int factor,
			IMoneyPartner payee) {
		this.factor = factor;
		this.payee = payee;
		this.state = state;
	}

	@Override
	public void execute() {
		ActionPayForDiceValues.execute(this.state, this.payee,
				this.factor);
	}

	public static void execute(GameState state, IMoneyPartner payee,
			int factor) {
		//TODO: Finish this!
		int[] diceValues = null;
		int diceSum = 0;
		for (int i : diceValues) {
			diceSum += i;
		}
		Bank.exchangeMoney(state.getActivePlayer(), payee, factor * diceSum);
	}

}
