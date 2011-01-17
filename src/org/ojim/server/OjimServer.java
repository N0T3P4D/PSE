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

import java.util.LinkedList;
import java.util.List;

import org.ojim.client.ai.AIClient;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.logic.Logic;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.ServerGameState;
import org.ojim.logic.state.ServerPlayer;
import org.ojim.logic.state.Street;

import edu.kit.iti.pse.iface.IServer;
import edu.kit.iti.pse.iface.IServerAuction;
import edu.kit.iti.pse.iface.IServerTrade;

public class OjimServer implements IServer, IServerAuction, IServerTrade {

	private String name;
	private boolean isOpen = false;
	private int connectedClients;
	private int maxClients;
	private List<IClient> clients;

	private ServerGameState state;
	private ServerLogic logic;
	private GameRules rules;

	public OjimServer(String name) {
		this.name = name;

	}

	boolean initGame(int playerCount, int aiCount) {

		if (isOpen) {
			return false;
		}

		// Make sure no negative numbers appear and there are players at all
		if (playerCount < 0 || aiCount < 0 || playerCount + aiCount == 0) {
			display("Player- and AICount must not be negative and one of them must be positive");
			return false;
		}

		// Initializing Fields
		this.connectedClients = 0;
		this.maxClients = playerCount + aiCount;
		clients = new LinkedList<IClient>();
		this.state = new ServerGameState();

		// Add AIClients to the Game
		for (int i = 0; i < aiCount; i++) {
			addPlayer(new AIClient());
		}

		// Open the Game
		isOpen = true;
		return true;
	}

	boolean endGame() {

		if (!isOpen) {
			return true;
		}
		// Closing the Game
		isOpen = false;

		// Disconnecting all Clients
		for (IClient client : clients) {
			if (client instanceof AIClient) {
				client = null;
			} else {
				disconnect(client);
				client = null;
			}
		}

		// Setting the Fields to the Standard Values
		this.connectedClients = 0;
		this.maxClients = 0;
		clients = null;

		return true;
	}

