package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnMessage implements Runnable {

	private final ClientBase base;
	private final String text;
	private final Player sender;
	private final boolean privateMessage;
	
	public OnMessage(ClientBase base, String text, Player sender, boolean privateMessage) {
		this.base = base;
		this.text = text;
		this.sender = sender;
		this.privateMessage = privateMessage;
	}
	
	@Override
	public void run() {
		this.base.onMessage(text, sender, privateMessage);
	}

}
