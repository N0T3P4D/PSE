package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;import org.ojim.logic.rules.Card;
import java.util.ArrayList;
import java.util.List;

public class Player implements IMoneyPartner{

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

	private List<Card> cards;
	
	public Player() {
		this.cards = new ArrayList<Card>(2);
	}
	
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

}
