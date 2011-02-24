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

import org.ojim.logic.Logic;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.Field;

/**
 * Valuation parameters for the AI client Yet to be finally determined!
 * 
 * @author Jeremias Mechler
 * 
 */
public class ValuationParameters implements Cloneable {

	/**
	 * Minimum limit of cash
	 */
	private int baseCash;
	/**
	 * How many percents of the average cash of all opponents we should keep
	 */
	private double averageCashPercentage;
	/**
	 * How many percents of the cash of the opponent with the most money we should keep
	 */
	private double maxCashPercentage = 0.1;
	/**
	 * Contains the value of each buyable field
	 */
	private int[] fieldValue = new int[40];
	/**
	 * Valuation penalty for mortgage
	 */
	private double mortgageFactor;

	private int desiredNumberOfOutOfOjailCards;

	private double buildingFactor;

	private double fieldGroupFactor;

	private int[] buildingValue;
	
	private double propertyFactor;

	public ValuationParameters(int baseCash, double averageCashPercentage, double maxCashPercentage, int[] fieldValue,
			double mortgageFactor, int desiredNumberOfOutOfJailCards, double buildingFactor, double fieldGroupFactor,
			int[] buildingValue, Logic logic) {
		myLogic = logic;
		if (fieldValue == null) {
			for (int i = 0; i < 40; i++) {
				Field field = myLogic.getGameState().getFieldAt(i);
				if (field instanceof BuyableField) {
					this.fieldValue[i] = ((BuyableField) myLogic.getGameState().getFieldAt(i)).getPrice();
				}

			}
		}
		if (buildingValue == null) {
			buildingValue = new int[40];
			for (int i = 0; i < 40; i++) {
				Field field = myLogic.getGameState().getFieldAt(i);
				if (field instanceof Street) {
					buildingValue[i] = ((Street) field).getFieldGroup().getHousePrice();
				}
			}
		}
	}

	public ValuationParameters(Logic logic) {
		this(2000, 0.02, 0.01, null, 0.5, 3, 2, 1.5, null, logic);
	}

	public static Logic myLogic = null;

	public int getBaseCash() {
		return baseCash;
	}

	public int getBuildingValue(int position, int level) {
		return (int) (buildingValue[position] * level * buildingFactor);
	}

	public int getDesiredNumberOfOutOfJailCards() {
		return desiredNumberOfOutOfOjailCards;
	}

	public double getAverageCashPercentage() {
		return averageCashPercentage;
	}

	public double getMaxCashPercentage() {
		return maxCashPercentage;
	}

	/**
	 * Get the value of a buyable field
	 * 
	 * @param id
	 *            The field's ID
	 * @return Value
	 */

	public int getStreetValue(int id) {
		return fieldValue[id];
	}

	public double getFieldGroupFactor(int alreadyOwned, int max) {
		if (alreadyOwned == 0) {
			return 1;
		} else {
			return (alreadyOwned * fieldGroupFactor);
		}
	}
	
	public double getMortgageFactor() {
		return mortgageFactor;
	}
	
	public double getPropertyFactor() {
		return propertyFactor;
	}

}
