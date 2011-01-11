package org.ojim.logic.state;

/**
 * Another dice example.
 * 
 * @author Fabian Neundorf.
 */
public class EasyDice extends Dice {

	public EasyDice(int sides) {
		super(sides, false);
	}

	@Override
	protected int getRoll() {
		return (int) (Math.random() * this.sides) + 1;
	}

}
