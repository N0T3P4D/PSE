/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

package org.ojim.logic.state;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.rules.ActionGetOutOfJailCard;
import org.ojim.logic.rules.ActionMoveForward;
import org.ojim.logic.rules.Card;
import org.ojim.iface.Rules;

public class GameState {

	private final int FIELDS_AMOUNT = 40;
	private Player[] players;
	private Bank bank;
	private Field[] fields;
	private Rules rules;
	private DiceSet dices;
	private Player activePlayer;

	private CardStack eventCards;
	private CardStack communityCards;
	
	public GameState(int maxPlayerCount) {
		this.players = new Player[maxPlayerCount];
		this.fields = new Field[FIELDS_AMOUNT];
		this.bank = new Bank();
		this.rules = new Rules();//30000, 2000, true, true, false, true);
		this.dices = new OjimDiceSet(1337);
		
		this.eventCards = new CardStack();
		this.eventCards.add(new Card("foobar", this, false, new ActionMoveForward(this, 5)));
		this.eventCards.add(new Card("anti jail", this, true, new ActionGetOutOfJailCard(this)));
		
		this.communityCards = new CardStack();
	}
	
	public DiceSet getDices() {
		return this.dices;
	}
	
	public Rules getRules() {
		return rules;
	}
	
	public void setPlayer(int id, Player player) {
		players[id] = player;
	}
	
	public Player getPlayerByID(int playerID) {
		if(playerID >= players.length) {
			return null;
		}
		return players[playerID];
	}
	
	//xZise: Klingt nach dem was es macht (siehe getFieldAt). Entweder jedes Feld durchgehen und ID checken oder entfernen.
	public Field getFieldByID(int fieldID) {
		if(fieldID >= FIELDS_AMOUNT) {
			return null;
		}
		return fields[fieldID];
	}
	
	public Field getFieldAt(int position) {
		if(position >= FIELDS_AMOUNT) {
			return null;
		}
		return fields[position];
	}
	
	public Player[] getPlayers() {
		return this.players.clone();
	}
	
	/**
	 * Returns the bank.
	 * @return The bank.
	 * @see {@link Bank}
	 */
	public Bank getBank() {
		return this.bank;
	}
	
	/**
	 * Returns the number of fields.
	 * @return The number of fields.
	 */
	public int getNumberOfFields() {
		return FIELDS_AMOUNT;
	}
	
	//TODO: Does this class support this? If so: finish!
	public Player getActivePlayer() {
		return this.activePlayer;
	}
	
	public boolean saveGameState(String path) {
		return saveGameState(new File(path));
	}

	private boolean saveGameState(File file) {
		try {
			if (!file.createNewFile()) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		try {
			FileWriter fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*
	 * CARD STACK
	 */
	
	public Card getFirstEventCard() {
		return this.eventCards.getPointedCard();
	}
	
	public Card getFirstCommunityCard() {
		return this.communityCards.getPointedCard();
	}
}
