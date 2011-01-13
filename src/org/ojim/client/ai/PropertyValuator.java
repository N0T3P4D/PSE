package org.ojim.client.ai;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.BuyableField;

import org.ojim.logic.state.GameState;


public class PropertyValuator extends ValuationFunction {
	
	private static ValuationFunction valuationFunction;
	
	private PropertyValuator() {
	}
	
	public static ValuationFunction getInstance() {
		if (valuationFunction == null) {
			valuationFunction = new PropertyValuator();
		}
		return valuationFunction;
	}
	
	public static double returnValuation() {
		if (((BuyableField)state.getFieldByID(0)).getPrice() > ValuationParameters.streetPrice) {
			return 1;
		}
	
		return 0;
	}

}
