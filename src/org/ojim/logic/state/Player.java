/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

import org.ojim.logic.accounting.IMoneyPartner;
import java.util.ArrayList;
import java.util.List;

public class Player implements IMoneyPartner {

	//TODO: Add NullPlayer behaviour
	public static final Player NullPlayer = new Player();
	
	/**
	 * Name to identify the player
	 */
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
	private int NumberOfGetOutOfJailCards;
	
	
	private List<Card> cards;

	/** Contains all fields which this player owns. */
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

	private boolean isInPrison;

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
		this.isInPrison = false;
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

	public void tooglePrison() {
		this.isInPrison = !this.isInPrison;
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

	/* FIELD STACK */
	
	public void addField(BuyableField field) {
		if (!this.fields.contains(field)) {
			this.fields.add(field);
			if (!field.getOwner().equals(this)) {
				field.buy(this);
			}
		}
	}

	public void removeField(BuyableField field) {
		if (this.fields.remove(field)) {
			if (field.getOwner().equals(this)) {
				field.buy(null);
			}
		}
	}

	public Object getIsInPrison() {
		return this.isInPrison;
	}
	
}
