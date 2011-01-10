package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.Card;

import java.util.ArrayList;
import java.util.List;

public class Player implements IMoneyPartner{

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

}
