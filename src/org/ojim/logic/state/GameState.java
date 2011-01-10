package org.ojim.logic.state;


public class GameState {

	private final int FIELDS_AMOUNT = 40;
	private Player[] players;
	private Field[] fields;
	
	public GameState(int maxPlayerCount) {
		players = new Player[maxPlayerCount];
		fields = new Field[FIELDS_AMOUNT];
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
