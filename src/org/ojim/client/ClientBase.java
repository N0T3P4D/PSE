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

package org.ojim.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ojim.client.triggers.OnAuction;
import org.ojim.client.triggers.OnBankruptcy;
import org.ojim.client.triggers.OnBuy;
import org.ojim.client.triggers.OnCardPull;
import org.ojim.client.triggers.OnCashChange;
import org.ojim.client.triggers.OnConstruct;
import org.ojim.client.triggers.OnDestruct;
import org.ojim.client.triggers.OnDiceValues;
import org.ojim.client.triggers.OnMessage;
import org.ojim.client.triggers.OnMortgageToogle;
import org.ojim.client.triggers.OnMove;
import org.ojim.client.triggers.OnNewPlayer;
import org.ojim.client.triggers.OnPlayerLeft;
import org.ojim.client.triggers.OnStartGame;
import org.ojim.client.triggers.OnTrade;
import org.ojim.client.triggers.OnTurn;
import org.ojim.iface.IClient;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.state.Auction;
import org.ojim.logic.state.DiceSet;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.StaticDice;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;
import org.ojim.logic.state.fields.Jail;
import org.ojim.logic.state.fields.Street;
import org.ojim.rmi.client.ImplNetClient;
import org.ojim.rmi.client.StartNetClient;
import org.ojim.rmi.server.NetOjim;

import edu.kit.iti.pse.iface.IServer;

/**
 * Basis Client für den GUIClient und AIClient.
 * 
 * @author Fabian Neundorf
 */
public abstract class ClientBase extends SimpleClient implements IClient,Serializable {

	private String name;
	private ExecutorService executor;
	private Logger logger;
	private StaticDice[] dice;

	public ClientBase() {
		super();
		this.executor = Executors.newFixedThreadPool(1);
		this.logger = OJIMLogger.getLogger(this.getClass().toString());
	}

	/*
	 * MISC
	 */

