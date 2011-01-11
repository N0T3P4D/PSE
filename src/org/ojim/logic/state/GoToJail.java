package org.ojim.logic.state;

import org.ojim.logic.rules.Action;
import org.ojim.logic.rules.ActionMoveToJail;
import org.ojim.logic.rules.FieldRule;

public class GoToJail extends Field {

	public GoToJail(GameState state, int position) {
		super(state);
		this.setRule(new FieldRule("Go to jail", position,
				new Action[] { new ActionMoveToJail(state) },
				new Action[0]));
	}

}
