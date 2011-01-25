/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ojim.client.ai.valuation;

import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;

/**
 * 
 * @author Jeremias Mechler
 * 
 */
public final class MortgageValuator extends ValuationFunction {

	private MortgageValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static ValuationFunction getInstance() {
		return MortgageValuator.getInstance(false, MortgageValuator.class);
	}

	public double returnValuation(int position) {
		if (position == -1) {
			position = getGameState().getActivePlayer().getPosition();
		}
		assert (position != -1);
		Field field = getGameState().getFieldAt(position);
		if (field instanceof BuyableField)
			return -ValuationParameters.mortgageFactor
					* ((BuyableField) field).getPrice();
		else {
			return 0;
		}
	}

	@Override
	public double returnValuation() {
		// TODO Auto-generated method stub
		return returnValuation(-1);
	}

}
