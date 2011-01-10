package org.ojim.logic.state;

import java.util.Random;

public class Dice {
	
	/**
	 * Sides the Dice is having, e.g. a 12-sided dice can rollvalues between 1 and 12
	 */
	private int sides;
	
	/**
	 * Number of times the Dice was rolled
	 */
	private int timesRolled;
	
	/**
	 * Startseed
	 */
	private int seed;
	
	/**
	 * Is the Dice deterministic?
	 */
	private boolean isDeterministic;
	
	/**
	 * Random for the dice
	 */
	private Random random;

	private int result;
	
	public boolean isDeterministic() {
		return this.isDeterministic;
	}
	
	public String toString() {
		return this.sides + "-sided, " + (!this.isDeterministic ? "not deterministic": ("deterministic, seed=" + this.seed));
	}
	
	public Dice(int sides) {
		this.sides = sides;
		this.random = new Random();
		this.seed = 0;
		this.isDeterministic = false;
		this.timesRolled = 0;
	}
	
	public Dice(int sides, int seed) {
		this.sides = sides;
		this.random = new Random(seed);
		this.seed = seed;
		this.isDeterministic = true;
		this.timesRolled = 0;
	}
	
	public void roll() {
		this.timesRolled++;
		this.result = random.nextInt(sides) + 1;
		
	}
	
	public int getResult() {
		return this.result;
		
	}
}
