package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.GameState;

/**
 * Merged superclass for:
 * <ul><li>{@link ActionTransferMoneyToPlayer}<br>
 * Instead of player use the player as partner.</li>
 * <li>{@link ActionTransferMoneyToBank}<br>
 * As partner set the Bank.</li>
 * <li>{@link ActionPayInFreeParking}<br>
 * As partner set the free parking.</li></ul>
 * 
 * @author Fabian Neundorf
 */
public class ActionTransferMoneyToPartner implements Action {

	private int amount;
	private IMoneyPartner partner;
	private final GameState state;

	public ActionTransferMoneyToPartner(GameState state, int amount,
			IMoneyPartner partner) {
		this.amount = amount;
		this.partner = partner;
		this.state = state;
	}

	@Override
	public void execute() {
		ActionTransferMoneyToPartner.execute(this.state, this.partner,
				this.amount);
	}

	public static void execute(GameState state, IMoneyPartner partner,
			int amount) {
		Bank.exchangeMoney(state.getActivePlayer(), partner, amount);
	}

}
