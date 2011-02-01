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
