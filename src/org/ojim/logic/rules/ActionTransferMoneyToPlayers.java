package org.ojim.logic.rules;

import org.ojim.logic.state.Player;
import org.ojim.logic.state.GameState;

public class ActionTransferMoneyToPlayers implements Action {

	private int amount;
	private final GameState state;
	
	public ActionTransferMoneyToPlayers(GameState state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToPlayers.execute(this.state, this.amount);
	}
	
	public static void execute(GameState state, int amount) {		
		// Get all players:
		Player activePlayer = state.getActivePlayer();
		Player[] players = state.getPlayers();
		
		// Lets get everybody the money
		for (Player player : players) {
			// If the player is not you
			if (!player.equals(activePlayer)) {
				player.transferMoney(amount);
			}
		}
		
		// Pay money
		activePlayer.transferMoney(-amount * players.length);
	}

}
