package org.ojim.logic.state;

import java.util.Random;

/**
 * Extends dice to generate a very variable dice.
 * 
 * @author Philip
 */
public class RandomDice extends Dice {

	/**
	 * Startseed
	 */
	private int seed;

	/**
	 * Random for the dice
	 */
	private Random random;

	public String toString() {
		return super.toString()
				+ (this.isDeterministic() ? ", seed=" + this.seed : "");
	}

	public RandomDice(int sides) {
		super(sides, false);
		this.random = new Random();
		this.seed = 0;
	}

	public RandomDice(int sides, int seed) {
		super(sides, true);
		this.random = new Random(seed);
		this.seed = seed;
	}

	@Override
	protected int getRoll() {
		return random.nextInt(sides) + 1;
	}
}
