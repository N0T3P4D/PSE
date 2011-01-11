package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.Card;
import java.util.ArrayList;
import java.util.List;

public class Player implements IMoneyPartner {

	/** Name to identify the player */
	private String name;

	/** The position of the player on the GameBoard */
	private int position;

	/** Amount of cash the player possesses */
	private int balance;

	/** The ID of the player */
	private int id;

	/** The color that symbolizes the Player */
	private int color;

	/** Contains all cards this player got (e.g. get out of jail cards). */
	private List<Card> cards;

	/** Contains all fields which this player owns. */
	private List<BuyableField> fields;

	/**
	 * Is the Player ready to start the Game
	 */
	private boolean isReady;

	/**
	 * Creates a new player object.
	 * 
	 * @param name
	 *            The name of the player.
	 * @param position
	 *            The position on the board.
	 * @param balance
	 *            The starting balance.
	 * @param id
	 *            The unique player ID.
	 * @param color
	 *            The color of the player.
	 */
	public Player(String name, int position, int balance, int id, int color) {
		this.cards = new ArrayList<Card>(2);
		this.fields = new ArrayList<BuyableField>();
		
		this.name = name;
		this.position = position;
		this.balance = balance;
		this.id = id;
		this.color = color;
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

	public int getColor() {
		return this.color;
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

	/* TODO: Make immutable? */
	public List<BuyableField> getFields() {
		return this.fields;
	}

	/* CARD STACK */

	/**
	 * Inserts a card in the players card stack.
	 * 
	 * @param card
	 *            New card in the stack.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}

	/**
	 * Removes the card from the players card stack.
	 * 
	 * @param card
	 *            The card which will be removed.
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}

}
