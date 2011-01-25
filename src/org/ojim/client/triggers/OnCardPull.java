package org.ojim.client.triggers;

import org.ojim.client.ClientBase;

public class OnCardPull implements Runnable {

	private final ClientBase base;
	private final String text;
	private final boolean communityCard;
	
	public OnCardPull(ClientBase base, String text, boolean communityCard) {
		this.base = base;
		this.text = text;
		this.communityCard = communityCard;
	}
	
	@Override
	public void run() {
		this.base.onCardPull(this.text, this.communityCard);
	}

}
