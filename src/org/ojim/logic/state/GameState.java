package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;
import org.ojim.iface.Rules;

public class GameState {

	private final int FIELDS_AMOUNT = 40;
	private Player[] players;
	private Bank bank;
	private Field[] fields;
	private Rules rules;
	private DiceSet dices;
	private Player activePlayer;
	
	
	public GameState(int maxPlayerCount) {
		this.players = new Player[maxPlayerCount];
		this.fields = new Field[FIELDS_AMOUNT];
		this.bank = new Bank();
		this.rules = new Rules();//30000, 2000, true, true, false, true);
		this.dices = new OjimDiceSet(1337);
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
	
	public Field getFieldByID(int fieldID) {
		if(fieldID >= FIELDS_AMOUNT) {
			return null;
		}
		return fields[fieldID];
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
}
