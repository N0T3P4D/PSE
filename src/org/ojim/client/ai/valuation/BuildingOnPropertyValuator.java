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

import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.StreetFieldGroup;

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

	private boolean allOfGroupOwned(Street street) {
		StreetFieldGroup group = street.getFieldGroup();
		assert (group.getFields().length != 1);
		int count = 0;
		for (Field field : group.getFields()) {
			if (((BuyableField) field).getOwner() == getGameState().getActivePlayer()) {
				count++;
			}
		}
		return (count == group.getFields().length);
	}

	@Override
	public double returnValuation(int position) {
		Player me = this.getGameState().getActivePlayer();
		getLogger();
		double result = 0;
		if (position == -1) {
			position = me.getPosition();
		}
		if (getGameState().getFieldAt(position) instanceof Street) {
			Street street = (Street) getGameState().getFieldAt(position);
			if (allOfGroupOwned(street)) {
				logger.log(Level.FINE, "All owned by me");
				if (getGameRules().isFieldUpgradable(me, street, street.getBuiltLevel() + 1)) {
					// double result = ValuationParameters.getBuildingValue(position,street.getNumberOfHouse() + 1);
					result = 10 * street.getRent(street.getBuiltLevel() + 1);
				}
			}
		}
		logger.log(Level.FINE, "Here! result = " + result);
		return result;
	}

}
