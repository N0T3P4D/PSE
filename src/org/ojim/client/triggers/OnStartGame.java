package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnStartGame implements Runnable {

	private final ClientBase base;
	private final Player[] players;
	
	public OnStartGame(ClientBase base, Player[] players) {
		this.base = base;
		this.players = players;
	}
	
	@Override
	public void run() {
		this.base.onStartGame(this.players);
	}

}
