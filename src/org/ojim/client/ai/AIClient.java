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
import org.ojim.client.ai.valuation.Valuator;

import edu.kit.iti.pse.iface.IServer;

/**
 * AI Client
 * 
 * @author Jeremias Mechler
 * 
 */
public class AIClient extends ClientBase {

	private Logger logger;
	private Valuator valuator;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 * Reference to the server
	 */
	public AIClient(IServer server) {
		super();
		logger = OJIMLogger.getLogger(this.getClass().toString());
		if (server == null) {
			throw new IllegalArgumentException("Server == null");
		}
		connect(server);
		logger.log(Level.INFO, "Hello! AI client with ID " + this.getPlayerId() + " created.");
		valuator = new Valuator(getGameState(), server, getPlayerId());
	}
	
	public void setReady() {
		ready();
	}
	
	@Override
	public void onTurn(Player player) {
//		assert (player == super)
		logger.log(Level.INFO, "Inform turn for client " + player.getName() + " (id: " + player.getId() + ")");
		logger.log(Level.INFO, "Position is " + this.getGameState().getActivePlayer().getPosition());
		this.rollDice();
	}
	
	@Override
	public void onCashChange(Player player, int cashChange) {
		logger.log(Level.INFO, "Call onCashChange!");

	}

	@Override
	public void onMessage(String text, Player sender, boolean privateMessage) {
		logger.log(Level.INFO, "Call onMessage!");
	}

	@Override
	public void onTrade(Player actingPlayer, Player partnerPlayer) {
		logger.log(Level.INFO, "Call onTrade!");
	}

	@Override
	public void onMove(Player player, int position) {
		logger.log(Level.INFO, "New position is " + position + " with name " + getLogic().getGameState().getFieldAt(position).getName());
		if (getLogic().getGameState().getFieldAt(position) instanceof BuyableField) {
			logger.log(Level.INFO, "On buyable field");
			valuator.returnBestCommand(position);
		}
//		isPrison(position);
		if (position == 11) {
//			blub("lol");
//			decline();
//			accept();
			decline();
		}
		
		
//		rollDice();
		endTurn();
//		rollDice();
//		rollDice();
	}

	@Override
	public void onBuy(Player player, BuyableField position) {
		logger.log(Level.INFO, "Call onBuy!");
	}
	
	private void isPrison(int position) {
		if (getLogic().getGameState().getFieldAt(position) instanceof Jail) {
		logger.log(Level.INFO, "In prison!");
		}
	}
	
	public void blub(String message) {
		System.out.println(message);
	}

	@Override
	public void onBankruptcy() {
		logger.log(Level.INFO, "Call onBankruptcy!");		
	}

	@Override
	public void onCardPull(String text, boolean communityCard) {
		logger.log(Level.INFO, "Call onCardPull!");		
	}

	@Override
	public void onConstruct(Street street) {
		logger.log(Level.INFO, "Call onConstruct!");		
	}

	@Override
	public void onDestruct(Street street) {
		logger.log(Level.INFO, "Call onDestruct!");		
	}

	@Override
	public void onDiceValues(int[] diceValues) {
		logger.log(Level.INFO, "Call onDiceValues!");		
	}

	@Override
	public void onMortgageToogle(BuyableField street) {
		logger.log(Level.INFO, "Call onMortgageToogle!");		
	}

	@Override
	public void onStartGame(Player[] players) {
		logger.log(Level.INFO, "Call onStartGame!");		
	}

	@Override
	public void onAuction(int auctionState) {
		logger.log(Level.INFO, "Call onAuction!");		
	}
}
