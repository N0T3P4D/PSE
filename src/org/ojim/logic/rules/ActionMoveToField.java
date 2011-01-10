package org.ojim.logic.rules;

import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;

public class ActionMoveToField implements Action {

	private Field[] fields;
	private GameState state;

	/**
	 * Erstellt eine Aktion die bei Ausführungs sich zum nächsten Feld.
	 * 
	 * @param state
	 *            Spielzustand.
	 * @param fields
	 *            Die Zielfelder.
	 */
	public ActionMoveToField(GameState state, Field... fields) {
		this.state = state;
		this.fields = fields;
	}

	@Override
	public void execute() {
		ActionMoveToField.execute(state, fields);
	}

	public static void execute(GameState state, Field... fields) {
		// Das Field suchen, was am nächsten ist
		int playerPos = (int) (Math.random() * state.getNumberOfFields());
		// TODO: Get position of the player.

		Field next = fields[0];
		for (int i = 1; i < fields.length; i++) {
			/*
			 * Checks if the distance to the selected field is lower than this
			 * to the previous determined field.
			 */
			if ((fields[i].getPosition() - playerPos + state
					.getNumberOfFields())
					% state.getNumberOfFields() < (next.getPosition()
					- playerPos + state.getNumberOfFields())
					% state.getNumberOfFields()) {
				next = fields[i];
			}
		}


		// Zu diesem Feld dann gehen
		/*
		 * movetofield(nächtesfeld)
		 */
	}

}