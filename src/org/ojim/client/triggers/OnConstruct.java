package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.fields.Street;

public class OnConstruct implements Runnable {

	private final ClientBase base;
	private final Street street;
	
	public OnConstruct(ClientBase base, Street street) {
		this.base = base;
		this.street = street;
	}
	
	@Override
	public void run() {
		this.base.onConstruct(this.street);
	}

}
