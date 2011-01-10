package org.ojim.logic.state;

import org.ojim.logic.rules.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

	/**
	 * Name to identify the player
	 */
	private String name;
	
	/**
	 * Amount of Cash the Player possesses
	 */
	private int balance;
	
	/**
	 * The ID of the Player
	 */
	private int id;

	private List<Card> cards;
	
	public Player() {
		this.cards = new ArrayList<Card>(2);
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
	
	public String getName() {
		return this.name;
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
