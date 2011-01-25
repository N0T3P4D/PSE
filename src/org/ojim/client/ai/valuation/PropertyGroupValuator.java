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
 * 
 * Returns the valuation of a property group
 * 
 * @author Jeremias Mechler
 * 
 */
public final class PropertyGroupValuator extends ValuationFunction {

	private PropertyGroupValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static PropertyGroupValuator getInstance() {
		return ValuationFunction
				.getInstance(false, PropertyGroupValuator.class);
	}

	public double returnValuation(int position) {
		if (position == -1) {
			position = getGameState().getActivePlayer().getPosition();
		}
		getLogger();
		int freeFields = 0;
		int ownedByMe = 0;
		int count = 0;
		boolean fremdfeld = false;
		boolean myField = false;

		for (BuyableField field : ((BuyableField[]) getGameState()
				.getFieldAt(position).getFieldGroup().getFields())) {
			count++;
			if (field.getOwner() == null) {
				freeFields++;
			} else if (field.getOwner() == getGameState().getActivePlayer()) {
				ownedByMe++;
				if (field.getPosition() == position) {
					myField = true;
				}
			} else if (field.getPosition() == position) {
				fremdfeld = true;
			}
		}

		if (myField) {
			return 0;
		} else if (count - ownedByMe != freeFields && !fremdfeld) {
			return -1;
		} else {
			return ValuationParameters.getFieldGroupFactor(ownedByMe, count);
		}
	}

	@Override
	public double returnValuation() {
		return returnValuation(-1);
	}
}
