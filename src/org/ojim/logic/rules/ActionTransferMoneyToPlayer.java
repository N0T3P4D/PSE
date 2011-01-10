package org.ojim.logic.rules;

import org.ojim.logic.state.Player;
import org.ojim.logic.state.GameState;

public class ActionTransferMoneyToPlayer implements Action {
	
	private int amount;
	private Player player;
	private final GameState state;
	
	public ActionTransferMoneyToPlayer(GameState state, int amount, Player player) {
		this.amount = amount;
		this.player = player;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToPlayer.execute(this.state, this.amount, this.player);
	}
	
	public static void execute(GameState state, int amount, Player player) {
		//TODO: Implement action!
	}
}
