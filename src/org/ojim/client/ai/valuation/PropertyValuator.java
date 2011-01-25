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

import org.ojim.logic.state.GameState;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;

/**
 * Property valuator
 * 
 * @author Jeremias Mechler
 * 
 */
public final class PropertyValuator extends ValuationFunction {

	protected PropertyValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static PropertyValuator getInstance() {
		return PropertyValuator.getInstance(false, PropertyValuator.class);
	}

	@Override
	public double returnValuation() {
		getLogger();
		int position = this.getGameState().getActivePlayer().getPosition();
		if (this.getGameState().getFieldAt(position) instanceof BuyableField) {
			logger.log(Level.INFO, "ID = "
					+ this.getGameState().getActivePlayer().getId());
			assert (position != 0);
			BuyableField field = (BuyableField) this.getGameState().getFieldAt(
					position);
			int price = field.getPrice();
			logger.log(Level.INFO, "Name = " + field.getName() + " Price = "
					+ price);

			boolean isMortgaged = field.isMortgaged();

			// Position or id?
			if (price > ValuationParameters.getStreetValue(position)) {
				logger.log(Level.INFO, "Denied");
				return -1;
			} else {
				if (isMortgaged) {
					if (price > ValuationParameters.getStreetValue(position)
							* ValuationParameters.mortgageFactor) {
						logger.log(Level.INFO, "Granted");
						return 1;
					} else {
						logger.log(Level.INFO, "Denied");
						return -1;
					}
				} else {
					logger.log(Level.INFO, "Granted");
					return 1;
				}
			}
		} else {
			return 0;
		}
	}

}
