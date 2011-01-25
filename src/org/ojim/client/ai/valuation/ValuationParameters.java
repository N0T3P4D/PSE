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

import java.util.ArrayList;

import org.ojim.logic.Logic;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.fields.BuyableField;

/**
 * Valuation parameters for the AI client
 * 
 * @author Jeremias Mechler
 * 
 */
public final class ValuationParameters {

	/**
	 * Minimum limit of cash
	 */
	public final static int baseCash = 200;
	/**
	 * How many percents of the average cash of all opponents we should keep
	 */
	public final static double averageCashPercentage = 0.01;
	/**
	 * How many percents of the cash of the opponent with the most money we
	 * should keep
	 */
	public final static double maxCashPercentage = 0.05;
	/**
	 * Contains the value of each buyable field
	 */
	public final static int[] FieldValue = new int[40];
	/**
	 * Valuation penalty for mortgage
	 */
	public final static double mortgageFactor = 0.5;

	/**
	 * Get the value of a buyable field
	 * 
	 * @param id
	 *            The field's ID
	 * @return Value
	 */
	public final static double fieldGroupFactor = 1.5;

	public static int getStreetValue(int id) {
		return FieldValue[id];
	}

	public static double getFieldGroupFactor(int alreadyOwned, int max) {
		if (alreadyOwned == 0) {
			return 1;
		} else {
			return (alreadyOwned * fieldGroupFactor);
		}
	}

	public static void init(Logic logic) {
		GameState state = logic.getGameState();
		for (int i = 0; i < 40; i++) {
			if (state.getFieldAt(i) instanceof BuyableField) {
				FieldValue[i] = ((BuyableField) state.getFieldAt(i)).getPrice();
			} else {
				FieldValue[i] = -1;
			}
		}
	}
}
