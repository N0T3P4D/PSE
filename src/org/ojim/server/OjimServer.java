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

package org.ojim.server;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ojim.client.SimpleClient.AuctionState;
import org.ojim.client.ai.AIClient;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.actions.ActionFactory;
import org.ojim.logic.actions.ActionPayForBuildings;
import org.ojim.logic.actions.ActionTransferMoneyToPlayers;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.ServerAuction;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.CardStack;
import org.ojim.logic.state.GetOutOfJailCard;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.ServerGameState;
import org.ojim.logic.state.ServerPlayer;
import org.ojim.logic.state.Trade;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.CardField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;
import org.ojim.logic.state.fields.FreeParking;
import org.ojim.logic.state.fields.GoField;
import org.ojim.logic.state.fields.GoToJail;
import org.ojim.logic.state.fields.InfrastructureField;
import org.ojim.logic.state.fields.InfrastructureFieldGroup;
import org.ojim.logic.state.fields.Jail;
import org.ojim.logic.state.fields.Station;
import org.ojim.logic.state.fields.StationFieldGroup;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.StreetFieldGroup;
import org.ojim.logic.state.fields.TaxField;
import org.ojim.rmi.server.ImplNetOjim;
import org.ojim.rmi.server.StartNetOjim;

import edu.kit.iti.pse.iface.IServer;
import edu.kit.iti.pse.iface.IServerAuction;
import edu.kit.iti.pse.iface.IServerTrade;

/**
 * 
 * @author Philip
 * 
 */
public class OjimServer implements IServer, IServerAuction, IServerTrade {

	private int round;
	private int maxRound;
	
	/**
	 * The name of the Server
	 */
	private String name;

	/**
	 * Can Clients connect to the server?
	 */
	private boolean isOpen = false;

	/**
	 * Is the Game started?
	 */
	private boolean gameStarted;

	/**
	 * The amount of connected Clients
	 */
	private int connectedClients;

	/**
	 * The amount of Clients that can be connected
	 */
	private int maxClients;

	/**
	 * All connected Clients
	 */
	private List<IClient> clients;

	/**
	 * The GameState
	 */
	private ServerGameState state;

	/**
	 * The Logic
	 */
	private ServerLogic logic;

	/**
	 * The Rules
	 */
	private GameRules rules;

	/**
	 * GameCards that need to be accepted/declined
	 */
	private List<Card> currentCards;

	/**
	 * current Auction, if null => no Auction
	 */
	private ServerAuction auction;

	/**
	 * current Trade, if null => no Trade
	 */
	private Trade trade;

	/**
	 * List of all AI-Clients
	 */
	private AIClient aiClients[];

	/**
	 * the Logger TODO private?
	 */
	Logger logger;

	private boolean initComplete = false;

	/**
	 * Creates a new Server. Has to be opened by initGame
	 * 
	 * @param name
	 *            The name of the Server
	 */
	public OjimServer(String name) {
		this.name = name;
		this.gameStarted = false;
		this.currentCards = new LinkedList<Card>();
		// AI added for AI
		this.state = new ServerGameState();
		this.rules = new GameRules(this.state, new Rules());
		this.logic = new ServerLogic(this.state, this.rules);
		this.logger = OJIMLogger.getLogger(this.getClass().toString());
		this.maxRound = 0;
	}

	public void setMaxRound(int maxRound) {
		this.maxRound = maxRound;
	}
	
