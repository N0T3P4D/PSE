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
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;

/**
 * 
 * Returns the valuation of a property group
 * 
 * @author Jeremias Mechler
 * 
 */
public final class PropertyGroupValuator extends ValuationFunction {

	/**
	 * Default constructor
	 */
	protected PropertyGroupValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static PropertyGroupValuator getInstance() {
		return ValuationFunction.getInstance(false, PropertyGroupValuator.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public double returnValuation(int playerID, int position) {
		// Call from outside without position
		if (position == -1) {
			position = getGameState().getPlayerById(playerID).getPosition();
		}
		getLogger();
		int freeFields = 0;
		int ownedByMe = 0;
		int count = 0;
		boolean fremdfeld = false;
		boolean myField = false;
		double result = 0;

		if (getGameState().getFieldAt(position) instanceof BuyableField) {
			getLogger();
			BuyableField field = ((BuyableField) getGameState().getFieldAt(position));
			if (!field.getSelected()) {
				// OJIMLogger.changeLogLevel(logger, Level.FINE);
				logger.log(Level.FINE, "Position = " + position);
				FieldGroup group = field.getFieldGroup();
				Field[] list = group.getFields();
				if (group != null) {
					for (Field temp : list) {
						BuyableField bfield = ((BuyableField) temp);
						count++;
						if (bfield.getOwner() == null) {
							freeFields++;
						} else if (bfield.getOwner() == getGameState().getPlayerById(playerID)) {
							ownedByMe++;
							if (bfield.getPosition() == position) {
								myField = true;
							}
						} else if (bfield.getPosition() == position) {
							fremdfeld = true;
						}
					}

					if (myField) {
						logger.log(Level.FINE, "Here! result = 0");
						result = 0;
					} else if (count - ownedByMe != freeFields && !fremdfeld) {
						logger.log(Level.FINE, "Here! result = -1");
						// assert(false);
						result = 2 * ((BuyableField) getGameState().getFieldAt(position)).getPrice();
					} else {
						result = parameters.getFieldGroupFactor(ownedByMe, count)
								* ((BuyableField) getGameState().getFieldAt(position)).getPrice();
						logger.log(Level.FINE, "Here! result = " + result);
					}
				}
			}
		}
		return result;
	}
}
