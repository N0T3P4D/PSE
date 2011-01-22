/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
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

import java.util.ArrayList;
import java.util.List;

import org.ojim.logic.state.fields.BuyableField;

/**
 * Saves everything related to a trade.
 * 
 * @author Philip Caroli, Fabian Neundorf
 */
public class Trade {
	
	//TODO: (xZise) finish all setters/getters. I didn't finished it :P

	private final Player acting;
	private final Player partner;
	
	private int offeredCash, requiredCash;
	private int offeredNumberOfGetOutOfJailCards, requiredNumberOfGetOutOfJailCards; //xZise: Jay :D
	private List<BuyableField> offeredEstates;
	private List<BuyableField> requiredEstates;

	public Trade(Player acting, Player partner) {
		this.acting = acting;
		this.partner = partner;
		
		this.offeredCash = 0;
		this.requiredCash = 0;
		this.offeredNumberOfGetOutOfJailCards = 0;
		this.requiredNumberOfGetOutOfJailCards = 0;
		this.offeredEstates	= new ArrayList<BuyableField>();
		this.requiredEstates = new ArrayList<BuyableField>();
	}
	
	/**
	 * @return the acting
	 */
	public Player getActing() {
		return this.acting;
	}

	/**
	 * @return the partner
	 */
	public Player getPartner() {
		return this.partner;
	}

	/**
	 * @return the offered cash
	 */
	public int getOfferedCash() {
		return this.offeredCash;
	}
	
	/**
	 * @return the required cash
	 */
	public int getRequiredCash() {
		return this.requiredCash;
	}

	/**
	 * @return the required number of GetOutOfJailCards
	 */
	public int getRequiredNumberOfGetOutOfJailCards() {
		return this.requiredNumberOfGetOutOfJailCards;
	}
	
	/**
	 * @return the offerd number of GetOutOfJailCards
	 */
	public int getOfferedNumberOfGetOutOfJailCards() {
		return this.offeredNumberOfGetOutOfJailCards;
	}

	public void setOfferdCash(int amount) {
		this.offeredCash = amount;
	}
	
	public void setRequiredCash(int amount) {
		this.requiredCash = amount;
	}

	/**
	 * Sets the number of "get out of jail cards" in the trade.
	 * @param amount The number of cards. Set it to a negative value to receive the cards. 
	 */	
	public void setNumberOfGetOutOfJailCards(int count) {
		this.numberOfGetOutOfJailCards = count;
	}
	
	/**
	 * Adds a estate to the selling list.
	 * @param estate The selling estate.
	 * @return True if the estate wasn't on the list before. Otherwise false.
	 */
	public boolean addOfferedEstate(BuyableField estate) {
		return this.offeredEstates.add(estate);
	}
	
	/**
	 * Adds a estate to the buying list.
	 * @param estate The buying estate.
	 * @return True if the estate wasn't on the list before. Otherwise false.
	 */
	public boolean addRequiredEstate(BuyableField estate) {
		return this.requiredEstates.add(estate);
	}
	
	public List<BuyableField> getOfferedEstates() {
		return this.offeredEstates;
	}
	
	public List<BuyableField> getRequiredEstates() {
		return this.requiredEstates;
	}
	
	public boolean removeOfferedEstate(BuyableField estate) {
		return this.offeredEstates.remove(estate);
	}
	
	public boolean removeRequiredEstate(BuyableField estate) {
		return this.requiredEstates.remove(estate);
	}
}
