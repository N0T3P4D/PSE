package org.ojim.logic.rules;

import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.GameState;

public class ActionGetFieldRent implements Action {

	private BuyableField field;
	private GameState state;
	
	/**
	 * Erstellt eine Aktion die die Miete eines Feldes bezahlt.
	 * 
	 * @param state Spielzustand.
	 * @param fields Das Feld für das die Miete eingezogen wird.
	 */
	public ActionGetFieldRent(GameState state, BuyableField field) {
		this.state = state;
		this.field = field;
	}
	
	@Override
	public void execute() {
		ActionMoveToField.execute(state, field);
	}
	
	public static void execute(GameState state, BuyableField field) {
		field.payRent(state.getActivePlayer());
	}

}
