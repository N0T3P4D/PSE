package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.BuyableField;

public class ActionGetFieldRent implements Action {

	private BuyableField field;
	private State state;
	
	/**
	 * Erstellt eine Aktion die die Miete eines Feldes bezahlt.
	 * 
	 * @param state Spielzustand.
	 * @param fields Das Feld für das die Miete eingezogen wird.
	 */
	public ActionGetFieldRent(State state, BuyableField field) {
		this.state = state;
		this.field = field;
	}
	
	@Override
	public void execute() {
		ActionMoveToField.execute(state, field);
	}
	
	public static void execute(State state, BuyableField field) {
		field.payRent(state.getActivePlayer());
	}

}
