package org.ojim.logic.accounting;

public class Bank implements IMoneyPartner {

	private int balance;
	private int houses;
	private int hotels;
	
	public Bank() {
		// All available amount of money!
		//TODO: Finish this!
		this.balance = 1000000;
	}
	
	@Override
	public void transferMoney(int amount) {
		this.balance += amount;
	}

	public int getHouses() {
		return this.houses;
	}
	
	public int getHotels() {
		return this.hotels;
	}
	
	public boolean changeHouses(int changeAmount) {
		if(this.houses - changeAmount < 0) {
			return false;
		}
		this.houses += changeAmount;
		return true;
	}
	
	public boolean changeHotels(int changeAmount) {
		if(this.hotels - changeAmount < 0) {
			return false;
		}
		this.hotels += changeAmount;
		return true;
	}
	
	/**
	 * Transfers the money amount from payer to payee. Information: If the
	 * amount is negative the payer will get cash and the payee has to pay
	 * it.
	 * 
	 * @param payer
	 *            The money of this partner will be decreased my the amount.
	 * @param payee
	 *            The money of this partner will be increased my the amount.
	 * @param amount
	 *            The money amount.
	 */
	//TODO: Move to a better position;
	public static void exchangeMoney(IMoneyPartner payer, IMoneyPartner payee, int amount) {
		payer.transferMoney(-amount);
		payee.transferMoney(amount);
	}

}
