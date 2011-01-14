package org.ojim.client.ai;

import org.ojim.logic.state.BuyableField;

public class PropertyValuator extends ValuationFunction {
	
	private PropertyValuator() {
	}
	
	public static ValuationFunction getInstance() {
		return ValuationFunction.getInstance(false, PropertyValuator.class);
	}
	
	public double returnValuation() {
		if (((BuyableField) this.getGameState().getFieldByID(0)).getPrice() > ValuationParameters.streetPrice) {
			return 1;
		}
	
		return 0;
	}

}
