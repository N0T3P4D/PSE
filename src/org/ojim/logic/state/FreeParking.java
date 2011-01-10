package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;

public class FreeParking extends Field implements IMoneyPartner {

	private int moneyInPot;
	
	public int getMoneyInPot() {
		return this.moneyInPot;
	}
	
	public void insertInPot(int amount) {
		this.moneyInPot += amount;
	}

	@Override
	public void transferMoney(int amount) {
		//TODO: Don't allow negative values!
		this.moneyInPot += amount;
	}
}
