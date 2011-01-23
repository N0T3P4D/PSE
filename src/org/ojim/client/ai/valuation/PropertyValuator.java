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

/**
 * Property valuator
 * 
 * @author Jeremias Mechler
 * 
 */
public final class PropertyValuator extends ValuationFunction {

	private PropertyValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static ValuationFunction getInstance() {
		return ValuationFunction.getInstance(false, PropertyValuator.class);
	}

	@Override
	public double returnValuation() {
		int position = this.getGameState().getActivePlayer().getPosition();
		BuyableField field = (BuyableField) this.getGameState().getFieldAt(position);
		int price = field.getPrice();
		boolean isMortgaged = field.isMortgaged();

		// Position or id?
		if (price > ValuationParameters.getStreetValue(position)) {
			return -1;
		} else {
			if (isMortgaged) {
				if (price > ValuationParameters.getStreetValue(position) * ValuationParameters.mortgageFactor) {
					return 1;
				} else {
					return -1;
				}
			} else {
				return 1;
			}
		}
	}

}
