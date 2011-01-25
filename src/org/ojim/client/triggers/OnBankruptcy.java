package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnBankruptcy implements Runnable {

	private final ClientBase base;

	public OnBankruptcy(ClientBase base) {
		this.base = base;
	}

	@Override
	public void run() {
		this.base.onBankruptcy();
	}

}
