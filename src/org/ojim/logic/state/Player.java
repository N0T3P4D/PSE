package org.ojim.logic.state;

import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.rules.Card;
import java.util.ArrayList;
import java.util.List;

public class Player implements IMoneyPartner{

	//TODO: Add NullPlayer behaviour
	public static final Player NullPlayer = new Player();
	
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
	 * The Color that symbolizes the Player
	 */
	private int color;
	
	private int NumberOfGetOutOfJailCards;
	
	
	private List<Card> cards;
	
	/**
	 * Contains all fields which this player owns.
	 */
	private List<BuyableField> fields;
	
	public Player() {
		this.cards = new ArrayList<Card>(2);
		this.fields = new ArrayList<BuyableField>();
		this.NumberOfGetOutOfJailCards = 0;
	}
	
	public int getNumberOfGetOutOfJailCards() {
		return this.NumberOfGetOutOfJailCards;
	}
	
	public void addGetOutOfJailCard() {
		this.NumberOfGetOutOfJailCards++;
	}
	
	public void takeGetOutOfJailCard() {
		this.NumberOfGetOutOfJailCards--;
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
	public Player(String name, int position, int balance, int id, int color) {
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
	
}
