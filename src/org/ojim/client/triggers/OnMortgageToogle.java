package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.fields.BuyableField;

public class OnMortgageToogle implements Runnable {

	private final ClientBase base;
	private final BuyableField street;
	
	public OnMortgageToogle(ClientBase base, BuyableField street) {
		this.base = base;
		this.street = street;
	}
	
	@Override
	public void run() {
		this.base.onMortgageToogle(street);
	}

}
