package org.ojim.logic;

public class ActionMoveToField implements Action {

	private Field[] fields;
	private State state;
	
	public ActionMoveToField(State state, Field[] fields) {
		this.state = state;
		this.fields = fields;
	}
	
	@Override
	public void execute() {
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