	@SuppressWarnings("unused")
	public synchronized boolean initRMIGame(int playerCount, int aiCount,
			String host) {
		StartNetOjim starter = new StartNetOjim();

		StartNetOjim start = new StartNetOjim();

		ImplNetOjim netServer = null;
		try {
			netServer = new ImplNetOjim(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		start.startServer(1099, host, netServer);
		return this.initGame(playerCount, aiCount);
	}

	/**
	 * Initializes the Server and opens it
	 * 
	 * @param playerCount
	 *            maximum Player (AI and GUI) count
	 * @param aiCount
	 *            Amount of AI-Players
	 * @return successful?
	 */
	public synchronized boolean initGame(int playerCount, int aiCount) {

		this.initComplete = false;
		if (isOpen) {
			return false;
		}

		// Make sure no negative numbers appear and there are players at all
		if (playerCount < 2 || aiCount < 0 || aiCount > playerCount) {
			return false;
		}

		// Init the GameFields
		Field[] fields = new Field[this.logic.getGameState()
				.getNumberOfFields()];
		this.loadDefaultGameStateFields(fields);
		for (Field field : fields) {
			this.logic.getGameState().setFieldAt(field, field.getPosition());
		}

		this.connectedClients = 0;
		this.maxClients = playerCount;
		clients = new LinkedList<IClient>();

		aiClients = new AIClient[aiCount];
		// AI Add AIClients to the Game
		for (int i = 0; i < aiCount; i++) {
			// AI changed
			aiClients[i] = new AIClient(this);
			logger.log(Level.CONFIG, "AI Client " + i + " added!");
			aiClients[i].setReady();
		}
		// AI added
		logger.log(Level.CONFIG, "All AI clients added");
		// Open the Game
		isOpen = true;
		initComplete = true;
		this.round = 0;
		
		if(aiCount == playerCount) {
			ServerAuction.setTimeDelay(1);
		}
		if (checkAllPlayersReady()) {
			this.startGame();
		}
		
		/*
		 * if (playerCount == aiCount) { while (!state.getGameIsWon()) { try {
		 * wait(300); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } } }
		 */
		return true;
	}

	/**
	 * Ends a Game and prepares the Server for the next Game
	 * 
	 * @return successful?
	 */
	public synchronized boolean endGame() {

		// Stops the Game
		this.gameStarted = false;
		if (!this.isOpen) {
			return true;
		}
		
		for(Player player : this.state.getPlayers()) {
			((ServerPlayer)player).getClient().informGameOver(this.state.getActivePlayer().getId());
		}

		// Closing the Game
		isOpen = false;
		gameStarted = false;
		this.logic.endGame();

		
		// Disconnecting all Clients
		while (clients.size() > 0) {
			IClient client = clients.get(0);
			disconnect(client);
			client = null;
		}

		this.clients = null;

		// Setting the Fields to the Standard Values
		this.connectedClients = 0;
		this.maxClients = 0;
		return true;
	}

	/**
	 * Disconnects a Client from the Game
	 * 
	 * @param client
	 *            The Client to disconnect
	 */
	private synchronized void disconnect(IClient client) {
		assert client != null;
		for (IClient oneClient : this.clients) {
			// TODO Add Language
			oneClient.informPlayerLeft(getIdOfClient(client));
			oneClient.informMessage("Client has been disconnected!", -1, false);
		}
		state.getPlayerById(getIdOfClient(client)).setBankrupt();
		this.clients.remove(client);
		client = null;
	}

	private synchronized int getIdOfClient(IClient client) {
		assert client != null;
		for (Player player : state.getPlayers()) {
			assert player instanceof ServerPlayer;
			if (((ServerPlayer) player).getClient().equals(client)) {
				return player.getId();
			}
		}
		return -1;
	}

	/**
	 * Displays a String currently on the Console
	 * 
	 * @param string
	 *            String to display
	 */
	private void display(String string) {
		System.out.println(string);
	}

	@Override
	public synchronized boolean initTrade(int actingPlayer, int partnerPlayer) {
		// If there is already a Trade in process, dont create a new one
		display("initializing trade between " + actingPlayer + " and " + partnerPlayer);
		if (state.getGameIsWon()) {
			return false;
		}
		if (trade != null) {
			if (trade.getTradeState() < 2) {
				return false;
			}
		}
		ServerPlayer acting = state.getPlayerById(actingPlayer);
		ServerPlayer partner = state.getPlayerById(partnerPlayer);
		if(acting == null || acting != this.state.getActivePlayer()){
			return false;
		}
		
		//Trade with bank
		if (partnerPlayer == -1) {
			trade = new Trade(acting, state.getBank(), rules);
			return true;
		}

		//Trade with player
		if (partner != null) {
			trade = new Trade(acting, partner);
			return true;
		}
		return false;
	}

	@Override
	public synchronized int getTradeState() {
		if (trade != null) {
			return trade.getTradeState();
		}
		return -1;
	}

	@Override
	public synchronized int getPartner() {
		if (trade != null && trade.getPartner() != null) {
			return trade.getPartner().getId();
		}
		return -1;
	}

	@Override
	public synchronized boolean offerCash(int playerID, int amount) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade.setOfferedCash(amount);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean offerGetOutOfJailCard(int playerID) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade.setOfferedNumberOfGetOutOfJailCards(trade
					.getOfferedNumberOfGetOutOfJailCards() + 1);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean offerEstate(int playerID, int position) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		Field field = state.getFieldAt(position);
		if (trade != null
				&& trade.getTradeState() == 0
				&& player != null
				&& player.equals(trade.getActing())
				&& field != null
				&& field instanceof BuyableField
				&& (!(field instanceof Street) || ((Street) field)
						.getBuiltLevel() == 0)) {
			return trade.addOfferedEstate((BuyableField) field);
		}
		return false;
	}

	@Override
	public synchronized boolean requireCash(int playerID, int amount) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade.setRequiredCash(amount);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean requireGetOutOfJailCard(int playerID) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade.setRequiredNumberOfGetOutOfJailCards(trade
					.getRequiredNumberOfGetOutOfJailCards() + 1);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean requireEstate(int playerID, int position) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		Field field = state.getFieldAt(position);
		if (trade != null
				&& trade.getTradeState() == 0
				&& player != null
				&& player.equals(trade.getActing())
				&& field != null
				&& field instanceof BuyableField
				&& (!(field instanceof Street) || ((Street) field)
						.getBuiltLevel() == 0)) {
			return trade.addRequiredEstate((BuyableField) field);
		}
		return false;
	}

	@Override
	public synchronized int[] getOfferedEstates() {
		if (trade != null) {
			int[] out = new int[trade.getOfferedEstates().size()];
			int i = 0;
			for (BuyableField field : trade.getOfferedEstates()) {
				if (i < out.length) {
					out[i] = field.getPosition();
					i++;
				}
			}
			return out;
		}
		return new int[0];
	}

	@Override
	public synchronized int getOfferedCash() {
		if (trade != null) {
			return trade.getOfferedCash();
		}
		return -1;
	}

	@Override
	public synchronized int getNumberOfOfferedGetOutOfJailCards() {
		if (trade != null) {
			return trade.getOfferedNumberOfGetOutOfJailCards();
		}
		return -1;
	}

