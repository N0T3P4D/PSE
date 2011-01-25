package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnCashChange implements Runnable {

	private final ClientBase base;
	private final Player player;
	private final int cashChange;
	
	public OnCashChange(ClientBase base, Player player, int cashChange) {
		this.base = base;
		this.player = player;
		this.cashChange = cashChange;
	}
	
	@Override
	public void run() {
		this.base.onCashChange(this.player, this.cashChange);
	}

}
