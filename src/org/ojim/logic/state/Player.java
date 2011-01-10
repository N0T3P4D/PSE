package org.ojim.logic.state;

public class Player {

	private String name;
	private int balance;
	private int id;

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