	@Override
	public synchronized int[] getRequiredEstates() {
		if (trade != null) {
			int[] out = new int[trade.getRequiredEstates().size()];
			int i = 0;
			for (BuyableField field : trade.getRequiredEstates()) {
				if (i < out.length) {
					out[i] = field.getPosition();
					i++;
				}
			}
			return out;
		}
		return new int[0];
	}

	@Override
	public synchronized int getRequiredCash() {
		if (trade != null) {
			return trade.getRequiredCash();
		}
		return -1;
	}

	@Override
	public synchronized int getNumberOfRequiredGetOutOfJailCards() {
		if (trade != null) {
			return trade.getRequiredNumberOfGetOutOfJailCards();
		}
		return -1;
	}

	@Override
	public synchronized boolean cancelTrade(int playerID) {
		display("trade canceled by " + playerID);
		if (state.getGameIsWon()) {
			return false;
		}
		ServerPlayer player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade = null;
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean proposeTrade(int playerID) {
		display("trade proposed by " + playerID);
		if (state.getGameIsWon()) {
			return false;
		}
		ServerPlayer player = state.getPlayerById(playerID);
		if (trade != null && trade.getTradeState() == 0 && player != null
				&& player.equals(trade.getActing())) {
			trade.setTradeState(1);
			if (trade.getPartner() != null) {
				trade.getPartner().getClient().informTrade();
				return true;
			} else {
				trade.setTradeState(3);
				trade.executeTrade(logic);
			}
		}
		return false;
	}

	@Override
	public synchronized int getAuctionState() {
		if (auction != null) {
			return auction.getState().value;
		}
		return AuctionState.NOT_RUNNING.value;
	}

	@Override
	public synchronized int getAuctionedEstate() {
		if (auction != null) {
			return auction.objective.getPosition();
		}
		return 0;
	}

	@Override
	public synchronized int getHighestBid() {
		if (auction != null) {
			return auction.getHighestBid();
		}
		return -1;
	}

	@Override
	public synchronized int getBidder() {
		if (auction != null) {
			if (auction.getHighestBidder() != null) {
				return auction.getHighestBidder().getId();
			}
		}
		return -1;
	}

	@Override
	public synchronized boolean placeBid(int playerID, int amount) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (auction != null && player != null && !player.getIsBankrupt()) {
			return auction.placeBid(player, amount);
		}
		return false;
	}

	@Override
	public synchronized int getPlayerPiecePosition(int playerID) {
		Player player = state.getPlayerById(playerID);
		if (player == null) {
			return -1;
		}
		return player.getPosition();
	}

	/**
	 * Adds a GameCard that needs accepting/declining
	 * 
	 * @param card
	 *            the Card to add
	 */
	public synchronized void addCurrentCard(Card card) {
		if (state.getGameIsWon()) {
			return;
		}
		this.currentCards.add(card);
	}

	/**
	 * Gets all GameCards that need accepting/declining
	 * 
	 * @return List of Cards that need accept()/decline()
	 */
	public synchronized List<Card> getCurrentCards() {
		return this.currentCards;
	}

	@Override
	public synchronized int addPlayer(IClient client) {

		for (int i = 0; i < maxClients; i++) {
			if (state.getPlayerById(i) == null) {

				this.clients.add(client);
				Player newPlayer = new ServerPlayer(client.getName(), 0,
						state.getRules().startMoney, i, i, client);
				state.setPlayer(newPlayer);
				this.connectedClients++;

				client.informCashChange(newPlayer.getId(), newPlayer.getBalance());
				
				// Inform all Players except the new one that a new Player is
				// there
				for (Player player : state.getPlayers()) {
					if (!player.equals(newPlayer)) {
						((ServerPlayer) player).getClient().informNewPlayer(i);
						((ServerPlayer) player).getClient().informCashChange(newPlayer.getId(), newPlayer.getBalance());
						client.informNewPlayer(player.getId());
					}
				}

				return i;
			}
		}
		return -1;
	}

	@Override
	public synchronized void setPlayerReady(int playerID) {

		// AI added
		assert (state != null);

		if (state.getPlayerById(playerID) == null) {
			return;
		}

		state.getPlayerById(playerID).setIsReady(true);
		// AI added
		logger.log(Level.INFO, "Number of connected players = "
				+ connectedClients);

		// Are all players Ready? then start the game
		if (checkAllPlayersReady()) {
			// If all (non-AI) Players are ready, start the Game
			startGame();
		}
	}

