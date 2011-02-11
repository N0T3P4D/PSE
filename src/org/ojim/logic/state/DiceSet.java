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

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A set of dices.
 * 
 * @author Philip, Fabian Neundorf 
 */
public class DiceSet implements Serializable {

	private boolean isDeterministic;
	private Dice[] dices;

	public DiceSet(List<? extends Dice> dices) {
		this(dices.toArray(new Dice[0]));
	}

	public DiceSet(int amount, int sides, int seed) {
		this(DiceSet.generateDices(amount, sides, seed));
	}

	public DiceSet(Dice[] dices) {
		// 0 Dices are Deterministic..
		this.isDeterministic = true;
		this.dices = dices;
		for (Dice dice : dices) {
			if (!dice.isDeterministic()) {
				this.isDeterministic = false;
			}
		}
	}

	public static Dice[] generateDices(int amount, int sides, int seed) {
		Dice[] tempList = new Dice[amount];
		if (seed == 0) {
			for (int i = 0; i < tempList.length; i++) {
				tempList[i] = new RandomDice(sides);
			}
		} else {
			Random rnd = new Random(seed);
			for (int i = 0; i < tempList.length; i++) {
				tempList[i] = new RandomDice(sides, rnd.nextInt());
			}
		}
		return tempList;
	}
	
	public boolean isDeterministic() {
		return this.isDeterministic;
	}

	public void roll() {
		for (Dice dice : this.dices) {
			dice.roll();
		}
	}

	public int[] getResult() {
		int[] result = new int[this.dices.length];
		for (int i = 0; i < this.dices.length; i++) {
			result[i] = this.dices[i].getResult();
		}
		return result;
	}

	public int getResultSum() {
		int sum = 0;
		for (int diceValue : this.getResult()) {
			sum += diceValue;
		}
		return sum;
	}

	public boolean isDouble() {

		// There can't be double with less than 2 dices
		if (this.dices.length < 2) {
			return false;
		}
		for (int i = 0; i < this.dices.length - 1; i++) {
			for (int j = i + 1; j < this.dices.length; j++) {
				if (dices[i].getResult() == dices[j].getResult()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + Arrays.toString(this.dices);
	}
}
