package org.ojim.logic.state;

public class BuyableField extends Field {

	private Player owner;
	
	public int getRent() {
		return 42;
	}
	
	/**
	 * Pays the rent from the player to the owner.
	 * @param player the player who got to the field.
	 */
	public void payRent(Player player) {
		if (!this.owner.equals(player)) {
			Player.exchangeMoney(player, this.owner, this.getRent());
		}
	}
	
	public Player getOwner() {
		return this.owner;
	}
}