	private synchronized boolean checkAllPlayersReady() {
		if (this.connectedClients == this.maxClients && initComplete) {
			for (Player player : state.getPlayers()) {
				// Check if the Player is ready
				if (!player.getIsReady()) {

					// AI Clients don't need to be set to ready
					if (player instanceof ServerPlayer
							&& !(((ServerPlayer) player).getClient() instanceof AIClient)) {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Starts a Game
	 */
	private synchronized void startGame() {
		this.gameStarted = true;

		logic.startGame();
	}

	@Override
	public synchronized String getPlayerName(int playerID) {
		Player player = state.getPlayerById(playerID);
		if (player != null) {
			String name = player.getName();
			if (name == null || name == "") {
				name = "Player" + player.getId();
				if (player instanceof ServerPlayer) {
					name = ((ServerPlayer) player).getName();
				}
			}
			return name;
		}
		return "";
	}

	@Override
	public synchronized int getPlayerColor(int playerID) {
		Player player = state.getPlayerById(playerID);
		if (player != null) {
			return player.getColor();
		}
		return -1;
	}

	@Override
	public synchronized Rules getRules() {
		if (this.state.getRules() != null) {
			return state.getRules();
		}
		return null;
	}

	@Override
	public synchronized String getEstateName(int position, int player) {
		Field field = state.getFieldAt(position);
		String name = "";
		if (field != null) {
			name = field.getName();
			if (field instanceof Street) {
				name = ((Street) field).getFieldGroup().getName() + ": " + name;
			}
		}
		return name;
	}

	@Override
	public synchronized int getEstateColorGroup(int position) {
		Field field = state.getFieldAt(position);
		if (field != null) {
			return field.getFieldGroup().getColor();
		}
		return 0;
	}

	@Override
	public synchronized int getEstateHouses(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof Street) {
			return ((Street) field).getBuiltLevel();
		}
		return -1;
	}

	@Override
	public synchronized int getEstatePrice(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof BuyableField) {
			return ((BuyableField) field).getPrice();
		}
		return -1;
	}

	@Override
	public synchronized int getEstateRent(int position, int houses) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof Street) {
			return ((Street) field).getRent(houses);
		} else if (field != null && field instanceof BuyableField) {
			return ((BuyableField) field).getRent();
		}
		return -1;
	}

	@Override
	public synchronized boolean isMortgaged(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof BuyableField) {
			return ((BuyableField) field).isMortgaged();
		}
		return false;
	}

