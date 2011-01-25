package org.ojim.client.triggers;

import org.ojim.client.ClientBase;

public class OnDiceValues implements Runnable {

	private final ClientBase base;
	private final int[] diceValues;
	
	public OnDiceValues(ClientBase base, int[] diceValues) {
		this.base = base;
		this.diceValues = diceValues;
	}
	
	@Override
	public void run() {
		this.base.onDiceValues(diceValues);
	}

}
