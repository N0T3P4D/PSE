package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Player;

public class ActionTransferMoneyToPlayer implements Action {
	
	private int amount;
	private Player player;
	private final State state;
	
	public ActionTransferMoneyToPlayer(State state, int amount, Player player) {
		this.amount = amount;
		this.player = player;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToPlayer.execute(this.state, this.amount, this.player);
	}
	
	public static void execute(State state, int amount, Player player) {
		Player.exchangeMoney(state.getActivePlayer(), player, amount);
	}
}
