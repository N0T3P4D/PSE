package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.GameState;

/**
 * Action to transfer the money between the player and one instance of
 * <code>IMoneyPartner</code>.
 * 
 * @author Fabian Neundorf
 */
public class ActionTransferMoneyToPartner implements Action {

	private int amount;
	private IMoneyPartner partner;
	private final GameState state;

	/**
	 * Creates a new action to transfer money to/from another trading partner.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. If negative the
	 *            player will get the money from the partner.
	 * @param partner
	 *            The partner of this money exchange.
	 */
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

	/**
	 * Exchanges the money to/from a trading partner.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. If negative the
	 *            player will get the money from the partner.
	 * @param partner
	 *            The partner of this money exchange.
	 */
	public static void execute(GameState state, IMoneyPartner partner,
			int amount) {
		Bank.exchangeMoney(state.getActivePlayer(), partner, amount);
	}

	/**
	 * Creates a new action for a default transfer to a bank.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. To transfer to
	 *            player input a negative value.
	 * @return New created action.
	 */
	public static ActionTransferMoneyToPartner newTransferMoneyToBank(
			GameState state, int amount) {
		return new ActionTransferMoneyToPartner(state, amount, state.getBank());
	}

	/**
	 * Creates a new action for a default transfer to the free parking pot.
	 * 
	 * @param state
	 *            The GameState.
	 * @param amount
	 *            The amount of money which will be transfered. To transfer to
	 *            player input a negative value.
	 * @return New created action.
	 */
	public static ActionTransferMoneyToPartner newTransferMoneyToFreeParking(
			GameState state, int amount) {
		// TODO: Get Free Parking field
		return new ActionTransferMoneyToPartner(state, amount, null);
	}

}
