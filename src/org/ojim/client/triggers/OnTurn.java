package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnTurn implements Runnable {

	private final ClientBase base;
	private final Player player;
	
	public OnTurn(ClientBase base, Player player) {
		this.base = base;
		this.player = player;
	}
	
	@Override
	public void run() {
		this.base.onTurn(player);
	}

}
