package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;

public class OnBuy implements Runnable {

	private final ClientBase base;
	private final Player player;
	private final BuyableField field;
	
	public OnBuy(ClientBase base, Player player, BuyableField field) {
		this.base = base;
		this.player = player;
		this.field = field;
	}
	
	@Override
	public void run() {
		this.base.onBuy(this.player, this.field);
	}

}
