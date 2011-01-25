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

import java.util.Arrays;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = -5243314726829706243L;
	private Logger logger;
	private Valuator valuator;
	private int count;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 *            Reference to the server
	 */
	public AIClient(IServer server) {
		super();
		logger = OJIMLogger.getLogger("AI_"+getPlayerId());
		if (server == null) {
			throw new IllegalArgumentException("Server == null");
		}
		connect(server);
		logger.log(Level.INFO, "Hello! AI client with ID " + getPlayerId()
				+ " created.");
		valuator = new Valuator(getLogic(), server, getPlayerId());
		count = 0;
	}

	public void setReady() {
		ready();
	}

	@Override
	public void onTurn(Player player) {
		// assert (player == super)
		logger.log(Level.INFO, "Inform turn for client " + player.getName()
				+ " (id: " + player.getId() + ")");
		logger.log(Level.INFO, "ID " + getPlayerId() + " Position is "
				+ this.getGameState().getActivePlayer().getPosition());
		this.rollDice();
		int position = getGameState().getActivePlayer().getPosition();
		count++;
		logger.log(
				Level.INFO,
				"ID " + getPlayerId() + " Move "
						+ count
						+ " New position is "
						+ position
						+ " with name "
						+ getLogic().getGameState()
								.getFieldAt(Math.abs(position)).getName());
		if (getLogic().getGameState().getFieldAt(Math.abs(position)) instanceof BuyableField) {
			logger.log(Level.INFO, "ID " + getPlayerId() + " On buyable field");
			assert(getGameState().getActivePlayer().getId() == getPlayerId());
			valuator.returnBestCommand(position).execute();
		}
		endTurn();
	}
	
	private void log(String call) {
		logger.log(Level.INFO, "Call (@" + this.getPlayerId() + ") " + call);
	}
	
	private String getPlayerInfo(Player player) {
		return player.getName() + " [id: " + player.getId() + "]"; 
	}

	private String getStreetInfo(Field field) {
		return field.getName() + " [@: " + field.getPosition() + "]";
	}
	
	@Override
	public void onCashChange(Player player, int cashChange) {
		this.log("onCashChange! Amount = " + cashChange
				+ " New cash = "
				+ getGameState().getActivePlayer().getBalance());
	}

	@Override
	public void onMessage(String text, Player sender, boolean privateMessage) {
		this.log("onMessage(From: " + this.getPlayerInfo(sender) + " Message: " + text + " Private: " + privateMessage + "!");
	}

	@Override
	public void onTrade(Player actingPlayer, Player partnerPlayer) {
		this.log("onTrade(" + this.getPlayerInfo(actingPlayer) + " -> " + this.getPlayerInfo(partnerPlayer) + ")!");
	}

	@Override
	public void onMove(Player player, int position) {
		this.log("onBuy(" + this.getPlayerInfo(player) + ", " + position + ")!");
	}

	@Override
	public void onBuy(Player player, BuyableField position) {
		// assert (position.getOwner() != null);
		this.log("onBuy(" + this.getPlayerInfo(player) + ", " + this.getStreetInfo(position) + ")!");
	}

	private boolean isPrison(int position) {
		if (getLogic().getGameState().getFieldAt(position) instanceof Jail) {
			logger.log(Level.INFO, "In prison!");
			return true;
		}
		return false;
	}

	public void blub(String message) {
		System.out.println(message);
	}

	@Override
	public void onBankruptcy() {
		this.log("onBankruptcy()!");
	}

	@Override
	public void onCardPull(String text, boolean communityCard) {
		this.log("onCardPull(" + text + ", " + (communityCard ? "comm" : "event") + "!");
	}

	@Override
	public void onConstruct(Street street) {
		this.log("onConstruct(" + this.getStreetInfo(street) + ")!");
	}

	@Override
	public void onDestruct(Street street) {
		this.log("onDestruct(" + this.getStreetInfo(street) + ")!");
	}

	@Override
	public void onDiceValues(int[] diceValues) {
		this.log("onDiceValues(" + Arrays.toString(diceValues) + ")!");
	}

	@Override
	public void onMortgageToogle(BuyableField street) {
		this.log("onMortgageToogle(" + this.getStreetInfo(street) + ")!");
	}

	@Override
	public void onStartGame(Player[] players) {
		String[] names = new String[players.length];
		for (int i = 0; i < players.length; i++) {
			names[i] = this.getPlayerInfo(players[i]);
		}
		this.log("onStartGame(" + Arrays.toString(names) + ")!");
	}

	@Override
	public void onAuction(int auctionState) {
		this.log("onAuction(" + auctionState + ")!");
	}
}
