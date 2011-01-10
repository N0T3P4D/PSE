package org.ojim.logic.rules;

import org.ojim.logic.State;

public class ActionPayForBuildings implements Action {

	private int costForEachHouse;
	private int costForEachHotel;
	private final State state;
	
	public ActionPayForBuildings(State state, int costForEachHouse, int costForEachHotel) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.state, this.costForEachHouse, this.costForEachHotel);
	}
	
	public static void execute(State state, int costForEachHouse, int costForEachHotel) {
		//TODO: Implement action!
	}

}
