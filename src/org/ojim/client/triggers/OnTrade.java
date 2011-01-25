package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnTrade implements Runnable {

	private final ClientBase base;
	private final Player actingPlayer;
	private final Player partnerPlayer;
	
	public OnTrade(ClientBase base, Player actingPlayer, Player partnerPlayer) {
		this.base = base;
		this.actingPlayer = actingPlayer;
		this.partnerPlayer = partnerPlayer;
	}
	
	@Override
	public void run() {
		this.base.onTrade(this.actingPlayer, this.partnerPlayer);
	}

}
