package org.ojim.logic.state;

/**
 * Default dice implementation. Every dice has to extends this class.
 * 
 * @author Philip, Fabian Neundorf.
 */
public abstract class Dice {

	/**
	 * Sides the Dice is having, e.g. a 12-sided dice can rollvalues between 1
	 * and 12
	 */
	protected final int sides;

	/**
	 * Is the Dice deterministic?
	 */
	private boolean isDeterministic;

	/**
	 * Number of times the Dice was rolled
	 */
	private int timesRolled;

	/**
	 * The rolled result.
	 */
	private int result;

	public boolean isDeterministic() {
		return this.isDeterministic;
	}

	public String toString() {
		return this.sides + "-sided, " + (!this.isDeterministic ? "not" : "")
				+ "deterministic";
	}

	public Dice(int sides, boolean deterministic) {
		this.sides = sides;
		this.timesRolled = 0;
	}

	protected abstract int getRoll();

	public final void roll() {
		this.timesRolled++;
		this.result = this.getRoll();
	}

	public final int getResult() {
		return this.result;
	}
}
