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
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
//import org.ojim.logic.state.fields.Jail;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;

import org.ojim.client.ClientBase;
import org.ojim.client.ai.commands.Command;
import org.ojim.client.ai.commands.EndTurnCommand;
import org.ojim.client.ai.commands.NullCommand;
import org.ojim.client.ai.valuation.Valuator;

import edu.kit.iti.pse.iface.IServer;

/**
 * AI Client
 * 
 * @author Jeremias Mechler
 * 
 */
public class AIClient extends ClientBase {

	private static final long serialVersionUID = -5243314726829706243L;
	private Logger logger;
	private Valuator valuator;
	private static int count;

	private static final String[] NAMES = { "Leopard", "Johannes", "Timo", "Vater", "Danny", "Mum", "Buckit", "Cor7",
			"Nutch", "Doppelkeks" };

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 *            Reference to the server
	 */
	public AIClient(IServer server) {
		super();
		if (server == null) {
			throw new IllegalArgumentException("Server == null");
		}
		logger = OJIMLogger.getLogger(getClass().toString());
		this.setName(NAMES[(int) (Math.random() * NAMES.length)]);
		connect(server);
		logger.log(Level.INFO, "Hello! AI client with ID " + getPlayerId() + " created.");
		valuator = new Valuator(getLogic(), server, getPlayerId());
		count = 0;
		OJIMLogger.changeLogLevel(logger, Level.WARNING);
		OJIMLogger.changeGlobalLevel(Level.FINEST);
	}

	/**
	 * Für den Server: Setzt
	 */
	public void setReady() {
		ready();
	}

	@Override
	public void onTurn(Player player) {
		logger.log(Level.INFO, this.log("onTurn(" + this.getPlayerInfo(player) + ")"));
		// My turn
		if (player.equals(this.getMe())) {
			logger.log(Level.INFO, "ID " + getPlayerId() + " Position is "
					+ this.getGameState().getActivePlayer().getPosition());
			this.rollDice();
			// get current position

		} else {
			logger.log(Level.FINE, log("Not my turn"));
		}
	}

	private String log(String call) {
		return "Call (@" + this.getPlayerId() + ") " + call;
		// return "";
	}

	private String getPlayerInfo(Player player) {
		if (player == null) {
			return "Server";
		}
		return player.getName() + " [id: " + player.getId() + "]";
	}

	private String getStreetInfo(Field field) {
		return field.getName() + " [@: " + field.getPosition() + "]";
	}

	@Override
	public void onCashChange(Player player, int cashChange) {
		logger.log(
				Level.INFO,
				this.log("onCashChange(" + this.getPlayerInfo(player) + ")! Amount = " + cashChange + " New cash = "
						+ player.getBalance()));

		if (player == getMe() && player.getBalance() < 0) {
				valuator.negative().execute();
		}
		assert (this.getGameState().getActivePlayer() != null);
	}

	@Override
	public void onMessage(String text, Player sender, boolean privateMessage) {
		logger.log(
				Level.INFO,
				this.log("onMessage(From: " + this.getPlayerInfo(sender) + " Message: " + text + " Private: "
						+ privateMessage + "!"));
	}

	@Override
	public void onTrade(Player actingPlayer, Player partnerPlayer) {
		logger.log(
				Level.INFO,
				this.log("onTrade(" + this.getPlayerInfo(actingPlayer) + " -> " + this.getPlayerInfo(partnerPlayer)
						+ ")!"));
		if (partnerPlayer.getId() == getPlayerId()) {
			valuator.actOnTradeOffer().execute();
		}
	}

	@Override
	public void onMove(Player player, int position) {
		logger.log(Level.FINE, this.log("onMove(" + this.getPlayerInfo(player) + ", " + position + ")!"));
		if (this.isMyTurn()) {
//			int position = getGameState().getActivePlayer().getPosition();
			// increment round count
			count++;
			logger.log(Level.INFO, "ID " + getPlayerId() + " Move " + count + " New position is " + position
					+ " with name " + getLogic().getGameState().getFieldAt(Math.abs(position)).getName());
			// if (getLogic().getGameState().getFieldAt(Math.abs(position)) instanceof BuyableField) {
			// logger.log(Level.INFO, "ID " + getPlayerId() + " On buyable field");
			// assert (getGameState().getActivePlayer().getId() == getPlayerId());
			// valuator.returnBestCommand(position).execute();
			// } else if (getLogic().getGameState().getFieldAt(Math.abs(position)) instanceof Jail) {
			// logger.log(Level.INFO, "ID " + getPlayerId() + " is in jail");
			// valuator.returnBestCommand(position).execute();
			
			// mortgages
			Command command = valuator.paybackMortgages();
			while (!(command instanceof NullCommand)) {
				command.execute();
				command = valuator.paybackMortgages();
			}
			command.execute();
			command = valuator.returnBestCommand(position);
			while (!(command instanceof EndTurnCommand)) {
				command.execute();
				command = valuator.returnBestCommand(position);
			}
			assert (command != null);
			System.out.println("EndTurnCommand");
			command.execute();
		}
		else {
			System.out.println("not my turn");
		}
	}

	@Override
	public void onBuy(Player player, BuyableField position) {
		// assert (position.getOwner() != null);
		logger.log(Level.FINE,
				this.log("onBuy(" + this.getPlayerInfo(player) + ", " + this.getStreetInfo(position) + ")!"));
	}

//	private boolean isPrison(int position) {
//		if (getLogic().getGameState().getFieldAt(position) instanceof Jail) {
//			logger.log(Level.FINE, "In prison!");
//			return true;
//		}
//		return false;
//	}
//
//	public void blub(String message) {
//		System.out.println(message);
//	}

	@Override
	public void onBankruptcy() {
		logger.log(Level.INFO, this.log("onBankruptcy()!"));
	}

	@Override
	public void onCardPull(String text, boolean communityCard) {
		logger.log(Level.FINE, this.log("onCardPull(" + text + ", " + (communityCard ? "comm" : "event") + "!"));
	}

	@Override
	public void onConstruct(Street street) {
		logger.log(Level.FINE, this.log("onConstruct(" + this.getStreetInfo(street) + ")!"));
	}

	@Override
	public void onDestruct(Street street) {
		logger.log(Level.FINE, this.log("onDestruct(" + this.getStreetInfo(street) + ")!"));
	}

	@Override
	public void onDiceValues(int[] diceValues) {
		logger.log(Level.FINE, this.log("onDiceValues(" + Arrays.toString(diceValues) + ")!"));
		assert(diceValues.length == 2);

	}

	@Override
	public void onMortgageToogle(BuyableField street) {
		logger.log(Level.FINE, this.log("onMortgageToogle(" + this.getStreetInfo(street) + ")!"));
	}

	@Override
	public void onStartGame(Player[] players) {
		String[] names = new String[players.length];
		for (int i = 0; i < players.length; i++) {
			names[i] = this.getPlayerInfo(players[i]);
		}
		logger.log(Level.FINE, this.log("onStartGame(" + Arrays.toString(names) + ")!"));
	}

	@Override
	public void onAuction(int auctionState) {
		// assert(false);
		logger.log(Level.FINE, this.log("onAuction(" + auctionState + ")!"));
		valuator.actOnAuction().execute();
		this.endTurn();
	}

	@Override
	public void onNewPlayer(Player player) {
		logger.log(Level.FINE, this.log("onNewPlayer(" + this.getPlayerInfo(player) + ")!"));
	}

	@Override
	public void onPlayerLeft(Player player) {
		logger.log(Level.FINE, this.log("onPlayerLeft(" + this.getPlayerInfo(player) + ")!"));

	}
}
