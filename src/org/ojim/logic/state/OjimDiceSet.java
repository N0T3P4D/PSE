package org.ojim.logic.state;

/**
 * Creates a set of 2 6-sided dices.
 * 
 * @author Philip.
 */
public class OjimDiceSet extends DiceSet {

	private int seed;

	public int getSeed() {
		return this.seed;
	}

	public OjimDiceSet(int seed) {
		super(2, 6, seed);
		this.seed = seed;
	}
}
