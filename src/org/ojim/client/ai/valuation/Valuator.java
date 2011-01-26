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
import java.util.logging.Logger;

import org.ojim.client.SimpleClient;
import org.ojim.client.ai.commands.AcceptCommand;
import org.ojim.client.ai.commands.Command;
import org.ojim.client.ai.commands.DeclineCommand;
import org.ojim.client.ai.commands.NullCommand;
import org.ojim.client.ai.commands.OutOfPrisonCommand;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Jail;
import edu.kit.iti.pse.iface.IServerTrade;

import org.ojim.logic.state.GameState;

import edu.kit.iti.pse.iface.IServer;

/**
 * Valuator - returns the best command
 * 
 * @author Jeremias Mechler
 * 
 */
public class Valuator extends SimpleClient {

	private double[] weights;
	private ValuationFunction[] valuationFunctions;
	private Logic logic;
	private int playerID;
	private IServer server;
	private Logger logger;

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
	public Valuator(Logic logic, IServer server, int playerID) {
		super(logic, playerID, server);
		assert (logic != null);
		assert (server != null);
		this.logic = logic;
		this.server = server;
		this.playerID = playerID;
		weights = new double[ValuationFunction.COUNT];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		this.logger = OJIMLogger.getLogger(this.getClass().toString());

		valuationFunctions = new ValuationFunction[6];
		valuationFunctions[0] = CapitalValuator.getInstance();
		valuationFunctions[1] = PropertyValuator.getInstance();
		valuationFunctions[2] = PrisonValuator.getInstance();
		valuationFunctions[0].setServer(server);

		// TODO for all!
		for (int i = 0; i < 3; i++) {
			assert (valuationFunctions[i] != null);
		}

		ValuationParameters.init(logic);
	}

	/**
	 * Returns the best command
	 * 
	 * @param pos
	 *            current position
	 * @return command
	 */
	public Command returnBestCommand(int pos) {
		Field field = getGameState().getFieldAt(Math.abs(pos));
		for (int i = 0; i < 3; i++) {
			valuationFunctions[i].setParameters(logic);
		}

		if (field instanceof BuyableField) {
			if (((BuyableField) field).getOwner() != logic.getGameState().getActivePlayer()) {
				logger.log(Level.INFO, "BuyableField!");
				double valuation = weights[0] * valuationFunctions[1].returnValuation() + weights[1]
						* ((CapitalValuator) valuationFunctions[0]).returnValuation(((BuyableField) field).getPrice());

				if (valuation > 0) {
					logger.log(Level.INFO, "Granted");
					assert (logic != null);
					assert (server != null);
					return new AcceptCommand(logic, server, playerID);
				} else {
					logger.log(Level.INFO, "Denied");
					return new DeclineCommand(logic, server, playerID);
				}
			}
		}
		// reicht das?
		else if (field instanceof Jail) {
			if (valuationFunctions[2].returnValuation() > 0) {
				return new OutOfPrisonCommand(logic, server, playerID);
			} else {
				return new NullCommand(logic, server, playerID);
			}
		}

		return new NullCommand(logic, server, playerID);
	}

	/**
	 * Einschränkung: Nur _eine_ Sache pro Angebot!
	 * 
	 * @return
	 */
	public Command actOnTradeOffer() {
		assert (getTradeState() == 1);
		if (getNumberOfOfferedGetOutOfJailCards() > 0
				&& this.getNumberOfGetOutOfJailCards(playerID) < ValuationParameters.desiredNumberOfOutOfOjailCards) {
			if (this.getRequiredCash() < ((Jail) getGameState().getFieldAt(10)).getMoneyToPay()) {
				return new AcceptCommand(logic, server, playerID);
			} else {
				return new DeclineCommand(logic, server, playerID);
			}
		} else if (this.getOfferedCash() > 0) {
			int[] requests = this.getRequiredEstates();
			assert (requests.length == 1);
			// TODO take property group + buildings into account!
			if (((PropertyValuator) valuationFunctions[1]).returnValuation(requests[1]) < this.getOfferedCash()) {
				return new AcceptCommand(logic, server, playerID);
			} else {
				return new DeclineCommand(logic, server, playerID);
			}
		} else if (this.getOfferedEstates().length == 1) {
			if (((PropertyValuator) valuationFunctions[1]).returnValuation(getOfferedEstates()[0]) < this
					.getRequiredCash()) {
				if (((CapitalValuator) valuationFunctions[0]).returnValuation(this.getRequiredCash()) >= 0) {
					return new AcceptCommand(logic, server, playerID);
				} else {
					return new DeclineCommand(logic, server, playerID);
				}
			} else {
				return new DeclineCommand(logic, server, playerID);
			}
		}
		System.out.println(this.getOfferedEstates().length);
		return new DeclineCommand(logic, server, playerID);
	}

	// private GameState getGameState() {
	// return logic.getGameState();
	// }
}
