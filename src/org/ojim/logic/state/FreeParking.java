package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.Action;
import org.ojim.logic.rules.ActionGiveMoneyFromFreeParking;
import org.ojim.logic.rules.FieldRule;

public class FreeParking extends Field implements IMoneyPartner {

	public FreeParking(GameState state, int position) {
		super(state);
		this.setRule(new FieldRule("Free parking", position,
				new Action[] { new ActionGiveMoneyFromFreeParking(state, this) },
				new Action[0]));
	}

	private int moneyInPot;

	public int getMoneyInPot() {
		return this.moneyInPot;
	}

	public void insertInPot(int amount) {
		this.moneyInPot += amount;
	}

	@Override
	public void transferMoney(int amount) {
		// TODO: Don't allow negative values!
		this.moneyInPot += amount;
	}
}
