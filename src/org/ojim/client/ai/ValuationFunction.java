package org.ojim.client.ai;
import org.ojim.iface.Rules;
import org.ojim.logic.Logic;
import org.ojim.logic.state.GameState;

public abstract class ValuationFunction {
	
	protected static Logic logic;
	protected static Rules rules;
	protected static GameState state;

	public abstract double returnValuation();

}
