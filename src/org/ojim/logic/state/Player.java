package org.ojim.logic.state;

import org.ojim.iface.Rules;

public class Player {

	/**
	 * Name to identify the player
	 */
	private String name;
	
	/**
	 * The Position of the Player on the GameBoard
	 */
	private int position;
	
	/**
	 * Amount of Cash the Player possesses
	 */
	private int balance;
	
	/**
	 * The ID of the Player
	 */
	private int id;

	/**
	 * Is the Player ready to start the Game
	 */
	private boolean isReady;
	
	/**
	 * 
	 * @param name
	 * @param position
	 * @param balance
	 * @param id
	 */	
	public Player(String name, int position, int balance, int id) {
		this.name = name;
		this.position = position;
		this.balance = balance;
		this.id = id;
		this.isReady = false;
	}
	
	/**
	 * Transfers the amount of money to the player.
	 * 
	 * @param amount
	 *            the amount of money.
	 */
	public void transferMoney(int amount) {
		this.balance += amount;
	}
	
	public void setIsReady(boolean ready) {
		this.isReady = ready;
	}
	
	public boolean getIsReady() {
		return this.isReady;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getBalance() {
		return this.balance;
	}

	/**
	 * Transfers the money amount from player1 to player2. Information: If the
	 * amount is negative the player1 will get cash and the player2 has to pay
	 * it.
	 * 
	 * @param player1
	 *            The money of this player will be decreased my the amount.
	 * @param player2
	 *            The money of this player will be increased my the amount.
	 * @param amount
	 *            The money amount.
	 */
	public static void exchangeMoney(Player player1, Player player2, int amount) {
		player1.transferMoney(-amount);
		player2.transferMoney(amount);
	}

}
