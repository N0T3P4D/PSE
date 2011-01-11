package org.ojim.logic.state;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A set of dices.
 * 
 * @author Philip, Fabian Neundorf 
 */
public class DiceSet {

	private boolean isDeterministic;
	private List<? extends Dice> dices;

	public DiceSet(List<? extends Dice> dices) {

		// 0 Dices are Deterministic..
		this.isDeterministic = true;
		this.dices = dices;
		for (Dice dice : dices) {
			if (!dice.isDeterministic()) {
				this.isDeterministic = false;
			}
		}
	}

	public DiceSet(int amount, int sides, int seed) {
		this(DiceSet.generateDices(amount, sides, seed));
	}

	public static List<Dice> generateDices(int amount, int sides, int seed) {
		List<Dice> tempList = new LinkedList<Dice>();
		if (seed == 0) {
			tempList.add(new RandomDice(sides));
			tempList.add(new RandomDice(sides));
		} else {
			Random rnd = new Random(seed);
			tempList.add(new RandomDice(sides, rnd.nextInt()));
			tempList.add(new RandomDice(sides, rnd.nextInt()));
		}
		return tempList;
	}

	public void roll() {
		for (Dice dice : this.dices) {
			dice.roll();
		}
	}

	public int[] getResult() {
		int[] result = new int[this.dices.size()];
		for (int i = 0; i < this.dices.size(); i++) {
			result[i] = this.dices.get(i).getResult();
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
		if (dices.size() < 2) {
			return false;
		}
		for (int i = 0; i < dices.size() - 1; i++) {
			for (int j = i + 1; j < dices.size(); j++) {
				if (dices.get(i).getResult() == dices.get(j).getResult()) {
					return true;
				}
			}
		}
		return false;
	}
}
