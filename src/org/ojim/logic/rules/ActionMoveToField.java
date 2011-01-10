package org.ojim.logic.rules;

import org.ojim.logic.State;
import org.ojim.logic.state.Field;

public class ActionMoveToField implements Action {

	private Field[] fields;
	private State state;
	
	/**
	 * Erstellt eine Aktion die bei Ausführungs sich zum nächsten Feld.
	 * 
	 * @param state Spielzustand.
	 * @param fields Die Zielfelder.
	 */
	public ActionMoveToField(State state, Field... fields) {
		this.state = state;
		this.fields = fields;
	}
	
	@Override
	public void execute() {
		ActionMoveToField.execute(state, fields);
	}
	
	public static void execute(State state, Field... fields) {
		// Das Field suchen, was am nächsten ist
		
		/*
		 * nächstesfeld = fields[0]
		 * für jedes field aus fields 
		 *   wenn (distanz zwischen mir und field) < (distanz zwischen mir und nächstesfeld) dann
		 *     nächstesfeld = field 
		 */
		
		// Zu diesem Feld dann gehen
		/*
		 * movetofield(nächtesfeld)
		 */
	}
	
}