	/**
	 * Disconnects a Client from the Game
	 * 
	 * @param client
	 *            The Client to disconnect
	 */
	private void disconnect(IClient client) {
		for (IClient oneClient : this.clients) {
			if (oneClient.equals(oneClient)) {
				// TODO Add Language
				oneClient.informMessage("You have been Disconnected!", 0, true);
				this.clients.remove(oneClient);
			}
			if (this.state.getActivePlayer().getId() != -1) {
				// TODO Add AI as replacement
				// TODO Add Language
				for (IClient informClient : this.clients) {
					informClient.informMessage("Client has been disconnected!",
							0, false);
				}
			}
		}

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
	public boolean initTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTradeState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPartner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean offerCash(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerEstate(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireCash(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requireEstate(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getOfferedEstates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOfferedCash() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfOfferedGetOutOfJailCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getRequiredEstates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRequiredCash() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfRequiredGetOutOfJailCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cancelTrade(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean proposeTrade(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAuctionState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAuctionedEstate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHighestBid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBidder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean placeBid(int playerID, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlayerPiecePosition(int playerID) {
		Player player = state.getPlayerByID(playerID);
		if (player == null) {
			return -1;
		}
		return player.getPosition();
	}

	@Override
	public synchronized int addPlayer(IClient client) {

		// TestClients are Threads to test things better
		// if(client instanceof TestClient) {
		// TestThread t = new TestThread((TestClient)client);
		// t.start();
		// }

		for (int i = 0; i < maxClients; i++) {
			if (state.getPlayerByID(i) == null) {
				this.clients.add(client);
				state.setPlayer(i,
						new Player(client.getName(), 0,
								state.getRules().startMoney, i, i));
				return i;
			}
		}
		return -1;
	}

	@Override
	public void setPlayerReady(int playerID) {
		state.getPlayerByID(playerID).setIsReady(true);

		// Are all players Ready? then start the game
		if (this.connectedClients == this.maxClients) {
			for (int i = 0; i < connectedClients; i++) {

				// If at least 1 Player is not ready, dont start the game
				if (!state.getPlayerByID(i).getIsReady()) {
					return;
				}
			}
			// All Players are ready, the Game can be started now
			startGame();
		}
	}

	private void startGame() {
		this.rules = new GameRules(this.state, new Rules());
		this.logic = new ServerLogic(this.state, this.rules);
		this.display("Started a Game!");
		logic.startGame();
	}

	@Override
	public String getPlayerName(int playerID) {
		return state.getPlayerByID(playerID).getName();
	}

	@Override
	public int getPlayerColor(int playerID) {
		return state.getPlayerByID(playerID).getColor();
	}

	@Override
	public Rules getRules() {
		return state.getRules();
	}

	@Override
	public String getEstateName(int position) {
		return state.getFieldByID(position).getName();
	}

	@Override
	public int getEstateColorGroup(int position) {
		if (position < 0 || position >= state.getNumberOfFields()) {
			return -1;
		}
		if (!(state.getFieldByID(position) instanceof BuyableField)) {
			return -1;
		}
		return ((BuyableField) state.getFieldByID(position)).getFieldGroup()
				.getColor();
	}

	@Override
	public int getEstateHouses(int position) {
		Field field = state.getFieldByID(position);
		if (field instanceof Street) {
			return ((Street) field).getBuiltLevel();
		}
		return -1;
	}

	@Override
	public int getEstatePrice(int position) {
		Field field = state.getFieldByID(position);
		if (field instanceof BuyableField) {
			((BuyableField) field).getPrice();
		}
		return -1;
	}

	@Override
	public int getEstateRent(int position, int houses) {
		Field field = state.getFieldByID(position);
		if (field instanceof Street) {
			((Street) field).getRent(houses);
		}
		return -1;
	}

	@Override
	public String getGameStatusMessage(int playerID) {
		Player player = state.getPlayerByID(playerID);
		if (player != null && player instanceof ServerPlayer) {
			return ((ServerPlayer) player).getGameStatusMessage();
		}
		return "";
	}

	@Override
	public boolean isMortgaged(int position) {
		Field field = state.getFieldByID(position);
		if (field instanceof BuyableField) {
			return ((BuyableField) field).isMortgaged();
		}
		return false;
	}

	@Override
	public int getOwner(int position) {
		Field field = state.getFieldByID(position);
		if (field instanceof BuyableField) {
			return ((BuyableField) field).getOwner().getId();
		}
		return -1;
	}

	@Override
	public int getDiceValue() {
		return state.getDices().getResultSum();
	}

	@Override
	public int[] getDiceValues() {
		return state.getDices().getResult();
	}

	@Override
	public int getPlayerCash(int playerID) {
		return state.getPlayerByID(playerID).getBalance();
	}

	@Override
	public int getPlayerOnTurn() {
		if (state.getActivePlayer() != null) {
			return state.getActivePlayer().getId();
		}
		return -1;
	}

	@Override
	public int getNumberOfGetOutOfJailCards(int playerID) {
		return state.getPlayerByID(playerID).getNumberOfGetOutOfJailCards();
	}

	@Override
	public int getNumberOfHousesLeft() {
		return state.getBank().getHouses();
	}

	@Override
	public int getNumberOfHotelsLeft() {
		return state.getBank().getHotels();
	}

	@Override
	public boolean rollDice(int playerID) {
		Player player = this.state.getPlayerByID(playerID);
		if (player == null) {
			return false;
		}

		if (this.rules.isPlayerInPrison(player)) {

			// Still need to wait
			if (player.getJail().getRoundsToWait() > 0) {
				// TODO continue
			}
		}
		if (playerID == state.getActivePlayer().getId()) {
			state.getDices().roll();
			return true;
		}
		return false;
	}

	@Override
	public boolean accept(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean decline(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endTurn(int playerID) {
		Player player = state.getPlayerByID(playerID);
		if (rules.isPlayerOnTurn(player)
				&& !rules.isRollRequiredByActivePlayer()) {

			// Player is bankrupt
			if (player.getBalance() < 0) {
				this.logic.setPlayerBankrupt(player);
			} 
			
			logic.startNewTurn();
			return true;
		}
		return false;
	}

	@Override
	public boolean declareBankruptcy(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean construct(int playerID, int position) {
		return changeLevel(playerID, position, 1);
	}

	private boolean changeLevel(int playerID, int position, int levelChange) {
		Player player = state.getPlayerByID(playerID);
		Field field = state.getFieldAt(position);
		if (player != null) {
			if (field != null) {
				if (rules.isFieldUpgradable(player, field, levelChange)) {
					logic.upgrade((Street) field, levelChange);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deconstruct(int playerID, int position) {
		return changeLevel(playerID, position, -1);
	}

	@Override
	public boolean toggleMortgage(int playerID, int position) {
		Player player = state.getPlayerByID(playerID);
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
	public void sendMessage(String text) {
		for (IClient client : this.clients) {
			// TODO Add sender ID
			client.informMessage(text, 0, false);
		}
	}

	@Override
	public void sendPrivateMessage(String text, int reciever) {
		if (reciever >= 0 && reciever < this.connectedClients) {
			Player player = state.getPlayerByID(reciever);
			if (player != null && player instanceof ServerPlayer) {
				((ServerPlayer) player).getClient()
						.informMessage(text, 0, true);
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public int getMaxClients() {
		return this.maxClients;
	}

	public int getConnectedClients() {
		return this.connectedClients;
	}

	@Override
	public int getTurnsInPrison(int playerID) {
		Player player = state.getPlayerByID(playerID);
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
	public boolean useGetOutOfJailCard(int playerID) {
		Player player = state.getPlayerByID(playerID);
		if (player != null && rules.isPlayerInPrison(player)) {
			if (rules.canPlayerGetOutOfJail(player, true)) {
				logic.playerUsesGetOutOfJailCard(player);
				return true;
			}
		}
		return false;
	}

	public List<IClient> getClients() {
		return this.clients;
	}

	@Override
	public boolean payFine(int playerID) {
		Player player = state.getPlayerByID(playerID);
		if (player != null && rules.isPlayerInPrison(player)) {
			if (player.getBalance() >= player.getJail().getMoneyToPay()) {
				logic.playerUsesFineForJail(player);
				return true;
			}
		}
		return false;
	}

}
