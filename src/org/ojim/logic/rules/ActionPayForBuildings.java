package org.ojim.logic.rules;

import org.ojim.logic.state.GameState;

public class ActionPayForBuildings implements Action {

	private int costForEachHouse;
	private int costForEachHotel;
	private final GameState state;
	
	public ActionPayForBuildings(GameState state, int costForEachHouse, int costForEachHotel) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.state, this.costForEachHouse, this.costForEachHotel);
	}
	
	public static void execute(GameState state, int costForEachHouse, int costForEachHotel) {
		//TODO: Implement action!
	}

}
