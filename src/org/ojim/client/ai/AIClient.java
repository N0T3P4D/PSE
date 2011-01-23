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

package org.ojim.client.ai;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Jail;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;

import org.ojim.client.ClientBase;
import edu.kit.iti.pse.iface.IServer;

/**
 * AI Client
 * 
 * @author Jeremias Mechler
 * 
 */
public class AIClient extends ClientBase {

	private Logger logger;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 * Reference to the server
	 * @param logic
	 * Reference to the game logic
	 * @param playerID
	 * This client's ID
	 */
	public AIClient(IServer server, Logic logic, int playerID) {
		super();
		logger = OJIMLogger.getLogger(this.getClass().toString());
		logger.log(Level.INFO, "Hello! AI client with ID " + playerID + " created.");
		if (server == null) {
			throw new IllegalArgumentException("Server == null");
		}
		if (logic == null) {
			throw new IllegalArgumentException("Logic == null");
		}
//		super.setParameters(logic, playerID, server);
		connect(server);
	}
	
	public void setReady() {
		ready();
	}
	
	@Override
	public void informTurn(int player) {
//		assert (player == super)
		logger.log(Level.INFO, "Inform turn for client " + player);
		logger.log(Level.INFO, "Position is " + this.getGameState().getActivePlayer().getPosition());
		this.rollDice();
	}
	
	@Override
	public void informCashChange(int player, int cashChange) {
		logger.log(Level.INFO, "Call!");

	}

	@Override
	public void informDiceValues(int[] diceValues) {
		logger.log(Level.INFO, "Call!");
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		logger.log(Level.INFO, "Call!");
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		logger.log(Level.INFO, "Call!");
	}

	@Override
	public void informMove(int playerId, int position) {
		logger.log(Level.INFO, "New position is " + position + " with name " + getLogic().getGameState().getFieldAt(position).getName());
		isPrison(position);
		if (position == 11) {
			blub("lol");
			decline();
			accept();
		}
		rollDice();
		endTurn();
		rollDice();
//		rollDice();
	}

	@Override
	public void informBuy(int player, int position) {
		logger.log(Level.INFO, "Call!");
	}

	@Override
	public void informAuction(int auctionState) {
		logger.log(Level.INFO, "Call!");
	}
	
	private void isPrison(int position) {
		if (getLogic().getGameState().getFieldAt(position) instanceof Jail) {
		logger.log(Level.INFO, "In prison!");
		}
	}
	
	public void blub(String message) {
		System.out.println(message);
	}
}
