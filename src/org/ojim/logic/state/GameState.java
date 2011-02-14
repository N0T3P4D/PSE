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

package org.ojim.logic.state;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.jdom.DataConversionException;
import org.jdom.Element;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.FieldGroup;
import org.ojim.iface.Rules;

public class GameState implements Serializable {

	public final static int MAXIMUM_PLAYER_COUNT = 8;
	private final static int FIELDS_AMOUNT = 40;
	
	private Map<Integer, Player> players;
	private List<Player> playerOrder;
	private Bank bank;
	private Field[] fields;
	private Rules rules;
	private DiceSet dices;
	private Player activePlayer;
	private boolean activePlayerNeedsToRoll;
	private boolean gameIsWon = false;
	private Map<Integer, FieldGroup> groups;
	private Auction auction;
	
	public GameState(int fieldsAmount) {
		this.players = new HashMap<Integer, Player>(MAXIMUM_PLAYER_COUNT);
		this.playerOrder = new ArrayList<Player>(MAXIMUM_PLAYER_COUNT);
		this.fields = new Field[fieldsAmount];
		this.bank = new Bank();
		this.rules = new Rules();//30000, 2000, true, true, false, true);
		this.dices = new OjimDiceSet(1337);
		this.groups = new HashMap<Integer, FieldGroup>();
		this.auction = null;
		
		//TODO (philip) really?
		this.activePlayerNeedsToRoll = true;
		//TODO Add the Possibility to load other GameStates		
	}
	
	public GameState() {
		this(GameState.FIELDS_AMOUNT);
	}
	
	public int getNumberOfFields() {
		return GameState.FIELDS_AMOUNT;
	}
	
	public Auction getAuction() {
		return this.auction;
	}
	
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	public DiceSet getDices() {
		return this.dices;
	}
	
	public void setDiceSet(DiceSet set) {
		this.dices = set;
	}
	
	public Rules getRules() {
		return rules;
	}
	
	public void setPlayer(Player player) {
		this.players.put(player.getId(), player);
		this.playerOrder.add(player);
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
		this.playerOrder.remove(player);
	}
	
	public void setPlayerOrder(Player... player) {
		if (player.length != this.players.size()) {
			this.playerOrder.clear();
			for (int i = 0; i < player.length; i++) {
				this.playerOrder.add(player[i]);
			}
		}
	}
	
	public Player getPlayerById(int playerId) {
		return this.players.get(playerId);
	}
	
	public Field getFieldAt(int position) {
		if(position >= FIELDS_AMOUNT) {
			return null;
		}
		return this.fields[position];
	}
	
	public void setFieldAt(Field field, int position) {
		this.fields[position] = field;
		FieldGroup group = field.getFieldGroup();
		this.groups.put(group.getColor(), group);
	}
	
	public Player[] getPlayers() {
		return this.players.values().toArray(new Player[0]);
	}
	
	//TODO: (xZise) return clone?
	public Map<Integer, Player> getPlayersMap() {
		return this.players;
	}
	
	/**
	 * Returns the bank.
	 * @return The bank.
	 * @see {@link Bank}
	 */
	public Bank getBank() {
		return this.bank;
	}
	
	//TODO: Does this class support this? If so: finish!
	public Player getActivePlayer() {
//		if(this.activePlayer == null) {
//			this.activePlayer = this.playerOrder.get(0);
//		}
		return this.activePlayer;
	}
	
//	public boolean saveGameState(String path) {
//		return saveGameState(new File(path));
//	}
//
//	private boolean saveGameState(File file) {
//		try {
//			if (!file.createNewFile()) {
//				return false;
//			}
//		} catch (IOException e) {
//			return false;
//		}
//		
//		try {
//			FileWriter fw = new FileWriter(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return true;
//	}
	
	public void saveGameState(File file) {
		
	}
	
	public void loadBoard(File file, ServerLogic logic) {
		
		// Irgendwie hat man hier jetzt alle Feldelemente:
		Element field = null;
		try {
		GameFieldLoader.readElement(field, logic, groups);
		} catch (DataConversionException e) {
			OJIMLogger.getLogger(this.getClass().toString()).log(Level.SEVERE, "unable to parse field element", e);
		}
		
	}

	public void startGame(int start) {
		this.activePlayer = this.players.get(start);
		
	}
	
	public void setActivePlayerNeedsToRoll(boolean needsToRoll) {
		this.activePlayerNeedsToRoll = needsToRoll;
	}

	public boolean getActivePlayerNeedsToRoll() {
		return this.activePlayerNeedsToRoll;
	}

	public void setActivePlayer(Player player) {
		this.activePlayer = player;		
	}

	public void setGameIsWon(boolean b) {
		this.gameIsWon = b;
	}
	
	public boolean getGameIsWon() {
		return this.gameIsWon;
	}
}
