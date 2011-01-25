package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.fields.Street;

public class OnDestruct implements Runnable {

	private final ClientBase base;
	private final Street street;
	
	public OnDestruct(ClientBase base, Street street) {
		this.base = base;
		this.street = street;
	}
	
	@Override
	public void run() {
		this.base.onDestruct(this.street);
	}

}
