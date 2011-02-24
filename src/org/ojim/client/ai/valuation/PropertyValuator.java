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

/**
 * Property valuator
 * 
 * @author Jeremias Mechler
 * 
 */
public final class PropertyValuator extends ValuationFunction {

	/**
	 * Default constructor
	 */
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
	public double returnValuation(int position) {
		getLogger();
		// position == 0 either means that our position is _really_ 0 or yet to be determined (called from
		// returnValuation)
		if (position == -1) {
			position = this.getGameState().getActivePlayer().getPosition();
		}
		assert (position != -1);
		if (this.getGameState().getFieldAt(position) instanceof BuyableField) {
			logger.log(Level.FINE, "ID = " + this.getGameState().getActivePlayer().getId());
			// We assume that position 0 will never be buyable
			assert (position != 0);
			BuyableField field = (BuyableField) this.getGameState().getFieldAt(position);
			if (!field.getSelected()) {
				if (field.getOwner() != this.getGameState().getActivePlayer()) {
					// Price is needed again later
					int price = field.getPrice();
					logger.log(Level.FINE, "Name = " + field.getName() + " Price = " + price);
					boolean isMortgaged = field.isMortgaged();
					if (price > parameters.getStreetValue(position)) {
						logger.log(Level.FINE, "Denied");
						return -1;
					} else {
						if (isMortgaged) {
							double result = ValuationParametersOld.getStreetValue(position)
									* ValuationParametersOld.mortgageFactor;
							if (price < result) {
								logger.log(Level.FINE, "Granted = " + result);
								return result;
							} else {
								logger.log(Level.FINE, "Denied");
								return -1;
							}
						} else {
							logger.log(Level.FINE, "Granted + " + parameters.getStreetValue(position));
							return parameters.getStreetValue(position);
						}
					}
				} else {
					return 0;
				}
			} else {
				logger.log(Level.FINE, "Here! result = 0");

				return 0;
			}
		} else {
			return 0;
		}
	}

}
