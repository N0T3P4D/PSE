package org.ojim.client.ai;

import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.GameState;

public abstract class ValuationFunction {
	
	private Logic logic;
	private GameRules rules;
	//xZise: Implicit set by logic (logic.getGameState)
//	protected GameState state;
	
	protected ValuationFunction() { }
	
	private static ValuationFunction instance;
	
	//xZise: Falls du das brauchst ;) Eine Möglichkeit
	
	public static ValuationFunction getInstance(Class<? extends ValuationFunction> clazz) {
		return CapitalValuator.getInstance(false, clazz);
	}
		
	
	public static ValuationFunction getInstance(boolean forceNew, Class<? extends ValuationFunction> clazz) {
		if (instance == null) {
			try {
				instance = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void setParameters(Logic logic, GameRules rules) {
		this.logic = logic;
		this.rules = rules;
	}
	
	protected final Logic getGameLogic() {
		return this.logic;
	}
	
	protected final GameRules getGameRules() {
		return this.rules;
	}
	
	protected final GameState getGameState() {
		return this.logic.getGameState();
	}
	
	public abstract double returnValuation();

}
