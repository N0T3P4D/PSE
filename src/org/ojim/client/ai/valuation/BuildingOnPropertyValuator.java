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

import java.util.logging.Level;

import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Street;

/**
 * 
 * Valuates a building on a property
 * 
 * @author Jeremias Mechler
 * 
 */
public final class BuildingOnPropertyValuator extends ValuationFunction {

	protected BuildingOnPropertyValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static ValuationFunction getInstance() {
		return BuildingOnPropertyValuator.getInstance(false, BuildingOnPropertyValuator.class);
	}

	@Override
	public double returnValuation(int position) {
		getLogger();
		if (position == -1) {
			position = this.getGameState().getActivePlayer().getPosition();
		}
		// number of houses? hotels?
		if (getGameState().getFieldAt(position) instanceof Street) {
			//erstmal schauen, ob mir alles gehört...
//			double result = ValuationParameters.getBuildingValue(position,
//					((Street) getGameState().getFieldAt(position)).getNumberOfHouse() + 1);
//			logger.log(Level.FINE, "Result = " + result);
//			return result;
			return 1;
		} else {
			return 0;
		}
//		logger.log(Level.FINE, "Here! result = 0.1");
//		return 0.1;
	}

}
