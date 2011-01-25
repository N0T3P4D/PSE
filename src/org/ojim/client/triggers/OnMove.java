package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnMove implements Runnable {

	private final ClientBase base;
	private final Player player;
	private final int position;
	
	public OnMove(ClientBase base, Player player, int position) {
		this.base = base;
		this.player = player;
		this.position = position;
	}
	
	@Override
	public void run() {
		this.base.onMove(this.player, this.position);
	}

}
