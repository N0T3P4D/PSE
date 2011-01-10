package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Player;

public class ActionTransferMoneyToPlayers implements Action {

	private int amount;
	private final State state;
	
	public ActionTransferMoneyToPlayers(State state, int amount) {
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionTransferMoneyToPlayers.execute(this.state, this.amount);
	}
	
	public static void execute(State state, int amount) {
		//TODO: Finish action!
		
		// Get all players:
		Player activePlayer = state.getActivePlayer();
		Player[] players = new Player[] { new Player(), activePlayer };
		
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
