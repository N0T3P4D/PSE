package org.ojim.logic.state;

import org.ojim.logic.accounting.Bank;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;

public class GameState {

	private final int FIELDS_AMOUNT = 40;
	private Player[] players;
	private Bank bank;
	private Field[] fields;
	private Rules rules;
	
	public GameState(int maxPlayerCount) {
		this.players = new Player[maxPlayerCount];
		this.fields = new Field[FIELDS_AMOUNT];
		this.bank = new Bank();
		rules = new Rules(30000, 2000, true, true, false, true);
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
	
	public Bank getBank() {
		return this.bank;
	}
	
	//TODO: Add possibility to return the number of fields.
	public int getNumberOfFields() {
		return FIELDS_AMOUNT;
	}
	
	//TODO: Does this class support this? If so: finish!s
	/* xZise: Implement this as buffer for some actions, can be modified if incorrect!*/
	public Player getActivePlayer() {
		return null;
	}
}
