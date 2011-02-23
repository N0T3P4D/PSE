/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

	@Override
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
