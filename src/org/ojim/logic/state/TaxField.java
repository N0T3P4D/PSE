package org.ojim.logic.state;

import org.ojim.logic.ServerLogic;
import org.ojim.logic.actions.ActionTransferMoneyToPartner;

public class TaxField extends Field {

	public TaxField(String name, int position, int amount) {
		super(name, position);
	}
	
	public TaxField(String name, int position, int amount, ServerLogic logic) {
		this(name, position, amount);
		this.setExecuteActions(ActionTransferMoneyToPartner.newTransferMoneyToBank(logic, amount));
	}

}