	private void loadGameBoard() {
		
		GameState state = this.getGameState();

		state.getBank().setHotels(this.getNumberOfHotelsLeft());
		state.getBank().setHouses(this.getNumberOfHousesLeft());
		
		Map<Integer, FieldGroup> groups = new HashMap<Integer, FieldGroup>(17);
		for (int position = 0; position < this.getGameState().getNumberOfFields(); position++) {
			Field field = this.getFieldFromServer(position, groups, new HashMap<Integer, Player>());
			if (field != null) {
				state.setFieldAt(field, position);
			}
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * ACTION METHODS
	 */

	protected final boolean connect(String host, int port) {
		try {
			StartNetClient starter = new StartNetClient();
			NetOjim netojim = starter.createClientRMIConnection(port, host, this);
			ImplNetClient  server = new ImplNetClient(this, netojim);
			this.setParameters(server, this);
			this.loadGameBoard();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected final void connect(IServer server, GameState state) {
		this.setParameters(server, this, state);
		this.loadGameBoard();
	}
	
	protected final void connect(IServer server) {
		this.connect(server, new GameState());
	}
	
	@Override
	protected void setParameters(IServer server, IClient client, GameState state) {
		// New dynamic way?
		this.dice = new StaticDice[2];
		this.dice[0] = new StaticDice(6);
		this.dice[1] = new StaticDice(6);
		DiceSet set = new DiceSet(this.dice);
		state.setDiceSet(set);
		super.setParameters(server, client, state);
	}

	/*
	 * TRIGGER-METHODS
	 */

	@Override
	public String getLanguage() {
		return "eng";
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public final void informBankruptcy() {
		this.logger.log(Level.INFO, "informBankruptcy()");
		this.getGameState().getActivePlayer().setBankrupt();
	//	this.onBankruptcy();
		this.executor.execute(new OnBankruptcy(this));
	}

	public abstract void onBankruptcy();

	@Override
	public final void informCardPull(String text, boolean communityCard) {
		this.logger.log(Level.INFO, "informCardPull(" + text + ", " + communityCard + ")");
		this.updatePlayersGetOutOfJailCards(this.getGameState().getActivePlayer());
		//this.onCardPull(text, communityCard);
		this.executor.execute(new OnCardPull(this, text, communityCard));
	}

	public abstract void onCardPull(String text, boolean communityCard);

	@Override
	public final void informCashChange(int playerId, int cashChange) {
		this.logger.log(Level.INFO, "informCashChange(" + playerId + "," + cashChange + ")");
		Player player = this.getGameState().getPlayerById(playerId);
		if (player != null) {
			player.transferMoney(cashChange);
		//	this.onCashChange(player, cashChange);
			this.executor.execute(new OnCashChange(this, player, cashChange));
		} else {
			this.logger.warning("Get informCashChange with invalid player (" + playerId
							+ ").");
		}
	}

	public abstract void onCashChange(Player player, int cashChange);

	@Override
	public final void informConstruct(int street) {
		this.logger.log(Level.INFO, "informConstruct(" + street + ")");		
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof Street) {
			this.getLogic().upgrade((Street) field, +1);
			//this.onConstruct((Street) field);
			this.executor.execute(new OnConstruct(this, (Street) field));
		} else {
			this.logger.warning(
					"Get informConstruct with invalid street.");
		}
	}

	public abstract void onConstruct(Street street);

	@Override
	public final void informDestruct(int street) {
		this.logger.log(Level.INFO, "informDestruct(" + street + ")");		
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof Street) {
			this.getLogic().upgrade((Street) field, -1);
		//	this.onDestruct((Street) field);
			this.executor.execute(new OnDestruct(this, (Street) field));
		} else {
			this.logger.warning("Get informDestruct with invalid street.");
		}
	}

	public abstract void onDestruct(Street street);

	@Override
	public final void informDiceValues(int[] diceValues) {
		this.logger.log(Level.INFO, "informDiceValues(" + Arrays.toString(diceValues) + ")");
		// Set dice values in dice set
		
		for (int i = 0; i < diceValues.length; i++) {
			this.dice[i].setResult(diceValues[i]);
		}
		
	//	this.onDiceValues(diceValues);
		this.executor.execute(new OnDiceValues(this, diceValues));
	}

	public abstract void onDiceValues(int[] diceValues);

	@Override
	public final void informMessage(String text, int sender,
			boolean privateMessage) {
		this.logger.log(Level.INFO, "informMessage(" + text + "," + sender + "," + privateMessage + ")");	
		Player player = null;
		if ((sender == -1)
				|| (player = this.getGameState().getPlayerById(sender)) != null) {
			//this.onMessage(text, player, privateMessage);
			this.executor.execute(new OnMessage(this, text, player,
					privateMessage));
		} else {
			this.logger.warning("Get informMessage with invalid player (" + sender + ").");
		}
	}

	public abstract void onMessage(String text, Player sender,
			boolean privateMessage);

	@Override
	public final void informMortgageToogle(int street) {
		this.logger.log(Level.INFO, "informMortgageToogle(" + street + ")");
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof BuyableField) {
			this.getLogic().toggleMortgage((BuyableField) field);
			//this.onMortgageToogle((BuyableField) field);
			this.executor.execute(new OnMortgageToogle(this,
					(BuyableField) field));
		} else {
			this.logger.warning("Get informMortgageToogle with invalid buyable field.");
		}
	}

	public abstract void onMortgageToogle(BuyableField street);

	@Override
	public final void informStartGame(int[] ids) {
		this.logger.log(Level.INFO, "informStartGame(" + Arrays.toString(ids) + ")");		
		GameState state = this.getGameState();
		Player[] order = new Player[ids.length];
		for (int i = 0; i < ids.length; i++) {
			order[i] = state.getPlayerById(ids[i]);
		}
		state.setPlayerOrder(order);
		//this.onStartGame(this.getGameState().getPlayers());
		this.executor.execute(new OnStartGame(this, this.getGameState()
				.getPlayers()));
	}

	public abstract void onStartGame(Player[] players);

	@Override
	public final void informTrade(int actingPlayer, int partnerPlayer) {
		this.logger.log(Level.INFO, "informTrade(" + actingPlayer + "," + partnerPlayer + ")");
		Player acting = this.getGameState().getPlayerById(actingPlayer);
		if (acting != null) {
			Player partner = this.getGameState().getPlayerById(partnerPlayer);
			if (partner != null) {
				//this.onTrade(acting, partner);
				this.executor.execute(new OnTrade(this, acting, partner));
			} else {
				this.logger.warning(
						"Get informTrade with invalid partner player.");
			}
		} else {
			this.logger.warning("Get informTrade with invalid acting player.");
		}
	}

	public abstract void onTrade(Player actingPlayer, Player partnerPlayer);

	@Override
	public final void informTurn(int player) {
		this.logger.log(Level.INFO, "informTurn(" + player + ")");
		Player newPlayer = this.getGameState().getPlayerById(player);
		if (newPlayer != null) {
			this.getGameState().setActivePlayer(newPlayer);
			//this.onTurn(newPlayer);
			this.executor.execute(new OnTurn(this, newPlayer));
		} else {
			this.logger.warning("Get informTurn with invalid player.");
		}
	}

	public abstract void onTurn(Player player);

	@Override
	public final void informMove(int playerId, int position) {
		this.logger.log(Level.INFO, "informMove(" + playerId + ", " + position + ")");
		Player player = this.getGameState().getPlayerById(playerId);
		if (player != null) {
			player.setPosition(position);
			if (position < 0 && this.getGameState().getFieldAt(Math.abs(position)) instanceof Jail) {
				player.sendToJail((Jail) this.getGameState().getFieldAt(Math.abs(position)));
			} else {
				player.sendToJail(null);
			}
			
			//this.onMove(player, position);
			this.executor.execute(new OnMove(this, player));
		} else {
			this.logger.warning("Get informMove with invalid player, ID = " + playerId);
		}
	}

	public abstract void onMove(Player player);

	@Override
	public final void informBuy(int playerId, int position) {
		this.logger.log(Level.INFO, "informBuy(" + playerId + ", " + position + ")");
		Player player = this.getGameState().getPlayerById(playerId);
		if (player != null) {
			Field field = this.getGameState().getFieldAt(position);
			if (field instanceof BuyableField) {
				((BuyableField) field).buy(player);
			//	this.onBuy(player, (BuyableField) field);
				this.executor.execute(new OnBuy(this, player,
						(BuyableField) field));
			} else {
				this.logger.warning("Get informBuy with invalid position.");
			}
		} else {
			this.logger.warning("Get informBuy with invalid player.");
		}
	}

	public abstract void onBuy(Player player, BuyableField field);

	@Override
	public final void informAuction(int auctionState) {
		this.logger.log(Level.INFO, "informAuction(" + auctionState + ")");
		try {
			Auction auction = this.getGameState().getAuction();
			if (auction != null) {
				auction = this.updateAuction(auction);
			} else {
				auction = this.getAuctionFromServer();
			}
			this.getGameState().setAuction(auction);
			
			//this.onAuction(auctionState);
			this.executor.execute(new OnAuction(this));
		} catch (IllegalArgumentException e) {
			this.logger.log(Level.WARNING, "Get informAuction with invalid auction.", e);
		}
	}

	public abstract void onAuction();

	public final void informNewPlayer(int playerId) {
		this.logger.log(Level.INFO, "informNewPlayer(" + playerId + ")");
		if (this.getGameState().getPlayerById(playerId) == null) {
			Player player = this.getPlayerFromServer(playerId);
			this.getGameState().setPlayer(player);
			// Update all owned fields
			for (int i = 0; i < this.getGameState().getNumberOfFields(); i++) {
				Field field = this.getGameState().getFieldAt(i);
				if (field instanceof BuyableField && ((BuyableField) field).getOwner() == null) {
					this.updateFieldOwner((BuyableField) field, this.getGameState().getPlayersMap());
				}
			}
			
			//this.onNewPlayer(player);
			this.executor.execute(new OnNewPlayer(this, player));
		} else {
			this.logger.warning("Get informNewPlayer with already existing player.");
		}
	}

	public abstract void onNewPlayer(Player player);

	public final void informPlayerLeft(int playerId) {
		this.logger.log(Level.INFO, "informPlayerLeft(" + playerId + ")");
		Player old = this.getGameState().getPlayerById(playerId);
		this.getGameState().removePlayer(old);
		// Remove all owners for this field
		for (int i = 0; i < this.getGameState().getNumberOfFields(); i++) {
			Field field = this.getGameState().getFieldAt(i);
			if (field instanceof BuyableField && ((BuyableField) field).getOwner().equals(old)) {
				((BuyableField) field).buy(null);
			}
		}
		//this.onPlayerLeft(old);
		this.executor.execute(new OnPlayerLeft(this, old));
	}

	public abstract void onPlayerLeft(Player player);

	@Override
	public void setPlayerId(int newId) {
		super.setPlayerId(newId);
	}
}