	@Override
	public synchronized int getOwner(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof BuyableField) {
			Player owner = ((BuyableField) field).getOwner();
			if (owner != null) {
				return owner.getId();
			}
		}
		return -1;
	}

	@Override
	public synchronized int getDiceValue() {
		return state.getDices().getResultSum();
	}

	@Override
	public synchronized int[] getDiceValues() {
		return state.getDices().getResult();
	}

	@Override
	public synchronized int getPlayerCash(int playerID) {
		Player player = state.getPlayerById(playerID);
		if (player != null) {
			return player.getBalance();
		}
		return -1;
	}

	@Override
	public synchronized int getPlayerOnTurn() {
		if (state.getActivePlayer() != null) {
			return state.getActivePlayer().getId();
		}
		return -1;
	}

	@Override
	public synchronized int getNumberOfGetOutOfJailCards(int playerID) {
		Player player = this.state.getPlayerById(playerID);
		if (player != null) {
			return player.getNumberOfGetOutOfJailCards();
		}
		return -1;
	}

	@Override
	public synchronized int getNumberOfHousesLeft() {
		return state.getBank().getHouses();
	}
	
	public boolean isGameRunning() {
		return this.state.getGameIsWon();
	}

	@Override
	public synchronized int getNumberOfHotelsLeft() {
		return state.getBank().getHotels();
	}

	/**
	 * How many doubles were there in this turn?
	 */
	private int doublesChain = 0;

	private boolean playerNeedsAcceptCancel;

	@Override
	public synchronized boolean rollDice(int playerID) {
		display(playerID + " wants to roll!");

		ServerPlayer player = this.state.getPlayerById(playerID);

		if (player == null || !rules.isPlayerOnTurn(player)
				|| this.gameStarted == false
				|| !this.rules.isRollRequiredByActivePlayer()
				|| this.state.getGameIsWon() || this.playerNeedsAcceptCancel) {
			return false;
		}

		if (player.getJail() != null) {
			display("he is in jail");
			// Roll and Inform everyone
			state.getDices().roll();
			informDiceAll();

			// Player has not rolled a Double and stays in jail
			if (!state.getDices().isDouble()) {
				state.setActivePlayerNeedsToRoll(false);
				return true;
			} else {
				// Get the Player out of Jail
				logic.playerRolledOutOfJail(player);
				// Inform all that the Player is now out of Prison (position
				// > -1)
				informMoveAll(player);
			}
		}

		display("He is now rolling");
		// Roll and Inform everyone
		state.getDices().roll();
		informDiceAll();
		display("He has rolled");

		// If the Player has not rolled a double, stop rolling
		if (!state.getDices().isDouble()) {
			state.setActivePlayerNeedsToRoll(false);
		} else {
			doublesChain++;
			if (doublesChain >= GameRules.MAX_DOUBLES_ALLOWED) {
				// Player has to get to jail
				logic.sendPlayerToJail(player, state.getDefaultJail());
				return true;
			}
		}

		display("he is moving");
		// Now move the Player forward
		logic.movePlayerForDice(player, state.getDices().getResultSum());

		// Is the Field the Player is standing on buyable?
		Field field = state.getFieldAt(player.getPosition());
		if (field instanceof BuyableField
				&& ((BuyableField) field).getOwner() == null) {
			playerNeedsAcceptCancel = true;
			player.getClient().informBuyEvent(player.getId(), player.getPosition());
		} else {
			for(Player onePlayer : this.state.getPlayers()) {
				((ServerPlayer)onePlayer).getClient().informCanEndTurn(this.state.getActivePlayer().getId());
			}
		}
		return true;
	}

	/**
	 * Informs all Clients that the Dice has been rolled
	 */
	private synchronized void informDiceAll() {
		for (IClient client : clients) {
			client.informDiceValues(state.getDices().getResult());
		}
	}

	/**
	 * Inform all Clients that a move has occured
	 * 
	 * @param player
	 *            the moving Player
	 */
	private synchronized void informMoveAll(Player player) {
		for (IClient client : clients) {
			client.informMove(player.getId(), player.getSignedPosition());
		}
	}

	@Override
	public synchronized boolean accept(int playerID) {
		display("accepting");

		ServerPlayer player = state.getPlayerById(playerID);
		// Does a Trade need Confirmation?
		if (trade != null && player != null && trade.getTradeState() == 1
				&& player.equals(trade.getPartner())) {
			display("trade accepted by " + playerID);
			trade.setTradeState(3);
			trade.executeTrade(logic);
			((ServerPlayer) trade.getActing()).getClient().informTrade();
		}

		if (state.getGameIsWon() || playerID != state.getActivePlayer().getId()) {
			return false;
		}

		if (player == null || !rules.isPlayerOnTurn(player)) {
			return false;
		}
		// First check if a Action needs Confirmation
		Card card = state.getFirstWaitingCard();
		if (card != null) {
			card.accept();
			state.RemoveWaitingCard(card);
			return true;
		}
		Field field = state.getFieldAt(player.getPosition());
		if (playerNeedsAcceptCancel
				&& player.getBalance() > ((BuyableField) field).getPrice()) {
			logic.buyStreet();
			playerNeedsAcceptCancel = false;
			
			for(Player onePlayer : this.state.getPlayers()) {
				((ServerPlayer)onePlayer).getClient().informCanEndTurn(this.state.getActivePlayer().getId());
			}
			
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean decline(int playerID) {
		display("decline");
		if (state.getGameIsWon()) {
			System.out.println("Game is won");
			return false;
		}

		ServerPlayer player = state.getPlayerById(playerID);

		if (trade != null && player != null && trade.getTradeState() == 1
				&& player.equals(trade.getPartner())) {
			System.out.println("trade declined by " + playerID);
			trade.setTradeState(2);
			((ServerPlayer) trade.getActing()).getClient().informTrade();
		}

		if (player == null || playerID != state.getActivePlayer().getId()) {
			return false;
		}

		// First check if a Action needs Confirmation
		Card card = state.getFirstWaitingCard();
		if (card != null) {
			card.decline();
			state.RemoveWaitingCard(card);
			return true;
		}

		// Check if the Buying of a field was declined
		if (playerNeedsAcceptCancel) {
			if (!(state.getFieldAt(state.getActivePlayer().getPosition()) instanceof BuyableField)) {
			}
			this.auction = new ServerAuction(state, logic, rules,
					(BuyableField) state.getFieldAt(state.getActivePlayer()
							.getPosition()));
			this.auction.setReturnParameters(this, state.getActivePlayer()
					.getId());
			// Do inform here, because before "this.auction" is not initalized!
			this.auction.informPlayers();
			this.playerNeedsAcceptCancel = false;
		}
		return false;
	}

	@Override
	public synchronized boolean endTurn(int playerID) {
		Player player = state.getPlayerById(playerID);
		
		//TODO: (xZise → Vikath) Allow endTurn if Trade is ACCEPTED/DECLINED?
		// Before it was only possible to end a turn, of the state is negative.
		// BUT you never set the state of the trade to a negative value.
		if(this.getTradeState() == 0 || this.getTradeState() == 1) {
			return false;
		}
		
		if (player != null && rules.isPlayerOnTurn(player)
				&& !state.getGameIsWon()) {
			if (player.getJail() != null) {
				player.waitInJail();
			}
			if (!rules.isRollRequiredByActivePlayer()) {

				// Player is bankrupt
				if (player.getBalance() < 0) {
					this.logic.setPlayerBankrupt(player);
				}

				// Is there an Auction running?
				if (this.auction != null
						&& this.auction.getState() != AuctionState.THIRD) {
					return false;
				}

				// does the Player have to accept/cancel to buy a field?
				if (playerNeedsAcceptCancel) {
					display("auction needs to be started");
					return false;
				}
				if (this.state.getGameIsWon()) {
					this.endGame();
					return true;
				}
				tries = 0;
				this.doublesChain = 0;
				this.round++;
				System.out.println(this.name + "|" + round);
				if(round == maxRound) {
					
					logger.log(Level.INFO, "Game ended because maxRounds was reached");
					this.endGame();
				}
				logic.startNewTurn();

				return true;
			} else {
				((ServerPlayer) player).getClient().informTurn(player.getId());
			}
		}
		return false;
	}

	@Override
	public synchronized boolean declareBankruptcy(int playerID) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		if (player != null) {
			this.logic.setPlayerBankrupt(player);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean construct(int playerID, int position) {
		if (state.getGameIsWon()) {
			return false;
		}
		if (changeLevel(playerID, position, 1)) {
			for (IClient client : clients) {
				client.informConstruct(position);
			}
			return true;
		}
		return false;
	}

	/**
	 * Changes the BuiltLevel of a Field
	 * 
	 * @param playerID
	 *            the Player that want to change
	 * @param position
	 *            The Position of the Field
	 * @param levelChange
	 *            the levelChange
	 * @return
	 */
	private boolean changeLevel(int playerID, int position, int levelChange) {
		Player player = state.getPlayerById(playerID);
		Field field = state.getFieldAt(position);

		if (player != null) {
			if (field != null) {
				if (rules.isFieldUpgradable(player, field, levelChange)) {
					if (levelChange > 0) {
						if ((this.getNumberOfHousesLeft() <= 0 && ((Street) field)
								.getBuiltLevel() + levelChange < getRules().maxNumOfHouses)
								|| (this.getNumberOfHotelsLeft() <= 0 && ((Street) field)
										.getBuiltLevel() + levelChange >= getRules().maxNumOfHouses)) {
							return false;
						}
					}
					int level = ((Street) field).getBuiltLevel();
					if (level + levelChange < getRules().maxNumOfHouses && level < getRules().maxNumOfHouses) {
						state.getBank().changeHouses(-levelChange);
					} else if (level + levelChange >= getRules().maxNumOfHouses) {
						state.getBank().changeHotels(-1);
						state.getBank().changeHouses(level);
					} else if (level + levelChange < getRules().maxNumOfHouses && level >= getRules().maxNumOfHouses) {
						state.getBank().changeHotels(1);
						state.getBank().changeHouses(level - 1 - levelChange);
					}
					logic.upgrade((Street) field, levelChange);
					logic.exchangeMoney(player, state.getBank(),
							((Street) field).getFieldGroup().getHousePrice()
									* levelChange);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public synchronized boolean deconstruct(int playerID, int position) {
		if (state.getGameIsWon()) {
			return false;
		}
		if (changeLevel(playerID, position, -1)) {
			for (IClient client : clients) {
				client.informDestruct(position);
			}
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean toggleMortgage(int playerID, int position) {
		if (state.getGameIsWon()) {
			return false;
		}
		Player player = state.getPlayerById(playerID);
		Field field = state.getFieldAt(position);
		if (player != null) {
			if (field != null) {
				if (rules.isFieldMortgageable(player, field)) {
					logic.toggleMortgage((BuyableField) field);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public synchronized void sendMessage(String text, int sender) {
		for (IClient client : this.clients) {
			client.informMessage(text, sender, false);
		}
	}

	@Override
	public synchronized void sendPrivateMessage(String text, int sender,
			int reciever) {
		if (reciever >= 0 && reciever < this.connectedClients) {
			Player player = state.getPlayerById(reciever);
			if (player != null && player instanceof ServerPlayer) {
				((ServerPlayer) player).getClient().informMessage(text, sender,
						true);
			}
		}
	}

	/**
	 * Gets the Name of the Server
	 * 
	 * @return Servername
	 */
	public synchronized String getName() {
		return this.name;
	}

	/**
	 * Gets the number of Clients that can connect
	 * 
	 * @return maximum Clientconnections
	 */
	public synchronized int getMaxClients() {
		return this.maxClients;
	}

	public synchronized int getConnectedClients() {
		return this.connectedClients;
	}

	@Override
	public synchronized int getTurnsInPrison(int playerID) {
		Player player = state.getPlayerById(playerID);
		if (player != null) {
			if (this.rules.isPlayerInPrison(player)) {
				return player.getJail().getRoundsToWait();
			} else {
				return 0;
			}
		}
		return -1;
	}

	@Override
	public synchronized boolean useGetOutOfJailCard(int playerID) {
		if (state.getGameIsWon()) {
			return false;
		}
		ServerPlayer player = state.getPlayerById(playerID);
		if (player != null && rules.isPlayerInPrison(player)) {
			if (rules.canPlayerGetOutOfJail(player, true)) {
				logic.playerUsesGetOutOfJailCard(player);
				return true;
			}
		}
		return false;
	}

	public synchronized List<IClient> getClients() {
		return this.clients;
	}

	private int tries = 0;
	
	@Override
	public synchronized boolean payFine(int playerID) {
		display("Player tries to use fine to get out of jail!");
		if (state.getGameIsWon()) {
			return false;
		}
		tries++;
		if(tries >= 10) {
			this.endTurn(playerID);
		}
		Player player = state.getPlayerById(playerID);
		System.out.println("player is on field " + player.getSignedPosition());
		if (player != null && rules.isPlayerInPrison(player)) {
			System.out.println("player is valid!");
			if (player.getBalance() >= player.getJail().getMoneyToPay()) {
				logic.playerUsesFineForJail(player);
				System.out.println("player is then on field " + player.getSignedPosition());
				display("Player has used fine to get out of jail!");
				return true;
			}
		}
		return true;
	}

	public int getMaximumBuiltLevel() {
		return 5;
	}

	@Override
	public synchronized int getEstateHousePrice(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof Street) {
			StreetFieldGroup group = ((Street) field).getFieldGroup();
			if (group != null) {
				return group.getHousePrice();
			}
		}
		return -1;
	}

	/**
	 * Returns the money the player has to pay to leave the jail.
	 * 
	 * @param position
	 *            The position of the jail.
	 * @return The money the player has to pay. If there is no money the return
	 *         is undefined;.
	 */
	public synchronized int getMoneyToPay(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof Jail) {
			return ((Jail) field).getMoneyToPay();
		}
		return -1;
	}

	/**
	 * Returns the number of rounds the player has to wait if the player is in
	 * jail.
	 * 
	 * @param position
	 *            The position of the jail.
	 * @return The number of rounds the player has to wait. If this is no jail
	 *         it return is undefined.
	 */
	public synchronized int getRoundsToWait(int position) {
		Field field = state.getFieldAt(position);
		if (field != null && field instanceof Jail) {
			return ((Jail) field).getRoundsToWait();
		}
		return -1;
	}

	private synchronized Field newEventCardField(int position, FieldGroup group) {
		return group.addField(new CardField("Ereignis- karte", position, false,
				this.logic));
	}

	private synchronized Field newCommunityCardField(int position,
			FieldGroup group) {
		return group.addField(new CardField("Gemein- schafts- karte", position,
				true, this.logic));
	}

	private void loadDefaultGameStateFields(Field[] fields) {

		// Initialise field groups
		StationFieldGroup stations = new StationFieldGroup();
		StreetFieldGroup[] streets = new StreetFieldGroup[8];
		streets[0] = new StreetFieldGroup(0, "Dagobah", 1000);
		streets[1] = new StreetFieldGroup(1, "Hoth", 1000);
		streets[2] = new StreetFieldGroup(2, "Tatooine", 2000);
		streets[3] = new StreetFieldGroup(3, "Yavin&nbsp;Vier", 2000);
		streets[4] = new StreetFieldGroup(4, "Wolkenstadt", 3000);
		streets[5] = new StreetFieldGroup(5, "Todesstern", 3000);
		streets[6] = new StreetFieldGroup(6, "Endor", 4000);
		streets[7] = new StreetFieldGroup(7, "Coruscant", 4000);
		InfrastructureFieldGroup infrastructures = new InfrastructureFieldGroup();
		FieldGroup taxGroups = new FieldGroup(FieldGroup.TAX);
		FieldGroup go = new FieldGroup(FieldGroup.GO);
		FieldGroup communityCards = new FieldGroup(FieldGroup.COMMUNITY);
		FieldGroup eventCards = new FieldGroup(FieldGroup.EVENT);
		FieldGroup jails = new FieldGroup(FieldGroup.JAIL);
		FieldGroup freeParkings = new FieldGroup(FieldGroup.FREE_PARKING);
		FieldGroup goToJail = new FieldGroup(FieldGroup.GO_TO_JAIL);

		FreeParking freeParking;

		// Add Streets
		fields[0] = go.addField(new GoField("Los", 0, this.logic));
		fields[1] = streets[0].addField(new Street("Sumpf", 1, new int[] { 40,
				200, 600, 1800, 3200, 5000 }, 0, 1200, logic));
		fields[2] = this.newEventCardField(2, eventCards);
		fields[3] = streets[0].addField(new Street("Jodas Hütte", 3, new int[] {
				80, 400, 1200, 3600, 6400, 9000 }, 0, 1200, logic));
		fields[4] = taxGroups.addField(new TaxField("Landungs- steuer", 4,
				4000, this.logic));
		fields[5] = stations.addField(new Station("TIE-Fighter", 5, 4000));
		fields[6] = streets[1].addField(new Street("Echo-Basis", 6, new int[] {
				120, 600, 1800, 5400, 8000, 11000 }, 0, 2000, logic));
		fields[7] = this.newCommunityCardField(7, communityCards);
		fields[8] = streets[1].addField(new Street("Eis-Steppen", 8, new int[] {
				120, 600, 1800, 5400, 8000, 11000 }, 0, 2000, logic));
		fields[9] = streets[1].addField(new Street("Nordgebirge", 9, new int[] {
				160, 800, 2000, 6000, 9000, 12000 }, 0, 2400, logic));
		fields[10] = jails.addField(new Jail("Gefängnis", 10, 1000, 3));
		fields[11] = streets[2].addField(new Street("Lars Heimstatt", 11,
				new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, 0, 2800,
				logic));
		fields[12] = infrastructures.addField(new InfrastructureField(
				"Kern-Reaktor", 12, 3000, this.logic));
		fields[13] = streets[2].addField(new Street("Mos Eisley", 13,
				new int[] { 200, 1000, 3000, 9000, 12500, 15000 }, 0, 2800,
				logic));
		fields[14] = streets[2].addField(new Street("Jabbas Palast", 14,
				new int[] { 240, 1200, 3600, 10000, 14000, 18000 }, 0, 3200,
				logic));
		fields[15] = stations.addField(new Station("Millenium Falke", 15, 4000,
				this.logic));
		fields[16] = streets[3].addField(new Street("Kommandozentrale", 16,
				new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, 0, 3600,
				logic));
		fields[17] = this.newEventCardField(17, eventCards);
		fields[18] = streets[3].addField(new Street("Massassi Tempel", 18,
				new int[] { 280, 1400, 4000, 11000, 15000, 19000 }, 0, 3600,
				logic));
		fields[19] = streets[3].addField(new Street("Tempel-Thronsaal", 19,
				new int[] { 320, 1600, 4400, 12000, 16000, 20000 }, 0, 4000,
				logic));
		freeParking = new FreeParking("Frei Parken", 20, this.logic);
		fields[20] = freeParkings.addField(freeParking);
		fields[21] = streets[4].addField(new Street("Andockbucht", 21,
				new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, 0, 4400,
				logic));
		fields[22] = this.newCommunityCardField(22, communityCards);
		fields[23] = streets[4].addField(new Street("Karbon- Gefrierkammer",
				23, new int[] { 360, 1800, 5000, 14000, 17500, 21000 }, 0,
				4400, logic));
		fields[24] = streets[4].addField(new Street("Reaktor- Kontrollraum",
				24, new int[] { 400, 2000, 6000, 15000, 18500, 22000 }, 0,
				4800, logic));
		fields[25] = stations.addField(new Station("X-Wing Fighter", 25, 4000,
				this.logic));
		fields[26] = streets[5].addField(new Street("Lande-Deck", 26,
				new int[] { 440, 2200, 6600, 16000, 19500, 23000 }, 0, 5200,
				logic));
		fields[27] = streets[5].addField(new Street("Thronsaal", 27, new int[] {
				440, 2200, 6600, 16000, 19500, 23000 }, 0, 5200, logic));
		fields[28] = infrastructures.addField(new InfrastructureField(
				"Wasser- Farm", 28, 3000, this.logic));
		fields[29] = streets[5].addField(new Street("Hauptreaktor", 29,
				new int[] { 480, 2400, 7200, 17000, 20500, 24000 }, 0, 5600,
				logic));
		fields[30] = goToJail.addField(new GoToJail("Gehe ins Gefängnis", 30,
				this.logic, (Jail) fields[10]));
		// Fabians Strange gelbe Karte wurde hiermit hoch offiziell von Max
		// entfernt!
		// fields[30] = streets[5].addField(new Street("foobar", 30, new int[] {
		// 480, 2400, 7200, 17000, 20500, 240()00 }, 0, 5600, logic));
		fields[31] = streets[6].addField(new Street("Wald", 31, new int[] {
				520, 2600, 7800, 18000, 22000, 25500 }, 0, 6000, logic));
		fields[32] = streets[6].addField(new Street("Schildgenerator", 32,
				new int[] { 520, 2600, 7800, 18000, 22000, 25500 }, 0, 6000,
				logic));
		fields[33] = this.newEventCardField(33, eventCards);
		fields[34] = streets[6].addField(new Street("Ewok-Dorf", 34, new int[] {
				560, 3000, 9000, 20000, 24000, 28000 }, 0, 6400, logic));
		fields[35] = stations
				.addField(new Station("Stern-Zerstörer", 35, 4000));
		fields[36] = this.newCommunityCardField(36, communityCards);
		fields[37] = streets[7].addField(new Street("Platz des Volkes", 37,
				new int[] { 700, 3500, 10000, 22000, 16000, 30000 }, 0, 7000,
				logic));
		fields[38] = taxGroups.addField(new TaxField("Kopf-Geld Prämie", 38,
				2000));
		fields[39] = streets[7].addField(new Street("Imperialer Palast", 39,
				new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }, 0, 8000,
				logic));

		stations.setRent(new int[] { 500, 1000, 2000, 4000 });
		infrastructures.setFactors(new int[] { 80, 200 });

		// Add Cards
		CardStack comm = ((ServerGameState) this.logic.getGameState())
				.getCommunityCards();
		for (int i = 0; i < 1000; i++) {
		    comm.add(new GetOutOfJailCard("comm jail (" + i + ")", comm, this.logic));    
		}
		comm.add(Card.newNormalCard("comm +100 money", comm,
				ActionFactory.newTransferMoneyToBank(this.logic, -100)));
		comm.add(Card.newNormalCard("comm 100 m > free", comm, ActionFactory
				.newTransferMoneyToFreeParking(this.logic, 100, freeParking)));
		comm.add(Card.newNormalCard("comm 100 m (p. Hou) 1000 (p. Hot)", comm,
				new ActionPayForBuildings(this.logic, 100, 1000, this.logic
						.getGameState().getBank())));
		comm.add(Card.newNormalCard("comm +100 m (p. Ply)", comm,
				new ActionTransferMoneyToPlayers(this.logic, 100)));

		CardStack even = ((ServerGameState) this.logic.getGameState())
				.getEventCards();
		for (int i = 0; i < 1000; i++) {
		    even.add(new GetOutOfJailCard("even jail (" + i + ")", even, this.logic));    
		}
		even.add(Card.newNormalCard("even +100 money", even,
				ActionFactory.newTransferMoneyToBank(this.logic, -100)));
		even.add(Card.newNormalCard("even 100 m > free", even, ActionFactory
				.newTransferMoneyToFreeParking(this.logic, 100, freeParking)));
		even.add(Card.newNormalCard("even 100 m (p. Hou) 1000 (p. Hot)", even,
				new ActionPayForBuildings(this.logic, 100, 1000, this.logic
						.getGameState().getBank())));
		even.add(Card.newNormalCard("even +100 m (p. Ply)", even,
				new ActionTransferMoneyToPlayers(this.logic, 100)));
	}

	// TODO remove after testing is complete
	public ServerGameState getGameState() {
		return this.state;
	}

	// TODO remove after testing is complete
	public ServerLogic getLogic() {
		return this.logic;
	}

	@Override
	public int getActing() {
		if (trade != null && trade.getPartner() != null) {
			return trade.getPartner().getId();
		}
		return -1;
	}

	@Override
	public int getFreeParkingPot(int position) {
		Field f = this.getGameState().getFieldAt(position);
		if (f instanceof FreeParking) {
			return ((FreeParking) f).getMoneyInPot();
		} else {
			return -1;
		}
	}
}
