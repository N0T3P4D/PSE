package org.ojim.logic.state;

import java.util.LinkedList;
import java.util.Random;

public class DiceSet {
	
	private boolean isDeterministic;
	private LinkedList<Dice> dices;
	
	public DiceSet(LinkedList<Dice> dices) {
		
		//0 Dices are Deterministic..
		this.isDeterministic = true;
		this.dices = dices;
		for(Dice dice : dices) {
			if(!dice.isDeterministic()) {
				this.isDeterministic = false;
			}
		}
	}
	
	public DiceSet(int amount, int sides, int seed) {
		LinkedList<Dice> tempList = new LinkedList<Dice>();
		if(seed == 0) {
			tempList.add(new Dice(6));
			tempList.add(new Dice(6));
		} else {
			Random rnd = new Random(seed);			
			tempList.add(new Dice(6, rnd.nextInt()));
			tempList.add(new Dice(6, rnd.nextInt()));
		}
	}

	public void roll() {
		for(Dice dice : dices) {
			dice.roll();
		}
	}
	
	public int[] getResult() {
		int[] result = new int[dices.size()];
		for(int i = 0; i < dices.size(); i++) {
			result[i] = dices.get(i).getResult();
		}
		return result;
	}
	
	public int getResultSum() {
		int[] tmp = getResult();
		int sum = 0;
		for(int i = 0; i < tmp.length; i++) {
			sum += tmp[i];
		}
		return sum;
	}
	
	public boolean isDouble() {
		
		//There can't be doubles with less than 2 dices
		if(dices.size() < 2) {
			return false;
		}
		for(int i = 0; i < dices.size() - 1; i++) {
			for(int j = i + 1; j < dices.size(); j++) {
				if(dices.get(i).getResult() == dices.get(j).getResult()) {
					return true;
				}
			}
		}
		return false;
	}
}
