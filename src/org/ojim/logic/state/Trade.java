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

/**
 * Saves everything related to a trade.
 * 
 * @author Philip Caroli, Fabian Neundorf
 */
public class Trade {
	
	//TODO: (xZise) finish all setters/getters. I didn't finished it :P

	private final Player acting;
	private final Player partner;
	
	private int cash;
	private int numberOfGetOutOfJailCards; //xZise: Jay :D
	private List<BuyableField> sellEstates;
	private List<BuyableField> buyEstates;

	public Trade(Player acting, Player partner) {
		this.acting = acting;
		this.partner = partner;
		
		this.sellEstates = new ArrayList<BuyableField>();
		this.buyEstates = new ArrayList<BuyableField>();
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
	 * @return the cash
	 */
	public int getCash() {
		return this.cash;
	}

	/**
	 * @return the numberOfGetOutOfJailCards
	 */
	public int getNumberOfGetOutOfJailCards() {
		return this.numberOfGetOutOfJailCards;
	}

	/**
	 * Sets the cash of the trade.
	 * @param amount The cash value of the trade. Set it to a negative value to receive the money. 
	 */
	public void setCash(int amount) {
		this.cash = amount;
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
	public boolean addSellingEstate(BuyableField estate) {
		return this.sellEstates.add(estate);
	}
	
	/**
	 * Adds a estate to the buying list.
	 * @param estate The buying estate.
	 * @return True if the estate wasn't on the list before. Otherwise false.
	 */
	public boolean addBuyingEstate(BuyableField estate) {
		return this.buyEstates.add(estate);
	}
	
	public BuyableField[] getSellingEstates() {
		return this.sellEstates.toArray(new BuyableField[0]);
	}
	
	public BuyableField[] getBuyingEstates() {
		return this.buyEstates.toArray(new BuyableField[0]);
	}
	
	public boolean removeSellingEstate(BuyableField estate) {
		return this.sellEstates.remove(estate);
	}
	
	public boolean removeBuyingEstate(BuyableField estate) {
		return this.buyEstates.remove(estate);
	}
}
