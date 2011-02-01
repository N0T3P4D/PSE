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

package org.ojim.logic;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ojim.iface.IClient;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.GetOutOfJailCard;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.ServerGameState;
import org.ojim.logic.state.ServerPlayer;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FreeParking;
import org.ojim.logic.state.fields.Jail;

/**
 * Acts as logic, but this class informs also the players.
 * 
 * @author Fabian Neundorf
 */
public class ServerLogic extends Logic {

	// AI
	private Logger logger;

	/**
	 * Creates a new ServerLogic which contains Server-specific content
	 * 
	 * @param state
	 *            The GameState on which the Logic applies
	 * @param rules
	 *            The GameRules that apply for this Server
	 */
	public ServerLogic(ServerGameState state, GameRules rules) {
		super(state, rules);
		logger = OJIMLogger.getLogger(this.getClass().toString());
	}

	public void setPlayerBankrupt(Player player) {
		player.transferMoney(-(player.getBalance() + 1));
		player.setBankrupt();
		Field field;
		for (int i = 0; i < this.getGameState().FIELDS_AMOUNT; i++) {
			field = this.getGameState().getFieldAt(i);
			if (field instanceof BuyableField
					&& ((BuyableField) field).getOwner() == player) {
				((BuyableField) field).buy(null);
			}
		}
		if (player instanceof ServerPlayer) {
			((ServerPlayer) player).getClient().informBankruptcy();
		}

		// Inform All Players that this Player is bankrupt
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if (onePlayer instanceof ServerPlayer) {
				((ServerPlayer) onePlayer).sendMessage(
						"Current Player is Bankrupt!", -1, false);
			}
		}
	}

	int number = 0;

	/**
	 * Start a new Turn in the Game
	 */
	public void startNewTurn() {
		// AI
		number++;
		if ((number % 1000000) == 0)
			logger.log(Level.INFO, "Starting new turn " + number++);
		// Get a new Player On Turn
		this.getNewPlayerOnTurn();
		this.getGameState().setActivePlayerNeedsToRoll(true);
		int id = this.getGameState().getActivePlayer().getId();
		// Inform All Player that a new Turn has come

		for (Player player : this.getGameState().getPlayers()) {
			if(player != this.getGameState().getActivePlayer()) {
				((ServerPlayer) player).getClient().informTurn(id);
			}
		}
		((ServerPlayer)this.getGameState().getActivePlayer()).getClient().informTurn(id);
		
		this.getGameState().setActivePlayerNeedsToRoll(true);
	}

	/**
	 * Called when a MoneyPot of a Free-Parking-Field should be emptied
	 * 
	 * @param field
	 */
	public void getFreeParkingMoney(FreeParking field) {
		this.exchangeMoney(field, this.getGameState().getActivePlayer(),
				field.getMoneyInPot());
	}

	/**
	 * Called when a Player should get into Jail
	 * 
	 * @param player
	 *            The Player who is getting into Jail
	 * @param jail
	 *            the specific Jail where the Player goes to
	 */
	public void sendPlayerToJail(ServerPlayer player, Jail jail) {
		player.sendToJail(jail);
		this.getGameState().setActivePlayerNeedsToRoll(false);
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if (onePlayer instanceof ServerPlayer) {
				((ServerPlayer) onePlayer).getClient().informMove(
						player.getId(), -jail.getPosition());
				// TODO Inform Players that this one is in Prison?
			}
		}
	}

	/**
	 * Start a Game, sets the current Player and informs all Players the Game
	 * has started
	 */
	public void startGame() {
		// Set a random Player to be the StartPlayer
		int playerCount = this.getGameState().getPlayers().length;
		int start = 1;//new Random().nextInt(playerCount);
		this.getGameState().startGame(start);

		int[] ids = new int[playerCount];
		int i = 0;
		for (Player player : this.getGameState().getPlayers()) {
			ids[i++] = player.getId();
		}

		// Send all Players notification that the Game has started
		for (Player player : this.getGameState().getPlayers()) {
			if (player instanceof ServerPlayer) {
				// AI
				IClient client = ((ServerPlayer) player).getClient();
				client.informStartGame(ids);
			}
		}
		// AI added
		logger.log(Level.INFO, "Almost Starting new turn");
		startNewTurn();
		
//		while(!this.getGameState().getGameIsWon() && this.getGameState().getActivePlayer() != null) {
//			startNewTurn();
//		}
		

	}

	/**
	 * Exchanges Money and informs all Players if payer or payee is a Player.
	 * 
	 * @param payer
	 *            The Player paying Money.
	 * @param payee
	 *            The Player getting Money.
	 * @param amount
	 *            The amount of Money.
	 */
	public void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee,
			int amount) {
		Bank.exchangeMoney(payer, payee, amount);

		// Inform other Players that Cash has changed
		for (Player player : this.getGameState().getPlayers()) {
			if (player instanceof ServerPlayer) {
				IClient client = ((ServerPlayer) player).getClient();
				if (payer instanceof Player) {
					client.informCashChange(((Player) payer).getId(), -amount);
				}
				if (payee instanceof Player) {
					client.informCashChange(((Player) payee).getId(), amount);
				}
			}
		}
	}

	/**
	 * Called when a Player should use a GetOutOfJail-Card
	 * 
	 * @param player
	 *            The Player using the Card
	 */
	public void playerUsesGetOutOfJailCard(ServerPlayer player) {
		for (Card card : player.getCards()) {
			if (card instanceof GetOutOfJailCard) {
				card.file();
			}
		}
	}

	public void playerUsesFineForJail(Player player) {

		// Lets a Player pay money to get out of Jail
		this.exchangeMoney(player, this.getGameState().getBank(), player
				.getJail().getMoneyToPay());
		this.sendPlayerOutOfJail(player);
	}

	public void sendPlayerOutOfJail(Player player) {
		player.sendToJail(null);

		// Inform all Player that the current Player is now out of Jail
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if (onePlayer instanceof ServerPlayer) {
				// TODO Add Language
				((ServerPlayer) onePlayer).getClient().informMessage(
						"Current Player is now out of Jail!", -1, false);
				((ServerPlayer) onePlayer).getClient().informMove(player.getId(), player.getPosition());
			}
			
		}

	}

	public void getNewPlayerOnTurn() {
		Player[] players = this.getGameState().getPlayers();
		Player currentPlayer = this.getGameState().getActivePlayer();
		for (int i = 0; i < players.length; i++) {
			if (players[i].equals(currentPlayer)) {
				for (int j = 1; j < players.length; j++) {
					int playerPos = (j + i) % players.length;
					if (players[playerPos] != null
							&& !players[playerPos].getIsBankrupt()) {
						this.getGameState().setActivePlayer(players[playerPos]);
						this.getGameState().setActivePlayerNeedsToRoll(true);
						return;
					}
				}
				
				// When only 1 Player is there and he is not bankrupt, he has not won
				if(players.length == 1 && !players[0].getIsBankrupt()) {
					return;
				}
				
				System.out.println("Player " + this.getGameState().getActivePlayer().getName() + " has won!");
				for (Player player : this.getGameState().getPlayers()) {
					if (player instanceof ServerPlayer) {
						// TODO add language
						((ServerPlayer) player).getClient().informMessage(
								"Player " + currentPlayer.getName()
										+ " has won!", -1, false);
					}
				}
				this.getGameState().setGameIsWon(true);
			}
		}

	}

	public void playerRolledOutOfJail(Player player) {
		this.sendPlayerOutOfJail(player);
	}
	
	public void auctionWithoutResult(BuyableField objective) {
		System.out.println("Action without result!");
		return;
	}	
	
	public void auctionWithResult(BuyableField objective, Player winner, int price) {
		objective.buy(winner);
		for(Player player : this.getGameState().getPlayers()) {
			if(player instanceof ServerPlayer) {
				((ServerPlayer)player).getClient().informBuy(winner.getId(), objective.getPosition());
			}
		}
		this.exchangeMoney(winner, this.getGameState().getBank(), price);
	}

	public void movePlayerTo(Field field, Player player, boolean secondary,
			boolean executePasses) {
		int fieldPos = field.getPosition();
		int playerPos = player.getPosition();
		while (playerPos != fieldPos) {
			playerPos = ++playerPos % GameState.FIELDS_AMOUNT;
			player.setPosition(playerPos);
			Field currentField = this.getGameState().getFieldAt(playerPos);
			if (executePasses) {
				currentField.passThrough();
			}
		}

		if (field instanceof Jail && secondary) {
			player.sendToJail((Jail) field);
		}
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if (onePlayer instanceof ServerPlayer) {
				((ServerPlayer) onePlayer).getClient().informMove(
						player.getId(), player.getSignedPosition());
			}
		}
	}

	public void payRent(Player player) {
		Field field = this.getGameState().getFieldAt(player.getPosition());
		payRent(player, (BuyableField) field);
	}

	public void payRent(Player player, BuyableField field) {
		Player owner = field.getOwner();
		if (owner != null && !player.equals(owner) && !field.isMortgaged()) {
			this.exchangeMoney(player, owner, field.getRent());
		}
	}

	public void movePlayerForDice(Player player, int result) {
		// Do the passthrough-Action for all Fields the Player steps on
		int position = player.getPosition();
		for (int i = 1; i < result; i++) {
			// Move Player 1 forward
			player.setPosition((position + i)
					% this.getGameState().FIELDS_AMOUNT);

			// Do the passthrough
			this.getGameState()
					.getFieldAt(
							(position + i) % this.getGameState().FIELDS_AMOUNT)
					.passThrough();
		}
		player.setPosition((position + result)
				% this.getGameState().FIELDS_AMOUNT);

		// Inform everyone that the Player has moved
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if (onePlayer instanceof ServerPlayer) {
				((ServerPlayer) onePlayer).getClient().informMove(
						player.getId(), player.getSignedPosition());
			}
		}
		// Do the Execute for the Field the Player is standing on
		this.getGameState()
				.getFieldAt(
						(position + result) % this.getGameState().FIELDS_AMOUNT)
				.execute();

	}

	public void buyStreet() {
		Player player = this.getGameState().getActivePlayer();
		int position = player.getPosition();
		BuyableField field = (BuyableField) this.getGameState().getFieldAt(
				position);
		this.exchangeMoney(player, this.getGameState().getBank(),
				field.getPrice());
		changeFieldOwner(null, player, field);
	}

	public void changeFieldOwner(Player oldOwner, Player newOwner,
			BuyableField field) {
		int newOwnerId = -1;
		if (newOwner != null) {
			newOwnerId = newOwner.getId();
		}
		field.buy(newOwner);
		for (Player player : this.getGameState().getPlayers()) {
			if (player instanceof ServerPlayer) {
				((ServerPlayer) player).getClient().informBuy(newOwnerId,
						field.getPosition());
			}
		}
	}

	
	
	public void endGame() {
		// TODO Free Stack here

	}

}
