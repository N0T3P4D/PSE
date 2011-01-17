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

import org.ojim.iface.IClient;
import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.actions.Action;
import org.ojim.logic.actions.ActionGetOutOfJailCard;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.Jail;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.ServerGameState;
import org.ojim.logic.state.ServerPlayer;

/**
 * Acts as logic, but this class informs also the players.
 * 
 * @author Fabian Neundorf
 */
public class ServerLogic extends Logic {

	/**
	 * Creates a new ServerLogic which contains Server-specific content 
	 * @param state The GameState on which the Logic applies
	 * @param rules The GameRules that apply for this Server
	 */
	public ServerLogic(ServerGameState state, GameRules rules) {
		super(state, rules);
	}
	
	public void setPlayerBankrupt(Player player) {
		player.transferMoney(-(player.getBalance() + 1));
		player.setBankrupt();
		
		// Inform All Players that this Player is bankrupt
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if(onePlayer instanceof ServerPlayer) {
				((ServerPlayer)onePlayer).sendMessage("Current Player is Bankrupt!", 0, false);
			}
		}		
	}
	
	/**
	 * Start a new Turn in the Game
	 */
	public void startNewTurn() {
		//Get a new Player On Turn
		this.GetNewPlayerOnTurn();
		
		//Inform All Player that a new Turn has come
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if(onePlayer instanceof ServerPlayer) {
				((ServerPlayer)onePlayer).getClient().informTurn(this.state.getActivePlayer().getId());
			}
		}
	}
	
	/**
	 * Called when a MoneyPot of a Free-Parking-Field should be emptied
	 * @param field
	 */
	public void getFreeParkingMoney(FreeParking field) {
		this.exchangeMoney(field, this.getGameState().getActivePlayer(), field.getMoneyInPot());
	}
	
	/**
	 * Called when a Player should get into Jail
	 * @param player The Player who is getting into Jail
	 * @param jail the specific Jail where the Player goes to
	 */
	public void sendPlayerToJail(ServerPlayer player, Jail jail) {
		 player.sendToJail(jail);
		 for (Player onePlayer : this.getGameState().getPlayers()) {
			if(onePlayer instanceof ServerPlayer) {
				((ServerPlayer)onePlayer).getClient().informMove(jail.getPosition(), player.getId());
				//TODO Inform Players that this one is in Prison?
			}
		}
	}
	
	/**
	 * Start a Game, sets the current Player and informs all Players the Game has started
	 */
	public void startGame() {
		//Set a random Player to be the StartPlayer
		int playerCount = this.getGameState().getPlayers().length;
		int start = new Random().nextInt(playerCount);
		this.getGameState().startGame(start);
		
		//Send all Players notification that the Game has started
		for(Player player : this.getGameState().getPlayers()) {
			if(player instanceof ServerPlayer) {
				IClient client = ((ServerPlayer)player).getClient();
				client.informStartGame(playerCount);
			}
		}
		
	}
	
	/**
	 * Exchanges Money and informs all Players if payer or payee is a Player
	 * @param payer The Player paying Money
	 * @param payee the Player getting Money 
	 * @param amount The amount of Money
	 */
	public void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee,
			int amount) {
		Bank.exchangeMoney(payer, payee, amount);
		
		
		//Inform other Players that Cash has changed
		for(Player player : this.getGameState().getPlayers()) {
			if(player instanceof ServerPlayer) {
				IClient client = ((ServerPlayer)player).getClient();
				if(payer instanceof Player) {
					client.informCashChange(((Player)payer).getId(), -amount);
				}
				if(payee instanceof Player) {
					client.informCashChange(((Player)payee).getId(), amount);
				}
			}
		}
	}

	/**
	 * Called when a Player should use a GetOutOfJail-Card
	 * @param player The Player using the Card
	 */
	public void playerUsesGetOutOfJailCard(Player player) {
		for(Card card : player.getCards()) {
			for(Action action : card.getActions()) {
				if(action instanceof ActionGetOutOfJailCard) {
					card.execute();
				}
			}
		}
	}

	public void playerUsesFineForJail(Player player) {
		
		//Lets a Player pay money to get out of Jail
		this.exchangeMoney(player, this.getGameState().getBank(), player.getJail().getMoneyToPay());
		player.sendToJail(null);
		
		//Inform all Player that the current Player is now out of Jail
		for (Player onePlayer : this.getGameState().getPlayers()) {
			if(onePlayer instanceof ServerPlayer) {
				//TODO Add Language
				((ServerPlayer)onePlayer).getClient().informMessage("Current Player is now out of Jail!", 0, false);
			}
		}
	}

	public void GetNewPlayerOnTurn() {
		Player[] players = this.getGameState().getPlayers();
		Player currentPlayer = this.getGameState().getActivePlayer();
		for(int i = 0; i < players.length; i++) {
			if(players[i].equals(currentPlayer)) {
				for(int j = 1; j < players.length; j++) {
					if(players[i] != null && !players[i].getIsBankrupt()) {
						this.getGameState().setActivePlayer(players[i]);
						this.getGameState().setActivePlayerNeedsToRoll(true);
					}
				}
			}
		}
		
	}

}
