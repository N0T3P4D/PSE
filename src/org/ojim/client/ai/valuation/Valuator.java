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

import org.ojim.client.ai.commands.AcceptCommand;
import org.ojim.client.ai.commands.Command;
import org.ojim.client.ai.commands.DeclineCommand;
import org.ojim.client.ai.commands.OutOfPrisonCommand;
import org.ojim.logic.Logic;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Jail;

import org.ojim.logic.state.GameState;

import edu.kit.iti.pse.iface.IServer;

/**
 * Valuator - returns the best command
 * 
 * @author Jeremias Mechler
 * 
 */
public class Valuator {

	double[] weights;
	GameState state;
	ValuationFunction[] valuationFunctions;
	Logic logic;
	IServer server;
	int playerID;

	PrisonValuator prisonValuator;
	CapitalValuator capitalValuator;
	PropertyValuator propertyValuator;

	/**
	 * Constructor
	 * 
	 * @param state
	 *            reference to state
	 * @param logic
	 *            reference to logic
	 * @param server
	 *            reference to server
	 * @param playerID
	 *            The player's ID
	 */
	public Valuator(GameState state, Logic logic, IServer server, int playerID) {
		weights = new double[ValuationFunction.COUNT];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		valuationFunctions = new ValuationFunction[6];
		this.state = state;
		this.server = server;
		this.playerID = playerID;
		this.logic = logic;
	}

	/**
	 * Returns the best command
	 * 
	 * @param pos
	 *            current position
	 * @return command
	 */
	public Command returnBestCommand(int pos) {
		Field field = state.getFieldByID(pos);
		// Jail?
		if (field.getClass().isInstance(Jail.class)) {
			if (prisonValuator.returnValuation() > 0) {
				return new OutOfPrisonCommand(logic, server, playerID);
			}
		}

		// Buy House
		if (field.getClass().isInstance(BuyableField.class)) {
			double valuation = weights[0] * propertyValuator.returnValuation() + weights[1]
					* capitalValuator.returnValuation(((BuyableField) field).getPrice());

			if (valuation > 0) {
				return new DeclineCommand(logic, server, playerID);
			} else {
				return new AcceptCommand(logic, server, playerID);
			}
		}
		return null;
	}

}
