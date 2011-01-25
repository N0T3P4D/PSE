package org.ojim.client.triggers;

import org.ojim.client.ClientBase;

public class OnAuction implements Runnable {

	private final ClientBase base;
	private final int auctionState;

	public OnAuction(ClientBase base, int auctionState) {
		this.base = base;
		this.auctionState = auctionState;
	}

	@Override
	public void run() {
		this.base.onAuction(auctionState);
	}

}
