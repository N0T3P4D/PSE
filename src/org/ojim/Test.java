package org.ojim;

import org.ojim.logic.ActionMoveToField;
import org.ojim.logic.Card;
import org.ojim.logic.Field;
import org.ojim.logic.State;

/**
 * NUR EINE BEISPIELKLASSE!
 * @author xZise
 */
public class Test {
	
	private Card[] cards;
	
	private Test() {
		State state = new State();
		
		this.cards = new Card[10];
		for (int i = 0; i < this.cards.length; i++) {  /* Hier kann eine andere Aktion hin, oder mehrere hintereinander */
			this.cards[i] = new Card("Beispielkarte #" + i, new ActionMoveToField(state, new Field()));
		}
	}
}
