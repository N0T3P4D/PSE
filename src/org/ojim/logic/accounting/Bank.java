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

package org.ojim.logic.accounting;

/**
 * Simple bank that holds the houses/hotels and the amount of money.
 * @author Fabian Neundorf.
 */
public class Bank implements IMoneyPartner {

	private int balance;
	private int houses;
	private int hotels;

	public Bank() {
		// All available amount of money!
		// TODO: Finish this!
		this.balance = 1000000;

		// TODO Add bankHouses / bankHotels somewhere in the Rules
		this.houses = 40;
		this.hotels = 20;
	}

	@Override
	public void transferMoney(int amount) {
		this.balance += amount;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	public int getHouses() {
		return this.houses;
	}

	public void setHotels(int hotels) {
		this.hotels = hotels;
	}
	
	public int getHotels() {
		return this.hotels;
	}

	public boolean changeHouses(int changeAmount) {
		if (this.houses - changeAmount < 0) {
			return false;
		}
		this.houses += changeAmount;
		return true;
	}

	public boolean changeHotels(int changeAmount) {
		if (this.hotels - changeAmount < 0) {
			return false;
		}
		this.hotels += changeAmount;
		return true;
	}

	/**
	 * Transfers the money amount from payer to payee. Information: If the
	 * amount is negative the payer will get cash and the payee has to pay it.
	 * 
	 * @param payer
	 *            The money of this partner will be decreased my the amount.
	 * @param payee
	 *            The money of this partner will be increased my the amount.
	 * @param amount
	 *            The money amount.
	 */
	public static void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee,
			int amount) {
		payer.transferMoney(-amount);
		payee.transferMoney(amount);
	}

	/**
	 * Transfers the money amount from payer to payee. Information: If the
	 * amount is negative the payer will get cash and the payee has to pay it.
	 * To exchange payer and payee it is possible to set the <code>change</code>
	 * -Parameter to true.
	 * 
	 * @param payer
	 *            The money of this partner will be decreased my the amount.
	 * @param payee
	 *            The money of this partner will be increased my the amount.
	 * @param amount
	 *            The money amount.
	 * @param change
	 *            The payee and payer will be swaped.
	 */
	public static void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee,
			int amount, boolean change) {
		Bank.exchangeMoney(payer, payee, (change ? -1 : +1) * amount);
	}
}
