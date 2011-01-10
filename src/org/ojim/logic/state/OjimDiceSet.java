package org.ojim.logic.state;

import java.util.LinkedList;
import java.util.Random;

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
