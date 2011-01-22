package org.ojim.logic.state;

import org.ojim.logic.ServerLogic;
import org.ojim.logic.actions.Action;
import org.ojim.logic.actions.ActionGetOutOfJailCard;

/**
 * Default get out of jail card.
 *
 * @author Fabian Neundorf.
 */
public class GetOutOfJailCard extends Card {

	public GetOutOfJailCard(String text, ServerLogic logic) {
		super(text, logic.getGameState(), new Action[0], new Action[0], new Action[0], new Action[] { new ActionGetOutOfJailCard(logic) });
	}

}
