package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnNewPlayer implements Runnable {

	private final ClientBase base;
	private final Player player;

	public OnNewPlayer(ClientBase base, Player player) {
		this.base = base;
		this.player = player;
	}

	@Override
	public void run() {
		this.base.onNewPlayer(player);
	}

}
